-- Categorías
INSERT INTO categorias (nombre, descripcion, imagen, tipo) VALUES
                                                               ('Organic Chicken', 'CANINO · ORGÁNICO', 'Organic.jpg', 'perros'),
                                                               ('Venison & Truffle', 'CANINO · GOURMET', 'venisonTruffle.jpg', 'perros'),
                                                               ('Gourmet Feast', 'FELINO · GOURMET', 'GorumetFeast.jpg', 'gatos'),
                                                               ('Salmon & Caviar', 'FELINO · PREMIUM', 'SalmonCaviar.jpg', 'gatos');

-- Marcas
INSERT INTO marcas (nombre, descripcion, imagen) VALUES
                                                     ('Crown & Collar', 'Marca premium de alimentación animal', 'crowncollar.jpg'),
                                                     ('Royal Paw', 'Alta gama para perros y gatos', 'royalpaw.jpg'),
                                                     ('Nature Feast', 'Ingredientes naturales y orgánicos', 'naturefeast.jpg');

-- Productos
INSERT INTO productos (codigo_ean, nombre, descripcion, imagen, precio, descuento, marca_id) VALUES
                                                                                                 ('0000000000001', 'Organic Chicken Deluxe', 'Alimento orgánico para perros', 'organicDeluxe.jpg', 24.99, 10, 1),
                                                                                                 ('0000000000002', 'Venison & Truffle Supreme', 'Receta gourmet con trufa', 'venisonSupreme.jpg', 29.99, 0, 1),
                                                                                                 ('0000000000003', 'Salmon & Caviar Elegance', 'Receta premium para gatos', 'salmonElegance.jpg', 27.50, 15, 2);
