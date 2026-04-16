package org.example.tiendalemlah.web.config;

import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInicializador {

    @Bean
    public CommandLineRunner initCategories(CategoriaServicio categoryService) {
        return args -> {
            Categoria perros = new Categoria();
            perros.setNombre("El Patrimonio Canino");
            perros.setDescripcion("Selecciones gourmet para perros de linaje distinguido.");
            perros.setImagen("perros-patrimonio.png");
            categoryService.save(perros);

            Categoria gatos = new Categoria();
            gatos.setNombre("La Herencia Felina");
            gatos.setDescripcion("Creaciones delicadas para gatos de gusto refinado.");
            gatos.setImagen("gatos-herencia.png");
            categoryService.save(gatos);

            Categoria recomendaciones = new Categoria();
            recomendaciones.setNombre("Recomendaciones del Mes");
            recomendaciones.setDescripcion("Venison & Truffle, Salmon & Caviar, Organic Chicken, Gourmet Feast.");
            recomendaciones.setImagen("recomendaciones-mes.png");
            categoryService.save(recomendaciones);

            Categoria promesa = new Categoria();
            promesa.setNombre("La Promesa");
            promesa.setDescripcion("Ingredientes de grado humano, logística discreta y garantía de satisfacción real.");
            promesa.setImagen(null); // sin imagen, para probar el fallback
            categoryService.save(promesa);
        };
    }
}
