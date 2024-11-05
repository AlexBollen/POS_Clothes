
CREATE DATABASE IF NOT EXISTS ClothesBD;

USE ClothesBD;

-- Desactivar verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar las tablas (y claves foráneas implícitamente)
DROP TABLE IF EXISTS Factura;
DROP TABLE IF EXISTS DetalleVenta;
DROP TABLE IF EXISTS Compra;
DROP TABLE IF EXISTS DetalleCompra;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Proveedor;
DROP TABLE IF EXISTS TipoProducto;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS Stock;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Rol;
DROP TABLE IF EXISTS Caja;
DROP TABLE IF EXISTS EstadoCompra;
DROP TABLE IF EXISTS TipoPago;
DROP TABLE IF EXISTS SerieFactura;

-- Reactivar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;


-- Creación_Y eliminacion de tablas
-- Tabla Cliente
CREATE TABLE `Cliente`
(
  `IdCliente` Int NOT NULL AUTO_INCREMENT,
  `Nit` Varchar(13),
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdPersona` Int,
  PRIMARY KEY (`IdCliente`),
  UNIQUE KEY `IdCliente` (`IdCliente`)
);

CREATE INDEX `IX_Relationship19` ON `Cliente` (`IdPersona`);

-- Tabla Producto

CREATE TABLE `Producto`
(
  `IdProducto` Int NOT NULL AUTO_INCREMENT,
  `NombreProducto` Varchar(125) NOT NULL,
  `PrecioCosto` Decimal(5,2) NOT NULL,
  `PrecioVenta` Decimal(5,2) NOT NULL,
  `DescuentoPorcentual` Decimal(5,2),
  `IdTipoProducto` Int,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdProducto`),
  UNIQUE KEY `IdProducto` (`IdProducto`)
);

CREATE INDEX `IX_Relationship2` ON `Producto` (`IdTipoProducto`);

-- Tabla TipoProducto

CREATE TABLE `TipoProducto`
(
  `IdTipoProducto` Int NOT NULL AUTO_INCREMENT,
  `NombreTipoProducto` Varchar(100) NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdTipoProducto`),
  UNIQUE KEY `IdTipoProducto` (`IdTipoProducto`)
);

-- Tabla Stock

CREATE TABLE `Stock`
(
  `IdStock` Int NOT NULL AUTO_INCREMENT,
  `CantidadInicial` Int NOT NULL,
  `CantidadDisponible` Int NOT NULL,
  `FechaIngreso` Date NULL,
  `DescripcionStock` Varchar(250),
  `UbicacionBodega` Varchar(3) NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdProducto` Int,
  PRIMARY KEY (`IdStock`),
  UNIQUE KEY `IdStock` (`IdStock`)
);

CREATE INDEX `IX_Relationship3` ON `Stock` (`IdProducto`);

-- Tabla Compra

CREATE TABLE `Compra`
(
  `IdCompra` Int NOT NULL AUTO_INCREMENT,
  `DescripcionCompra` Varchar(250),
  `CantidadPedida` Int,
  `CantidadRecibida` Int NOT NULL,
  `TotalCompra` Decimal(10,2) NOT NULL,
  `FechaCompra` Date NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdProveedor` Int,
  `IdTipoPago` Int,
  `IdEstadoCompra` Int,
  PRIMARY KEY (`IdCompra`),
  UNIQUE KEY `IdCompra` (`IdCompra`)
);

CREATE INDEX `IX_Relationship6` ON `Compra` (`IdProveedor`);
CREATE INDEX `IX_Relationship7` ON `Compra` (`IdTipoPago`);
CREATE INDEX `IX_Relationship21` ON `Compra` (`IdEstadoCompra`);

-- Tabla DetalleCompra

CREATE TABLE `DetalleCompra`
(
  `IdDetalleCompra` Int NOT NULL AUTO_INCREMENT,
  `IdCompra` Int NOT NULL,
  `IdProducto` Int NOT NULL,
  `CantidadProducto` Int NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdDetalleCompra`),
  UNIQUE KEY `IdDetalleCompra` (`IdDetalleCompra`)
);

