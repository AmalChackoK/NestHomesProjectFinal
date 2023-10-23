-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.11-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema nest
--

CREATE DATABASE IF NOT EXISTS nest;
USE nest;

--
-- Definition of table `dailyhelp`
--

DROP TABLE IF EXISTS `dailyhelp`;
CREATE TABLE `dailyhelp` (
  `dh_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dc_id` int(10) unsigned NOT NULL,
  `dh_name` varchar(45) NOT NULL,
  `dh_address` varchar(45) NOT NULL,
  `dh_phone` varchar(45) NOT NULL,
  PRIMARY KEY (`dh_id`),
  KEY `FK_dailyhelp_1` (`dc_id`),
  CONSTRAINT `FK_dailyhelp_1` FOREIGN KEY (`dc_id`) REFERENCES `dailyhelpcategory` (`dc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dailyhelp`
--

/*!40000 ALTER TABLE `dailyhelp` DISABLE KEYS */;
/*!40000 ALTER TABLE `dailyhelp` ENABLE KEYS */;


--
-- Definition of table `dailyhelpcategory`
--

DROP TABLE IF EXISTS `dailyhelpcategory`;
CREATE TABLE `dailyhelpcategory` (
  `dc_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dc_category` varchar(45) NOT NULL,
  PRIMARY KEY (`dc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dailyhelpcategory`
--

/*!40000 ALTER TABLE `dailyhelpcategory` DISABLE KEYS */;
INSERT INTO `dailyhelpcategory` (`dc_id`,`dc_category`) VALUES 
 (5,'COOK');
/*!40000 ALTER TABLE `dailyhelpcategory` ENABLE KEYS */;


--
-- Definition of table `facility`
--

DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `fm_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fm_name` varchar(45) NOT NULL,
  `fm_des` varchar(45) NOT NULL,
  `fm_duty` varchar(45) NOT NULL,
  `fm_phone` varchar(45) NOT NULL,
  PRIMARY KEY (`fm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `facility`
--

/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
INSERT INTO `facility` (`fm_id`,`fm_name`,`fm_des`,`fm_duty`,`fm_phone`) VALUES 
 (2,'amal','cook','cooking','8592897285');
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;


--
-- Definition of table `flats`
--

DROP TABLE IF EXISTS `flats`;
CREATE TABLE `flats` (
  `f_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `f_number` varchar(45) NOT NULL,
  `f_tower` varchar(45) NOT NULL,
  `f_type` varchar(45) NOT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `flats`
--

/*!40000 ALTER TABLE `flats` DISABLE KEYS */;
INSERT INTO `flats` (`f_id`,`f_number`,`f_tower`,`f_type`) VALUES 
 (2,'27B','FIRST','3BHK'),
 (4,'22B','FLOWERS','3BHK'),
 (5,'21A','ROYAL','1BHK'),
 (6,'28','ROSY','2BHK');
/*!40000 ALTER TABLE `flats` ENABLE KEYS */;


--
-- Definition of table `gasdue`
--

DROP TABLE IF EXISTS `gasdue`;
CREATE TABLE `gasdue` (
  `gd_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `f_id` int(10) unsigned NOT NULL,
  `gd_due` double NOT NULL,
  PRIMARY KEY (`gd_id`),
  KEY `FK_gasdue_1` (`f_id`),
  CONSTRAINT `FK_gasdue_1` FOREIGN KEY (`f_id`) REFERENCES `flats` (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gasdue`
--

/*!40000 ALTER TABLE `gasdue` DISABLE KEYS */;
INSERT INTO `gasdue` (`gd_id`,`f_id`,`gd_due`) VALUES 
 (7,4,450),
 (8,6,40);
/*!40000 ALTER TABLE `gasdue` ENABLE KEYS */;


--
-- Definition of table `gasrate`
--

DROP TABLE IF EXISTS `gasrate`;
CREATE TABLE `gasrate` (
  `g_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `g_rate` int(10) unsigned NOT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gasrate`
--

/*!40000 ALTER TABLE `gasrate` DISABLE KEYS */;
INSERT INTO `gasrate` (`g_id`,`g_rate`) VALUES 
 (2,45);
/*!40000 ALTER TABLE `gasrate` ENABLE KEYS */;


--
-- Definition of table `gasusage`
--

DROP TABLE IF EXISTS `gasusage`;
CREATE TABLE `gasusage` (
  `gu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `f_id` int(10) unsigned NOT NULL,
  `gu_unit` double NOT NULL,
  PRIMARY KEY (`gu_id`),
  KEY `FK_gasusage_1` (`f_id`),
  CONSTRAINT `FK_gasusage_1` FOREIGN KEY (`f_id`) REFERENCES `flats` (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gasusage`
--

/*!40000 ALTER TABLE `gasusage` DISABLE KEYS */;
INSERT INTO `gasusage` (`gu_id`,`f_id`,`gu_unit`) VALUES 
 (19,4,10),
 (20,6,10);
/*!40000 ALTER TABLE `gasusage` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `lo_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lo_username` varchar(45) NOT NULL,
  `lo_password` varchar(45) NOT NULL,
  `lo_type` varchar(45) NOT NULL,
  PRIMARY KEY (`lo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`lo_id`,`lo_username`,`lo_password`,`lo_type`) VALUES 
 (30,'AMAL','amal','ADMIN'),
 (31,'ROYAL','royal','ACCOUNT');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `owner`
--

DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `o_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `o_name` varchar(45) NOT NULL,
  `o_address` text NOT NULL,
  `o_phone` varchar(45) NOT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `owner`
--

/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` (`o_id`,`o_name`,`o_address`,`o_phone`) VALUES 
 (2,'AMAL','kuruthukulangara(h)\r\npo.kandassankadavu\r\nthrissur\r\n680613','8592897285'),
 (3,'AMAL','kuruthukulangarahouse','123456'),
 (4,'ROYAL','calicut','8137866866'),
 (5,'ANANDAKRISHNAN','kakkanade','9495150473');
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;


--
-- Definition of table `ownership`
--

DROP TABLE IF EXISTS `ownership`;
CREATE TABLE `ownership` (
  `os_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `o_id` int(10) unsigned NOT NULL,
  `f_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`os_id`),
  KEY `FK_ownership_1` (`o_id`),
  KEY `FK_ownership_2` (`f_id`),
  CONSTRAINT `FK_ownership_1` FOREIGN KEY (`o_id`) REFERENCES `owner` (`o_id`),
  CONSTRAINT `FK_ownership_2` FOREIGN KEY (`f_id`) REFERENCES `flats` (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ownership`
--

/*!40000 ALTER TABLE `ownership` DISABLE KEYS */;
INSERT INTO `ownership` (`os_id`,`o_id`,`f_id`) VALUES 
 (5,4,4);
/*!40000 ALTER TABLE `ownership` ENABLE KEYS */;


--
-- Definition of table `rental`
--

DROP TABLE IF EXISTS `rental`;
CREATE TABLE `rental` (
  `r_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `f_id` int(10) unsigned NOT NULL,
  `t_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `FK_rental_1` (`f_id`),
  KEY `FK_rental_2` (`t_id`),
  CONSTRAINT `FK_rental_1` FOREIGN KEY (`f_id`) REFERENCES `flats` (`f_id`),
  CONSTRAINT `FK_rental_2` FOREIGN KEY (`t_id`) REFERENCES `tenant` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rental`
--

/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` (`r_id`,`f_id`,`t_id`) VALUES 
 (3,4,1),
 (4,2,3),
 (5,5,3);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;


--
-- Definition of table `socitydaue`
--

DROP TABLE IF EXISTS `socitydaue`;
CREATE TABLE `socitydaue` (
  `sd_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sdc_id` int(10) unsigned NOT NULL,
  `f_id` int(10) unsigned NOT NULL,
  `sd_date` date NOT NULL,
  `sd_total` double NOT NULL,
  `sd_payed` double NOT NULL,
  PRIMARY KEY (`sd_id`),
  KEY `FK_socitydaue_1` (`sdc_id`),
  KEY `FK_socitydaue_2` (`f_id`),
  CONSTRAINT `FK_socitydaue_1` FOREIGN KEY (`sdc_id`) REFERENCES `socityduescat` (`sdc_id`),
  CONSTRAINT `FK_socitydaue_2` FOREIGN KEY (`f_id`) REFERENCES `flats` (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `socitydaue`
--

/*!40000 ALTER TABLE `socitydaue` DISABLE KEYS */;
INSERT INTO `socitydaue` (`sd_id`,`sdc_id`,`f_id`,`sd_date`,`sd_total`,`sd_payed`) VALUES 
 (1,1,6,'2023-07-26',400,450),
 (2,1,6,'2023-07-26',10,50);
/*!40000 ALTER TABLE `socitydaue` ENABLE KEYS */;


--
-- Definition of table `socityduescat`
--

DROP TABLE IF EXISTS `socityduescat`;
CREATE TABLE `socityduescat` (
  `sdc_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sdc_cat` varchar(45) NOT NULL,
  PRIMARY KEY (`sdc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `socityduescat`
--

/*!40000 ALTER TABLE `socityduescat` DISABLE KEYS */;
INSERT INTO `socityduescat` (`sdc_id`,`sdc_cat`) VALUES 
 (1,'gas');
/*!40000 ALTER TABLE `socityduescat` ENABLE KEYS */;


--
-- Definition of table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `t_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `t_name` varchar(45) NOT NULL,
  `t_address` varchar(45) NOT NULL,
  `t_phone` varchar(45) NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tenant`
--

/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` (`t_id`,`t_name`,`t_address`,`t_phone`) VALUES 
 (1,'AMAL','kuruthukulangara(h)','8592897285'),
 (3,'AMAL','khglijo','123456789');
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
