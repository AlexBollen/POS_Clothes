USE ClothesBD;

DROP PROCEDURE IF EXISTS spVenta;

DELIMITER $$

CREATE PROCEDURE spVenta(
	IN noFactura VARCHAR(20),
	IN totalFactura DECIMAL(8,2),
	IN fechaFactura DATE,
	IN idCliente INT,
	IN idTipoPago INT,
	IN idCaja INT,
	IN idSerie VARCHAR(25),
	IN detalleVenta JSON
)
BEGIN
	DECLARE idVenta INT;
	DECLARE i INT DEFAULT 0;
	DECLARE n INT DEFAULT 0;
	DECLARE idStock INT;
	DECLARE cantidad INT;
	
	-- CREAR FACTURA
	INSERT INTO factura (NoFactura, TotalFactura, FechaFactura, IdCliente, IdTipoPago, IdCaja, IdSerie)
	VALUES (noFactura, totalFactura, fechaFactura, idCliente, idTipoPago, idCaja, idSerie);
	
	SET idVenta = LAST_INSERT_ID();
	
	SET n = JSON_LENGTH(detalleventa);
	
	-- CREAR REGISTROS DE DETALLES DE VENTA Y DESCONTAR STOCKS
	WHILE i < n DO
		SET idStock = JSON_UNQUOTE(JSON_EXTRACT(detalleVenta, CONCAT('$[',i,'].idStock')));
		SET cantidad = JSON_UNQUOTE(JSON_EXTRACT(detalleventa	, CONCAT('$[',i,'].cantidad')));
		
		INSERT INTO detalleventa (IdStock, IdFactura, Cantidad)
		VALUES (idStock, idVenta, cantidad);
		
		UPDATE Stock SET CantidadDisponible = Stock.CantidadDisponible - cantidad
		WHERE Stock.IdStock = idStock;
		
		SET i = i + 1;
	END WHILE;
	
END$$
DELIMITER ;
