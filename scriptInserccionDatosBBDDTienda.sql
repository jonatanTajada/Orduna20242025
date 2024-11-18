-- Limpieza previa de tablas (opcional)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE pedidos;
TRUNCATE TABLE productos;
TRUNCATE TABLE usuarios;
TRUNCATE TABLE categorias;
TRUNCATE TABLE proveedores;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertar datos en la tabla de categorías
INSERT INTO categorias (id, nombre) VALUES
(1, 'Electrónica'),
(2, 'Hogar'),
(3, 'Deportes'),
(4, 'Ropa'),
(5, 'Alimentos');

-- 2. Insertar datos en la tabla de usuarios (sin la columna "rol")
INSERT INTO usuarios (id, username, password) VALUES
(1, 'admin', 'admin123'),
(2, 'empleado1', 'empleado123'),
(3, 'empleado2', 'empleado123');

-- 3. Insertar datos en la tabla de proveedores
INSERT INTO proveedores (id, nombre, contacto, direccion) VALUES
(1, 'Proveedor A', '111111111', 'Calle Primera 123'),
(2, 'Proveedor B', '222222222', 'Calle Segunda 456');

-- 4. Insertar datos en la tabla de productos con descripciones
INSERT INTO productos (id, nombre, descripcion, precio, stock, categoria_id, activo) VALUES
(1, 'Smartphone', 'Un teléfono inteligente de última generación', 599.99, 50, 1, 1),
(2, 'Laptop', 'Computadora portátil con alta capacidad', 999.99, 30, 1, 1),
(3, 'Silla', 'Silla ergonómica para oficina', 49.99, 100, 2, 1),
(4, 'Mesa', 'Mesa de comedor para 6 personas', 89.99, 40, 2, 1),
(5, 'Balón de fútbol', 'Balón oficial de alta calidad', 19.99, 200, 3, 1),
(6, 'Camiseta deportiva', 'Camiseta para practicar deportes', 29.99, 150, 3, 1),
(7, 'Pantalón vaquero', 'Pantalón de mezclilla clásico', 39.99, 80, 4, 1),
(8, 'Zapatillas deportivas', 'Zapatillas cómodas para correr', 59.99, 70, 4, 1),
(9, 'Manzanas', 'Manzanas frescas por kilogramo', 1.99, 500, 5, 1),
(10, 'Leche', 'Leche de vaca pasteurizada', 0.99, 300, 5, 1);


-- 5. Insertar datos en la tabla de pedidos
INSERT INTO pedidos (id, proveedor_id, id_usuario, fecha, total, estado) VALUES
(1, 1, 2, '2024-11-01', 120.50, 'pendiente'),
(2, 2, 3, '2024-11-02', 89.99, 'pendiente'),
(3, 1, 2, '2024-11-03', 199.99, 'entregado');

-- Verificar las relaciones (opcional)
SELECT * FROM categorias;
SELECT * FROM productos;
SELECT * FROM usuarios;
SELECT * FROM pedidos;
SELECT * FROM proveedores;



-- Trigger para caluclar el subtotal
DELIMITER $$

CREATE TRIGGER before_insert_linea_ventas
BEFORE INSERT ON linea_ventas
FOR EACH ROW
BEGIN
    -- Calcular el subtotal: precio * cantidad
    DECLARE precio_producto DOUBLE;
    SELECT precio INTO precio_producto FROM productos WHERE id = NEW.producto_id;

    SET NEW.subtotal = precio_producto * NEW.cantidad;
END$$

DELIMITER ;

