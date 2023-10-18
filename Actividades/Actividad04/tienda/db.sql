-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tienda` DEFAULT CHARACTER SET utf8 ;
USE `tienda` ;

-- -----------------------------------------------------
-- Table `tienda`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `usuNombre` VARCHAR(45) NOT NULL,
  `usuApPat` VARCHAR(45) NOT NULL,
  `usuApMat` VARCHAR(45) NULL,
  `usuEmail` VARCHAR(45) NOT NULL,
  `usuPassword` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`tiendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`tiendas` (
  `idTienda` INT NOT NULL AUTO_INCREMENT,
  `tienNombre` VARCHAR(45) NOT NULL,
  `tienDescripcion` VARCHAR(45) NOT NULL,
  `usuarios_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idTienda`),
  INDEX `fk_tiendas_usuarios1_idx` (`usuarios_idUsuario` ASC) ,
  CONSTRAINT `fk_tiendas_usuarios1`
    FOREIGN KEY (`usuarios_idUsuario`)
    REFERENCES `tienda`.`usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`productos` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `prodNombre` VARCHAR(255) NOT NULL,
  `prodDescripcion` LONGTEXT NOT NULL,
  `prodPrecio` INT NOT NULL,
  `prodStock` INT NOT NULL,
  `prodFechaCaducidad` VARCHAR(45) NULL,
  `prodMarca` VARCHAR(45) NULL,
  `prodCategoria` VARCHAR(45) NULL,
  `prodColor` VARCHAR(45) NULL,
  `prodTalla` VARCHAR(45) NULL,
  `prodImg` VARCHAR(255) NOT NULL,
  `tiendas_idTienda` INT NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_productos_tiendas1_idx` (`tiendas_idTienda` ASC) ,
  CONSTRAINT `fk_productos_tiendas1`
    FOREIGN KEY (`tiendas_idTienda`)
    REFERENCES `tienda`.`tiendas` (`idTienda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`pagos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`pagos` (
  `idPagos` INT NOT NULL AUTO_INCREMENT,
  `pagoPlataforma` VARCHAR(255) NOT NULL,
  `pagoFecha` DATE NOT NULL,
  `pagoCargo` FLOAT NOT NULL,
  `pagoNumCuenta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPagos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`envios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`envios` (
  `idEnvio` INT NOT NULL AUTO_INCREMENT,
  `envEstado` VARCHAR(45) NOT NULL,
  `envColonia` VARCHAR(45) NOT NULL,
  `envCalle` VARCHAR(45) NOT NULL,
  `envCiudad` VARCHAR(45) NOT NULL,
  `envNumCasa` INT NOT NULL,
  PRIMARY KEY (`idEnvio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`pedidos` (
  `idPedidos` INT NOT NULL AUTO_INCREMENT,
  `pedFecha` DATE NOT NULL,
  `pedTotal` INT NOT NULL,
  `pedIVA` INT NOT NULL,
  `usuarios_idUsuario` INT NOT NULL,
  `pagos_idPagos` INT NOT NULL,
  `envios_idEnvio` INT NOT NULL,
  PRIMARY KEY (`idPedidos`),
  INDEX `fk_pedidos_usuarios1_idx` (`usuarios_idUsuario` ASC) ,
  INDEX `fk_pedidos_pagos1_idx` (`pagos_idPagos` ASC) ,
  INDEX `fk_pedidos_envios1_idx` (`envios_idEnvio` ASC) ,
  CONSTRAINT `fk_pedidos_usuarios1`
    FOREIGN KEY (`usuarios_idUsuario`)
    REFERENCES `tienda`.`usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_pagos1`
    FOREIGN KEY (`pagos_idPagos`)
    REFERENCES `tienda`.`pagos` (`idPagos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_envios1`
    FOREIGN KEY (`envios_idEnvio`)
    REFERENCES `tienda`.`envios` (`idEnvio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`pedidos_productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`pedidos_productos` (
  `idPedidosProductos` INT NOT NULL AUTO_INCREMENT,
  `pedidos_idPedidos` INT NOT NULL,
  `productos_idProducto` INT NOT NULL,
  `prod_pedPrecioDeVenta` INT NOT NULL,
  INDEX `fk_pedidos_has_productos_productos1_idx` (`productos_idProducto` ASC) ,
  INDEX `fk_pedidos_has_productos_pedidos1_idx` (`pedidos_idPedidos` ASC) ,
  PRIMARY KEY (`idPedidosProductos`),
  CONSTRAINT `fk_pedidos_has_productos_pedidos1`
    FOREIGN KEY (`pedidos_idPedidos`)
    REFERENCES `tienda`.`pedidos` (`idPedidos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_has_productos_productos1`
    FOREIGN KEY (`productos_idProducto`)
    REFERENCES `tienda`.`productos` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`comentarios` (
  `idComentario` INT NOT NULL AUTO_INCREMENT,
  `comComentario` VARCHAR(255) NOT NULL,
  `comFecha` DATE NOT NULL,
  `productos_idProducto` INT NOT NULL,
  `tiendas_idTienda` INT NOT NULL,
  `usuarios_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idComentario`),
  INDEX `fk_comentarios_productos1_idx` (`productos_idProducto` ASC) ,
  INDEX `fk_comentarios_tiendas1_idx` (`tiendas_idTienda` ASC) ,
  INDEX `fk_comentarios_usuarios1_idx` (`usuarios_idUsuario` ASC) ,
  CONSTRAINT `fk_comentarios_productos1`
    FOREIGN KEY (`productos_idProducto`)
    REFERENCES `tienda`.`productos` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentarios_tiendas1`
    FOREIGN KEY (`tiendas_idTienda`)
    REFERENCES `tienda`.`tiendas` (`idTienda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentarios_usuarios1`
    FOREIGN KEY (`usuarios_idUsuario`)
    REFERENCES `tienda`.`usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`carrito` (
  `idCarrito` INT NOT NULL AUTO_INCREMENT,
  `usuarios_idUsuario` INT NOT NULL,
  INDEX `fk_carrito_usuarios1_idx` (`usuarios_idUsuario` ASC) ,
  PRIMARY KEY (`idCarrito`),
  CONSTRAINT `fk_carrito_usuarios1`
    FOREIGN KEY (`usuarios_idUsuario`)
    REFERENCES `tienda`.`usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`detalle_carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`detalle_carrito` (
  `idDetalleCarrito` INT NOT NULL AUTO_INCREMENT,
  `carrito_idCarrito` INT NOT NULL,
  `productos_idProducto` INT NOT NULL,
  `active` TINYINT NOT NULL,
  `stock` INT NOT NULL,
  INDEX `fk_carrito_has_productos_productos1_idx` (`productos_idProducto` ASC) ,
  INDEX `fk_carrito_has_productos_carrito1_idx` (`carrito_idCarrito` ASC) ,
  PRIMARY KEY (`idDetalleCarrito`),
  CONSTRAINT `fk_carrito_has_productos_carrito1`
    FOREIGN KEY (`carrito_idCarrito`)
    REFERENCES `tienda`.`carrito` (`idCarrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_carrito_has_productos_productos1`
    FOREIGN KEY (`productos_idProducto`)
    REFERENCES `tienda`.`productos` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
