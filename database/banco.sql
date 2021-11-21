-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.18-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para oficial2poo
CREATE DATABASE IF NOT EXISTS `oficial2poo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `oficial2poo`;

-- Copiando estrutura para tabela oficial2poo.books
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `author` varchar(100) NOT NULL,
  `publishing_company` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela oficial2poo.books: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`, `title`, `summary`, `author`, `publishing_company`, `category`, `created_at`, `updated_at`) VALUES
	(1, 'Harry Potter e a Pedra de Crack', 'Harry Potter é um garoto órfão que vive infeliz com seus tios, os Dursleys. Ele recebe uma carta contendo um convite para ingressar em Hogwarts.', 'J. Krack', 'Larica Books', 'Autoajuda', '2021-11-21 05:41:36', '2021-11-21 05:41:36'),
	(2, 'A culpa é das estrelas do PT', 'E o Lula? E o PT?', 'Bolsominion', 'Direita Books', 'Drama', '2021-11-21 05:43:02', '2021-11-21 05:43:02'),
	(3, 'O Mínimo que Você Precisa Saber para Ser um Idiota', 'A obra consiste em uma coletânea de 193 artigos e ensaios escritos entre 1997 e 2013, que foram publicados em diversos veículos da imprensa brasileira', 'Olavo Retardado', 'Terra plana', 'Autodestruição', '2021-11-21 05:45:30', '2021-11-21 05:46:04'),
	(4, 'O código dá 20: print(15 + 5);', 'É um código simples que imprime 20 no console', 'Dan Brown', 'CIência Moderna', 'TI', '2021-11-21 05:48:32', '2021-11-21 05:48:32');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
