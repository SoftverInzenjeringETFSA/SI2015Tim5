-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: si2015tim5
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jelo`
--

LOCK TABLES `jelo` WRITE;
/*!40000 ALTER TABLE `jelo` DISABLE KEYS */;
INSERT INTO `jelo` VALUES (1,'doner',7,'opis1','0'),(2,'supa',3,'opis1','0'),(3,'palacinci',1.5,'opis1','0'),(4,'hot dog',3,'opis1','0'),(5,'pizza',6,'opis1','0'),(6,'tunjevina',7,'opis1','0'),(7,'sendvic',2,'opis1','0'),(8,'hamburger',3.5,'opis1','1'),(9,'pljeskavice',4,'opis1','0'),(10,'ustipci',5,'opis1','0'),(11,'teletina',10,'opis1','1'),(12,'cevapi',6,'opis1','0'),(13,'proba',12.3,'e','0'),(14,'NOVO',12.4,'NOVO','1'),(15,'vela',12.3,'bla','1'),(16,'em',12.1,'vmv','1'),(17,'novohiljadu',1.234,'pop','1'),(18,'KAFA',1.1,'E','1'),(19,'kafa2',12.345,'ffms','1'),(20,'novojelo',11,'jelo','1'),(21,'novojelo2',2,'f','1'),(22,'radioactive',12.3,'f','1'),(23,'blfomfoamo',2324,'fks','1'),(24,'novakafa',12.3,'meme','1'),(25,'NOVOPROBNOJELO',1.22,'e','1'),(26,'probno2545',12.22,'eme','1'),(27,'eminica23fmfm',11.2,'e','0'),(28,'stooo',12.1112,'meme','0'),(29,'ZASTO ME MRZIS',22,'22','0'),(30,'ne ne ne',23,'23','0'),(31,'231324',12,'e','1'),(32,'blafdf',121,'f','1'),(33,'EMINA3455',2,'e','0'),(34,'zadnje2',12,'EF','0'),(35,'gmsklmg',234.53,'ememe','0'),(36,'Metropolis',100.23,'r','0'),(37,'pupu',12,'kkk','0'),(38,'Neem',12,'e','0'),(39,'bl',12.32,'em','1'),(40,'pita',223.13,'afrikaaa','0');
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
INSERT INTO `kupac` VALUES (0,'adresa10',61111231,'opis kupca 10'),(1,'adresa1',61111222,'opis kupca 1'),(2,'adresa2',61111223,'opis kupca 2'),(3,'adresa3',61111224,'opis kupca 3'),(4,'adresa4',61111225,'opis kupca 4'),(5,'adresa5',61111226,'opis kupca 5'),(6,'adresa6',61111227,'opis kupca 6'),(7,'adresa7',61111228,'opis kupca 7'),(8,'adresa8',61111229,'opis kupca 8'),(9,'adresa9',61111230,'opis kupca 9'),(10,'bla',123123123,'bla'),(11,'e',123123111,'e');
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
INSERT INTO `narudzba` VALUES (1,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,NULL,2016,'dd'),(2,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,'2016-08-08 00:15:00',0,'dd'),(3,3,1,2,1,NULL,5,'2016-08-08 00:00:00',NULL,NULL,'2016-08-08 00:27:00',0,'dd'),(4,3,1,2,1,NULL,5,'2016-08-08 00:41:00',NULL,NULL,NULL,0,'dd'),(5,35,4,1,2,NULL,5,'2016-05-14 02:24:15',NULL,NULL,'2016-05-14 02:59:15',0,'Test opis prve narudzbe'),(6,16,5,1,2,NULL,5,'2016-05-14 02:35:29',NULL,NULL,NULL,0,'Opis TESTTTTT	'),(7,58.5,6,1,2,NULL,NULL,'2016-05-14 02:40:32',NULL,NULL,NULL,0,'Sve sa svim'),(8,32.4,7,1,2,NULL,NULL,'2016-05-14 02:42:11',NULL,NULL,NULL,0,'OPIS'),(9,9.975,8,1,2,NULL,NULL,'2016-05-14 02:44:15',NULL,NULL,NULL,0,'OPISISISISIIS'),(10,31.5,9,1,2,NULL,NULL,'2016-05-14 02:52:11',NULL,NULL,NULL,0,'ZADNJI TEST'),(11,15.675,10,1,2,NULL,NULL,'2016-05-14 02:55:29',NULL,NULL,NULL,0,'OPISISISISIS'),(12,2.85,10,1,4,NULL,NULL,'2016-05-18 13:43:38',NULL,NULL,NULL,0,'bla		'),(13,4.275,11,1,4,NULL,NULL,'2016-05-18 13:52:28',NULL,NULL,NULL,0,'e');
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
INSERT INTO `narudzbajeloveza` VALUES (1,2,1,2),(2,2,2,2),(3,2,3,2),(4,2,4,2),(5,2,5,2),(6,2,6,2),(7,4,6,3),(8,1,7,3),(9,4,7,5),(10,5,7,5),(11,3,8,3),(12,2,9,3),(13,1,10,5),(14,2,11,3),(15,4,11,2),(16,3,11,2),(17,3,12,2),(18,3,13,3);
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
INSERT INTO `popust` VALUES (1,0,9,12),(2,10,50,0),(3,20,29,0),(4,30,39,0),(5,40,49,0),(6,50,10000,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojak`
--

