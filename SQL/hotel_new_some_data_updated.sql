-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_new
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `booking_date` datetime NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `period` int NOT NULL,
  `guest_id` int NOT NULL,
  `bill` int NOT NULL,
  `room_type_name` varchar(20) NOT NULL,
  `hotel_id` int NOT NULL,
  `room_number` int NOT NULL,
  PRIMARY KEY (`booking_id`,`guest_id`),
  KEY `fk_Booking_guest1_idx` (`guest_id`),
  KEY `fk_booking_room1_idx` (`room_number`,`room_type_name`,`hotel_id`),
  CONSTRAINT `fk_Booking_guest1` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`guest_id`),
  CONSTRAINT `fk_booking_room1` FOREIGN KEY (`room_number`, `room_type_name`, `hotel_id`) REFERENCES `room` (`room_number`, `room_type_name`, `hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2020-02-20 20:00:00','2020-02-20','2020-02-25',5,10000,18,'Single',111,1),(2,'2020-07-20 20:00:00','2020-07-20','2020-07-26',6,10001,18,'Single',111,1),(3,'2020-03-20 19:00:00','2020-08-20','2020-08-25',5,10002,18,'Single',111,1),(4,'2020-01-20 20:00:00','2020-01-20','2020-02-17',28,10004,30,'Double',111,2),(5,'2020-05-20 20:00:00','2020-05-20','2020-06-14',25,10005,30,'Double',111,2),(6,'2020-05-20 20:00:00','2020-05-20','2020-05-25',5,10006,30,'Double',111,2),(7,'2020-08-20 20:00:00','2020-08-20','2020-08-25',5,10000,60,'Double',112,7),(8,'2020-10-20 20:00:00','2020-10-20','2020-10-23',3,10001,60,'Double',112,7),(9,'2020-10-25 20:00:00','2020-10-25','2020-11-01',7,10000,60,'Double',112,7),(10,'2020-10-25 20:00:00','2020-10-27','2020-11-05',9,10001,100,'Studio',112,8),(11,'2020-10-25 20:00:00','2020-11-01','2020-11-07',7,10002,100,'Studio',112,8);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_has_services`
--

DROP TABLE IF EXISTS `booking_has_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_has_services` (
  `booking_id` int NOT NULL,
  `guest_id` int NOT NULL,
  `service_name` varchar(45) NOT NULL,
  PRIMARY KEY (`booking_id`,`guest_id`,`service_name`),
  KEY `fk_booking_has_services_services1_idx` (`service_name`),
  KEY `fk_booking_has_services_booking1_idx` (`booking_id`,`guest_id`),
  CONSTRAINT `fk_booking_has_services_booking1` FOREIGN KEY (`booking_id`, `guest_id`) REFERENCES `booking` (`booking_id`, `guest_id`),
  CONSTRAINT `fk_booking_has_services_services1` FOREIGN KEY (`service_name`) REFERENCES `services` (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_has_services`
--

LOCK TABLES `booking_has_services` WRITE;
/*!40000 ALTER TABLE `booking_has_services` DISABLE KEYS */;
INSERT INTO `booking_has_services` VALUES (1,10000,'Car Rental'),(1,10000,'Flower arrangement'),(2,10001,'Car Rental'),(2,10001,'mail'),(3,10002,'Car Rental'),(3,10002,'Massage'),(4,10004,'Doctor'),(4,10004,'mail'),(5,10005,'Doctor'),(5,10005,'Ironing'),(6,10006,'Doctor'),(6,10006,'Flower arrangement');
/*!40000 ALTER TABLE `booking_has_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category` varchar(45) NOT NULL,
  `discount` double NOT NULL,
  `discout` double DEFAULT NULL,
  PRIMARY KEY (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('basic',0.75,NULL),('Military ',0.5,NULL),('VIP',0,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dayoff`
--

DROP TABLE IF EXISTS `dayoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dayoff` (
  `employee_id` int NOT NULL,
  `day` date NOT NULL,
  PRIMARY KEY (`day`,`employee_id`),
  KEY `fk_dayoff_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_dayoff_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayoff`
--

LOCK TABLES `dayoff` WRITE;
/*!40000 ALTER TABLE `dayoff` DISABLE KEYS */;
INSERT INTO `dayoff` VALUES (1,'2020-03-03'),(2,'2020-03-03'),(3,'2020-03-03'),(4,'2020-03-03'),(5,'2020-03-03'),(6,'2020-03-03'),(7,'2020-03-03'),(8,'2020-03-03'),(9,'2020-03-03'),(10,'2020-03-03'),(11,'2020-03-03'),(12,'2020-03-03'),(13,'2020-03-03'),(14,'2020-03-03'),(15,'2020-03-03'),(16,'2020-03-03'),(17,'2020-03-03');
/*!40000 ALTER TABLE `dayoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `second_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  `employee_type` varchar(45) NOT NULL,
  `hotel_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  KEY `fk_Employee_Employee1_idx` (`manager_id`),
  KEY `fk_employee_hotel1_idx` (`hotel_id`),
  KEY `fk_employee_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_Employee_Employee1` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `fk_employee_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `fk_employee_user_role1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Zhuldyz','L','zhuldyz@gmail.com','1','87075566453',6,'Administrator',111,2),(2,'Amina','K','amina@gmail.com','2','87075566543',9,'Administrator',111,2),(3,'Kamilla','N','kamilla@gmail.com','3','87073456275',9,'Administrator',111,2),(4,'Justin','M','justin@gmail.com','4','87035622673',9,'Administrator',111,2),(5,'Ariana','B','ariana@gmail.com','5','87074276323',6,'Administrator',111,2),(6,'Altynay','C','Altynay@gmail.com','6','87073264572',6,'Manager',111,3),(7,'Lyubov','D','lybov@gmail.com','7','87072364272',6,'Manager',111,3),(8,'Zhurek','E','zhurek@gmail.com','8','87072634562',6,'DeskClerk',111,4),(9,'Gulnar','R','gulnar@gmail.com','9','87072736472',9,'DeskClerk',111,4),(10,'Kymbat','T','kymbat@gmail.com','10','87072364527',6,'DeskClerk',111,4),(11,'Qarashash','U','qarashash@gmail.com','11','87072734682',6,'DeskClerk',111,4),(12,'Gulnaz','I','zhambylova25@gmail.com','12','87075566500',8,'DeskClerk',111,4),(13,'Joanna','O','joanna@gmail.com','12','87075566000',8,'Cleaner',111,5),(14,'Selena','L','selena@gmail.com','13','87075566511',8,'Cleaner',111,5),(15,'Lola','K','lola@gmail.com','14','87074623764',6,'Cleaner',111,5),(16,'Dora','A','dora@gmail.com','15','87077642846',6,'Cleaner',111,5),(17,'Kora','W','kora@gmail.com','16','87077426732',9,'Cleaner',111,5);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `guest_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_type` varchar(45) NOT NULL,
  `id_number` varchar(45) NOT NULL,
  `address` varchar(150) NOT NULL,
  `mobile_number` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `role_id` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guest_id`),
  UNIQUE KEY `guest_id_UNIQUE` (`guest_id`),
  KEY `fk_guest_category1_idx` (`category`),
  KEY `fk_guest_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_guest_category1` FOREIGN KEY (`category`) REFERENCES `category` (`category`),
  CONSTRAINT `fk_guest_user_role1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (10000,'Donald','Trump','donaldtrump@gmail.com','7073475683','makeamericagreatagain','passport','000001','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10001,'Barack ','Obama','barackobama@gmail.com','7077362472','zhulbern','passport','000002','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10002,'Lindsey','Lohan','lindseylohan@gmail.com','7072473882','lindsey007','passport','000003','Xixi 12 Almaty Kazakhstan','123456789','VIP',1,'verified'),(10003,'Margaret','Tatcher','margarettatcher@gmail.com','7027253849','maragarate','passport','000004','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'notverified'),(10004,'Demi','Lovato','demilovato@gmail.com','2358529297','demiiiii','passport','000001','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10005,'Steve','Jobs','stevejobs@gmail.com','2357286732','steveaoki','passport','000005','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10006,'Aooki','Steven','aookisteven@gmail.com','2358792823','aookisteve','passport','000006','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10007,'Miras','Lohan','miraslohan@gmail.com','7072473882','miraslohan','passport','000007','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10008,'Lindsey','Steven','lindseysteven@gmail.com','7072473882','lindseysteven','passport','000008','Xixi 12 Almaty Kazakhstan','123456789','VIP',1,'notverified'),(10009,'Jonas','Joe','jonasjoe@gmail.com','7072473882','jonasjoe','passport','000001','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10010,'Kristin','Lero','kristinlero@gmail.com','5748393782','kristenlero','passport','000009','Xixi 12 Almaty Kazakhstan','123456789','basic',1,'verified'),(10011,'Heroshi','Dorna','heroshidorna@gmail.com','5843798272','heroshidorna','passport','00010','Xixi 12 Almaty Kazakhstan','123456789','Military',1,'notverified'),(10012,'Zhuldyz','Namazbayeva','zhuldyznamazbay@gmail.com','7074372676','zhuldyzay','passport','000011','Xixi 12 Almaty Kazakhstan','123456789','Military',1,'notverified'),(10013,'Gulnaz','Zhambulova','zhambylova25@gmail.com','+77054709827','$2a$10$fL4fvZhtZJqkWv18QchIuOnQWC00JqGpQJLwocvcAJgFYo/lf9QN.','passport','12345','Kabanbay batyr','+77054709827','basic',1,'VERIFIED');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `hotel_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `country_code` char(2) NOT NULL,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_id_UNIQUE` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (111,'Zhuldyz','+7','Almaty','Shalyapin, 65','Kazakhstan'),(112,'Kamilla','+7','Nur-Sultan','Kabanbay, 56','Kazakhstan'),(113,'Amina','+7','Almaty','Nazarbayev, 56','Kazakhstan'),(114,'Gulnaz','+7','New York','Dostyq, 46','USA'),(115,'Altynay','+7','London','Zhurgenev, 34','UK'),(116,'Borovoe','+7','Borovoe','Abylaihana, 56','Kazakhstan'),(117,'Tokyo','+9','Tokyo','Hong, 56','Japan'),(118,'Moscow','+6','Moscow','Blue, 56','Russia'),(119,'Lux','+5','Prague','Lyubovenko, 67','Czech Republic'),(120,'Hilton','+4','Rome','Doro, 56','Italy'),(121,'Fugue','+5','Prague','Dorenko, 536','Czech Republic');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_has_season`
--

DROP TABLE IF EXISTS `hotel_has_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_has_season` (
  `hotel_id` int NOT NULL,
  `season_name` varchar(45) NOT NULL,
  PRIMARY KEY (`hotel_id`,`season_name`),
  KEY `fk_hotel_has_season_season1_idx` (`season_name`),
  KEY `fk_hotel_has_season_hotel1_idx` (`hotel_id`),
  CONSTRAINT `fk_hotel_has_season_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `fk_hotel_has_season_season1` FOREIGN KEY (`season_name`) REFERENCES `season` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_has_season`
--

LOCK TABLES `hotel_has_season` WRITE;
/*!40000 ALTER TABLE `hotel_has_season` DISABLE KEYS */;
INSERT INTO `hotel_has_season` VALUES (111,'Fall'),(112,'Fall'),(113,'Fall'),(114,'Fall'),(115,'Winter'),(116,'Winter'),(117,'Winter'),(118,'Spring'),(119,'Spring'),(120,'Spring'),(121,'Spring');
/*!40000 ALTER TABLE `hotel_has_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_has_weekday`
--

DROP TABLE IF EXISTS `hotel_has_weekday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_has_weekday` (
  `hotel_id` int NOT NULL,
  `weekday` varchar(10) NOT NULL,
  PRIMARY KEY (`hotel_id`,`weekday`),
  KEY `fk_hotel_has_weekday_weekday1_idx` (`weekday`),
  KEY `fk_hotel_has_weekday_hotel1_idx` (`hotel_id`),
  CONSTRAINT `fk_hotel_has_weekday_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `fk_hotel_has_weekday_weekday1` FOREIGN KEY (`weekday`) REFERENCES `weekday` (`weekday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_has_weekday`
--

LOCK TABLES `hotel_has_weekday` WRITE;
/*!40000 ALTER TABLE `hotel_has_weekday` DISABLE KEYS */;
INSERT INTO `hotel_has_weekday` VALUES (111,'Friday'),(111,'Monday'),(111,'Saturday'),(111,'Sunday'),(111,'Thursday'),(111,'Tuesday'),(111,'Wednesday'),(112,'Friday'),(112,'Monday'),(112,'Saturday'),(112,'Sunday'),(112,'Thursday'),(112,'Tuesday'),(112,'Wednesday'),(113,'Friday'),(113,'Monday'),(113,'Saturday'),(113,'Sunday'),(113,'Thursday'),(113,'Tuesday'),(113,'Wednesday'),(114,'Friday'),(114,'Monday'),(114,'Saturday'),(114,'Sunday'),(114,'Thursday'),(114,'Tuesday'),(114,'Wednesday'),(115,'Friday'),(115,'Monday'),(115,'Saturday'),(115,'Sunday'),(115,'Thursday'),(115,'Tuesday'),(115,'Wednesday'),(116,'Friday'),(116,'Monday'),(116,'Saturday'),(116,'Sunday'),(116,'Thursday'),(116,'Tuesday'),(116,'Wednesday'),(117,'Friday'),(117,'Monday'),(117,'Saturday'),(117,'Sunday'),(117,'Thursday'),(117,'Tuesday'),(117,'Wednesday'),(118,'Friday'),(118,'Monday'),(118,'Saturday'),(118,'Sunday'),(118,'Thursday'),(118,'Tuesday'),(118,'Wednesday'),(119,'Friday'),(119,'Monday'),(119,'Saturday'),(119,'Sunday'),(119,'Thursday'),(119,'Tuesday'),(119,'Wednesday'),(120,'Friday'),(120,'Monday'),(120,'Saturday'),(120,'Sunday'),(120,'Thursday'),(120,'Tuesday'),(120,'Wednesday'),(121,'Friday'),(121,'Monday'),(121,'Saturday'),(121,'Sunday'),(121,'Thursday'),(121,'Tuesday'),(121,'Wednesday');
/*!40000 ALTER TABLE `hotel_has_weekday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotelfeatures`
--

DROP TABLE IF EXISTS `hotelfeatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotelfeatures` (
  `feature` varchar(100) NOT NULL,
  `hotel_id` int NOT NULL,
  PRIMARY KEY (`hotel_id`,`feature`),
  KEY `fk_Features_hotel1_idx` (`hotel_id`),
  CONSTRAINT `fk_Features_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotelfeatures`
--

LOCK TABLES `hotelfeatures` WRITE;
/*!40000 ALTER TABLE `hotelfeatures` DISABLE KEYS */;
INSERT INTO `hotelfeatures` VALUES ('Skating',111),('SPA',111),('Swimming pool',111),('Communal Spaces',112),('Swimming pool',112),('Breakfast',113),('SPA',113),('In-Room Amenities',114),('Parking',114),('Top Business Centre',114),('Arctic Resort',115),('Concierge Services',115);
/*!40000 ALTER TABLE `hotelfeatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupant`
--

DROP TABLE IF EXISTS `occupant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occupant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `mobile_number` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `booking_id` int NOT NULL,
  `guest_id` int NOT NULL,
  PRIMARY KEY (`id`,`booking_id`,`guest_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_occupant_booking1_idx` (`booking_id`,`guest_id`),
  CONSTRAINT `fk_occupant_booking1` FOREIGN KEY (`booking_id`, `guest_id`) REFERENCES `booking` (`booking_id`, `guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupant`
--

LOCK TABLES `occupant` WRITE;
/*!40000 ALTER TABLE `occupant` DISABLE KEYS */;
INSERT INTO `occupant` VALUES (1,'Amina Xsdm','5345453','465345','Almaty2121 Almaty',1,10000),(2,'Amina sassed','12345','23456','Almaty323 Almaty',1,10000),(3,'Amina ggdfds','1234567','2345678','Almaty434 Almaty',2,10001),(4,'Amina dfsfs','876543','87654565456','Almaty4343Almaty',2,10001),(5,'Amina ultra','23456','456543456','Almaty43456 Almaty',2,10001),(6,'Amina jhgf','76543','345654345','Almaty7765 Almaty',3,10002),(7,'Amina dfgh','1234567','3454345','Almaty3246 Almaty',4,10004);
/*!40000 ALTER TABLE `occupant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonenumbers`
--

DROP TABLE IF EXISTS `phonenumbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phonenumbers` (
  `hotel_id` int NOT NULL,
  `phonenumbers` varchar(45) NOT NULL,
  PRIMARY KEY (`hotel_id`,`phonenumbers`),
  CONSTRAINT `fk_PhoneNumbers_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonenumbers`
--

LOCK TABLES `phonenumbers` WRITE;
/*!40000 ALTER TABLE `phonenumbers` DISABLE KEYS */;
INSERT INTO `phonenumbers` VALUES (111,'2384782847'),(111,'7074657351'),(112,'3827482973'),(112,'7458375322'),(113,'3827462700'),(113,'3827462734'),(114,'0034782792'),(114,'2834782792'),(115,'0034687226'),(115,'2734687226'),(116,'3748927263'),(117,'3824789227'),(118,'2746326836');
/*!40000 ALTER TABLE `phonenumbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_number` int NOT NULL AUTO_INCREMENT,
  `floor` varchar(45) NOT NULL,
  `cleaning_status` varchar(45) NOT NULL,
  `room_type_name` varchar(20) NOT NULL,
  `hotel_id` int NOT NULL,
  `available` tinyint NOT NULL,
  `cleaning_staff_id` int NOT NULL,
  PRIMARY KEY (`room_number`,`room_type_name`,`hotel_id`),
  KEY `fk_Room_Room Type1_idx` (`room_type_name`,`hotel_id`),
  KEY `fk_room_employee1_idx` (`cleaning_staff_id`),
  CONSTRAINT `fk_room_employee1` FOREIGN KEY (`cleaning_staff_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `fk_Room_Room Type1` FOREIGN KEY (`room_type_name`, `hotel_id`) REFERENCES `room_type` (`room_type_name`, `hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'1','Clean','Single',111,1,13),(2,'1','Clean','Double',111,1,14),(3,'1','Clean','Quad',111,1,13),(4,'1','Clean','Studio',111,1,14),(5,'1','Clean','Triple',111,1,15),(6,'1','Clean','Twin',111,1,13),(7,'1','Clean','Double',112,1,15),(8,'2','Clean','Studio',112,1,15),(9,'1','Clean','Single',112,1,14);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type` (
  `room_type_name` varchar(20) NOT NULL,
  `size` int NOT NULL,
  `capacity` int NOT NULL,
  `price` int NOT NULL,
  `hotel_id` int NOT NULL,
  PRIMARY KEY (`room_type_name`,`hotel_id`),
  KEY `fk_Room Type_hotel1_idx` (`hotel_id`),
  CONSTRAINT `fk_Room Type_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES ('Cabana',50,3,80,113),('Deluxe',160,8,200,114),('Double',25,2,30,111),('Double',50,2,60,112),('Double',80,2,120,113),('Double',160,2,250,114),('Double-double',40,4,60,111),('Duplex',200,8,250,115),('King',30,2,50,111),('Lanai',60,2,120,113),('Master Suite',100,8,150,112),('Penthouse Suite',250,4,300,115),('Presidential Suite',300,6,400,114),('Quad',45,4,60,111),('Queen',30,2,50,111),('Single',15,1,18,111),('Single',40,1,50,112),('Single',70,1,100,113),('Single',80,1,150,114),('Single',80,1,150,115),('Studio',25,1,40,111),('Studio',70,4,100,112),('Triple',30,3,45,111),('Triple',60,3,70,112),('Twin',30,2,50,111);
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type_features`
--

DROP TABLE IF EXISTS `room_type_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type_features` (
  `room_type_name` varchar(20) NOT NULL,
  `hotel_id` int NOT NULL,
  `features` varchar(100) NOT NULL,
  PRIMARY KEY (`features`,`room_type_name`,`hotel_id`),
  KEY `fk_room_type_features_room_type1_idx` (`room_type_name`,`hotel_id`),
  CONSTRAINT `fk_room_type_features_room_type1` FOREIGN KEY (`room_type_name`, `hotel_id`) REFERENCES `room_type` (`room_type_name`, `hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type_features`
--

LOCK TABLES `room_type_features` WRITE;
/*!40000 ALTER TABLE `room_type_features` DISABLE KEYS */;
INSERT INTO `room_type_features` VALUES ('Cabana',113,'Balcony'),('Cabana',113,'Swimming Pool'),('Deluxe',116,'Antiques'),('Double',111,'Dressing table'),('Double',113,'King-size double bed'),('Double',114,'Swimming Pool'),('Double-double',111,'Double bed for children'),('Lanai',113,'Waterfall'),('Master Suite',112,'Terrace space'),('Presidential Suite',114,'Antique paintings'),('Single',114,'Swimming Pool'),('Single',115,'Swimming Pool');
/*!40000 ALTER TABLE `room_type_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `employee_id` int NOT NULL,
  `from` time NOT NULL,
  `to` time NOT NULL,
  `salary_per_hour` int NOT NULL,
  `salary_per_month` int DEFAULT NULL,
  `weekdays` varchar(7) NOT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `fk_schedule_employee1_idx` (`employee_id`),
  CONSTRAINT `fk_schedule_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'09:00:00','17:00:00',25,6000,'MTWRF'),(2,'09:00:00','17:00:00',25,6000,'MTWRF'),(3,'09:00:00','17:00:00',25,6000,'MTWRF'),(4,'09:00:00','17:00:00',25,6000,'MTWRF'),(5,'09:00:00','17:00:00',25,6000,'MTWRF'),(6,'09:00:00','17:00:00',25,6000,'MTWRF'),(7,'09:00:00','17:00:00',25,6000,'MTWRF'),(8,'09:00:00','17:00:00',25,6000,'MTWRF'),(9,'09:00:00','17:00:00',25,6000,'MTWRF'),(10,'09:00:00','17:00:00',25,6000,'MTWRF'),(11,'09:00:00','17:00:00',25,6000,'MTWRF'),(12,'09:00:00','17:00:00',25,6000,'MTWRF'),(13,'09:00:00','17:00:00',25,6000,'MTWRF'),(14,'09:00:00','17:00:00',25,6000,'MTWRF'),(15,'09:00:00','17:00:00',25,6000,'MTWRF'),(16,'09:00:00','17:00:00',25,6000,'MTWRF'),(17,'09:00:00','17:00:00',25,6000,'MTWRF');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season`
--

DROP TABLE IF EXISTS `season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `season` (
  `name` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season`
--

LOCK TABLES `season` WRITE;
/*!40000 ALTER TABLE `season` DISABLE KEYS */;
INSERT INTO `season` VALUES ('Fall','2022-09-01','2022-11-30',0.9),('Spring','2021-03-01','2021-05-30',1.2),('Summer','2021-06-01','2021-08-30',1.5),('Winter','2020-12-01','2021-02-28',0.75);
/*!40000 ALTER TABLE `season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_name` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `hotel_id` int NOT NULL,
  PRIMARY KEY (`service_name`,`hotel_id`),
  KEY `fk_services_hotel1_idx` (`hotel_id`),
  CONSTRAINT `fk_services_hotel1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES ('Car Rental',55,111),('Caterin',55,111),('Doctor',45,111),('Dry cleaning',35,111),('Flower arrangement',34,111),('Ironing',86,111),('mail',55,111),('Massage',90,111),('shoeshine',66,111);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'guest'),(2,'Administrator'),(3,'Manager'),(4,'DeskClerk'),(5,'Cleaner');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weekday`
--

DROP TABLE IF EXISTS `weekday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weekday` (
  `weekday` varchar(10) NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`weekday`),
  UNIQUE KEY `weekday_UNIQUE` (`weekday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weekday`
--

LOCK TABLES `weekday` WRITE;
/*!40000 ALTER TABLE `weekday` DISABLE KEYS */;
INSERT INTO `weekday` VALUES ('Friday',1.2),('Monday',0.9),('Saturday',1.4),('Sunday',1.5),('Thursday',1),('Tuesday',0.8),('Wednesday',1);
/*!40000 ALTER TABLE `weekday` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-13 21:36:11
