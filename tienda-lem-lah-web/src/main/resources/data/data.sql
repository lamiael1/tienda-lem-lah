-- ============================================================
-- MARCAS (situación 1: marcas con varios productos, situación 2: marca sin productos)
-- ============================================================
INSERT INTO marcas (nombre, descripcion, imagen) VALUES
                                                     ('Crown & Collar',  'Marca premium de alimentación animal',     'venisonTruffle.jpg'),
                                                     ('Royal Paw',       'Alta gama para perros y gatos',            'venisonTruffle.jpg'),
                                                     ('Nature Feast',    'Ingredientes naturales y orgánicos',       'venisonTruffle.jpg'),
                                                     ('Prestige Pets',   'Sin productos aún — marca sin productos',  'venisonTruffle.jpg');
-- marca 4 (Prestige Pets) no tendrá productos → SITUACIÓN 2

-- ============================================================
-- CATEGORÍAS (situación 6: categorías sin productos)
-- ============================================================
INSERT INTO categorias (nombre, descripcion, imagen, tipo) VALUES
                                                               ('Organic Chicken',   'CANINO ORGÁNICO',  'Organic.jpg',        'perros'),
                                                               ('Venison & Truffle', 'CANINO GOURMET',   'venisonTruffle.jpg', 'perros'),
                                                               ('Gourmet Feast',     'FELINO GOURMET',   'GorumetFeast.jpg',   'gatos'),
                                                               ('Salmon & Caviar',   'FELINO PREMIUM',   'SalmonCaviar.jpg',   'gatos'),
                                                               ('Raw & Natural',     'CANINO NATURAL',    'Organic.jpg',                 'perros'),
                                                               ('Kitten Premium',    'FELINO JUNIOR',     'GorumetFeast.jpg',                 'gatos');
-- categorías 5 (Raw & Natural) y 6 (Kitten Premium) no tendrán productos → SITUACIÓN 6

-- ============================================================
-- PRODUCTOS (21 productos para cumplir el mínimo de 20)
-- ============================================================
INSERT INTO productos (codigo_ean, nombre, descripcion, imagen, precio, descuento,stock, marca_id) VALUES
-- Crown & Collar (marca 1)
('0000000000001', 'Organic Chicken Deluxe',
 'Alimento orgánico para perros elaborado con pollo de corral criado en libertad. Sin conservantes, sin colorantes artificiales, sin subproductos. Cada receta pasa por más de 60 controles de calidad antes de llegar a su hogar. Formulado con el asesoramiento de veterinarios nutricionistas especializados en alimentación canina de alto rendimiento.',
 'venisonTruffle.jpg', 24.99, 10, 100,1),

('0000000000002', 'Venison & Truffle Supreme',
 'Receta gourmet con ciervo y trufa negra para perros de paladar exigente. La proteína magra del ciervo combinada con la intensidad aromática de la trufa ofrece una experiencia gastronómica sin igual. Elaborado en lotes pequeños para garantizar frescura y calidad artesanal en cada bocado.',
 'venisonTruffle.jpg', 29.99, 0,100, 1),

('0000000000003', 'Royal Pheasant & Foie',
 'Mezcla exclusiva de faisán de granja y foie gras de pato para perros. Una propuesta culinaria pensada para los compañeros más distinguidos. Rico en proteínas nobles y grasas saludables de origen animal. Textura suave y aroma irresistible que estimula el apetito incluso en animales inapetentes.',
 'venisonTruffle.jpg', 34.99, 5, 100,1),

('0000000000004', 'Lobster Bisque Canine',
 'Sopa de langosta deshidratada para perros. Ingrediente estrella: langosta del Atlántico Norte capturada de forma sostenible. Apta para perros con sensibilidades alimentarias. Sin gluten, sin cereales, sin lactosa. Preparación sencilla: añada agua templada y sirva.',
 'venisonTruffle.jpg', 39.99, 0,100, 1),

-- Royal Paw (marca 2)
('0000000000005', 'Salmon & Caviar Elegance',
 'Receta premium para gatos con salmón del Atlántico y caviar de esturión. Fuente excepcional de Omega-3 y proteínas de alta digestibilidad. Formulada para mantener el pelaje brillante, la salud articular y la vitalidad de su felino. Libre de cereales y subproductos cárnicos.',
 'venisonTruffle.jpg', 27.50, 15,100, 2),

('0000000000006', 'Tuna & Black Truffle Feline',
 'Atún de aleta amarilla con trufa negra rallada para gatos. La combinación de proteína marina magra con los aceites esenciales de la trufa estimula el sistema inmunológico felino. Textura cremosa especialmente apreciada por gatos mayores y de razas selectas.',
 'venisonTruffle.jpg', 31.00, 0,100, 2),

