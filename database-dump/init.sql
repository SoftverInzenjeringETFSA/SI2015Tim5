SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; 
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; 
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';  




-- ----------------------------------------------------- 
-- Schema SI2015Tim5
-- ----------------------------------------------------- 
 
CREATE SCHEMA IF NOT EXISTS `SI2015Tim5` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ; 
USE `SI2015Tim5` ; 


-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Jelo` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Jelo`( 
`id` INT(3) NOT NULL AUTO_INCREMENT, 
`naziv` VARCHAR(50) NOT NULL,
`cijena` double NOT NULL,
PRIMARY KEY (`id`)) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Jelo` (`id` ASC);  
CREATE UNIQUE INDEX `naziv_UNIQUE` ON `SI2015Tim5`.`Jelo` (`naziv` ASC); 



-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Sastojak` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Sastojak`( 
`id` INT(10) NOT NULL AUTO_INCREMENT, 
`naziv` VARCHAR(50) NOT NULL, 
`opis` VARCHAR(500) NULL,  
`mjernaJedinica` VARCHAR(10) NOT NULL,
PRIMARY KEY (`id`)) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Sastojak` (`id` ASC);  
CREATE UNIQUE INDEX `naziv_UNIQUE` ON `SI2015Tim5`.`Sastojak` (`naziv` ASC); 



-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Sastojci_Jelo_Veza` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Sastojci_Jelo_Veza`( 
`JeloId` INT(3) NOT NULL, 
`SastojakId` INT(10) NOT NULL, 
`kolicina` INT(2) NOT NULL,
PRIMARY KEY (`JeloId`, `SastojakId`), 
CONSTRAINT `fk_Sastojci_Jelo_Veza_Jelo` 
FOREIGN KEY (`JeloId`) 
REFERENCES `SI2015Tim5`.`Jelo` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION, 
CONSTRAINT `fk_Sastojci_Jelo_Veza_Sastojak` 
FOREIGN KEY (`SastojakId`) 
REFERENCES `SI2015Tim5`.`Sastojak` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION) 
ENGINE = InnoDB;  
CREATE INDEX `fk_Sastojci_Jelo_Veza_Jelo_index` ON `SI2015Tim5`.`Sastojci_Jelo_Veza`(`JeloId` ASC);  
CREATE INDEX `fk_Sastojci_Jelo_Veza_Sastojak_index` ON `SI2015Tim5`.`Sastojci_Jelo_Veza`(`SastojakId` ASC);






-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Osoba` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS 
`SI2015Tim5`.`Osoba` ( 
`id` INT(10) NOT NULL AUTO_INCREMENT, 
`imePrezime` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Osoba` (`id` ASC);  





-- -----------------------------------------------------
-- Table `SI2015Tim5`.`Kupac` 
-- ----------------------------------------------------- 

