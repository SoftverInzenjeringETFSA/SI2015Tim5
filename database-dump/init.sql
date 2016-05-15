CREATE DATABASE  IF NOT EXISTS `si2015tim5` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `si2015tim5`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: si2015tim5
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jelo`
--

LOCK TABLES `jelo` WRITE;
/*!40000 ALTER TABLE `jelo` DISABLE KEYS */;
INSERT INTO `jelo` VALUES (1,'doner',7,'opis1',NULL), (2,'supa',3,'opis1',NULL), (3,'palacinci',1.50,'opis1',NULL), (4,'hot dog',2,'opis1',NULL), (5,'pizza',6,'opis1',NULL), (6,'tunjevina',7,'opis1',NULL), (7,'sendvic',2,'opis1',NULL), (8,'hamburger',3.50,'opis1',NULL), (9,'pljeskavice',4,'opis1',NULL), (10,'ustipci',5,'opis1',NULL), (11,'teletina',10,'opis1',NULL), (12,'cevapi',6,'opis1',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupac`
--

LOCK TABLES `kupac` WRITE;
/*!40000 ALTER TABLE `kupac` DISABLE KEYS */;
INSERT INTO `kupac` VALUES (1,'adresa1',061111222,'opis kupca 1'), (2,'adresa2',061111223,'opis kupca 2'),(3,'adresa3',061111224,'opis kupca 3'),(4,'adresa4',061111225,'opis kupca 4'),(5,'adresa5',061111226,'opis kupca 5'),(6,'adresa6',061111227,'opis kupca 6'),(7,'adresa7',061111228,'opis kupca 7'),(8,'adresa8',061111229,'opis kupca 8'),(9,'adresa9',061111230,'opis kupca 9'),(0,'adresa10',061111231,'opis kupca 10');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzba`
--

LOCK TABLES `narudzba` WRITE;
/*!40000 ALTER TABLE `narudzba` DISABLE KEYS */;
INSERT INTO `narudzba` VALUES (1,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,NULL,'2016-08-08 00:07:00','dd'),(2,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,'2016-08-08 00:15:00',0,'dd'),(3,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,'2016-08-08 00:27:00',0,'dd'),(4,3,1,2,1,NULL,5,'2016-08-08 00:41:00',NULL,NULL,NULL,0,'dd'),(5,35,4,1,2,NULL,5,'2016-05-14 02:24:15',NULL,NULL,'2016-05-14 02:59:15',0,'Test opis prve narudzbe'),(6,16,5,1,2,NULL,5,'2016-05-14 02:35:29',NULL,NULL,NULL,0,'Opis TESTTTTT	'),(7,58.5,6,1,2,NULL,NULL,'2016-05-14 02:40:32',NULL,NULL,NULL,0,'Sve sa svim'),(8,32.4,7,1,2,NULL,NULL,'2016-05-14 02:42:11',NULL,NULL,NULL,0,'OPIS'),(9,9.975,8,1,2,NULL,NULL,'2016-05-14 02:44:15',NULL,NULL,NULL,0,'OPISISISISIIS'),(10,31.5,9,1,2,NULL,NULL,'2016-05-14 02:52:11',NULL,NULL,NULL,0,'ZADNJI TEST'),(11,15.675,10,1,2,NULL,NULL,'2016-05-14 02:55:29',NULL,NULL,NULL,0,'OPISISISISIS');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbajeloveza`
--

LOCK TABLES `narudzbajeloveza` WRITE;
/*!40000 ALTER TABLE `narudzbajeloveza` DISABLE KEYS */;
INSERT INTO `narudzbajeloveza` VALUES (1,2,1,2),(2,2,2,2),(3,2,3,2),(4,2,4,2),(5,2,5,2),(6,2,6,2),(7,4,6,3),(8,1,7,3),(9,4,7,5),(10,5,7,5),(11,3,8,3),(12,2,9,3),(13,1,10,5),(14,2,11,3),(15,4,11,2),(16,3,11,2);
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
  `iznos` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popust`
--

LOCK TABLES `popust` WRITE;
/*!40000 ALTER TABLE `popust` DISABLE KEYS */;
INSERT INTO `popust` VALUES (1,10,30,5),(2,30,50,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radnomjesto`
--

LOCK TABLES `radnomjesto` WRITE;
/*!40000 ALTER TABLE `radnomjesto` DISABLE KEYS */;
INSERT INTO `radnomjesto` VALUES (1,'sef','ad'),(2,'primalac','ad'),(3,'kuhar','ad'),(4,'dostavljac','ad');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojak`
--

LOCK TABLES `sastojak` WRITE;
/*!40000 ALTER TABLE `sastojak` DISABLE KEYS */;
INSERT INTO `sastojak` VALUES (1,'meso', 'telece', 'g'),(2,'krastavac', NULL, 'kriska'),(3,'kupus', NULL, 'list'),(4,'somun', NULL, 'kom'),(5,'paprika', NULL, 'kom'),(6,'luk', NULL, 'g'),(7,'paradajz', NULL, 'kriska'),(8,'majoneza', NULL, 'g'),(9,'kecap', NULL, 'g'),(10,'cilli', NULL, 'g'),(11,'biber', NULL, 'g');
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
  `kolicina` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Sastojci_Jelo_Veza_Jelo_index` (`JeloId`),
  KEY `fk_Sastojci_Jelo_Veza_Sastojak_index` (`SastojakId`),
  CONSTRAINT `fk_Sastojci_Jelo_Veza_Jelo` FOREIGN KEY (`JeloId`) REFERENCES `jelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sastojci_Jelo_Veza_Sastojak` FOREIGN KEY (`SastojakId`) REFERENCES `sastojak` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `opis` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastus`
--

LOCK TABLES `sastus` WRITE;
/*!40000 ALTER TABLE `sastus` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik`
--

LOCK TABLES `zaposlenik` WRITE;
/*!40000 ALTER TABLE `zaposlenik` DISABLE KEYS */;
INSERT INTO `zaposlenik` VALUES (1,'aissejfoi','ads','2014-5-5',1,NULL,NULL),(2,'dzanafer','pw','1994-12-25',1,'Dzana Feratovic',NULL),(3,'ivona','ivona','1994-12-16',2,'Ivona Ivkovic',NULL),(4,'admira','admira','1994-12-16',3,'Ivona Ivkovic',NULL),(5,'emina','emina','1994-12-16',4,'Emina Huskic',NULL);
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

-- Dump completed on 2016-05-15 14:50:53
