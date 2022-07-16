--
-- Base de datos: `xdxdxdxd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--


CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` int(20) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `telefono` int(15) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `origen` varchar(200) NOT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

#INSERT INTO `clientes` (`id`, `dni`, `nombre`, `telefono`, `direccion`) VALUES
#(1, '1234598', 'Angel sifuentes', '924878', 'Lima - Perú');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datostienda`
--
CREATE TABLE `datostienda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruc` varchar(20) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `telefono` int(15) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `razon` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `config`
--

#INSERT INTO `config` (`id`, `ruc`, `nombre`, `telefono`, `direccion`, `mensaje`) VALUES
#(1, 71347267, 'Vida Informático', 925491523, 'Lima - Perú', 'Vida Informático');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalleproduc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_ventas` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle`
--

#INSERT INTO `detalle` (`id`, `id_pro`, `cantidad`, `precio`, `id_venta`) VALUES
#(4, 1, 5, '3000.00', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--


CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(30) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `proveedor` varchar(100) NOT NULL,
  `stock` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

##INSERT INTO `productos` (`id`, `codigo`, `nombre`, `proveedor`, `stock`, `precio`) VALUES
##(1, '79878789', 'Laptop lenovo', 1, 20, '3000.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

#CREATE TABLE `proveedor` (
 # `id` int(11) NOT NULL,
  #`ruc` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  #`nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  #`telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  #`direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL
#) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruc` int(20) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `telefono` int(15) NOT NULL,
  `correo` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

#INSERT INTO `proveedor` (`id`, `ruc`, `nombre`, `telefono`, `direccion`) VALUES
#(1, '998787', 'Open Services', '798978879', 'Lima - Perú');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

#CREATE TABLE `usuarios` (
 # `id` int(11) NOT NULL,
  #`nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  #`correo` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  #`pass` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  #`rol` varchar(20) COLLATE utf8_spanish_ci NOT NULL
#) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

#INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `pass`, `rol`) VALUES
#(1, 'Angel Sifuentes', 'angel@gmail.com', 'admin', 'Administrador'),
#(2, 'Vida Informatico', 'admin@gmail.com', 'admin', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

#CREATE TABLE `ventas` (
 # `id` int(11) NOT NULL,
 # `cliente` int(11) NOT NULL,
 ## `vendedor` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
 # `total` decimal(10,2) NOT NULL,
 # `fecha` varchar(20) COLLATE utf8_spanish_ci NOT NULL
#) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
CREATE TABLE `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) NOT NULL,
  `vendedor` varchar(100) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

#INSERT INTO `ventas` (`id`, `cliente`, `vendedor`, `total`, `fecha`) VALUES
#(4, 1, 'Angel Sifuentes', '15000.00', '25/07/2021');