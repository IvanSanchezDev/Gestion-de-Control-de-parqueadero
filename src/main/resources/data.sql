-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-02-2023 a las 00:23:43
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parqueaderobd`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarEstadoMensualidad` ()  MODIFIES SQL DATA
UPDATE mensualidades SET habilitado=0 WHERE fecha_fin=DATE(now())$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensualidades`
--

CREATE TABLE `mensualidades` (
  `id` bigint(20) NOT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `propietario` varchar(255) DEFAULT NULL,
  `telefono` bigint(20) NOT NULL,
  `tipo_vehiculo` varchar(255) DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `habilitado` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensualidades`
--

INSERT INTO `mensualidades` (`id`, `placa`, `propietario`, `telefono`, `tipo_vehiculo`, `fecha_fin`, `fecha_inicio`, `habilitado`) VALUES
(28, 'IUY58A', 'andres', 312546212, 'carro', '2023-03-12', '2023-02-12', b'1'),
(29, 'HUJ58A', 'Juanes', 32145566, 'moto', '2023-03-13', '2023-02-13', b'1'),
(34, 'HGK44F', 'Jairo Andres', 3122135487, 'moto', '2023-03-15', '2023-02-15', b'1'),
(35, 'JUH588', 'Pedro Ramirez', 315235648, 'carro', '2023-03-20', '2023-02-20', b'1'),
(36, 'Wilson', 'Noriega', 3112546987, 'moto', '2023-03-20', '2023-02-20', b'0'),
(37, 'fgy528', 'Carlos Perez', 32251445, 'carro', '2023-03-20', '2023-02-20', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `rol_nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol_nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifas`
--

CREATE TABLE `tarifas` (
  `id` bigint(20) NOT NULL,
  `tipo_vehiculo` varchar(255) DEFAULT NULL,
  `valor_hora` int(11) NOT NULL,
  `valor_mes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarifas`
--

INSERT INTO `tarifas` (`id`, `tipo_vehiculo`, `valor_hora`, `valor_mes`) VALUES
(1, 'Motocicleta', 1000, 60000),
(2, 'Automovil', 2500, 170000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `habilitado` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` bigint(20) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `habilitado`, `password`, `telefono`, `username`) VALUES
(1, b'1', '$2a$10$aDoHf2el.XmsnFChuJBu7e2TEgVBui28zzuenltxunndYB0dAf1ha', 3123195510, 'admin'),
(14, b'1', '$2a$10$m7FDGajVEbW9iH..dEJUe.oBpw6zYwRLepA4XDntZoMM0ck5TbekW', 315423698, 'empleado1'),
(19, b'1', '$2a$10$qZelLmIpvzquoEqoQmwUw.8duj0niD59upgWeQQREkSKth.v2RXuu', 12546, 'andres');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`usuario_id`, `rol_id`) VALUES
(1, 1),
(1, 2),
(14, 2),
(19, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id_vehiculo` int(11) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `hora_entrada` datetime NOT NULL,
  `hora_salida` datetime DEFAULT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `tipo_vehiculo` varchar(255) DEFAULT NULL,
  `valor_pagado` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`id_vehiculo`, `estado`, `hora_entrada`, `hora_salida`, `placa`, `tipo_vehiculo`, `valor_pagado`) VALUES
(249, 'No Disponible', '2022-12-20 18:26:15', '2022-12-20 18:26:24', 'ptt', 'carro', 2500),
(250, 'No Disponible', '2022-12-20 18:29:56', '2022-12-20 18:30:00', 'kl', 'moto', 800),
(251, 'No Disponible', '2022-12-20 18:29:56', '2022-12-20 18:30:00', 'kl', 'moto', 800),
(252, 'No Disponible', '2022-12-21 09:15:26', '2022-12-21 09:15:33', 'jeje', 'moto', 800),
(253, 'No Disponible', '2022-12-21 09:16:35', '2022-12-21 09:16:40', 'yu', 'carro', 2500),
(257, 'No Disponible', '2022-12-23 13:51:42', '2022-12-23 13:51:51', 'rr', 'carro', 2500),
(259, 'No Disponible', '2022-12-23 13:55:18', '2022-12-23 13:55:24', 'zs', 'carro', 2500),
(263, 'No Disponible', '2022-11-16 15:35:15', '2022-11-16 15:45:15', 'UUYH54', 'carro', 2500),
(264, 'No Disponible', '2022-10-12 15:49:15', '2022-11-17 15:49:15', 'IUYPL4', 'Moto', 50000),
(265, 'No Disponible', '2022-11-09 16:30:07', '2022-11-11 16:30:07', 'jejy58', 'carro', 35000),
(268, 'No Disponible', '2022-12-28 17:46:13', '2022-12-28 17:46:19', 'tyu', 'moto', 1000),
(269, 'No Disponible', '2022-12-28 17:46:44', '2022-12-28 17:50:19', 'fgh', 'carro', 2500),
(270, 'No Disponible', '2022-12-28 17:49:49', '2022-12-28 17:49:54', 'ttt', 'carro', 2500),
(272, 'No Disponible', '2022-12-31 13:43:19', '2022-12-31 13:44:50', 's', 'carro', 2500),
(273, 'No Disponible', '2022-12-31 13:46:14', '2022-12-31 13:46:20', 'F', 'carro', 2500),
(274, 'No Disponible', '2022-12-31 13:49:13', '2022-12-31 13:49:24', 'ww', 'moto', 1000),
(275, 'No Disponible', '2022-12-31 14:37:59', '2023-01-02 20:40:47', 'Hgyta', 'moto', 55000),
(276, 'No Disponible', '2022-12-31 14:40:52', '2023-01-02 20:45:24', 'tgtg', 'carro', 137500),
(277, 'No Disponible', '2022-12-31 14:42:39', '2023-01-02 20:45:39', 'UYHPA', 'moto', 55000),
(279, 'No Disponible', '2023-01-02 20:41:24', '2023-01-02 20:45:47', 'Hgyta', 'carro', 2500),
(283, 'No Disponible', '2023-01-02 20:48:15', '2023-01-02 20:51:10', 'TYTY', 'moto', 1000),
(284, 'No Disponible', '2023-01-04 18:57:13', '2023-01-04 18:57:24', 'hyt58g', 'carro', 2500),
(287, 'No Disponible', '2023-01-04 19:07:13', '2023-01-04 19:11:20', 'iu', 'moto', 1000),
(291, 'No Disponible', '2023-01-04 19:11:46', '2023-01-04 19:15:06', 'HGJK44', 'carro', 2500),
(295, 'No Disponible', '2023-01-04 19:15:27', '2023-01-04 19:18:30', 'HGJK44', 'moto', 1000),
(296, 'No Disponible', '2023-01-04 19:16:29', '2023-01-04 19:18:37', 'TT', 'moto', 1000),
(301, 'No Disponible', '2023-01-05 20:46:09', '2023-01-05 20:46:46', 'wert', 'moto', 1000),
(302, 'No Disponible', '2023-01-05 20:46:23', '2023-01-05 20:47:03', 'ff', 'carro', 2500),
(303, 'No Disponible', '2023-01-05 20:49:17', '2023-01-05 20:54:54', 'tty', 'moto', 1000),
(304, 'No Disponible', '2023-01-05 20:53:35', '2023-01-05 20:54:38', 'tellito', 'moto', 1000),
(310, 'No Disponible', '2023-01-07 15:13:44', '2023-01-07 15:14:05', 'Hyj34g', 'carro', 2500),
(311, 'No Disponible', '2023-01-07 18:59:39', '2023-01-07 18:59:50', 'ytu68f', 'carro', 2500),
(312, 'No Disponible', '2023-01-07 20:25:09', '2023-01-10 10:30:47', 'uyh55h', 'moto', 63000),
(313, 'No Disponible', '2023-01-07 20:25:47', '2023-01-10 10:30:37', 'ffff', 'moto', 63000),
(314, 'No Disponible', '2023-01-07 20:32:59', '2023-01-10 10:29:43', 'typlo5', 'carro', 155000),
(316, 'No Disponible', '2023-01-19 16:12:26', '2023-01-19 16:14:43', 'Lorena', 'carro', 2500),
(317, 'No Disponible', '2023-01-23 15:22:56', '2023-01-23 15:23:42', 'YTR58H', 'carro', 2500),
(318, 'No Disponible', '2023-01-24 14:38:25', '2023-01-24 14:38:43', 'YTR58G', 'carro', 2500),
(319, 'No Disponible', '2023-01-24 14:38:55', '2023-01-24 14:40:17', 'PÑO58A', 'moto', 1000),
(320, 'No Disponible', '2023-01-24 14:42:52', '2023-01-24 14:43:02', 'UYT58G', 'carro', 2500),
(321, 'No Disponible', '2023-01-24 16:04:57', '2023-01-24 16:05:33', 'JPQ25J', 'carro', 2500),
(322, 'No Disponible', '2023-01-24 16:17:21', '2023-01-24 16:17:31', 'POL58G', 'moto', 1000),
(323, 'No Disponible', '2023-01-25 15:03:21', '2023-01-25 15:03:46', 'GYT58A', 'moto', 1000),
(324, 'No Disponible', '2023-01-25 15:14:17', '2023-01-25 15:14:31', 'hgt58a', 'carro', 2500),
(325, 'No Disponible', '2023-01-25 15:15:19', '2023-01-25 15:15:53', 'YUT58Q', 'carro', 2500),
(335, 'No Disponible', '2023-01-26 16:55:52', '2023-01-26 16:55:59', 'IUY58F', 'carro', 2500),
(336, 'No Disponible', '2023-01-26 16:56:04', '2023-01-26 16:56:06', 'IUY58F', 'moto', 1000),
(337, 'No Disponible', '2023-01-26 16:56:43', '2023-01-29 10:22:13', 'POJ58G', 'carro', 165000),
(338, 'No Disponible', '2023-01-26 17:12:44', '2023-01-26 17:12:52', 'TRY58V', 'carro', 2500),
(339, 'No Disponible', '2023-01-26 17:13:02', '2023-01-26 17:13:11', 'TRY58V', 'carro', 2500),
(340, 'No Disponible', '2023-01-26 17:13:17', '2023-01-26 17:13:25', 'TRY58V', 'moto', 1000),
(342, 'No Disponible', '2023-01-29 10:23:11', '2023-01-29 10:23:18', 'UYP69S', 'moto', 1000),
(343, 'No Disponible', '2023-01-29 10:23:37', '2023-01-29 10:23:49', 'UYP69S', 'moto', 1000),
(344, 'No Disponible', '2023-01-29 10:24:00', '2023-01-29 10:24:09', 'MUY32X', 'carro', 2500),
(345, 'No Disponible', '2023-02-01 10:09:21', '2023-02-01 10:09:32', 'HYUT58', 'carro', 2500),
(346, 'No Disponible', '2023-02-01 10:09:47', '2023-02-01 10:09:55', 'LLO69S', 'moto', 1000),
(347, 'No Disponible', '2023-02-02 09:22:14', '2023-02-02 09:22:25', 'POI25A', 'carro', 2500),
(348, 'No Disponible', '2023-02-02 09:22:38', '2023-02-02 09:22:45', 'UHT23A', 'moto', 1000),
(349, 'No Disponible', '2023-02-03 08:48:47', '2023-02-03 08:48:56', 'JUH58A', 'carro', 2500),
(350, 'No Disponible', '2023-02-03 08:49:00', '2023-02-03 14:12:00', 'JUH58A', 'carro', 15000),
(351, 'No Disponible', '2023-02-03 08:49:10', '2023-02-03 08:49:17', 'TYU32L', 'moto', 1000),
(352, 'No Disponible', '2023-02-03 08:49:21', '2023-02-03 14:15:45', 'TYU32L', 'moto', 6000),
(353, 'No Disponible', '2023-02-03 09:52:47', '2023-02-03 15:39:26', 'KIH52C', 'moto', 6000),
(354, 'No Disponible', '2023-02-03 15:48:33', '2023-02-03 16:14:35', 'NBT20X', 'carro', 2500),
(355, 'No Disponible', '2023-02-07 09:27:48', '2023-02-07 09:27:59', 'UYJ58A', 'carro', 2500),
(356, 'No Disponible', '2023-02-07 09:28:07', '2023-02-07 09:28:14', 'OIU20S', 'moto', 1000),
(357, 'No Disponible', '2023-02-07 09:58:40', '2023-02-07 09:58:49', 'IUY85S', 'moto', 1000),
(359, 'No Disponible', '2023-02-07 10:19:06', '2023-02-07 10:19:14', 'IUY58S', 'carro', 2500),
(360, 'No Disponible', '2023-02-07 10:28:59', '2023-02-07 10:29:06', 'HYT52X', 'carro', 2500),
(361, 'No Disponible', '2023-02-07 10:43:45', '2023-02-07 10:44:06', 'HGT21N', 'carro', 2500),
(362, 'No Disponible', '2023-02-07 14:11:29', '2023-02-07 14:39:26', 'UYH58S', 'moto', 1000),
(363, 'No Disponible', '2023-02-07 14:19:56', '2023-02-07 20:34:55', 'JUJ58F', 'carro', 17500),
(364, 'No Disponible', '2023-02-07 14:28:50', '2023-02-07 14:29:00', 'DFRTTT', 'carro', 2500),
(365, 'No Disponible', '2023-02-07 14:35:49', '2023-02-07 20:35:02', 'JUH58A', 'carro', 15000),
(366, 'No Disponible', '2023-02-07 14:38:57', '2023-02-07 20:34:46', 'JUH58S', 'moto', 6000),
(368, 'No Disponible', '2023-02-11 14:59:01', '2023-02-11 14:59:11', 'UYO58A', 'carro', 2500),
(369, 'No Disponible', '2023-02-11 14:59:24', '2023-02-11 15:00:03', 'IUP58S', 'carro', 2500),
(370, 'No Disponible', '2023-02-11 14:59:33', '2023-02-11 14:59:41', 'KNS69C', 'moto', 1000),
(371, 'No Disponible', '2023-02-12 15:37:06', '2023-02-12 18:00:18', 'KIJ58A', 'carro', 7500),
(372, 'No Disponible', '2023-02-12 15:39:17', '2023-02-12 18:00:29', 'YTO52A', 'moto', 3000),
(374, 'No Disponible', '2023-02-12 15:46:25', '2023-02-12 18:00:49', 'JHK44D', 'carro', 7500),
(375, 'No Disponible', '2023-02-12 16:24:52', '2023-02-12 18:00:42', 'JUH58S', 'carro', 5000),
(376, 'No Disponible', '2023-02-12 16:25:32', '2023-02-12 18:00:36', 'JUH58A', 'moto', 2000),
(377, 'No Disponible', '2023-02-12 18:02:26', '2023-02-12 18:07:26', 'JUH584', 'carro', 2500),
(378, 'No Disponible', '2023-02-12 18:02:38', '2023-02-12 18:07:04', 'FGT58A', 'moto', 1000),
(379, 'No Disponible', '2023-02-13 14:31:04', '2023-02-13 14:36:10', 'JUH87A', 'moto', 1000),
(382, 'No Disponible', '2023-02-13 15:29:29', '2023-02-13 15:46:28', 'GYT58A', 'moto', 1000),
(383, 'No Disponible', '2023-02-13 15:29:43', '2023-02-13 15:46:38', 'HYF855', 'carro', 2500),
(384, 'No Disponible', '2023-02-13 15:47:19', '2023-02-13 15:47:28', 'IUJ58A', 'moto', 1000),
(385, 'No Disponible', '2023-02-13 15:48:20', '2023-02-13 15:48:29', 'GYT587', 'carro', 2500),
(386, 'No Disponible', '2023-02-13 15:51:25', '2023-02-13 15:51:35', 'KIJ85S', 'carro', 2500),
(387, 'No Disponible', '2023-02-15 12:40:50', '2023-02-15 12:44:46', 'HUJ58A', 'moto', 1000),
(388, 'No Disponible', '2023-02-15 12:40:57', '2023-02-15 12:44:52', 'POL588', 'carro', 2500),
(389, 'No Disponible', '2023-02-15 12:41:10', '2023-02-15 12:44:57', 'UYS58W', 'moto', 1000),
(390, 'No Disponible', '2023-02-15 12:41:20', '2023-02-15 12:45:02', 'BHY222', 'carro', 2500),
(391, 'No Disponible', '2023-02-15 12:41:24', '2023-02-15 12:45:07', 'DFR855', 'carro', 2500),
(392, 'No Disponible', '2023-02-15 12:41:34', '2023-02-15 12:45:12', 'IUD58A', 'moto', 1000),
(393, 'No Disponible', '2023-02-15 12:41:44', '2023-02-15 12:45:16', 'GRT588', 'carro', 2500),
(394, 'No Disponible', '2023-02-15 12:41:52', '2023-02-15 12:45:20', 'DET236', 'carro', 2500),
(395, 'No Disponible', '2023-02-15 12:41:58', '2023-02-15 12:45:25', 'NGF36A', 'moto', 1000),
(396, 'No Disponible', '2023-02-15 12:42:05', '2023-02-15 12:45:29', 'ASD98W', 'moto', 1000),
(397, 'No Disponible', '2023-02-15 12:42:12', '2023-02-15 12:45:33', 'ASE224', 'carro', 2500),
(398, 'No Disponible', '2023-02-15 12:42:19', '2023-02-15 12:45:42', 'MNB124', 'carro', 2500),
(399, 'No Disponible', '2023-02-15 12:42:30', '2023-02-15 12:45:46', 'VCX987', 'carro', 2500),
(400, 'No Disponible', '2023-02-15 12:42:41', '2023-02-15 12:45:55', 'ZKJ123', 'carro', 2500),
(401, 'No Disponible', '2023-02-15 12:42:52', '2023-02-15 12:46:01', 'LKJ25L', 'moto', 1000),
(402, 'No Disponible', '2023-02-15 12:42:59', '2023-02-15 12:46:05', 'KJH65K', 'moto', 1000),
(403, 'No Disponible', '2023-02-15 12:43:07', '2023-02-15 12:46:10', 'FTF85S', 'moto', 1000),
(404, 'No Disponible', '2023-02-15 12:46:28', '2023-02-15 12:52:04', 'KIJ588', 'carro', 2500),
(405, 'No Disponible', '2023-02-15 12:46:37', '2023-02-15 12:52:09', 'QWE999', 'carro', 2500),
(406, 'No Disponible', '2023-02-15 12:46:44', '2023-02-15 12:52:19', 'RTY58A', 'moto', 1000),
(407, 'No Disponible', '2023-02-15 12:46:48', '2023-02-15 12:52:29', 'YUI584', 'carro', 2500),
(408, 'No Disponible', '2023-02-15 12:46:55', '2023-02-15 12:52:35', 'POL58A', 'moto', 1000),
(409, 'No Disponible', '2023-02-15 12:46:59', '2023-02-15 12:52:43', 'ASD584', 'carro', 2500),
(410, 'No Disponible', '2023-02-15 12:47:10', '2023-02-15 12:52:49', 'FGH562', 'carro', 2500),
(411, 'No Disponible', '2023-02-15 12:47:17', '2023-02-15 12:52:54', 'JMN25S', 'moto', 1000),
(412, 'No Disponible', '2023-02-15 12:47:25', '2023-02-15 12:52:58', 'XCV14S', 'moto', 1000),
(413, 'No Disponible', '2023-02-15 12:47:33', '2023-02-15 12:53:13', 'SFG54R', 'moto', 1000),
(414, 'No Disponible', '2023-02-15 12:47:39', '2023-02-15 12:53:18', 'YTR58F', 'moto', 1000),
(415, 'No Disponible', '2023-02-15 12:48:00', '2023-02-15 13:09:27', 'HYF45A', 'moto', 1000),
(416, 'No Disponible', '2023-02-15 12:48:14', '2023-02-15 13:12:46', 'FGT85I', 'moto', 1000),
(417, 'No Disponible', '2023-02-15 12:48:38', '2023-02-15 13:12:50', 'GFT58R', 'moto', 1000),
(418, 'No Disponible', '2023-02-15 12:48:53', '2023-02-15 13:08:53', 'AAA254', 'carro', 2500),
(419, 'No Disponible', '2023-02-15 12:49:12', '2023-02-15 13:12:54', 'DFG98A', 'moto', 1000),
(420, 'No Disponible', '2023-02-15 12:49:25', '2023-02-15 13:12:59', 'TYR85A', 'moto', 1000),
(421, 'No Disponible', '2023-02-15 12:49:40', '2023-02-15 13:13:05', 'HYU87D', 'moto', 1000),
(422, 'No Disponible', '2023-02-15 12:49:54', '2023-02-15 13:13:10', 'JUH533', 'carro', 2500),
(423, 'No Disponible', '2023-02-15 12:50:36', '2023-02-15 13:13:15', '584HYA', 'moto', 1000),
(424, 'No Disponible', '2023-02-15 12:50:45', '2023-02-15 13:13:19', '587SE5', 'carro', 2500),
(425, 'No Disponible', '2023-02-15 12:50:58', '2023-02-15 13:13:24', 'WEY25S', 'moto', 1000),
(426, 'No Disponible', '2023-02-15 12:51:07', '2023-02-15 13:13:28', 'EGN255', 'carro', 2500),
(427, 'No Disponible', '2023-02-15 12:51:13', '2023-02-15 13:13:37', 'IVA24S', 'moto', 1000),
(428, 'No Disponible', '2023-02-15 12:51:20', '2023-02-15 13:13:41', 'PAB25S', 'moto', 1000),
(429, 'No Disponible', '2023-02-15 12:51:29', '2023-02-15 13:44:31', 'NUB088', 'carro', 2500),
(430, 'No Disponible', '2023-02-15 12:51:35', '2023-02-15 13:45:11', 'LAU300', 'carro', 2500),
(431, 'No Disponible', '2023-02-15 12:51:45', '2023-02-15 14:07:37', 'NES25M', 'moto', 2000),
(432, 'No Disponible', '2023-02-15 13:36:52', '2023-02-15 13:44:44', 'OIP58A', 'moto', 1000),
(433, 'No Disponible', '2023-02-15 13:36:57', '2023-02-15 13:45:38', 'DJU222', 'carro', 2500),
(434, 'No Disponible', '2023-02-15 13:37:07', '2023-02-15 13:45:46', 'GHY855', 'carro', 2500),
(435, 'No Disponible', '2023-02-15 13:37:11', '2023-02-15 14:07:42', 'DRT58D', 'moto', 1000),
(436, 'No Disponible', '2023-02-15 13:37:33', '2023-02-15 14:07:46', 'JUG58A', 'moto', 1000),
(437, 'No Disponible', '2023-02-15 13:37:44', '2023-02-15 14:07:51', 'FGT844', 'carro', 2500),
(438, 'No Disponible', '2023-02-15 13:38:17', '2023-02-15 14:07:55', 'GHY84S', 'moto', 1000),
(439, 'No Disponible', '2023-02-15 13:38:24', '2023-02-15 14:07:59', 'SWR552', 'carro', 2500),
(440, 'No Disponible', '2023-02-15 13:38:28', '2023-02-15 14:08:03', 'WER69S', 'moto', 1000),
(441, 'No Disponible', '2023-02-15 13:38:34', '2023-02-15 14:08:11', 'GTY24A', 'moto', 1000),
(442, 'No Disponible', '2023-02-15 13:38:39', '2023-02-15 14:08:18', 'GYR566', 'carro', 2500),
(443, 'No Disponible', '2023-02-15 13:38:45', '2023-02-15 14:08:23', 'GBN254', 'carro', 2500),
(444, 'No Disponible', '2023-02-15 13:38:50', '2023-02-15 14:08:40', 'BHH5D', 'moto', 1000),
(445, 'No Disponible', '2023-02-15 13:38:58', '2023-02-15 14:08:47', 'HGH55A', 'moto', 1000),
(446, 'No Disponible', '2023-02-15 13:39:07', '2023-02-15 14:08:51', 'GRT84V', 'moto', 1000),
(447, 'No Disponible', '2023-02-15 13:39:13', '2023-02-15 14:08:56', 'DRT54S', 'moto', 1000),
(448, 'No Disponible', '2023-02-15 13:39:24', '2023-02-15 14:09:01', 'PQY25G', 'moto', 1000),
(449, 'No Disponible', '2023-02-15 13:39:40', '2023-02-15 14:09:05', 'GHF58S', 'moto', 1000),
(450, 'No Disponible', '2023-02-15 13:39:46', '2023-02-15 14:09:11', 'BGC65S', 'moto', 1000),
(451, 'No Disponible', '2023-02-15 13:40:59', '2023-02-15 14:09:14', 'GHY11A', 'moto', 1000),
(452, 'No Disponible', '2023-02-15 13:41:06', '2023-02-15 14:09:34', 'CXS36J', 'moto', 1000),
(453, 'No Disponible', '2023-02-15 13:41:45', '2023-02-15 14:09:25', 'NHD25Y', 'moto', 1000),
(454, 'No Disponible', '2023-02-15 13:42:41', '2023-02-15 14:09:38', 'JUH58S', 'moto', 1000),
(455, 'No Disponible', '2023-02-15 13:42:50', '2023-02-15 14:09:45', 'JUH21C', 'moto', 1000),
(456, 'No Disponible', '2023-02-15 13:42:59', '2023-02-15 14:09:49', 'NVX20H', 'moto', 1000),
(457, 'No Disponible', '2023-02-15 13:43:40', '2023-02-15 14:09:53', 'GHY58A', 'moto', 1000),
(458, 'No Disponible', '2023-02-15 13:44:55', '2023-02-15 13:45:05', 'NUB088', 'carro', 2500),
(459, 'No Disponible', '2023-02-15 13:45:15', '2023-02-15 14:10:07', 'NUB088', 'carro', 2500),
(460, 'No Disponible', '2023-02-15 13:45:31', '2023-02-15 14:10:12', 'OIP58A', 'moto', 1000),
(461, 'No Disponible', '2023-02-15 14:09:19', '2023-02-15 14:10:21', 'GHY11A', 'moto', 1000),
(462, 'No Disponible', '2023-02-15 14:09:57', '2023-02-15 14:10:26', 'GHY58A', 'moto', 1000),
(463, 'No Disponible', '2023-02-15 17:47:03', '2023-02-15 18:00:32', 'JUH587', 'carro', 2500),
(464, 'No Disponible', '2023-02-15 17:50:46', '2023-02-15 18:00:36', 'KIJ25A', 'moto', 1000),
(465, 'No Disponible', '2023-02-15 17:50:58', '2023-02-15 18:00:41', 'MJH211', 'carro', 2500),
(466, 'No Disponible', '2023-02-15 17:51:05', '2023-02-15 18:00:45', 'ZSS58T', 'moto', 1000),
(467, 'No Disponible', '2023-02-20 14:09:43', '2023-02-20 14:29:32', 'HJU58A', 'carro', 2500),
(468, 'No Disponible', '2023-02-20 14:12:28', '2023-02-20 14:30:50', 'KIJ588', 'carro', 2500),
(469, 'No Disponible', '2023-02-20 14:25:17', '2023-02-20 14:41:37', 'KJU58A', 'moto', 1000),
(470, 'No Disponible', '2023-02-20 14:28:56', '2023-02-20 14:32:08', 'JUH888', 'carro', 2500),
(471, 'No Disponible', '2023-02-20 14:30:36', '2023-02-20 14:55:31', 'UYH58F', 'carro', 2500),
(472, 'No Disponible', '2023-02-20 14:55:09', '2023-02-20 15:05:01', 'JUH558', 'carro', 2500),
(473, 'No Disponible', '2023-02-20 14:55:20', '2023-02-20 20:14:05', 'YJU52B', 'moto', 6000),
(474, 'No Disponible', '2023-02-23 15:50:00', '2023-02-23 15:50:14', 'JUH589', 'carro', 2500);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mensualidades`
--
ALTER TABLE `mensualidades`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD PRIMARY KEY (`usuario_id`,`rol_id`),
  ADD KEY `FKe3kd49gu3mhj2ty5kl44qsytp` (`rol_id`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id_vehiculo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mensualidades`
--
ALTER TABLE `mensualidades`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=475;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `FKe3kd49gu3mhj2ty5kl44qsytp` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKktsemf1f6awjww4da0ocv4n32` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