CREATE TABLE IF NOT EXISTS 
`SI2015Tim5`.`Kupac`( 
`OsobaId` INT(10) NOT NULL , 
`adresa` VARCHAR(50) NULL,
`brojTelefona` integer(9) NULL,
PRIMARY KEY (`OsobaId`), 
CONSTRAINT `fk_Osoba_Kupac` 
FOREIGN KEY (`OsobaId`) 
REFERENCES `SI2015Tim5`.`Osoba` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `OsobaId_UNIQUE` ON `SI2015Tim5`.`Kupac` (`OsobaId` ASC); 
CREATE INDEX 
`fk_Osoba_Kupac_index` ON `SI2015Tim5`.`Kupac`
(`OsobaId` ASC);  


-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Radno_Mjesto` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS 
`SI2015Tim5`.`Radno_Mjesto` ( 
`id` INT(10) NOT NULL AUTO_INCREMENT, 
`naziv` VARCHAR(50) NOT NULL,
`opis` VARCHAR(500) NULL,
PRIMARY KEY (`id`)) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Radno_Mjesto` (`id` ASC);  




-- -----------------------------------------------------
-- Table `SI2015Tim5`.`Zaposlenik` 
-- ----------------------------------------------------- 

CREATE TABLE IF NOT EXISTS 
`SI2015Tim5`.`Zaposlenik`( 
`OsobaId` INT(10) NOT NULL , 
`username` VARCHAR(50) NOT NULL,
`password` VARCHAR(50) NOT NULL,
`datumRođenja` VARCHAR(50) NULL,
`Radno_MjestoId` INT(10) NOT NULL,
PRIMARY KEY (`OsobaId`,`Radno_MjestoId`), 
CONSTRAINT `fk_Osoba_Zaposlenik` 
FOREIGN KEY (`OsobaId`) 
REFERENCES `SI2015Tim5`.`Osoba` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION,
CONSTRAINT `fk_RadnoMjesto_Zaposlenik` 
FOREIGN KEY (`Radno_MjestoId`) 
REFERENCES `SI2015Tim5`.`Radno_Mjesto` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `OsobaId_UNIQUE` ON `SI2015Tim5`.`Zaposlenik` (`OsobaId` ASC); 
CREATE UNIQUE INDEX `Username_UNIQUE` ON `SI2015Tim5`.`Zaposlenik` (`username` ASC); 
CREATE INDEX 
`fk_Osoba_Zaposlenik_index` ON `SI2015Tim5`.`Zaposlenik`
(`OsobaId` ASC);  
CREATE INDEX 
`fk_Radno_Mjesto_Zaposlenik_index` ON `SI2015Tim5`.`Zaposlenik`
(`Radno_MjestoId` ASC);  




-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Sastus_Narudžbe` 
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Sastus_Narudžbe` ( 
`id` INT(10) NOT NULL AUTO_INCREMENT, 
`opis` VARCHAR(20) NULL,
PRIMARY KEY (`id`)) 
ENGINE = InnoDB;  
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Sastus_Narudžbe` (`id` ASC);  




-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Narudžba`
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Narudžba`( 
`id` INT(10) NOT NULL AUTO_INCREMENT, 
`cijena` INT(10) NOT NULL,
`KupacOsobaId` INT(10) NOT NULL,
`Status_NarudžbeId` INT(10) NOT NULL,
`ZaposlenikOsobaId_Primalac` INT(10) NOT NULL,
`ZaposlenikOsobaId_Kuhar` INT(10) NULL,
`ZaposlenikOsobaId_Dostavljač` INT(10) NULL,
`vrijemePrijema` DATETIME NOT NULL,
`vrijemePočetkaPripreme` DATETIME NULL,
`vrijemePreuzimanja` DATETIME NULL,
`vrijemeDostave` DATETIME NULL,
PRIMARY KEY (`id`), 
CONSTRAINT `fk_Narudžba_Kupac` 
FOREIGN KEY (`KupacOsobaId`) 
REFERENCES `SI2015Tim5`.`Kupac` (`OsobaId`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION, 
CONSTRAINT `fk_Narudžba_Primalac` 
FOREIGN KEY (`ZaposlenikOsobaId_Primalac`) 
REFERENCES `SI2015Tim5`.`Zaposlenik` (`OsobaId`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION,
CONSTRAINT `fk_Narudžba_Kuhar` 
FOREIGN KEY (`ZaposlenikOsobaId_Kuhar`) 
REFERENCES `SI2015Tim5`.`Zaposlenik` (`OsobaId`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION,
CONSTRAINT `fk_Narudžba_Dostavljač` 
FOREIGN KEY (`ZaposlenikOsobaId_Dostavljač`) 
REFERENCES `SI2015Tim5`.`Zaposlenik` (`OsobaId`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION) 
ENGINE = InnoDB; 
CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2015Tim5`.`Narudžba` (`id` ASC); 
CREATE INDEX 
`fk_Narudžba_Kupac_index` ON `SI2015Tim5`.`Narudžba`
(`KupacOsobaId` ASC);  
CREATE INDEX 
`fk_Narudžba_Primalac_index` ON `SI2015Tim5`.`Narudžba`
(`ZaposlenikOsobaId_Primalac` ASC);   
CREATE INDEX 
`fk_Narudžba_Kuhar_index` ON `SI2015Tim5`.`Narudžba`
(`ZaposlenikOsobaId_Kuhar` ASC); 
CREATE INDEX 
`fk_Narudžba_Dostavljač_index` ON `SI2015Tim5`.`Narudžba`
(`ZaposlenikOsobaId_Dostavljač` ASC);   

-- ----------------------------------------------------- 
-- Table `SI2015Tim5`.`Narudžba_Jelo_Veza`
-- ----------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `SI2015Tim5`.`Narudžba_Jelo_Veza`( 
`JeloId` INT(3) NOT NULL, 
`NarudžbaId` INT(10) NOT NULL, 
PRIMARY KEY (`JeloId`, `NarudžbaId`), 
CONSTRAINT `fk_Narudžba_Jelo_Veza_Jelo` 
FOREIGN KEY (`JeloId`) 
REFERENCES `SI2015Tim5`.`Jelo` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION, 
CONSTRAINT `fk_Narudžba_Jelo_Veza_Narudžba` 
FOREIGN KEY (`NarudžbaId`) 
REFERENCES `SI2015Tim5`.`Narudžba` (`id`) 
ON DELETE NO ACTION 
ON UPDATE NO ACTION) 
ENGINE = InnoDB;  
CREATE INDEX 
`fk_Narudžba_Jelo_Veza_Jelo_index` ON `SI2015Tim5`.`Narudžba_Jelo_Veza`
(`JeloId` ASC);  
CREATE INDEX
`fk_Narudžba_Jelo_Veza_Narudžba_index` ON `SI2015Tim5`.`Narudžba_Jelo_Veza`
(`NarudžbaId` ASC) ;




SET SQL_MODE=@OLD_SQL_MODE; 
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; 
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; 