('0000000000007', 'Duck Confit Premium',
 'Confitado de pato para gatos con ingredientes de origen francés. Cocinado a baja temperatura para preservar todos los nutrientes naturales. Enriquecido con aceite de salmón y vitamina E. Ideal para gatos con alergias a las proteínas de pollo o ternera.',
 'venisonTruffle.jpg', 26.99, 20, 100,2),

('0000000000008', 'Wagyu Beef & Pearl Barley',
 'Ternera wagyu japonesa con cebada perlada para perros. Un lujo gastronómico accesible para su compañero más fiel. La cebada perlada aporta fibra prebiótica que favorece la salud intestinal. La grasa infiltrada del wagyu proporciona energía sostenida y sabor excepcional.',
 'venisonTruffle.jpg', 44.99, 0, 100,2),

('0000000000009', 'Quail & Rosehip Blend',
 'Codorniz de granja ecológica con extracto de rosa mosqueta para gatos. La rosa mosqueta aporta vitamina C natural y antioxidantes. La codorniz es una proteína de fácil digestión, ideal para gatos con estómagos sensibles o en proceso de recuperación.',
 'venisonTruffle.jpg', 28.50, 10,100, 2),

-- Nature Feast (marca 3)
('0000000000010', 'Wild Boar & Forest Herbs',
 'Jabalí salvaje con hierbas del bosque para perros activos. Proteína de caza de alta calidad, naturalmente rica en hierro y zinc. Las hierbas del bosque —romero, tomillo, orégano silvestre— aportan propiedades antiinflamatorias naturales. Sin aditivos, sin conservantes, sin colorantes.',
 'venisonTruffle.jpg', 22.99, 0, 100,3),

('0000000000011', 'Rabbit & Chamomile Sensitive',
 'Conejo con manzanilla para perros y gatos con sensibilidades digestivas. El conejo es la proteína de menor índice alergénico disponible en el mercado. La manzanilla calma la mucosa gástrica y reduce la inflamación intestinal. Recomendado por veterinarios especialistas en dermatología animal.',
 'venisonTruffle.jpg', 19.99, 5, 100,3),

('0000000000012', 'Herring & Sea Kelp Marine',
 'Arenque del Mar del Norte con alga kelp para gatos. El alga kelp es una fuente natural de yodo, calcio y magnesio. El arenque aporta Omega-3 EPA y DHA esenciales para la salud cerebral y ocular del felino. Especialmente recomendado para gatos de interior con poca actividad.',
 'venisonTruffle.jpg', 21.50, 0, 100,3),

('0000000000013', 'Bison & Sweet Potato Grain-Free',
 'Bisonte americano con batata sin cereales para perros. Una fórmula grain-free diseñada para perros con intolerancias. La batata aporta carbohidratos de liberación lenta y antioxidantes naturales. El bisonte es una proteína de caza magra con alto contenido en vitamina B12.',
 'venisonTruffle.jpg', 32.99, 0, 100, 3),

('0000000000014', 'Ostrich & Mango Exotic',
 'Avestruz con mango deshidratado para perros. Proteína exótica de muy baja alergenicidad. El mango aporta vitamina A, C y fibra soluble. Ideal como rotación proteica para perros con historial de alergias alimentarias. Textura crujiente que favorece la higiene dental.',
 'venisonTruffle.jpg', 36.99, 8, 100,3),

-- Más productos de Crown & Collar (marca 1) para situación 1
('0000000000015', 'Angus Beef & Black Garlic',
 'Ternera Angus escocesa con ajo negro fermentado para perros. El ajo negro es 100 veces más rico en antioxidantes que el ajo blanco y carece de los compuestos tóxicos para los perros. La ternera Angus se caracteriza por su terneza y el alto contenido en proteínas de alta calidad.',
 'venisonTruffle.jpg', 27.99, 12, 100,1),

('0000000000016', 'Caviar & Cream Feline Deluxe',
 'Caviar beluga con crema fresca para gatos. La crema fermentada aporta probióticos naturales que refuerzan la microbiota intestinal. El caviar beluga proporciona proteínas completas y grasas saludables. Una propuesta verdaderamente única en el mercado de la alimentación felina de lujo.',
 'venisonTruffle.jpg', 49.99, 0, 100,1),

('0000000000017', 'Lamb & Mint Highland',
 'Cordero de las tierras altas escocesas con menta fresca para perros. La proteína de cordero es altamente digestible y rica en leucina, el aminoácido más importante para el mantenimiento de la masa muscular. La menta refresca el aliento y facilita la digestión. Sin colorantes ni conservantes artificiales.',
 'venisonTruffle.jpg', 23.99, 0, 100,1),