CREATE INDEX `IX_Relationship4` ON `DetalleCompra` (`IdCompra`);
CREATE INDEX `IX_Relationship5` ON `DetalleCompra` (`IdProducto`);

-- Tabla Proveedor

CREATE TABLE `Proveedor`
(
  `IdProveedor` Int NOT NULL AUTO_INCREMENT,
  `NombreComercial` Varchar(100) NOT NULL,
  `Telefono2` Char(8),
  `Correo` Varchar(100),
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdPersona` Int,
  PRIMARY KEY (`IdProveedor`),
  UNIQUE KEY `IdProveedor` (`IdProveedor`)
);

CREATE INDEX `IX_Relationship20` ON `Proveedor` (`IdPersona`);

-- Tabla TipoPago

CREATE TABLE `TipoPago`
(
  `IdTipoPago` Int NOT NULL AUTO_INCREMENT,
  `NombreTipoPago` Varchar(75) NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdTipoPago`),
  UNIQUE KEY `IdTipoPago` (`IdTipoPago`)
);

-- Tabla Factura

CREATE TABLE `Factura`
(
  `IdFactura` Int NOT NULL AUTO_INCREMENT,
  `NoFactura` Varchar(20) NOT NULL,
  `TotalFactura` DECIMAL(8,2) NOT NULL,
  `FechaFactura` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdCliente` Int,
  `IdTipoPago` Int,
  `IdCaja` Int,
  `IdSerie` Varchar(25) NOT NULL,
  PRIMARY KEY (`IdFactura`, `IdSerie`),
  UNIQUE KEY `IdFactura` (`IdFactura`)
);

CREATE INDEX `IX_Relationship8` ON `Factura` (`IdCliente`);
CREATE INDEX `IX_Relationship11` ON `Factura` (`IdTipoPago`);
CREATE INDEX `IX_Relationship18` ON `Factura` (`IdCaja`);

-- Tabla DetalleVenta

CREATE TABLE `DetalleVenta`
(
  `IdDetalleVenta` Int NOT NULL AUTO_INCREMENT,
  `IdStock` Int NOT NULL,
  `IdFactura` Int NOT NULL,
  `Cantidad` Int NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdDetalleVenta`),
  UNIQUE KEY `IdDetalleVenta` (`IdDetalleVenta`)
);

CREATE INDEX `IX_Relationship9` ON `DetalleVenta` (`IdStock`);
CREATE INDEX `IX_Relationship10` ON `DetalleVenta` (`IdFactura`);

-- Tabla Rol

CREATE TABLE `Rol`
(
  `IdRol` Int NOT NULL AUTO_INCREMENT,
  `NombreRol` Varchar(100) NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdRol`),
  UNIQUE KEY `IdRol` (`IdRol`)
);

-- Tabla Usuario

CREATE TABLE `Usuario`
(
  `IdUsuario` Int NOT NULL AUTO_INCREMENT,
  `NombreUsuario` Varchar(75) NOT NULL,
  `Contrasenia` TEXT NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdPersona` Int,
  `IdRol` INT,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `IdUsuario` (`IdUsuario`)
);

CREATE INDEX `IX_Relationship14` ON `Usuario` (`IdPersona`);
CREATE INDEX `IX_Relationship15` ON `Usuario` (`IdRol`);

-- Tabla Persona

CREATE TABLE `Persona`
(
  `IdPersona` Int NOT NULL AUTO_INCREMENT,
  `NombrePersona` Varchar(100) NOT NULL,
  `Direccion` Varchar(150),
  `Telefono` Char(8),
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdPersona`),
  UNIQUE KEY `IdPersona` (`IdPersona`)
);

-- Tabla Caja

CREATE TABLE `Caja`
(
  `IdCaja` Int NOT NULL AUTO_INCREMENT,
  `MontoInicial` DECIMAL(10,2) NOT NULL,
  `Monto` DECIMAL(10,2) NOT NULL,
  `EstadoCaja` BIT NOT NULL,
  `FechaApertura` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Estado` BIT NOT NULL DEFAULT 1,
  `IdUsuario` Int,
  PRIMARY KEY (`IdCaja`),
  UNIQUE KEY `IdCaja` (`IdCaja`)
);

