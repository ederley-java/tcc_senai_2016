-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: agenda
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id_evento` int(11) NOT NULL AUTO_INCREMENT,
  `titulo_evento` varchar(45) DEFAULT NULL,
  `inicio_evento` datetime DEFAULT NULL,
  `fim_evento` datetime DEFAULT NULL,
  `status_evento` tinyint(1) DEFAULT NULL,
  `desc_evento` text,
  PRIMARY KEY (`id_evento`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'Teste','2016-04-30 00:00:00','2016-04-30 00:00:00',1,'Teste'),(2,'ww','2016-05-05 22:00:00','2016-05-08 05:09:00',1,'ww'),(3,'rr','2016-05-06 20:30:00','2016-05-06 22:30:00',1,'rr'),(4,'Ederley','2016-05-07 10:00:00','2016-05-07 11:00:00',0,'Cabelo'),(5,'','2016-05-15 00:00:00','2016-05-15 00:00:00',0,''),(6,'Rr','2016-05-03 10:00:00','2016-05-03 14:00:00',0,'Yy'),(7,'Evento dia 13','2016-05-07 10:00:00','2016-05-07 12:00:00',0,'Evento 13'),(8,'Reunião Condominio','2016-05-03 06:30:00','2016-05-03 06:30:00',0,'Reunião geral'),(9,'rrrrr','2016-05-06 00:00:00','2016-05-06 00:00:00',1,'rrrrr'),(10,'yyyyg','2016-05-05 00:00:00','2016-05-05 00:00:00',1,'xx'),(11,'333333','2016-05-19 10:00:00','2016-05-19 10:00:00',1,'333333'),(12,'','2016-05-21 10:00:00','2016-05-21 11:00:00',1,''),(13,'ww','2016-05-12 14:00:00','2016-05-12 16:00:00',0,'jkjkj'),(14,'','2016-05-12 11:30:00','2016-05-12 11:30:00',0,''),(15,'','2016-04-28 09:30:00','2016-04-28 09:30:00',0,''),(16,'','2016-05-04 16:30:00','2016-05-04 16:30:00',0,''),(17,'Curso Senai','2016-05-20 12:30:00','2016-05-20 12:30:00',0,'Aula de Hibernat'),(18,'sssssssss','2016-05-21 17:00:00','2016-05-21 16:30:00',1,'ssssssssssss'),(19,'ll','2016-04-28 01:00:00','2016-04-30 01:00:00',0,'ll'),(20,'','2016-05-20 10:00:00','2016-05-20 10:00:00',0,''),(21,'fdasf','2016-06-23 01:00:00','2016-06-23 01:00:00',0,'adsfas'),(22,'','2016-06-23 07:00:00','2016-06-23 07:00:00',0,''),(23,'','2016-06-23 06:00:00','2016-06-23 06:00:00',0,'');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `id_servico` int(11) NOT NULL AUTO_INCREMENT,
  `id_evento` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id_servico`),
  KEY `id_evento` (`id_evento`),
  CONSTRAINT `servico_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-23 20:14:24
