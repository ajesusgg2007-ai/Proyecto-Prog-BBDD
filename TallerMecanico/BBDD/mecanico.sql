-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2026 a las 17:23:00
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mecanico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_Cliente` int(5) NOT NULL,
  `Nombre` text NOT NULL,
  `Telefono` int(9) NOT NULL,
  `Email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID_Cliente`, `Nombre`, `Telefono`, `Email`) VALUES
(1, ' Salvador Galán', 123456, 'a@ies.es');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehículos`
--

CREATE TABLE `vehículos` (
  `Matrícula` varchar(5) NOT NULL,
  `ID_Cliente` int(5) NOT NULL,
  `Modelo` varchar(20) NOT NULL,
  `Marca` varchar(20) NOT NULL,
  `Arreglo` varchar(30) NOT NULL,
  `Limpiado` tinyint(1) NOT NULL,
  `Fecha_Entrada` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `vehículos`
--

INSERT INTO `vehículos` (`Matrícula`, `ID_Cliente`, `Modelo`, `Marca`, `Arreglo`, `Limpiado`, `Fecha_Entrada`) VALUES
('XM11', 1, 'Corolla', 'Toyota', 'Fallo Motor', 0, '2026-05-22');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_Cliente`);

--
-- Indices de la tabla `vehículos`
--
ALTER TABLE `vehículos`
  ADD PRIMARY KEY (`Matrícula`),
  ADD KEY `ID_Cliente` (`ID_Cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID_Cliente` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `vehículos`
--
ALTER TABLE `vehículos`
  ADD CONSTRAINT `fk_cliente_vehiculo` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