CREATE INDEX `IX_Relationship16` ON `Caja` (`IdUsuario`);

-- Tabla EstadoCompra

CREATE TABLE `EstadoCompra`
(
  `IdEstadoCompra` Int NOT NULL AUTO_INCREMENT,
  `NombreEstadoCompra` Varchar(100) NOT NULL,
  `Estado` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdEstadoCompra`),
  UNIQUE KEY `IdEstadoCompra` (`IdEstadoCompra`)
);

-- Tabla SerieFactura

CREATE TABLE `SerieFactura`
(
  `IdSerie` Varchar(25) NOT NULL,
  `FechaInicio` Datetime NOT NULL,
  `Estado` BIT NOT NULL,
  PRIMARY KEY (`IdSerie`),
  UNIQUE KEY `IdSerie` (`IdSerie`)
);

-- Crear Llaves Foraneas (relaciones)  -------------------------------------------------

ALTER TABLE `Producto` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`IdTipoProducto`) REFERENCES `TipoProducto` (`IdTipoProducto`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Stock` ADD CONSTRAINT `Relationship3` FOREIGN KEY (`IdProducto`) REFERENCES `Producto` (`IdProducto`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `DetalleCompra` ADD CONSTRAINT `Relationship4` FOREIGN KEY (`IdCompra`) REFERENCES `Compra` (`IdCompra`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `DetalleCompra` ADD CONSTRAINT `Relationship5` FOREIGN KEY (`IdProducto`) REFERENCES `Producto` (`IdProducto`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Compra` ADD CONSTRAINT `Relationship6` FOREIGN KEY (`IdProveedor`) REFERENCES `Proveedor` (`IdProveedor`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Compra` ADD CONSTRAINT `Relationship7` FOREIGN KEY (`IdTipoPago`) REFERENCES `TipoPago` (`IdTipoPago`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Factura` ADD CONSTRAINT `Relationship8` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `DetalleVenta` ADD CONSTRAINT `Relationship9` FOREIGN KEY (`IdStock`) REFERENCES `Stock` (`IdStock`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `DetalleVenta` ADD CONSTRAINT `Relationship10` FOREIGN KEY (`IdFactura`) REFERENCES `Factura` (`IdFactura`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Factura` ADD CONSTRAINT `Relationship11` FOREIGN KEY (`IdTipoPago`) REFERENCES `TipoPago` (`IdTipoPago`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Factura` ADD CONSTRAINT `Relationship18` FOREIGN KEY (`IdCaja`) REFERENCES `Caja` (`IdCaja`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Proveedor` ADD CONSTRAINT `Relationship20` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Cliente` ADD CONSTRAINT `Relationship19` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Compra` ADD CONSTRAINT `Relationship21` FOREIGN KEY (`IdEstadoCompra`) REFERENCES `EstadoCompra` (`IdEstadoCompra`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship14` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship15` FOREIGN KEY (`IdRol`) REFERENCES `Rol` (`IdRol`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `Caja` ADD CONSTRAINT `Relationship16` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuario` (`IdUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Insercion de Datos

INSERT INTO `Persona` (`NombrePersona`, `Direccion`, `Telefono`, `Estado`)
VALUES ('Juan Perez', '10 Calle 6-89 Zona 10, Quetzaltenango', '12345678', 1),
       ('Maria Gomez', '2 Avenida 8-74 Zona 2, Quetzaltenango', '87654321', 1);
       
INSERT INTO `Proveedor` (`NombreComercial`, `Telefono2`, `Correo`, `Estado`, `IdPersona`)
VALUES ('Proveedor ABC', '87654321', 'abc@proveedor.com', 1, 1),
       ('Proveedor XYZ', '12345678', 'xyz@proveedor.com', 1, 2);
       
INSERT INTO `Cliente` (`Nit`, `Estado`, `IdPersona`)
VALUES ('1234567890123', 1, 1), 
       ('9876543210987', 1, 2);
       
INSERT INTO `TipoProducto` (`NombreTipoProducto`, `Estado`)
VALUES ('Pantalon', 1), 
       ('Camisas', 1);
       
INSERT INTO `Producto` (`NombreProducto`, `PrecioCosto`, `PrecioVenta`, `DescuentoPorcentual`, `IdTipoProducto`, `Estado`)
VALUES ('Pantalón de Tela Versace', 500.00, 600.00, 0.05, 1, 1),
       ('Camisa Polo Lacoste', 10.00, 15.00, 0.10, 2, 1);
       
INSERT INTO `Stock` (`CantidadInicial`, `CantidadDisponible`, `FechaIngreso`, `DescripcionStock`, `UbicacionBodega`, `Estado`, `IdProducto`)
VALUES (100, 95, '2024-09-01', 'Pantalón de Tela Versace stock inicial', 'A01', 1, 1),
       (50, 45, '2024-09-01', 'Camisa Polo Lacoste stock inicial', 'B02', 1, 2);
       
INSERT INTO `TipoPago` (`NombreTipoPago`, `Estado`)
VALUES ('Efectivo', 1);
       
INSERT INTO `EstadoCompra` (`NombreEstadoCompra`,`Estado`)
VALUES ('Pendiente', 1),
       ('Completada', 1),
       ('En proceso',1),
	   ('Cancelada', 1);
       
INSERT INTO `Compra` (`DescripcionCompra`, `CantidadPedida`, `CantidadRecibida`, `TotalCompra`, `FechaCompra`, `Estado`, `IdProveedor`, `IdTipoPago`, `IdEstadoCompra`)
VALUES ('Compra de Pantalones de tela Versace', 50, 50, 250.00, '2024-09-01', 1, 1, 1, 1),
       ('Compra de Camisas Polo Lacoste', 30, 30, 300.00, '2024-09-01', 1, 2, 1, 2);
       
INSERT INTO `DetalleCompra` (`IdCompra`, `IdProducto`, `CantidadProducto`, `Estado`)
VALUES (1, 1, 50, 1),
       (2, 2, 30, 1);
       
INSERT INTO `Rol` (`NombreRol`, `Estado`)
VALUES ('Administrador', 1),
       ('Vendedor', 1);
       
INSERT INTO `Usuario` (`NombreUsuario`, `Contrasenia`, `Estado`, `IdPersona`, `IdRol`)
VALUES ('admin', '$2y$10$Z43qTeGzYeQE8kRCPCfoUOwHk.SW7bWgG49//bCpJd4Czq0kti3.i', 1, 1, 1),
       ('vendedor', '$2y$10$nddaqfK5DmXEoCYmHv3VJe/dWTyPCqaS3j6Dh651Yl2utWzwkCtay', 1, 2, 2);

INSERT INTO `Caja` (`MontoInicial`, `Monto`, `EstadoCaja`, `FechaApertura`, `Estado`, `IdUsuario`)
VALUES (500.00, 1200.00, 1, '2024-09-01 09:00:00', 1, 1),
       (500.00, 600.00, 1, '2024-09-02 09:00:00', 1, 2);
       
INSERT INTO `SerieFactura` (`IdSerie`, `FechaInicio`, `Estado`)
VALUES ('SERIEA', '2024-09-01 00:00:00', 1),
       ('SERIEB', '2024-09-01 00:00:00', 1);
       
INSERT INTO `Factura` (`NoFactura`, `TotalFactura`, `FechaFactura`, `Estado`, `IdCliente`, `IdTipoPago`, `IdCaja`, `IdSerie`)
VALUES ('FAC-001', 150.00, '2024-09-02 10:00:00', 1, 1, 1, 1, 'SERIEA'),
       ('FAC-002', 60.00, '2024-09-02 11:00:00', 1, 2, 1, 2, 'SERIEB');
       
INSERT INTO `DetalleVenta` (`IdStock`, `IdFactura`, `Cantidad`, `Estado`)
VALUES (1, 1, 1, 1),
       (2, 2, 3, 1);
          
-- Fin de Script

