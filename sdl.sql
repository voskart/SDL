CREATE DATABASE  IF NOT EXISTS `sdl` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sdl`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: sdl
-- ------------------------------------------------------
-- Server version	5.6.17-log

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
-- Table structure for table `importstones`
--

DROP TABLE IF EXISTS `importstones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `importstones` (
  `id` int(11) NOT NULL,
  `InvNr` varchar(45) DEFAULT NULL,
  `MusId` int(11) DEFAULT NULL,
  `Link` varchar(100) DEFAULT NULL,
  `InvNr2` varchar(100) DEFAULT NULL,
  `Test` varchar(100) DEFAULT NULL,
  `Titel` varchar(100) DEFAULT NULL,
  `Masze` varchar(100) DEFAULT NULL,
  `Hoehe` varchar(100) DEFAULT NULL,
  `Breite` varchar(100) DEFAULT NULL,
  `Tiefe` varchar(100) DEFAULT NULL,
  `Unit` varchar(100) DEFAULT NULL,
  `Material` varchar(100) DEFAULT NULL,
  `MaterialId` varchar(100) DEFAULT NULL,
  `Datierung` varchar(100) DEFAULT NULL,
  `Erdzeitalter` varchar(100) DEFAULT NULL,
  `ErdzeitalterId` varchar(100) DEFAULT NULL,
  `Herkunft` varchar(100) DEFAULT NULL,
  `HerkunftCoord` varchar(100) DEFAULT NULL,
  `HerkunftId` varchar(100) DEFAULT NULL,
  `HerkunftGeoId` varchar(100) DEFAULT NULL,
  `Fundort` varchar(100) DEFAULT NULL,
  `FundortCoord` varchar(100) DEFAULT NULL,
  `FundortId` varchar(100) DEFAULT NULL,
  `FundortGeoId` varchar(100) DEFAULT NULL,
  `Kommentar` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importstones`
--

LOCK TABLES `importstones` WRITE;
/*!40000 ALTER TABLE `importstones` DISABLE KEYS */;
/*!40000 ALTER TABLE `importstones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-09 22:18:36
