package org.example.tiendalemlah.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.tiendalemlah.api.exception.CategoriaNoEncontradaException;
import org.example.tiendalemlah.api.exception.MarcaNoEncontradaException;
import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.entities.Marca;
import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.repositories.CategoriaRepositorio;
import org.example.tiendalemlah.common.repositories.MarcaRepositorio;
import org.example.tiendalemlah.common.repositories.ProductoRepositorio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/v1/xml")
public class XmlControlador {

    private final ProductoRepositorio productoRepo;
    private final MarcaRepositorio marcaRepo;
    private final CategoriaRepositorio categoriaRepo;

    public XmlControlador(ProductoRepositorio productoRepo,
                          MarcaRepositorio marcaRepo,
                          CategoriaRepositorio categoriaRepo) {
        this.productoRepo = productoRepo;
        this.marcaRepo = marcaRepo;
        this.categoriaRepo = categoriaRepo;
    }

    // ─── GET /api/v1/xml — EXPORTAR con DOM ───────────────────────────────
    @GetMapping
    public void exportar(HttpServletResponse response) throws Exception {
        List<Producto> productos = productoRepo.findAll();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("productos");
        doc.appendChild(root);

        for (Producto p : productos) {
            Element prodEl = doc.createElement("producto");
            root.appendChild(prodEl);

            appendElement(doc, prodEl, "id",          String.valueOf(p.getId()));
            appendElement(doc, prodEl, "codigoEan",   p.getCodigoEan());
            appendElement(doc, prodEl, "nombre",      p.getNombre());
            appendElement(doc, prodEl, "descripcion", p.getDescripcion());
            appendElement(doc, prodEl, "precio",      String.valueOf(p.getPrecio()));
            appendElement(doc, prodEl, "descuento",   String.valueOf(p.getDescuento()));
            appendElement(doc, prodEl, "stock",       String.valueOf(p.getStock()));

            // Marca — id y nombre según enunciado
            if (p.getMarca() != null) {
                Element marcaEl = doc.createElement("marca");
                prodEl.appendChild(marcaEl);
                appendElement(doc, marcaEl, "id",     String.valueOf(p.getMarca().getId()));
                appendElement(doc, marcaEl, "nombre", p.getMarca().getNombre());
            }

            // Categorías — id y nombre según enunciado
            Element catsEl = doc.createElement("categorias");
            prodEl.appendChild(catsEl);
            for (Categoria cat : p.getCategorias()) {
                Element catEl = doc.createElement("categoria");
                catsEl.appendChild(catEl);
                appendElement(doc, catEl, "id",     String.valueOf(cat.getId()));
                appendElement(doc, catEl, "nombre", cat.getNombre());
            }
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm"));
        String filename = "products-export." + fecha + ".xml";

        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        transformer.transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
    }

    private void appendElement(Document doc, Element parent, String tag, String text) {
        Element el = doc.createElement(tag);
        el.setTextContent(text != null ? text : "");
        parent.appendChild(el);
    }

    // ─── POST /api/v1/xml — IMPORTAR con SAX ──────────────────────────────
    @PostMapping
    @Transactional
    public void importar(@RequestParam("productsfile") MultipartFile file) throws Exception {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();

        List<Producto> productosAGuardar = new ArrayList<>();

        // Array de un elemento para capturar excepciones desde dentro del handler
        // (las lambdas/clases anónimas no permiten lanzar checked exceptions ni
        // asignar variables locales externas, pero sí mutar un array)
        RuntimeException[] errorCapturado = {null};

        DefaultHandler handler = new DefaultHandler() {
            private Producto productoActual;
            private Long marcaIdActual;
            private Long categoriaIdActual;
            private StringBuilder texto = new StringBuilder();
            private boolean enMarca     = false;
            private boolean enCategoria = false;

            @Override
            public void startElement(String uri, String localName,
                                     String qName, Attributes attributes) {
                texto.setLength(0);
                switch (qName) {
                    case "producto"  -> {
                        productoActual = new Producto();
                        productoActual.setCategorias(new HashSet<>());
                    }
                    case "marca"     -> { marcaIdActual = null;     enMarca     = true; }
                    case "categoria" -> { categoriaIdActual = null; enCategoria = true; }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                texto.append(ch, start, length);
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (errorCapturado[0] != null) return; // ya hay un error, no seguir procesando

                String valor = texto.toString().trim();

                if (enMarca) {
                    switch (qName) {
                        case "id"    -> marcaIdActual = Long.parseLong(valor);
                        case "marca" -> {
                            // Validar que existe — capturamos el error para lanzarlo fuera del SAX
                            marcaRepo.findById(marcaIdActual).ifPresentOrElse(
                                    m -> { productoActual.setMarca(m); enMarca = false; },
                                    () -> errorCapturado[0] = new MarcaNoEncontradaException(marcaIdActual)
                            );
                        }
                    }
                } else if (enCategoria) {
                    switch (qName) {
                        case "id"        -> categoriaIdActual = Long.parseLong(valor);
                        case "categoria" -> {
                            categoriaRepo.findById(categoriaIdActual).ifPresentOrElse(
                                    c -> { productoActual.getCategorias().add(c); enCategoria = false; },
                                    () -> errorCapturado[0] = new CategoriaNoEncontradaException(categoriaIdActual)
                            );
                        }
                    }
                } else {
                    switch (qName) {
                        case "codigoEan"   -> productoActual.setCodigoEan(valor);
                        case "nombre"      -> productoActual.setNombre(valor);
                        case "descripcion" -> productoActual.setDescripcion(valor);
                        case "precio"      -> productoActual.setPrecio(Double.parseDouble(valor));
                        case "descuento"   -> productoActual.setDescuento(Integer.parseInt(valor));
                        case "stock"       -> productoActual.setStock(Integer.parseInt(valor));
                        case "producto"    -> productosAGuardar.add(productoActual);
                    }
                }
            }
        };

        saxParser.parse(file.getInputStream(), handler);

        // Lanzar aquí — fuera del SAX — para que @Transactional haga rollback de todo
        if (errorCapturado[0] != null) {
            throw errorCapturado[0];
        }

        productoRepo.saveAll(productosAGuardar);
    }
}