-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2023 a las 18:13:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jcvd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Genero` varchar(25) DEFAULT NULL,
  `FechaLanzamiento` date DEFAULT NULL,
  `Compañia` varchar(50) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`id`, `Nombre`, `Genero`, `FechaLanzamiento`, `Compañia`, `Precio`) VALUES
(1, 'The Witcher 3: Wild Hunt', 'RPG', '2015-05-19', 'CD Projekt RED', 60.00),
(2, 'Red Dead Redemption 2', 'Acción y Aventura', '2018-10-26', 'Rockstar Games', 50.00),
(3, 'The Legend of Zelda: Breath of the Wild', 'Aventura', '2017-03-03', 'Nintendo', 60.00),
(4, 'FIFA 22', 'Deportes', '2021-10-01', 'Electronic Arts', 70.00),
(5, 'Horizon 0 Dawn', 'Acción y Aventura', '2017-03-01', 'Guerrilla Games', 36.50),
(7, 'The Witcher 2: Wild Hunt', 'RPG', '2015-05-19', 'CD Projekt RED', 59.00),
(8, 'Red Dead Redemption 1', 'Acción y Aventura', '2018-10-26', 'Rockstar Games', 49.00),
(9, 'The Legend', 'Aventura', '2017-03-03', 'Nintendo', 59.00),
(10, 'FIFA 23', 'Deportes', '2021-10-01', 'Electronic Arts', 69.00),
(11, 'Horizon', 'Acción y Aventura', '2017-02-28', 'Guerrilla Games', 39.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
