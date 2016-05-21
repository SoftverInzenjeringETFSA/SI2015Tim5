CREATE DATABASE  IF NOT EXISTS `tim5` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tim5`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: tim5
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `jelo`
--

DROP TABLE IF EXISTS `jelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jelo` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `cijena` double NOT NULL,
  `opis` varchar(100) DEFAULT NULL,
  `izbrisano` varchar(45) DEFAULT NULL,
  `slika` mediumblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jelo`
--

LOCK TABLES `jelo` WRITE;
/*!40000 ALTER TABLE `jelo` DISABLE KEYS */;
/*!40000 ALTER TABLE `jelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupac`
--

DROP TABLE IF EXISTS `kupac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kupac` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(50) DEFAULT NULL,
  `brojTelefona` int(9) DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `OsobaId_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupac`
--

LOCK TABLES `kupac` WRITE;
/*!40000 ALTER TABLE `kupac` DISABLE KEYS */;
/*!40000 ALTER TABLE `kupac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzba`
--

DROP TABLE IF EXISTS `narudzba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzba` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cijena` double NOT NULL,
  `KupacOsobaId` int(10) NOT NULL,
  `status` int(10) NOT NULL,
  `ZaposlenikOsobaId_Primalac` int(10) NOT NULL,
  `ZaposlenikOsobaId_Kuhar` int(10) DEFAULT NULL,
  `ZaposlenikOsobaId_Dostavljac` int(10) DEFAULT NULL,
  `vrijemePrijema` datetime NOT NULL,
  `vrijemePocetkaPripreme` datetime DEFAULT NULL,
  `vrijemePreuzimanja` datetime DEFAULT NULL,
  `vrijemeDostave` datetime DEFAULT NULL,
  `novacaDostavljeno` double NOT NULL,
  `opis` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Narudzba_Kupac_idx` (`KupacOsobaId`),
  KEY `fk_Narudzba_Kuhar_idx` (`ZaposlenikOsobaId_Kuhar`),
  KEY `fk_Narudzba_Dostavljac_idx` (`ZaposlenikOsobaId_Dostavljac`),
  KEY `fk_Narudzba_Primalac_idx` (`ZaposlenikOsobaId_Primalac`),
  CONSTRAINT `fk_Narudzba_Dostavljac` FOREIGN KEY (`ZaposlenikOsobaId_Dostavljac`) REFERENCES `zaposlenik` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_Kuhar` FOREIGN KEY (`ZaposlenikOsobaId_Kuhar`) REFERENCES `zaposlenik` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_Kupac` FOREIGN KEY (`KupacOsobaId`) REFERENCES `kupac` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_Primalac` FOREIGN KEY (`ZaposlenikOsobaId_Primalac`) REFERENCES `zaposlenik` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzba`
--

LOCK TABLES `narudzba` WRITE;
/*!40000 ALTER TABLE `narudzba` DISABLE KEYS */;
/*!40000 ALTER TABLE `narudzba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbajeloveza`
--

DROP TABLE IF EXISTS `narudzbajeloveza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzbajeloveza` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `JeloId` int(3) NOT NULL,
  `NarudzbaId` int(10) NOT NULL,
  `kolicina` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Narudžba_Jelo_Veza_Jelo_index` (`JeloId`),
  KEY `fk_Narudžba_Jelo_Veza_Narudžba_index` (`NarudzbaId`),
  CONSTRAINT `fk_Narudzba_Jelo_Veza_Jelo` FOREIGN KEY (`JeloId`) REFERENCES `jelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudžba_Jelo_Veza_Narudžba` FOREIGN KEY (`NarudzbaId`) REFERENCES `narudzba` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbajeloveza`
--

LOCK TABLES `narudzbajeloveza` WRITE;
/*!40000 ALTER TABLE `narudzbajeloveza` DISABLE KEYS */;
/*!40000 ALTER TABLE `narudzbajeloveza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popust`
--

DROP TABLE IF EXISTS `popust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `popust` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `od` double NOT NULL,
  `do` double NOT NULL,
  `iznos` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popust`
--

LOCK TABLES `popust` WRITE;
/*!40000 ALTER TABLE `popust` DISABLE KEYS */;
INSERT INTO `popust` VALUES (1,0,9.99,0),(2,10,19.99,3),(3,20,29.99,5),(4,30,39.99,10),(5,40,49.99,15),(6,50,10000,20);
/*!40000 ALTER TABLE `popust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radnomjesto`
--

DROP TABLE IF EXISTS `radnomjesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radnomjesto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `opis` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radnomjesto`
--

LOCK TABLES `radnomjesto` WRITE;
/*!40000 ALTER TABLE `radnomjesto` DISABLE KEYS */;
INSERT INTO `radnomjesto` VALUES (1,'sef','radi'),(2,'primalac','radi'),(3,'kuhar','radi'),(4,'dostavljac','radi'),(5,'ex-sef','otpusten'),(6,'ex-primalac','otpusten'),(7,'ex-kuhar','otpusten'),(8,'ex-dostavljac','otpusten');
/*!40000 ALTER TABLE `radnomjesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sastojak`
--

DROP TABLE IF EXISTS `sastojak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sastojak` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `opis` varchar(500) DEFAULT NULL,
  `mjernaJedinica` varchar(10) NOT NULL,
  `izbrisan` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojak`
--

LOCK TABLES `sastojak` WRITE;
/*!40000 ALTER TABLE `sastojak` DISABLE KEYS */;
/*!40000 ALTER TABLE `sastojak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sastojci_jelo_veza`
--

DROP TABLE IF EXISTS `sastojci_jelo_veza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sastojci_jelo_veza` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `JeloId` int(3) NOT NULL,
  `SastojakId` int(10) NOT NULL,
  `kolicina` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Sastojci_Jelo_Veza_Jelo_index` (`JeloId`),
  KEY `fk_Sastojci_Jelo_Veza_Sastojak_index` (`SastojakId`),
  CONSTRAINT `fk_Sastojci_Jelo_Veza_Jelo` FOREIGN KEY (`JeloId`) REFERENCES `jelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sastojci_Jelo_Veza_Sastojak` FOREIGN KEY (`SastojakId`) REFERENCES `sastojak` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojci_jelo_veza`
--

LOCK TABLES `sastojci_jelo_veza` WRITE;
/*!40000 ALTER TABLE `sastojci_jelo_veza` DISABLE KEYS */;
/*!40000 ALTER TABLE `sastojci_jelo_veza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sastus`
--

DROP TABLE IF EXISTS `sastus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sastus` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `opis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastus`
--

LOCK TABLES `sastus` WRITE;
/*!40000 ALTER TABLE `sastus` DISABLE KEYS */;
INSERT INTO `sastus` VALUES (1,'Preuzeta od primaoca'),(2,'Preuzeta od kuhara'),(3,'Spremna za dostavljaca'),(4,'Preuzeta od dostavljaca'),(5,'Dostavljena');
/*!40000 ALTER TABLE `sastus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposlenik`
--

DROP TABLE IF EXISTS `zaposlenik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zaposlenik` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `datumRodenja` varchar(50) DEFAULT NULL,
  `radnoMjesto` int(10) NOT NULL,
  `imePrezime` varchar(20) DEFAULT NULL,
  `dodatneInformacije` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `OsobaId_UNIQUE` (`id`),
  KEY `fk_Radno_Mjesto_Zaposlenik_index` (`radnoMjesto`),
  CONSTRAINT `fk_RadnoMjesto_Zaposlenik` FOREIGN KEY (`radnoMjesto`) REFERENCES `radnomjesto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik`
--

LOCK TABLES `zaposlenik` WRITE;
/*!40000 ALTER TABLE `zaposlenik` DISABLE KEYS */;
INSERT INTO `zaposlenik` VALUES (2,'dzanafer','ddd687b467cebcff9b5394fd2ddd746e','24/12/1992',1,'Dzana Feratovic','');
/*!40000 ALTER TABLE `zaposlenik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-21 19:14:07
