CREATE DATABASE  IF NOT EXISTS `si2015tim5` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `si2015tim5`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: si2015tim5
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jelo`
--

LOCK TABLES `jelo` WRITE;
/*!40000 ALTER TABLE `jelo` DISABLE KEYS */;
INSERT INTO `jelo` VALUES (1,'Hamburger',3),(2,'Ćevapi - velika porcija',6),(3,'Ćevapi - mala porcija',3);
/*!40000 ALTER TABLE `jelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupac`
--

DROP TABLE IF EXISTS `kupac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kupac` (
  `OsobaId` int(10) NOT NULL,
  `adresa` varchar(50) DEFAULT NULL,
  `brojTelefona` int(9) DEFAULT NULL,
  PRIMARY KEY (`OsobaId`),
  UNIQUE KEY `OsobaId_UNIQUE` (`OsobaId`),
  KEY `fk_Osoba_Kupac_index` (`OsobaId`),
  CONSTRAINT `fk_Osoba_Kupac` FOREIGN KEY (`OsobaId`) REFERENCES `osoba` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupac`
--

LOCK TABLES `kupac` WRITE;
/*!40000 ALTER TABLE `kupac` DISABLE KEYS */;
INSERT INTO `kupac` VALUES (1,'Titova 2',33123456),(2,'Grbavička 10',33654321);
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
  `cijena` int(10) NOT NULL,
  `KupacOsobaId` int(10) NOT NULL,
  `Status_NarudžbeId` int(10) NOT NULL,
  `ZaposlenikOsobaId_Primalac` int(10) NOT NULL,
  `ZaposlenikOsobaId_Kuhar` int(10) DEFAULT NULL,
  `ZaposlenikOsobaId_Dostavljač` int(10) DEFAULT NULL,
  `vrijemePrijema` datetime NOT NULL,
  `vrijemePočetkaPripreme` datetime DEFAULT NULL,
  `vrijemePreuzimanja` datetime DEFAULT NULL,
  `vrijemeDostave` datetime DEFAULT NULL,
  `statusNarudzbe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Narudžba_Kupac_index` (`KupacOsobaId`),
  KEY `fk_Narudžba_Primalac_index` (`ZaposlenikOsobaId_Primalac`),
  KEY `fk_Narudžba_Kuhar_index` (`ZaposlenikOsobaId_Kuhar`),
  KEY `fk_Narudžba_Dostavljač_index` (`ZaposlenikOsobaId_Dostavljač`),
  CONSTRAINT `fk_Narudžba_Dostavljač` FOREIGN KEY (`ZaposlenikOsobaId_Dostavljač`) REFERENCES `zaposlenik` (`OsobaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudžba_Kuhar` FOREIGN KEY (`ZaposlenikOsobaId_Kuhar`) REFERENCES `zaposlenik` (`OsobaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudžba_Kupac` FOREIGN KEY (`KupacOsobaId`) REFERENCES `kupac` (`OsobaId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudžba_Primalac` FOREIGN KEY (`ZaposlenikOsobaId_Primalac`) REFERENCES `zaposlenik` (`OsobaId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzba`
--

LOCK TABLES `narudzba` WRITE;
/*!40000 ALTER TABLE `narudzba` DISABLE KEYS */;
INSERT INTO `narudzba` VALUES (1,9,1,4,5,3,4,'2016-05-05 20:45:00','2016-05-05 20:46:00','2016-05-05 20:59:00','2016-05-05 21:15:00',NULL),(2,6,2,4,5,3,4,'2016-05-05 20:50:00','2016-05-05 20:51:00','2016-05-05 21:05:00','2016-05-05 21:25:00',NULL);
/*!40000 ALTER TABLE `narudzba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzba_jelo_veza`
--

DROP TABLE IF EXISTS `narudzba_jelo_veza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzba_jelo_veza` (
  `JeloId` int(3) NOT NULL,
  `NarudžbaId` int(10) NOT NULL,
  PRIMARY KEY (`JeloId`,`NarudžbaId`),
  KEY `fk_Narudžba_Jelo_Veza_Jelo_index` (`JeloId`),
  KEY `fk_Narudžba_Jelo_Veza_Narudžba_index` (`NarudžbaId`),
  CONSTRAINT `fk_Narudžba_Jelo_Veza_Jelo` FOREIGN KEY (`JeloId`) REFERENCES `jelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudžba_Jelo_Veza_Narudžba` FOREIGN KEY (`NarudžbaId`) REFERENCES `narudzba` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzba_jelo_veza`
--

LOCK TABLES `narudzba_jelo_veza` WRITE;
/*!40000 ALTER TABLE `narudzba_jelo_veza` DISABLE KEYS */;
INSERT INTO `narudzba_jelo_veza` VALUES (1,1),(1,2),(2,1),(3,2);
/*!40000 ALTER TABLE `narudzba_jelo_veza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osoba` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(50) NOT NULL,
  `DISCRIMINATOR` varchar(255) NOT NULL,
  `OsobaId` bigint(20) DEFAULT NULL,
  `adresa` varchar(255) DEFAULT NULL,
  `brojTelefona` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `datumRođenja` datetime DEFAULT NULL,
  `Radno_MjestoId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
INSERT INTO `osoba` VALUES (1,'Merisa Golić','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Emina Huskić','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Admira Husić','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'Ivona Ivković','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Džana Feratović','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Arnela Duzan','',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radno_mjesto`
--

DROP TABLE IF EXISTS `radno_mjesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radno_mjesto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `opis` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radno_mjesto`
--

LOCK TABLES `radno_mjesto` WRITE;
/*!40000 ALTER TABLE `radno_mjesto` DISABLE KEYS */;
INSERT INTO `radno_mjesto` VALUES (1,'radnik na telefonu','osoba se javlja na telefon pri pozivu, te komunicira sa kupcem i unosi podatke o narudžbi u računar'),(2,'kuhar','osoba priprema jela prema naružbi'),(3,'dostavljač','osoba dostavlja pripremljene narudžbe na adresu kupca'),(4,'sef','osoba zaduzena za upravljanje sistemom za telefonske narudžbe');
/*!40000 ALTER TABLE `radno_mjesto` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sastojak`
--

LOCK TABLES `sastojak` WRITE;
/*!40000 ALTER TABLE `sastojak` DISABLE KEYS */;
INSERT INTO `sastojak` VALUES (1,'pljeskvica','pljeskavica od mljevenog mesa','komad'),(2,'salata','zelena salata','list'),(3,'zemička','orkuglo pecivo','komad'),(4,'kečap','paradajz sos','gram'),(5,'ćevap','ćevap od mljeveog mesa','komad'),(6,'somun','vrsta okruglog hljeba','pola kom');
/*!40000 ALTER TABLE `sastojak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sastojci_jelo_veza`
--

DROP TABLE IF EXISTS `sastojci_jelo_veza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sastojci_jelo_veza` (
  `JeloId` int(3) NOT NULL,
  `SastojakId` int(10) NOT NULL,
  `kolicina` int(2) NOT NULL,
  PRIMARY KEY (`JeloId`,`SastojakId`),
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
INSERT INTO `sastojci_jelo_veza` VALUES (1,1,1),(1,2,1),(1,3,1),(1,4,30),(2,5,10),(2,6,2),(3,5,5),(3,6,1);
/*!40000 ALTER TABLE `sastojci_jelo_veza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_narudzbe`
--

DROP TABLE IF EXISTS `status_narudzbe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_narudzbe` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `opis` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_narudzbe`
--

LOCK TABLES `status_narudzbe` WRITE;
/*!40000 ALTER TABLE `status_narudzbe` DISABLE KEYS */;
INSERT INTO `status_narudzbe` VALUES (1,'primljena'),(2,'uPripremi'),(3,'pripremljena'),(4,'naDostavi'),(5,'dostavljena');
/*!40000 ALTER TABLE `status_narudzbe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposlenik`
--

DROP TABLE IF EXISTS `zaposlenik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zaposlenik` (
  `OsobaId` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `datumRođenja` varchar(50) DEFAULT NULL,
  `Radno_MjestoId` int(10) NOT NULL,
  PRIMARY KEY (`OsobaId`,`Radno_MjestoId`),
  UNIQUE KEY `OsobaId_UNIQUE` (`OsobaId`),
  UNIQUE KEY `Username_UNIQUE` (`username`),
  KEY `fk_Osoba_Zaposlenik_index` (`OsobaId`),
  KEY `fk_Radno_Mjesto_Zaposlenik_index` (`Radno_MjestoId`),
  CONSTRAINT `fk_Osoba_Zaposlenik` FOREIGN KEY (`OsobaId`) REFERENCES `osoba` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_RadnoMjesto_Zaposlenik` FOREIGN KEY (`Radno_MjestoId`) REFERENCES `radno_mjesto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik`
--

LOCK TABLES `zaposlenik` WRITE;
/*!40000 ALTER TABLE `zaposlenik` DISABLE KEYS */;
INSERT INTO `zaposlenik` VALUES (3,'admira_kuhar','admirakuhar','01.01.1994.',2),(4,'ivona_dostavljac','ivonadostavljac','01.01.1994.',3),(5,'dzana_operater','dzanaoperater','01.01.1994.',1);
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

-- Dump completed on 2016-05-11 10:52:26
