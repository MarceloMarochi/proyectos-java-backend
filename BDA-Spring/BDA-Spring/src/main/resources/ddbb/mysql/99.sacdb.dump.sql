-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: mysql80    Database: sacdb
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `pid` int NOT NULL,
  `legajo` varchar(16) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `legajo` (`legajo`),
  CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `persona` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,'LA-10.001'),(2,'LA-10.002'),(3,'LA-10.003'),(4,'LA-10.004'),(5,'LA-10.005'),(6,'LA-10.006'),(7,'LA-10.007'),(8,'LA-10.008'),(9,'LA-10.009'),(10,'LA-10.010'),(11,'LA-10.011'),(12,'LA-10.012'),(13,'LA-10.013'),(14,'LA-10.014'),(15,'LA-10.015'),(16,'LA-10.016'),(17,'LA-20.001'),(18,'LA-20.002'),(19,'LA-20.003'),(20,'LA-20.004'),(21,'LA-20.005'),(22,'LA-20.006'),(23,'LA-20.007'),(24,'LA-20.008'),(25,'LA-20.009'),(26,'LA-20.010'),(27,'LA-20.011'),(28,'LA-20.012'),(29,'LA-30.001'),(30,'LA-30.002'),(31,'LA-30.003'),(32,'LA-30.004'),(33,'LA-30.005'),(34,'LA-30.006'),(35,'LA-30.007'),(36,'LA-30.008'),(37,'LA-30.009'),(38,'LA-30.010'),(39,'LA-30.011'),(40,'LA-30.012');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `pid` int NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `descripcion` text,
  `cupo` smallint NOT NULL DEFAULT '0',
  `finicio` timestamp NOT NULL,
  `ffin` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `mid` (`mid`,`pid`),
  KEY `finicio` (`finicio`),
  CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`mid`, `pid`) REFERENCES `materia_docentes` (`mid`, `pid`),
  CONSTRAINT `curso_chk_1` CHECK ((`cupo` > 0)),
  CONSTRAINT `curso_chk_2` CHECK (((`ffin` is null) or (`finicio` < `ffin`)))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,1,1,'Física Teórica','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.',24,'2016-03-01 13:45:10',NULL),(2,2,17,'Historia de la Música','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.',24,'2016-03-01 13:47:18',NULL),(3,3,29,'Introducción a la Filosofía','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.',24,'2016-03-01 13:52:22',NULL);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docenteEntity`
--

DROP TABLE IF EXISTS `docenteEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docenteEntity` (
  `pid` int NOT NULL,
  `legajo` varchar(16) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `legajo` (`legajo`),
  CONSTRAINT `docente_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `persona` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docenteEntity`
--

LOCK TABLES `docenteEntity` WRITE;
/*!40000 ALTER TABLE `docenteEntity` DISABLE KEYS */;
INSERT INTO `docenteEntity` VALUES (1,'LD-10.001'),(17,'LD-20.001'),(29,'LD-30.001');
/*!40000 ALTER TABLE `docenteEntity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripcion` (
  `cid` int NOT NULL,
  `pid` int NOT NULL,
  `cfinal` int DEFAULT NULL,
  PRIMARY KEY (`cid`,`pid`),
  KEY `pid` (`pid`),
  CONSTRAINT `inscripcion_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `curso` (`cid`),
  CONSTRAINT `inscripcion_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `alumno` (`pid`),
  CONSTRAINT `inscripcion_chk_1` CHECK (((`cfinal` is null) or (`cfinal` between 0 and 10)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (1,2,NULL),(1,3,NULL),(1,4,NULL),(1,5,NULL),(1,6,NULL),(1,7,NULL),(1,8,NULL),(1,9,NULL),(1,10,NULL),(1,11,NULL),(1,12,NULL),(1,13,NULL),(1,14,NULL),(1,15,NULL),(1,16,NULL),(2,18,NULL),(2,19,NULL),(2,20,NULL),(2,21,NULL),(2,22,NULL),(2,23,NULL),(2,24,NULL),(2,25,NULL),(2,26,NULL),(2,27,NULL),(2,28,NULL),(3,30,NULL),(3,31,NULL),(3,32,NULL),(3,33,NULL),(3,34,NULL),(3,35,NULL),(3,36,NULL),(3,37,NULL),(3,38,NULL),(3,39,NULL),(3,40,NULL);
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiaEntity`
--

