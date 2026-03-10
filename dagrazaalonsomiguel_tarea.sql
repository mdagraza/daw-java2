-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-03-2025 a las 17:03:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dagrazaalonsomiguel_tarea4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `idLibros` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Autor` varchar(100) DEFAULT NULL,
  `Descripcion` text DEFAULT NULL,
  `Pais` varchar(100) DEFAULT NULL,
  `Codigo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`idLibros`, `Nombre`, `Autor`, `Descripcion`, `Pais`, `Codigo`) VALUES
(1, 'Cien años de soledad', 'Gabriel García Márquez', 'Esta obra maestra de la literatura latinoamericana narra la historia de la familia Buendía en el pueblo ficticio de Macondo, abordando temas de soledad, amor y fatalidad.', 'Colombia', '978-3-16-148410-0'),
(2, '1984', 'George Orwell', 'Una novela distópica que describe un mundo totalitario donde el gobierno controla todos los aspectos de la vida de los ciudadanos, incluida su propia percepción de la realidad.', 'Reino Unido', '978-0451524935'),
(4, 'El gran Gatsby', 'F. Scott Fitzgerald', 'Esta novela explora el exceso y la decadencia de la sociedad estadounidense en los años 20, centrándose en la vida de Jay Gatsby y su obsesión por Daisy Buchanan.', 'Estados Unidos', '978-0743273565'),
(5, 'Matar a un ruiseñor', 'Harper Lee', 'Esta novela aborda temas como el racismo y la justicia en el sur de los Estados Unidos a través de los ojos de Scout Finch, una niña que presencia los juicios de un hombre negro acusado injustamente de violación.', 'Estados Unidos', '978-0061120084'),
(6, 'La sombra del viento', 'Carlos Ruiz Zafón', 'En la Barcelona de la posguerra, un joven llamado Daniel Sempere descubre un misterioso libro en un cementerio de libros olvidados, lo que lo lleva a desentrañar secretos oscuros sobre el autor del libro y su propia vida.', 'España', '978-8408090217'),
(9, 'Don Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 'La historia de un hidalgo, Don Quijote, que, influenciado por la lectura de libros de caballería, se embarca en un viaje lleno de aventuras y luchas contra molinos de viento, creyendo que son gigantes.', 'España', '978-8491055133'),
(12, 'Crimen y castigo', 'Fiódor Dostoyevski', 'Esta novela psicológica sigue a Raskólnikov, un joven estudiante que comete un asesinato, enfrentándose a los remordimientos de conciencia y la lucha moral interna.', 'Rusia', '978-0140449136'),
(14, 'El alquimista', 'Paulo Coelho', 'La historia de un joven pastor llamado Santiago que emprende un viaje en busca de un tesoro, aprendiendo lecciones sobre el destino, los sueños y la vida.', 'Brasil', '978-0061122415'),
(15, 'La casa de los espíritus', 'Isabel Allende', 'Esta novela histórica y de realismo mágico narra las vidas de varias generaciones de una familia chilena, explorando temas como la política, la lucha por la justicia y lo sobrenatural.', 'Chile', '978-1501117015'),
(16, 'Ulises', 'James Joyce', 'Esta obra modernista es un relato sobre un solo día en la vida de tres personajes en Dublín, donde Joyce explora la identidad, la religión y el lenguaje con una narrativa innovadora.', 'Irlanda', '978-0199535675'),
(18, 'Niebla', 'Miguel de Unamuno', '\"Niebla\" es una obra filosófica que aborda temas como la existencia, el libre albedrío y el sentido de la vida. La novela relata la historia de Augusto Pérez, un joven que lucha con la cuestión de la vida y la muerte, hasta que se encuentra con la paradoja de que su vida podría ser solo una obra de ficción.', 'España', '978-8437611392'),
(19, 'Fervor de Buenos Aires', 'Miguel de Unamuno', 'Esta obra de Miguel de Unamuno está marcada por la exploración de la identidad y la cultura de Argentina, con la ciudad de Buenos Aires como eje central. El autor, un filósofo y escritor español, combina sus reflexiones filosóficas con su amor por la literatura latinoamericana.', 'España', '978-8437624811');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`idLibros`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `idLibros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