-- Productos Royal Paw adicionales
('0000000000018', 'Prawn & Dill Oceanic',
 'Gambas del Pacífico con eneldo para gatos. Las gambas aportan taurina natural, esencial para la salud cardiovascular y ocular felina. El eneldo proporciona propiedades digestivas y un aroma que los gatos encuentran irresistible. Sin espinas, sin caparazón, sin sal añadida.',
 'venisonTruffle.jpg', 33.50, 0,100, 2),

('0000000000019', 'Elk & Blueberry Nordic',
 'Alce escandinavo con arándanos silvestres para perros. Inspirado en la dieta tradicional de los perros de trineo del norte de Europa. Los arándanos silvestres son ricos en antocianinas, potentes antioxidantes que protegen la visión y el sistema inmunológico. Proteína de caza de máxima calidad.',
 'venisonTruffle.jpg', 38.99, 5, 100,2),

-- Producto sin imagen y sin categoría → SITUACIÓN 3
('0000000000020', 'Mystery Blend Prototype',
 'Formulación experimental en fase de pruebas. Mezcla de proteínas de origen sostenible sin identificar para test de palatabilidad. No disponible para venta al público. Solo para uso interno de control de calidad del laboratorio Crown & Collar. Sin imagen disponible.',
 NULL, 9.99, 0, 100,1),

-- Producto extra para completar
('0000000000021', 'Turbot & Saffron Mediterranean',
 'Rodaballo mediterráneo con azafrán de La Mancha para gatos. El rodaballo es uno de los pescados planos de mayor valor gastronómico. El azafrán, la especia más cara del mundo, aporta crocina y picrocrocina, compuestos con propiedades antioxidantes y antiinflamatorias documentadas científicamente.',
 'venisonTruffle.jpg', 42.00, 0,100, 2);

-- ============================================================
-- PRODUCTO_CATEGORIA — cubre las 8 situaciones del enunciado
-- ============================================================

-- SITUACIÓN 4: Productos en solo una categoría
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (1, 1);  -- Organic Chicken Deluxe → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (5, 4);  -- Salmon & Caviar → Salmon & Caviar
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (6, 3);  -- Tuna & Black Truffle → Gourmet Feast
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (12, 4); -- Herring & Sea Kelp → Salmon & Caviar
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (18, 3); -- Prawn & Dill → Gourmet Feast
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (21, 4); -- Turbot & Saffron → Salmon & Caviar

-- SITUACIÓN 5: Productos en varias categorías
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (2, 1);  -- Venison & Truffle → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (2, 2);  -- Venison & Truffle → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (7, 3);  -- Duck Confit → Gourmet Feast
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (7, 4);  -- Duck Confit → Salmon & Caviar
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (8, 1);  -- Wagyu Beef → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (8, 2);  -- Wagyu Beef → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (11, 1); -- Rabbit & Chamomile → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (11, 2); -- Rabbit & Chamomile → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (13, 1); -- Bison & Sweet Potato → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (13, 2); -- Bison & Sweet Potato → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (15, 1); -- Angus Beef → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (15, 2); -- Angus Beef → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (19, 1); -- Elk & Blueberry → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (19, 2); -- Elk & Blueberry → Venison & Truffle

-- Más asignaciones para categorías con varios productos → SITUACIÓN 8
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (3, 2);  -- Royal Pheasant → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (4, 1);  -- Lobster Bisque → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (9, 3);  -- Quail & Rosehip → Gourmet Feast
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (10, 2); -- Wild Boar → Venison & Truffle
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (14, 1); -- Ostrich & Mango → Organic Chicken
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (16, 3); -- Caviar & Cream → Gourmet Feast
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (17, 1); -- Lamb & Mint → Organic Chicken

-- ============================================================
-- ROLES
-- ============================================================
INSERT INTO roles (id, descripcion) VALUES ('USER',  'Usuario normal');
INSERT INTO roles (id, descripcion) VALUES ('ADMIN', 'Administrador');

-- ============================================================
-- USUARIO ADMIN
-- Hash bcrypt cost=12 de la contraseña "Password"
-- Genera el tuyo en https://bcrypt-generator.com (Rounds: 12)
-- ============================================================
INSERT INTO usuarios (nombre, apellidos, email, password, fecha_registro) VALUES
    ('Admin', 'Tienda', 'admin@tienda.com',
     '$2a$12$DeiOeW/TOcGxuAUGePNMzOPYguTwvjVaoo3R9JB661a86sOOLTXcS',
     CURRENT_TIMESTAMP);

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 'USER');
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 'ADMIN');

-- ============================================================
-- USUARIO NORMAL
-- ============================================================
INSERT INTO usuarios (nombre, apellidos, email, password, fecha_registro) VALUES
    ('Usuario', 'Normal', 'user@tienda.com',
     '$2a$12$DeiOeW/TOcGxuAUGePNMzOPYguTwvjVaoo3R9JB661a86sOOLTXcS',
     CURRENT_TIMESTAMP);

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 'USER');