USE ClothesBD;

DROP PROCEDURE IF EXISTS spVenta;
DROP PROCEDURE IF EXISTS spCompra;

DELIMITER $$

CREATE PROCEDURE spVenta(
	IN noFactura VARCHAR(20),
	IN totalFactura DECIMAL(8,2),
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
		INSERT INTO Factura (NoFactura, TotalFactura, IdCliente, IdTipoPago, IdCaja, IdSerie)
		VALUES (noFactura, totalFactura, idCliente, idTipoPago, idCaja, idSerie);
		
		SET idVenta = LAST_INSERT_ID();
		
		UPDATE Caja SET Monto = Monto + totalFactura
		WHERE IdCaja = idCaja;
		
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

DELIMITER $$
CREATE PROCEDURE `spCompra`(
	IN `idCompraActualizar` INT,
	IN `descripcionCompra` VARCHAR(250),
	IN `cantidadPedida` INT,
	IN `cantidadRecibida` INT,
	IN `ubicacionGenerada` VARCHAR(5),
	IN `detalleCompra` JSON,
	OUT `mensaje` VARCHAR(100),
	OUT `estado` INT
)
CompraSP:BEGIN
    DECLARE idProducto INT;
    DECLARE cantidadProducto INT;
    DECLARE estadoStock INT DEFAULT 1;
    DECLARE idestadocompra INT DEFAULT 2;
    DECLARE i INT DEFAULT 0;
    DECLARE n INT DEFAULT 0;

    -- MANEJO DE POSIBLES ERRORES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 mensaje = MESSAGE_TEXT;
        ROLLBACK;
        SET estado = 0;
    END;

    START TRANSACTION;
    
    SET idestadocompra = 2;
    -- ACTUALIZAR COMPRA
    UPDATE Compra Set DescripcionCompra = descripcionCompra, CantidadPedida=cantidadPedida, CantidadRecibida=cantidadRecibida, IdEstadoCompra=idestadocompra WHERE IdCompra = idCompraActualizar;


    -- VERIFICAR SI LA CANTIDAD RECIBIDA ES IGUAL A LA CANTIDAD PEDIDA
    IF cantidadRecibida = cantidadPedida THEN
        IF detalleCompra IS NULL OR JSON_LENGTH(detalleCompra) = 0 THEN
            SET mensaje = 'No se asignaron productos a la compra';
            SET estado = 0;
            ROLLBACK;
            LEAVE CompraSP;
        END IF;

        SET n = JSON_LENGTH(detalleCompra);

        -- RECORRER DETALLE DE LA COMPRA Y ACTUALIZAR STOCK
        WHILE i < n DO
            SET idProducto = JSON_UNQUOTE(JSON_EXTRACT(detalleCompra, CONCAT('$[',i,'].idProducto')));
            SET cantidadProducto = JSON_UNQUOTE(JSON_EXTRACT(detalleCompra, CONCAT('$[',i,'].cantidadProducto')));
				
				
            -- AGREGAR PRODUCTO AL STOCK O ACTUALIZAR SI YA EXISTE
            INSERT INTO Stock (IdProducto, CantidadInicial, CantidadDisponible, FechaIngreso, DescripcionStock, UbicacionBodega, Estado)
            VALUES (
                idProducto,
                cantidadProducto,
                cantidadProducto,
                CURDATE(),
                descripcionCompra,
                ubicacionGenerada,
                estadoStock
            )
            ON DUPLICATE KEY UPDATE 
                CantidadDisponible = CantidadDisponible + cantidadProducto;

            SET i = i + 1;
        END WHILE;

        SET mensaje = 'Compra registrada y stock actualizado correctamente';
        SET estado = 1;

    ELSE
        -- SI LA CANTIDAD RECIBIDA ES DIFERENTE A LA CANTIDAD PEDIDA
        SET mensaje = 'La cantidad recibida no coincide con la cantidad pedida';
        SET estado = 0;
        ROLLBACK;
    END IF;

    COMMIT;
END$$
DELIMITER ;
