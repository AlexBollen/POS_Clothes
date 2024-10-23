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
	IN detalleVenta JSON,
	OUT mensaje VARCHAR(100),
	OUT estado INT
)
VentaSP:BEGIN
	DECLARE idVenta INT;
	DECLARE i INT DEFAULT 0;
	DECLARE n INT DEFAULT 0;
	DECLARE idStock INT;
	DECLARE cantidad INT;
	
	-- MANEJO DE POSIBLES ERRORES
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			mensaje = MESSAGE_TEXT;
		ROLLBACK;
		SET estado = 0;
	END;
	
	START TRANSACTION;
		SET n = JSON_LENGTH(detalleVenta);
		
		-- VERIFICACIÓN DE PRODUCTOS EN LA VENTA
		IF detalleVenta IS NULL OR n = 0 THEN
			SET mensaje = 'No se asignaron productos a la venta';
			SET estado = 0;
			ROLLBACK;
			LEAVE VentaSP;
		END IF;
	
		-- CREAR FACTURA
		INSERT INTO Factura (NoFactura, TotalFactura, FechaFactura, IdCliente, IdTipoPago, IdCaja, IdSerie)
		VALUES (noFactura, totalFactura, fechaFactura, idCliente, idTipoPago, idCaja, idSerie);
		
		SET idVenta = LAST_INSERT_ID();
		
		-- CREAR REGISTROS DE DETALLES DE VENTA Y DESCONTAR STOCKS
		WHILE i < n DO
			SET idStock = JSON_UNQUOTE(JSON_EXTRACT(detalleVenta, CONCAT('$[',i,'].idStock')));
			SET cantidad = JSON_UNQUOTE(JSON_EXTRACT(detalleVenta	, CONCAT('$[',i,'].cantidad')));
			
			-- VERIFICAR EXISTENCIAS SUFICIENTES PARA EL DESCUENTO
			IF (SELECT CantidadDisponible FROM Stock WHERE IdStock = idStock LIMIT 1) < cantidad THEN
				SET mensaje = CONCAT('Existencias insuficientes en stock con id: ', idStock);
				SET estado = 0;
				ROLLBACK; 
				LEAVE VentaSP;  
			END IF;
			
			INSERT INTO DetalleVenta (IdStock, IdFactura, Cantidad)
			VALUES (idStock, idVenta, cantidad);
			
			UPDATE Stock SET CantidadDisponible = Stock.CantidadDisponible - cantidad
			WHERE Stock.IdStock = idStock;
			
			SET i = i + 1;
		END WHILE;
		SET mensaje = 'Venta realizada correctamente';
		SET estado = 1;
		COMMIT ;

END$$
DELIMITER ;