LOCK TABLES `sastojak` WRITE;
/*!40000 ALTER TABLE `sastojak` DISABLE KEYS */;
INSERT INTO `sastojak` VALUES (1,'meso','telece','g',0),(2,'krastavac',NULL,'kriska',0),(3,'kupus',NULL,'list',0),(4,'somun',NULL,'kom',0),(5,'paprika',NULL,'kom',0),(6,'luk','opis1','gproba',0),(7,'paradajz',NULL,'kriska',1),(8,'majoneza2','g2','opis',0),(9,'kecap',NULL,'g',0),(10,'cilli','cili	','cili',0),(11,'biber',NULL,'g',1),(12,'novi','em','em',0),(13,'bla','bla','bla',1),(14,'eminin2','em','em',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojci_jelo_veza`
--

LOCK TABLES `sastojci_jelo_veza` WRITE;
/*!40000 ALTER TABLE `sastojci_jelo_veza` DISABLE KEYS */;
INSERT INTO `sastojci_jelo_veza` VALUES (1,1,5,0),(2,1,6,0),(347,2,1,0),(349,27,7,0),(350,27,7,0),(351,2,7,0),(352,2,11,0),(353,1,7,0),(354,1,11,0),(355,5,7,0),(356,29,7,0),(357,33,1,0),(358,4,1,0),(359,4,2,0),(360,4,3,0),(363,10,1,1),(364,13,1,5555555),(365,13,2,2.3),(366,35,1,0),(367,35,6,3.4),(368,35,8,3.33),(369,35,4,2.4),(370,29,1,1.23),(371,29,5,4.223),(372,6,2,0),(373,6,8,2.3),(374,6,10,3.22),(375,6,12,2.2),(376,6,13,1.1),(377,3,1,32.2),(378,39,1,123),(379,39,2,0),(380,39,3,0),(381,39,4,0),(382,39,5,0),(383,39,6,0),(384,39,8,0),(385,39,9,0),(386,39,10,0),(387,39,12,0),(388,39,13,0),(389,40,1,12.3),(390,40,2,4343.3),(391,40,3,4.4),(392,40,4,0),(393,40,5,0),(394,40,6,0),(395,40,8,3.44),(396,40,9,0),(397,40,10,0),(398,40,12,3.2),(399,40,13,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik`
--

LOCK TABLES `zaposlenik` WRITE;
/*!40000 ALTER TABLE `zaposlenik` DISABLE KEYS */;
INSERT INTO `zaposlenik` VALUES (1,'aissejfoi','2deb000b57bfac9d72c14d4ed967b572','05/05/2015',4,'ime',''),(2,'dzanafer','397daf114e82af135fec79a261944588','24/12/1992',1,'Dzana Feratovic',''),(3,'ivona','12b41c761b41698d39ef68fdd9429578','15/12/2009',3,'Ivona Ivkovic',''),(4,'admira','5f02422df1dcdb200d2d61d389c8bedf','12/12/1997',2,'Ivona Ivkovic',''),(5,'emina','emina','1994-12-16',8,'Emina Huskic',NULL),(6,'emi','ddd687b467cebcff9b5394fd2ddd746e','12/12/1222',5,'Emina','m'),(7,'eminica','75feda1a9f18c061f41c5a1f8e6bc698','12/12/1222',1,'eminica','e');
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

-- Dump completed on 2016-05-18 15:50:14
