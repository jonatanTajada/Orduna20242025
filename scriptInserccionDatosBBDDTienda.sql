-- Limpieza previa de tablas (opcional)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE pedidos;
TRUNCATE TABLE productos;
TRUNCATE TABLE usuarios;
TRUNCATE TABLE categorias;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertar datos en la tabla de categorías
INSERT INTO categorias (id, nombre) VALUES
(1, 'Electrónica'),
(2, 'Hogar'),
(3, 'Deportes'),
(4, 'Ropa'),
(5, 'Alimentos');

-- 2. Insertar datos en la tabla de productos
INSERT INTO productos (id, nombre, precio, stock, categoria_id) VALUES
(1, 'Smartphone', 599.99, 50, 1),
(2, 'Laptop', 999.99, 30, 1),
(3, 'Silla', 49.99, 100, 2),
(4, 'Mesa', 89.99, 40, 2),
(5, 'Balón de fútbol', 19.99, 200, 3),
(6, 'Camiseta deportiva', 29.99, 150, 3),
(7, 'Pantalón vaquero', 39.99, 80, 4),
(8, 'Zapatillas deportivas', 59.99, 70, 4),
(9, 'Manzanas', 1.99, 500, 5),
(10, 'Leche', 0.99, 300, 5);


-- 3. Insertar datos en la tabla de usuarios
INSERT INTO usuarios (id, username, password, rol) VALUES
(1, 'admin', 'admin123', 'Administrador'),
(2, 'empleado1', 'empleado123', 'Empleado'),
(3, 'empleado2', 'empleado123', 'Empleado');

-- 4. Insertar datos en la tabla de pedidos
INSERT INTO pedidos (id, fecha, total, id_usuario) VALUES
(1, '2024-11-01', 120.50, 2),
(2, '2024-11-02', 89.99, 3),
(3, '2024-11-03', 199.99, 2);

-- Verificar las relaciones (opcional)
SELECT * FROM categorias;
SELECT * FROM productos;
SELECT * FROM usuarios;
SELECT * FROM pedidos;