DROP TABLE IF EXISTS `materiaEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiaEntity` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(64) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiaEntity`
--

LOCK TABLES `materiaEntity` WRITE;
/*!40000 ALTER TABLE `materiaEntity` DISABLE KEYS */;
INSERT INTO `materiaEntity` VALUES (1,'Física','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'),(2,'Música','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.'),(3,'Filosofía','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus.');
/*!40000 ALTER TABLE `materiaEntity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia_docentes`
--

DROP TABLE IF EXISTS `materia_docentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia_docentes` (
  `mid` int NOT NULL,
  `pid` int NOT NULL,
  PRIMARY KEY (`mid`,`pid`),
  KEY `pid` (`pid`),
  CONSTRAINT `materia_docentes_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `materiaEntity` (`mid`),
  CONSTRAINT `materia_docentes_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `docenteEntity` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia_docentes`
--

LOCK TABLES `materia_docentes` WRITE;
/*!40000 ALTER TABLE `materia_docentes` DISABLE KEYS */;
INSERT INTO `materia_docentes` VALUES (1,1),(2,17),(3,29);
/*!40000 ALTER TABLE `materia_docentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(32) NOT NULL,
  `apellido` varchar(64) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `dni` (`dni`),
  KEY `apellido` (`apellido`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'1642-12-25/1727-03-20','Newton','Isaac'),(2,'1629-04-14/1695-07-08','Huygens','Christiaan'),(3,'1646-07-01/1716-11-14','Leibniz','Gottfried Wilhelm'),(4,'1736-06-14/1806-08-23','de Coulomb','Charles-Augustin'),(5,'1736-01-19/1819-08-25','Watt','James'),(6,'1745-02-18/1827-03-05','Volta','Alessandro Giuseppe Antonio Anastasio'),(7,'1775-01-20/1836-06-10','Ampère','André-Marie'),(8,'1789-03-16/1854-07-06','Ohm','Georg Simon'),(9,'1791-09-22/1867-08-25','Faraday','Michael'),(10,'1818-12-24/1889-10-11','Joule','James Prescott'),(11,'1831-06-13/1879-11-05','Maxwell','James Clerk'),(12,'1856-07-10/1943-01-07','Tesla','Nikola'),(13,'1857-02-22/1894-01-01','Hertz','Heinrich Rudolf'),(14,'1867-11-07/1934-07-04','Skłodowska-Curie','Maria Salomea'),(15,'1879-03-14/1955-04-18','Einstein','Albert'),(16,'1885-10-07/1962-11-18','Bohr','Niels'),(17,'1685-03-31/1750-07-28','Bach','Johann Sebastian'),(18,'1678-03-04/1741-07-28','Vivaldi','Antonio Lucio'),(19,'1756-01-27/1791-12-05','Mozart','Wolfgang Amadeus'),(20,'1770-12-??/1827-03-26','van Beethoven','Ludwig'),(21,'1810-03-01/1849-10-17','Chopin','Frédéric François'),(22,'1811-10-22/1886-07-31','Liszt','Franz'),(23,'1813-05-22/1883-02-13','Wagner','Wilhelm Richard'),(24,'1825-10-25/1899-06-03','Strauss','Johann Baptist II'),(25,'1833-05-07/1897-04-03','Brahms','Johannes'),(26,'1840-05-07/1893-11-06','Tchaikovsky','Pyotr Ilyich'),(27,'1928-11-10/2020-07-06','Morricone','Enio'),(28,'1932-02-08/','Williams','John Towner'),(29,'1596-03-31/1650-02-11','Descartes','René'),(30,'1632-08-29/1704-10-28','Locke','John'),(31,'1724-04-22/1804-02-12','Kant','Immanuel'),(32,'1770-08-27/1831-11-14','Hegel','Georg Wilhelm Friedrich'),(33,'1788-02-22/1860-09-21','Schopenhauer','Arthur'),(34,'1798-01-19/1857-09-05','Comte','Isidore Marie Auguste François Xavier'),(35,'1844-10-15/1900-08-25','Nietzsche','Friedrich Wilhelm'),(36,'1864-04-21/1920-06-14','Weber','Maximilian Karl Emil'),(37,'1889-09-26/1976-05-26','Heidegger','Martin'),(38,'1872-05-18/1970-02-02','Russell','Bertrand Arthur William'),(39,'1883-05-09/1955-10-18','Ortega y Gasset','José'),(40,'1905-06-21/1980-04-15','Sartre','Jean-Paul Charles Aymard');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_alumno`
--

DROP TABLE IF EXISTS `v_alumno`;
/*!50001 DROP VIEW IF EXISTS `v_alumno`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_alumno` AS SELECT 
 1 AS `pid`,
 1 AS `dni`,
 1 AS `apellido`,
 1 AS `nombre`,
 1 AS `legajo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_curso`
--

DROP TABLE IF EXISTS `v_curso`;
/*!50001 DROP VIEW IF EXISTS `v_curso`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_curso` AS SELECT 
 1 AS `cid`,
 1 AS `curso`,
 1 AS `mid`,
 1 AS `materiaEntity`,
 1 AS `cupo`,
 1 AS `did`,
 1 AS `docenteEntity`,
 1 AS `descripcion`,
 1 AS `finicio`,
 1 AS `ffin`,
 1 AS `legajo`,
 1 AS `pid`,
 1 AS `dni`,
 1 AS `apellido`,
 1 AS `nombre`,
 1 AS `cfinal`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_docente`
--

DROP TABLE IF EXISTS `v_docente`;
/*!50001 DROP VIEW IF EXISTS `v_docente`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_docente` AS SELECT 
 1 AS `pid`,
 1 AS `dni`,
 1 AS `apellido`,
 1 AS `nombre`,
 1 AS `legajo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_persona`
--

DROP TABLE IF EXISTS `v_persona`;
/*!50001 DROP VIEW IF EXISTS `v_persona`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_persona` AS SELECT 
 1 AS `pid`,
 1 AS `dni`,
 1 AS `apellido`,
 1 AS `nombre`,
 1 AS `legajo_docente`,
 1 AS `legajo_alumno`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_alumno`
--

/*!50001 DROP VIEW IF EXISTS `v_alumno`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sacusr`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_alumno` AS select `p`.`pid` AS `pid`,`p`.`dni` AS `dni`,`p`.`apellido` AS `apellido`,`p`.`nombre` AS `nombre`,`a`.`legajo` AS `legajo` from (`alumno` `a` join `persona` `p` on((`a`.`pid` = `p`.`pid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_curso`
--

/*!50001 DROP VIEW IF EXISTS `v_curso`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sacusr`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_curso` AS select `c`.`cid` AS `cid`,`c`.`nombre` AS `curso`,`m`.`mid` AS `mid`,`m`.`nombre` AS `materiaEntity`,`c`.`cupo` AS `cupo`,`c`.`pid` AS `did`,concat('(',`d`.`legajo`,') ',`pd`.`apellido`,', ',`pd`.`nombre`) AS `docenteEntity`,`c`.`descripcion` AS `descripcion`,`c`.`finicio` AS `finicio`,`c`.`ffin` AS `ffin`,`a`.`legajo` AS `legajo`,`pa`.`pid` AS `pid`,`pa`.`dni` AS `dni`,`pa`.`apellido` AS `apellido`,`pa`.`nombre` AS `nombre`,`i`.`cfinal` AS `cfinal` from ((((((`curso` `c` join `materiaEntity` `m` on((`c`.`mid` = `m`.`mid`))) join `docenteEntity` `d` on((`c`.`pid` = `d`.`pid`))) join `persona` `pd` on((`c`.`pid` = `pd`.`pid`))) left join `inscripcion` `i` on((`c`.`cid` = `i`.`cid`))) left join `alumno` `a` on((`i`.`pid` = `a`.`pid`))) left join `persona` `pa` on((`a`.`pid` = `pa`.`pid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_docente`
--

/*!50001 DROP VIEW IF EXISTS `v_docente`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sacusr`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_docente` AS select `p`.`pid` AS `pid`,`p`.`dni` AS `dni`,`p`.`apellido` AS `apellido`,`p`.`nombre` AS `nombre`,`d`.`legajo` AS `legajo` from (`docenteEntity` `d` join `persona` `p` on((`d`.`pid` = `p`.`pid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_persona`
--

/*!50001 DROP VIEW IF EXISTS `v_persona`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sacusr`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_persona` AS select `p`.`pid` AS `pid`,`p`.`dni` AS `dni`,`p`.`apellido` AS `apellido`,`p`.`nombre` AS `nombre`,`d`.`legajo` AS `legajo_docente`,`a`.`legajo` AS `legajo_alumno` from ((`persona` `p` left join `docenteEntity` `d` on((`p`.`pid` = `d`.`pid`))) left join `alumno` `a` on((`p`.`pid` = `a`.`pid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
