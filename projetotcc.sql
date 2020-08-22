CREATE DATABASE  IF NOT EXISTS `hidrofire` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hidrofire`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: hidrofire
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.32-MariaDB

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
-- Table structure for table `cargaincendio`
--

DROP TABLE IF EXISTS `cargaincendio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargaincendio` (
  `idcarga` int(11) NOT NULL AUTO_INCREMENT,
  `uso` varchar(50) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `divisao` varchar(15) DEFAULT NULL,
  `carga` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcarga`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargaincendio`
--

LOCK TABLES `cargaincendio` WRITE;
/*!40000 ALTER TABLE `cargaincendio` DISABLE KEYS */;
INSERT INTO `cargaincendio` VALUES (1,'Residencial','Alojamento estudantis','A-3',300),(2,'Residencial','Apartamentos','A-2',300),(3,'Residencial','Casas térreas ou sobrados','A-1',300),(4,'Residencial','Pensionatos','A-3',300),(5,'Serviços de Hospedagem','Hotéis','B-1',500),(6,'Serviços de Hospedagem','Motéis','B-1',500),(7,'Serviços de Hospedagem','Apart-hotéis','B-2',500),(8,'Comercial varejista, Loja','Açougue','C-1',40),(9,'Comercial varejista, Loja','Animais (\"Pet shop\")','C-2',600),(10,'Comercial varejista, Loja','Antiguidades','C-2',700),(11,'Comercial varejista, Loja','Aparelhos eletrodomésticos','C-1',300),(12,'Comercial varejista, Loja','Aparelhos eletrônicos','C-2',400),(13,'Comercial varejista, Loja','Armarinhos','C-2',600),(14,'Comercial varejista, Loja','Armas','C-1',300),(15,'Comercial varejista, Loja','Artigos de bijuteria, metal ou vidro','C-1',300),(16,'Comercial varejista, Loja','Artigos de cera','C-2',2100),(17,'Comercial varejista, Loja','Artigos de couro, borracha, esportivo','C-2',800),(18,'Comercial varejista, Loja','Automóveis','C-1',200),(19,'Comercial varejista, Loja','Bebidas destiladas','C-2',700),(20,'Comercial varejista, Loja','Brinquedos','C-2',500),(21,'Comercial varejista, Loja','Calçados','C-2',500),(22,'Comercial varejista, Loja','Artigos de couro','C-2',700),(23,'Comercial varejista, Loja','Drogariais (incluindo depósitos)','C-2',1000),(24,'Comercial varejista, Loja','Artigos de esporte','C-2',800),(25,'Comercial varejista, Loja','Ferragens','C-1',300),(26,'Comercial varejista, Loja','Floricultura','C-1',80),(27,'Comercial varejista, Loja','Galeria de quadros','C-1',200),(28,'Comercial varejista, Loja','Joaheria','C-1',300),(29,'Comercial varejista, Loja','Livrarias','C-2',1000),(30,'Comercial varejista, Loja','Lojas de departamento ou centro de compras (shppin','C-2',800),(31,'Comercial varejista, Loja','Lojas de departamento ou centro de compras (shppin','C-3',800),(32,'Comercial varejista, Loja','Materiais de construção','C-2',800),(33,'Comercial varejista, Loja','Máquinas de custura ou de escritório','C-1',300),(34,'Comercial varejista, Loja','Materiais fotográficos','C-1',300),(35,'Comercial varejista, Loja','Móveis','C-2',400),(36,'Comercial varejista, Loja','Papelarias','C-2',700),(37,'Comercial varejista, Loja','Perfumarias','C-2',400),(38,'Comercial varejista, Loja','Produtos têxteis','C-2',600),(39,'Comercial varejista, Loja','Relojoarias','C-2',500),(40,'Comercial varejista, Loja','Supermercados (vendas)','C-2',600),(41,'Comercial varejista, Loja','Tapetes','C-2',800),(42,'Comercial varejista, Loja','Tintas e vernizes','C-2',1000),(43,'Comercial varejista, Loja','Verduras frescas','C-1',200),(44,'Comercial varejista, Loja','Vinhos','C-1',200),(45,'Comercial varejista, Loja','Vulcanização','C-2',1000),(46,'Serviços profissionais pessoais e técnicos','Agências bancárias','D-2',300),(47,'Serviços profissionais pessoais e técnicos','Agências de correios','D-1',400),(48,'Serviços profissionais pessoais e técnicos','Centrais telefônicas','D-1',200),(49,'Serviços profissionais pessoais e técnicos','Cabeleireiros','D-1',200),(50,'Serviços profissionais pessoais e técnicos','Copiadora','D-1',400),(51,'Serviços profissionais pessoais e técnicos','Encadernadoras','D-1',1000),(52,'Serviços profissionais pessoais e técnicos','Escritórios','D-1',700),(53,'Serviços profissionais pessoais e técnicos','Estúdios de rádio ou de televisão ou de fotografia','D-1',300),(54,'Serviços profissionais pessoais e técnicos','Laboratórios químicos','D-4',500),(55,'Serviços profissionais pessoais e técnicos','Laboratórios (outros)','D-4',300),(56,'Serviços profissionais pessoais e técnicos','Lavanderias','D-3',300),(57,'Serviços profissionais pessoais e técnicos','Oficinas hidráulicas ou mecânicas','D-3',600),(58,'Serviços profissionais pessoais e técnicos','Oficinas elétricas','D-3',200),(59,'Serviços profissionais pessoais e técnicos','Pinturas','D-3',500),(60,'Serviços profissionais pessoais e técnicos','Processamentos de dados','D-1',400),(61,'Educacional e cultura física','Academias de ginástica e similares','E-3',300),(62,'Educacional e cultura física','Pré-escolas e similares','E-5',300),(63,'Educacional e cultura física','Creches e similares','E-5',300),(64,'Educacional e cultura física','Escolas em geral','E-1',300),(65,'Educacional e cultura física','Escolas em geral','E-2',300),(66,'Educacional e cultura física','Escolas em geral','E-4',300),(67,'Educacional e cultura física','Escolas em geral','E-6',300),(68,'Locais de reunião de público','Bibliotecas','F-1',2000),(69,'Locais de reunião de público','Cinemas, teatros e similares','F-5',600),(70,'Locais de reunião de público','Circos e assemelhados','F-7',500),(71,'Locais de reunião de público','Centros esportivos e de exibição','F-3',150),(72,'Locais de reunião de público','Clubes sociais, boates e similares','F-6',600),(73,'Locais de reunião de público','Clubes sociais, boates e similares','F-11',600),(74,'Locais de reunião de público','Estações e terminais de passageiros','F-4',200),(75,'Locais de reunião de público','Exposições','F-10',0),(76,'Locais de reunião de público','Igrejas e templos','F-2',200),(77,'Locais de reunião de público','Lan house, jogos eletrônicos','F-6',450),(78,'Locais de reunião de público','Museus','F-1',300),(79,'Locais de reunião de público','Restaurantes','F-8',300),(80,'Serviços automotivos e assemelhados','Estacionamentos','G-1',200),(81,'Serviços automotivos e assemelhados','Estacionamentos','G-2',200),(82,'Serviços automotivos e assemelhados','Oficinas de conserto de veículos e manutenção','G-4',300),(83,'Serviços automotivos e assemelhados','Postos de abastecimentos (tanque enterrado)','G-3',300),(84,'Serviços automotivos e assemelhados','Hangares','G-5',200),(85,'Serviços de saúde e Institucionais','Asilos','H-2',350),(86,'Serviços de saúde e Institucionais','Clínicas e consultórios médicos ou odontológicos','H-6',300),(87,'Serviços de saúde e Institucionais','Hospitais em geral','H-1',300),(88,'Serviços de saúde e Institucionais','Hospitais em geral','H-3',300),(89,'Serviços de saúde e Institucionais','Presídios e similares','H-5',200),(90,'Serviços de saúde e Institucionais','Quartéis e similares','H-4',450),(91,'Serviços de saúde e Institucionais','Veterinárias','H-1',300),(92,'Industrial','Aparelhos eletroeletrônicos, fotográficos, ópticos','I-2',400),(93,'Industrial','Acessórios para automóveis','I-1',300),(94,'Industrial','Acetileno','I-2',700),(95,'Industrial','Alimentação (alimentos)','I-2',800),(96,'Industrial','Aço, corte e dobra, sem pintura, sem embalagem','I-1',40),(97,'Industrial','Artigos de borracha, coriça, couro, feltro, espuma','I-2',600),(98,'Industrial','Artigos de argila, cerâmica ou porcelanas','I-1',200),(99,'Industrial','Artigos de bijuteria','I-1',200),(100,'Industrial','Artigos de cera','I-2',1000),(101,'Industrial','Artigos de gesso','I-1',80),(102,'Industrial','Artigos de madeira em geral','I-2',800),(103,'Industrial','Artigos de madeira, impregnação','I-3',3000),(104,'Industrial','Artigos de mármore','I-1',40),(105,'Industrial','Artigos de metal, forjados','I-1',80),(106,'Industrial','Artigos de metal, fresados','I-1',200),(107,'Industrial','Artigos de peles','I-2',500),(108,'Industrial','Artigos de plásticos em geral','I-2',1000),(109,'Industrial','Artigos de tabaco','I-1',200),(110,'Industrial','Artigos de vidro','I-1',80),(111,'Industrial','Automotiva e autopeças (exceto pintura)','I-1',300),(112,'Industrial','Automotiva e autopeças (pintura)','I-2',500),(113,'Industrial','Aviões','I-2',600),(114,'Industrial','Balanças','I-1',300),(115,'Industrial','Barcos de madeira ou de plástico','I-2',600),(116,'Industrial','Barcos de metal','I-2',600),(117,'Industrial','Baterias','I-2',800),(118,'Industrial','Bebidas destilada','I-2',500),(119,'Industrial','Bebidas não alcoólicas','I-1',80),(120,'Industrial','Bicicletas','I-1',200),(121,'Industrial','Brinquedos','I-2',500),(122,'Industrial','Café (inclusive torrefação)','I-2',400),(123,'Industrial','Caixotes barris ou pallets de madeira','I-2',1000),(124,'Industrial','Calçados','I-2',600),(125,'Industrial','Carpintarias e marcenarias','I-2',800),(126,'Industrial','Cera de polimento','I-3',2000),(127,'Industrial','Cerâmica','I-1',200),(128,'Industrial','Cereais','I-3',1700),(129,'Industrial','Cervejarias','I-1',80),(130,'Industrial','Chapas de aglomerado ou compensado','I-1',300),(131,'Industrial','Chocolate','I-2',400),(132,'Industrial','Cimento','I-1',40),(133,'Industrial','Cobertores, tapetes','I-2',600),(134,'Industrial','Colas','I-2',800),(135,'Industrial','Colchões (exceto espuma)','I-2',500),(136,'Industrial','Condimentos, conservas','I-1',40),(137,'Industrial','Confeitarias','I-2',400),(138,'Industrial','Congelados','I-2',800),(139,'Industrial','Artigos de cortiça','I-2',600),(140,'Industrial','Couro, curtume','I-2',700),(141,'Industrial','Couro sintético','I-2',1000),(142,'Industrial','Defumados','I-1',200),(143,'Industrial','Discos de música','I-2',600),(144,'Industrial','Doces','I-2',800),(145,'Industrial','Espumas','I-3',3000),(146,'Industrial','Estaleiros','I-2',700),(147,'Industrial','Farinhas','I-3',2000),(148,'Industrial','Feltros','I-2',600),(149,'Industrial','Fermentos','I-2',800),(150,'Industrial','Ferragens','I-1',300),(151,'Industrial','Fiações','I-2',600),(152,'Industrial','Fibras sintéticas','I-1',300),(153,'Industrial','Fios elétricos','I-1',300),(154,'Industrial','Flores artificiais','I-1',300),(155,'Industrial','Fornos de secagem com grade de madeira','I-2',1000),(156,'Industrial','Forragem','I-3',2000),(157,'Industrial','Frigoríficos','I-3',2000),(158,'Industrial','Fundições de metal','I-1',40),(159,'Industrial','Galpões de secagem com grade de madeira','I-2',400),(160,'Industrial','Galvanoplastia','I-1',200),(161,'Industrial','Geladeiras','I-2',1000),(162,'Industrial','Gelatinas','I-2',800),(163,'Industrial','Gesso','I-1',80),(164,'Industrial','Gorduras comestíveis','I-2',1000),(165,'Industrial','Gráficas (empacotamento)','I-3',2000),(166,'Industrial','Gráficas (produção)','I-2',400),(167,'Industrial','Guarda-chuvas','I-1',300),(168,'Industrial','Instrumentos musicais','I-2',600),(169,'Industrial','Janelas e portas de madeira','I-2',800),(170,'Industrial','Joias','I-1',200),(171,'Industrial','Laboratórios farmacêuticos','I-1',300),(172,'Industrial','Laboratórios químicos','I-2',500),(173,'Industrial','Lápis','I-2',600),(174,'Industrial','Lâmpadas','I-1',40),(175,'Industrial','Latas metálicas, sem embalagem','I-1',100),(176,'Industrial','Laticínios','I-1',200),(177,'Industrial','Malas, fábrica','I-2',1000),(178,'Industrial','Malharias','I-1',300),(179,'Industrial','Máquinas de lavar de costura ou de escritório','I-1',300),(180,'Industrial','Massas alimentícias','I-2',1000),(181,'Industrial','Mastiques','I-2',1000),(182,'Industrial','Matadouro / Abatedouro / Criadouro','I-1',40),(183,'Industrial','Materiais sintéticos','I-3',2000),(184,'Industrial','Metalúrgica','I-1',200),(185,'Industrial','Montagens de automóveis','I-1',300),(186,'Industrial','Motocicletas','I-1',300),(187,'Industrial','Motores elétricos','I-1',300),(188,'Industrial','Móveis','I-2',600),(189,'Industrial','Olarias','I-1',100),(190,'Industrial','Óleos comestíveis e óleos em geral','I-2',1000),(191,'Industrial','Padarias','I-2',1000),(192,'Industrial','Papéis (acabamento)','I-2',500),(193,'Industrial','Papéis (preparo de celulose)','I-1',80),(194,'Industrial','Papéis (procedimento)','I-2',800),(195,'Industrial','Papelões betuminados','I-3',2000),(196,'Industrial','Papelões ondulados','I-2',800),(197,'Industrial','Pedras','I-1',40),(198,'Industrial','Perfumes','I-1',300),(199,'Industrial','Pneus','I-2',700),(200,'Industrial','Produtos adesivos','I-2',1000),(201,'Industrial','Produtos de adubo químico','I-1',200),(202,'Industrial','Produtos alimentícios (expedição)','I-2',1000),(203,'Industrial','Produtos com ácido acético','I-1',200),(204,'Industrial','Produtos com ácido carbônico','I-1',40),(205,'Industrial','Produtos com ácido inorgânico','I-1',80),(206,'Industrial','Produtos com albumina','I-3',2000),(207,'Industrial','Produtos com alcatrão','I-2',800),(208,'Industrial','Produtos com amido','I-3',2000),(209,'Industrial','Produtos com soda','I-1',40),(210,'Industrial','Produtos com soda','I-1',40),(211,'Industrial','Produtos de limpeza','I-3',2000),(212,'Industrial','Produtos graxos','I-2',1000),(213,'Industrial','Produtos refratários','I-1',200),(214,'Industrial','Rações balanceadas','I-2',800),(215,'Industrial','Relógios','I-1',300),(216,'Industrial','Resinas','I-3',3000),(217,'Industrial','Resinas, em placas','I-2',800),(218,'Industrial','Roupas','I-2',500),(219,'Industrial','Sabões','I-1',300),(220,'Industrial','Sacos de papel','I-2',800),(221,'Industrial','Sacos de juta','I-2',500),(222,'Industrial','Serralheria','I-1',200),(223,'Industrial','Sorvetes','I-1',80),(224,'Industrial','Sucos de fruta','I-1',200),(225,'Industrial','Tapetes','I-2',600),(226,'Industrial','Têxteis em geral (tecidos)','I-2',700),(227,'Industrial','Tintas e solventes','I-3',4000),(228,'Industrial','Tintas e vernizes','I-3',2000),(229,'Industrial','Tintas látex','I-2',800),(230,'Industrial','Tintas não-inflamáveis','I-1',200),(231,'Industrial','Transformadores','I-1',200),(232,'Industrial','Tratamento de madeira','I-3',3000),(233,'Industrial','Tratores','I-1',300),(234,'Industrial','Vagões','I-1',200),(235,'Industrial','Vassouras ou escovas','I-2',700),(236,'Industrial','Velas de cera','I-3',1300),(237,'Industrial','Vidros ou espelhos','I-1',200),(238,'Industrial','Vinagres','I-1',80),(239,'Industrial','Vulcanização','I-2',1000);
/*!40000 ALTER TABLE `cargaincendio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depositos`
--

DROP TABLE IF EXISTS `depositos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `depositos` (
  `iddeposito` int(11) NOT NULL AUTO_INCREMENT,
  `material` varchar(100) DEFAULT NULL,
  `altura1` int(11) DEFAULT NULL,
  `altura2` int(11) DEFAULT NULL,
  `altura4` int(11) DEFAULT NULL,
  `altura6` int(11) DEFAULT NULL,
  `altura8` int(11) DEFAULT NULL,
  `altura10` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddeposito`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depositos`
--

LOCK TABLES `depositos` WRITE;
/*!40000 ALTER TABLE `depositos` DISABLE KEYS */;
INSERT INTO `depositos` VALUES (1,'Açúcar',3780,7560,15120,22680,30240,37800),(2,'Açúcar, produtos de',360,720,1440,2160,2880,3600),(3,'Acumuladores/baterias',360,720,1440,2160,2880,3600),(4,'Adubos químicos',90,180,360,540,720,900),(5,'Alcatrão',1530,3060,6120,9180,12240,15300),(6,'Algodão',585,1170,2340,3510,4680,5850),(7,'Alimentação (alimentos industrializados)',1530,3060,6120,9180,12240,15300),(8,'Aparelhos eletroeletrônicos',180,360,720,1080,1440,1800),(9,'Aparelhos fotográficos',270,540,1080,1620,2160,2700),(10,'Bebidas alcoólicas',360,720,1440,2160,2880,3600),(11,'Borracha',12870,25740,51480,77220,102960,128700),(12,'Artigos de borracha',2250,4500,9000,13500,18000,22500),(13,'Brinquedos',360,720,1440,2160,2880,3600),(14,'Cabos elétricos',270,540,1080,1620,2160,2700),(15,'Cacau, produtos de',2610,5220,10440,15660,20880,26100),(16,'Café cru',1305,2610,5220,7830,10440,13050),(17,'Caixas de madeira',270,540,1080,1620,2160,2700),(18,'Calçado',180,360,720,1080,1440,1800),(19,'Celuloide',1530,3060,6120,9180,12240,15300),(20,'Cera',1530,3060,6120,9180,12240,15300),(21,'Cera, artigos de',945,1890,3780,5670,7560,9450),(22,'Chocolate	',1530,3060,6120,9180,12240,15300),(23,'Colas combustíveis',1530,3060,6120,9180,12240,15300),(24,'Colchões não sintéticos',2250,4500,9000,13500,18000,22500),(25,'Cosméticos',248,495,990,1485,1980,2475),(26,'Couro',765,1530,3060,4590,6120,7650),(27,'Couro, artigos de',270,540,1080,1620,2160,2700),(28,'Couro sintético',765,1530,3060,4590,6120,7650),(29,'Couro sintético, artigos de',360,720,1440,2160,2880,3600),(30,'Depósitos de mercadorias incombustíveis em pilhas de caixas de madeira ou de papelão',90,180,360,540,720,900),(31,'Depósitos de mercadorias incombustíveis em pilhas de caixas de plástico',90,180,360,540,720,900),(32,'Depósitos de mercadorias incombustíveis em estantes metálicas (sem embalagem)',9,18,36,54,72,90),(33,'Depósitos de paletes de madeira',1530,3060,6120,9180,12240,15300),(34,'Espumas sintéticas',1125,2250,4500,6750,9000,11250),(35,'Espumas sintéticas, artigos de',360,720,1440,2160,2880,3600),(36,'Farinha em sacos',3780,7560,15120,22680,30240,37800),(37,'Feltro',360,720,1440,2160,2880,3600),(38,'Feno, fardos de',450,900,1800,2700,3600,4500),(39,'Fiação, produtos de fio',765,1530,3060,4590,6120,7650),(40,'Fiação, produtos de lã',855,1710,3420,5130,6840,8550),(41,'Fósforos',360,720,1440,2160,2880,3600),(42,'Gorduras',8100,16200,32400,48600,64800,81000),(43,'Gorduras comestíveis',8505,17010,34020,51030,68040,85050),(44,'Grãos, sementes',360,720,1440,2160,2880,3600),(45,'Instrumentos de ótica',90,180,360,540,720,900),(46,'Legumes, verduras, hortifrutigranjeiros',158,315,630,945,1260,1575),(47,'Leite em pó',4050,8100,16200,24300,32400,40500),(48,'Lenha',1125,2250,4500,6750,9000,11250),(49,'Madeira em troncos',2835,5670,11340,17010,22680,28350),(50,'Madeira, aparas',945,1890,3780,5670,7560,9450),(51,'Madeira, restos de',1350,2700,5400,8100,10800,13500),(52,'Madeira, vigas e tábuas',1890,3780,7560,11340,15120,18900),(53,'Malte',6030,12060,24120,36180,48240,60300),(54,'Massas alimentícias',765,1530,3060,4590,6120,7650),(55,'Materiais de construção',360,720,1440,2160,2880,3600),(56,'Materiais sintéticos',2655,5310,10620,15930,21240,26550),(57,'Material de escritório',585,1170,2340,3510,4680,5850),(58,'Medicamentos, embalagem',360,720,1440,2160,2880,3600),(59,'Depósitos de mercadorias incombustíveis em pilhas de caixas de plástico',90,180,360,540,720,900),(60,'Móveis de madeira',360,720,1440,2160,2880,3600),(61,'Móveis, estofados sem espuma sintética',180,360,720,1080,1440,1800),(62,'Painel de madeira aglomerada',3015,6030,12060,18090,24120,30150),(63,'Papel',3780,7560,15120,22680,30240,37800),(64,'Papel prensado',945,1890,3780,5670,7560,9450),(65,'Papelaria, estoque',495,990,1980,2970,3960,4950),(66,'Produtos farmacêuticos, estoque',360,720,1440,2160,2880,3600),(67,'Peças automotivas',360,720,1440,2160,2880,3600),(68,'Perfumaria, artigos de',225,450,900,1350,1800,2250),(69,'Pneus',810,1620,3240,4860,6480,8100),(70,'Portas de madeira',810,1620,3240,4860,6480,8100),(71,'Produtos químicos combustíveis',450,900,1800,2700,3600,4500),(72,'Queijos',1125,2250,4500,6750,9000,11250),(73,'Resinas sintéticas',1890,3780,7560,11340,15120,18900),(74,'Resinas sintéticas, placas de',1530,3060,6120,9180,12240,15300),(75,'Sabão',1890,3780,7560,11340,15120,18900),(76,'Sacos de papel',5670,11340,22680,34020,45360,56700),(77,'Sacos de plástico',11340,22680,45360,68040,90720,113400),(78,'Tabaco em bruto',765,1530,3060,4590,6120,7650),(79,'Tabaco, artigos de',945,1890,3780,5670,7560,9450),(80,'Tapeçarias',765,1530,3060,4590,6120,7650),(81,'Tapeçarias',900,1800,3600,5400,7200,9000),(82,'Tecidos sintéticos',585,1170,2340,3510,4680,5850),(83,'Tecidos, fardos de algodão',585,1170,2340,3510,4680,5850),(84,'Tecidos, seda artificial',450,900,1800,2700,3600,4500),(85,'Toldos ou lonas',450,900,1800,2700,3600,4500),(86,'Velas de cera',10080,20160,40320,60480,80640,100800),(87,'Vernizes',1125,2250,4500,6750,9000,11250),(88,'Papel',3780,7560,15120,22680,30240,37800),(89,'Papel prensado',945,1890,3780,5670,7560,9450),(90,'Papelaria, estoque',495,990,1980,2970,3960,4950),(91,'Vernizes de cera',2250,4500,9000,13500,18000,22500);
/*!40000 ALTER TABLE `depositos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiais`
--

DROP TABLE IF EXISTS `materiais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materiais` (
  `idmaterial` int(11) NOT NULL AUTO_INCREMENT,
  `material` varchar(50) DEFAULT NULL,
  `podercalorifico` float DEFAULT NULL,
  PRIMARY KEY (`idmaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiais`
--

LOCK TABLES `materiais` WRITE;
/*!40000 ALTER TABLE `materiais` DISABLE KEYS */;
INSERT INTO `materiais` VALUES (1,'Acetileno	',50),(2,'Acetileno dissolvido',17),(3,'Acetona',30),(4,'Acrílico',28),(5,'Açúcar',17),(6,'Amido',17),(7,'Algodão',18),(8,'Álcool Alílico',34),(9,'Álcool Amílico',42),(10,'Álcool Etílico',25),(11,'Álcool metílico',21),(12,'Benzeno',40),(13,'Benzina',42),(14,'Celulose',16),(15,'Biodiesel',39),(16,'Borracha espuma',37),(17,'Borracha em tiras',32),(18,'Butano',46),(19,'Cacau em pó',17),(20,'Café',17),(21,'Cafeína',21),(22,'Cálcio',4),(23,'Carbono',34),(24,'Carvão',36),(25,'Celulose',16),(26,'Cereais',17),(27,'C-Heptano',46),(28,'C-Pentano',46),(29,'C-Propano',50),(30,'C-Hexano',46),(31,'Chocolate',25),(32,'Chá',17),(33,'Cloreto de polivinil',21),(34,'Couro',19),(35,'Creosoto/fenol',37),(36,'D-glucose',15),(37,'Diesel',43),(38,'Dietilamina',42),(39,'Dietilcetona',34),(40,'Dietileter',37),(41,'Epóxi',34),(42,'Etano',47),(43,'Etanol',26),(44,'Eteno',50),(45,'Éter amílico',42),(46,'Éter etílico',34),(47,'Etileno',50),(48,'Etino',48),(49,'Enxofre',8.4),(50,'Farinha de trigo',17),(51,'Hexaptano',46),(52,'Fenol',34),(53,'Fibra sintética 6,6',29),(54,'Fósforo',25),(55,'Gás Natural',26),(56,'Gasolina',47),(57,'Glicerina',17),(58,'Gordura e óleo vegetal',42),(59,'Grãos',17),(60,'Grãos',41),(61,'Heptano',46),(62,'Hexametileno',46),(63,'Hexano',46),(64,'Hidreto de sódio',9),(65,'Hidrogênio',143),(66,'Hidreto de magnésio',17),(67,'Látex',44),(68,'Lã',23),(69,'Leite em pó',17),(70,'Linho',17),(71,'Linóleo',2),(72,'Lixo de cozinha',18),(73,'Madeira',19),(74,'Magnésio',25),(75,'Manteiga',37),(76,'Polipropileno',43),(77,'Metano',50),(78,'Metanol',19),(79,'Monóxido de carbono',10),(80,'Nafta',42),(81,'N-Butano',45),(82,'Nitrocelulose',8.4),(83,'N-Octano',44),(84,'N-Pentano',45),(85,'Óleo de linhaça',37),(86,'Óleo vegetal',42),(87,'Palha',16),(88,'Papel',17),(89,'Parafina',46),(90,'Petróleo',41),(91,'Plástico',31),(92,'Poliacrilonitrico',30),(93,'Policarbonato',29),(94,'Poliéster',31),(95,'Poliestireno',39),(96,'Polietileno',44),(97,'Polimetilmetacrilico',24),(98,'Polioximetileno',15),(99,'Poliuretano',23),(100,'Polivinilclorido',16),(101,'Propano',46),(102,'PVC',17),(103,'Resina de fenol',25),(104,'Resina de uréia',21),(105,'Resina melamínica',18),(106,'Seda',19),(107,'Sisal',17),(108,'Tabaco',17),(109,'Tolueno',42),(110,'Turfa',34),(111,'Ureia',9),(112,'Viscose',17);
/*!40000 ALTER TABLE `materiais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocupacao`
--

DROP TABLE IF EXISTS `ocupacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocupacao` (
  `idocupacao` int(11) NOT NULL AUTO_INCREMENT,
  `grupo` varchar(2) DEFAULT NULL,
  `uso` varchar(30) DEFAULT NULL,
  `divisao` varchar(5) DEFAULT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idocupacao`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocupacao`
--

LOCK TABLES `ocupacao` WRITE;
/*!40000 ALTER TABLE `ocupacao` DISABLE KEYS */;
INSERT INTO `ocupacao` VALUES (1,'A','Residencial','A-1','Habitação unifamiliar'),(2,'A','Residencial','A-2','Habitação multifamiliar'),(3,'A','Residencial','A-3','Habitação coletiva'),(4,'B','Serviço de Hospedagem','B-1','Hotel e assemelhado'),(5,'B','Serviço de Hospedagem','B-2','Hotel residencial'),(6,'C','Comercial','C-1','Comércio com baixa carga de incêndio (até 300 MJ/m²)'),(7,'C','Comercial','C-2','Comércio com média e alta carga de incêndio (acima de 300 MJ/m²)'),(8,'C','Comercial','C-3','Shopping centers'),(9,'D','Serviço Profissional','D-1','Local para prestação de serviço profissional ou condução de negócios'),(10,'D','Serviço Profissional','D-2','Agência bancária'),(11,'D','Serviço Profissional','D-3','Serviço de reparação (exceto os classificados em G-4)'),(12,'D','Serviço Profissional','D-4','Laboratório'),(13,'E','Educacional e Cultura Física','E-1','Escola em geral'),(14,'E','Educacional e Cultura Física','E-2','Escola especial'),(15,'E','Educacional e Cultura Física','E-3','Espaço para cultura física'),(16,'E','Educacional e Cultura Física','E-4','Centro de treinamento profissional'),(17,'E','Educacional e Cultura Física','E-5','Pré-escola'),(18,'E','Educacional e Cultura Física','E-6','Escola para portadores de necessidades especiais'),(19,'F','Local de Reunião Público','F-1','Local onde há objeto de valor inestimável'),(20,'F','Local de Reunião Público','F-2','Local religioso e velório'),(21,'F','Local de Reunião Público','F-3','Centro esportivo e de exibição'),(22,'F','Local de Reunião Público','F-4','Estação e terminal de passageiro'),(23,'F','Local de Reunião Público','F-5','Arte cênica e auditório'),(24,'F','Local de Reunião Público','F-6','Casas de shows'),(25,'F','Local de Reunião Público','F-7','Construção provisóoria e eventos temporários'),(26,'F','Local de Reunião Público','F-8','Local para refeição'),(27,'F','Local de Reunião Público','F-9','Recreação pública'),(28,'F','Local de Reunião Público','F-10','Exposição de objetos ou animais'),(29,'F','Local de Reunião Público','F-11','Clubes sociais e diversão'),(30,'G','Serviço Automotivo e assemelha','G-1','Garagem sem acesso de público e sem abastecimento'),(31,'G','Serviço Automotivo e assemelha','G-2','Garagem com acesso de público e sem abastecimento'),(32,'G','Serviço Automotivo e assemelha','G-3','Local dotado de abastecimento combustível'),(33,'G','Serviço Automotivo e assemelha','G-4','Serviço de conservação, manutenção e reparos'),(34,'G','Serviço Automotivo e assemelha','G-5','Hangares'),(35,'G','Serviço Automotivo e assemelha','G-6','Marinas'),(36,'H','Serviço de Saúde e Institucion','H-1','Hospital veterinário e assemelhados'),(37,'H','Serviço de Saúde e Institucion','H-2','Local onde pessoaos requerem cuidados especiais por limitações físicas ou mentais'),(38,'H','Serviço de Saúde e Institucion','H-3','Hospital e assemelhado'),(39,'H','Serviço de Saúde e Institucion','H-4','Edificações das forças armadas policiais'),(40,'H','Serviço de Saúde e Institucion','H-5','Local onde a liberdade das pessoas sofre restrições'),(41,'H','Serviço de Saúde e Institucion','H-6','Clínica e consultório médico e odontológico'),(42,'I','Indústria','I-1','Locais onde as atividades exercidas e os materiais utilizados apresentam baixo potencial de incêndio (carga de incêndio até 300 MJ/m²)'),(43,'I','Indústria','I-2','Locais onde as atividades exercidas e os materiais utilizados apresentam médio potencial de incêndio (carga de incêndio acima de 300 MJ/m² e até 1200 '),(44,'I','Indústria','I-3','Locais onde a risco de incêndio (Carga de incêndio acima de 1200 MJ/m²)'),(45,'J','Depósito','J-1','Depósiitos de material incombustível'),(46,'J','Depósito','J-2','Todo tipo de depósito (carga de incêndio até 300 MJ/m²)'),(47,'J','Depósito','J-3','Todo tipo de depósito (carga de incêndio acima de 300 MJ/m² e até 1200 MJ/m²)'),(48,'J','Depósito','J-4','Todo tipo de depósito (Carga de incêndio acima de 1200 MJ/m²)'),(49,'L','Explosivo','L-1','Comércio'),(50,'L','Explosivo','L-2','Indústria'),(51,'L','Explosivo','L-3','Depósito'),(52,'M','Especial','M-1','Túnel'),(53,'M','Especial','M-2','Líquido ou gás inflamáveis ou combustíveis'),(54,'M','Especial','M-3','Central de comunicação e energia-equipamentos'),(55,'M','Especial','M-4','Propriedade em transformação'),(56,'M','Especial','M-5','Unidades de armazenamento e beneficiamento de produtos agrícolas'),(57,'M','Especial','M-6','Terra selvagem'),(58,'M','Especial','M-7','Pátio de Contêineres');
/*!40000 ALTER TABLE `ocupacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proprietario` varchar(150) NOT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `municipio` varchar(150) DEFAULT NULL,
  `responsavel` varchar(150) DEFAULT NULL,
  `ocupacao` varchar(150) DEFAULT NULL,
  `crea` varchar(50) DEFAULT NULL,
  `risco` varchar(30) DEFAULT NULL,
  `numerodehidrantes` varchar(15) DEFAULT NULL,
  `tipodesistema` varchar(50) DEFAULT NULL,
  `materialdatubulacao` varchar(100) DEFAULT NULL,
  `rti` varchar(50) DEFAULT NULL,
  `vazaobomba` varchar(50) DEFAULT NULL,
  `hmbomba` varchar(50) DEFAULT NULL,
  `diametromangueira` varchar(50) DEFAULT NULL,
  `vazaoH` varchar(50) DEFAULT NULL,
  `vazaoH1` varchar(50) DEFAULT NULL,
  `vazaoHM` varchar(50) DEFAULT NULL,
  `vazaoR` varchar(50) DEFAULT NULL,
  `vazaoS` varchar(50) DEFAULT NULL,
  `alturaH` varchar(50) DEFAULT NULL,
  `alturaH1` varchar(50) DEFAULT NULL,
  `alturaHM` varchar(50) DEFAULT NULL,
  `alturaR` varchar(50) DEFAULT NULL,
  `alturaS` varchar(50) DEFAULT NULL,
  `diaH` varchar(50) DEFAULT NULL,
  `diaH1` varchar(50) DEFAULT NULL,
  `diaHM` varchar(50) DEFAULT NULL,
  `diaR` varchar(50) DEFAULT NULL,
  `diaS` varchar(50) DEFAULT NULL,
  `pcH` varchar(50) DEFAULT NULL,
  `pcH1` varchar(50) DEFAULT NULL,
  `pcHM` varchar(50) DEFAULT NULL,
  `pcR` varchar(50) DEFAULT NULL,
  `pcS` varchar(50) DEFAULT NULL,
  `desH` varchar(50) DEFAULT NULL,
  `desH1` varchar(50) DEFAULT NULL,
  `desHM` varchar(50) DEFAULT NULL,
  `desR` varchar(50) DEFAULT NULL,
  `desS` varchar(50) DEFAULT NULL,
  `velH` varchar(50) DEFAULT NULL,
  `velH1` varchar(50) DEFAULT NULL,
  `velHM` varchar(50) DEFAULT NULL,
  `velR` varchar(50) DEFAULT NULL,
  `velS` varchar(50) DEFAULT NULL,
  `compTotH` varchar(50) DEFAULT NULL,
  `compTotH1` varchar(50) DEFAULT NULL,
  `compTotHM` varchar(50) DEFAULT NULL,
  `compTotR` varchar(50) DEFAULT NULL,
  `compTotS` varchar(50) DEFAULT NULL,
  `compMangH` varchar(50) DEFAULT NULL,
  `compMangH1` varchar(50) DEFAULT NULL,
  `compMangHM` varchar(50) DEFAULT NULL,
  `diaMangH1` varchar(50) DEFAULT NULL,
  `diaMangHM` varchar(50) DEFAULT NULL,
  `difPressao` varchar(50) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `altura` varchar(50) DEFAULT NULL,
  `divisao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` VALUES (1,'Teste1','Teste111','Teste1','Teste1','Residencial','Teste1','Leve','4','TIPO-2','Aço galvanizado','8','391,76','46,23','0.04','180','211,76','391,76','391,76','391,76','15','15,35','35,06','37,95','45,8','0.065','0.065','0.065','0.065','0.075','6,72','5,88','9,67','3,85','0,43','-2,5','-2,5','1,5','0','4','0,92','1,08','2,01','2,01','1,51','41,3','12,5','44','44,7','10','30','30','30','0,04','0,04','0,5','400','6','A-2'),(2,'Teste2','Teste2','Teste2','Teste2','Residencial','Teste2','Leve','4','TIPO-2','Aço galvanizado','8','391,76','46,23','0.04','180','211,76','391,76','391,76','391,76','15','15,35','35,88','37,95','45,8','0.065','0.065','0.065','0.065','0.075','6,72','5,88','8,85','3,85','0,43','-2,5','-2,5','1,5','0','4','0,92','1,08','2,01','2,01','1,51','41,3','12,5','34,5','44,7','10','30','30','30','0,04','0,04','0,5','22','2','A-3'),(3,'Teste3','Teste3','Teste3','Teste3','Residencial','Teste3','Leve','2','TIPO-2','Aço galvanizado','8','391,76','41,7','0.04','180','211,76','391,76','391,76','391,76','15','15,35','35,58','32,07','39,99','0.065','0.065','0.065','0.065','0.075','3,78','2,94','4,62','3,92','1,71','-2,5','-2,5','1,5','0','4','0,92','1,08','2,01','2,01','1,51','41,3','12,5','19,5','45,5','39,9','15','15','15','0,04','0,04','0,5','22','2','A-2'),(4,'Projeto TCC','Projeto TCC','Projeto TCC','Projeto TCC','Comercial varejista, Loja','00000/AA','Leve','4','TIPO-2','Aço galvanizado','8','382,35','47,44','0.04','180','202,35','382,35','382,35','382,35','15','15','36,76','38,11','46,85','0.065','0.065','0.065','0.065','0.075','6,8','6,31','9,18','4,74','0,59','-2,5','-2,5','-1,5','0','4','0,92','1,04','1,96','1,96','1,47','45,1','16,8','40','57,5','14,4','30','30','30','0,04','0,04','0,5','1500','6','C-1'),(5,'Projeto TCC','Projeto TCC','Projeto TCC','Projeto TCC','Comercial varejista, Loja','00000/AA','Leve','4','TIPO-2','Aço galvanizado','8','362,35','45,03','0.04','180','182,35','362,35','362,35','362,35','15','15','22,65','36,2','44,49','0.065','0.065','0.065','0.065','0.075','5,83','5,38','20,88','4,29','0,54','-2,5','-2,5','-1,5','0','4','0,92','0,93','1,86','1,86','1,39','45,1','16,8','40','57,5','14,4','30','30','30','0,04','0,04','0,45','1500','6','C-1'),(6,'Projeto TCC','Projeto TCC','Projeto TCC','Projeto TCC','Comercial varejista, Loja','00000/AA','Leve','4','TIPO-2','Aço galvanizado','8','362,35','59,03','0.04','180','182,35','362,35','362,35','362,35','15','15','29,65','50,2','58,49','0.065','0.065','0.065','0.065','0.075','12,83','12,38','27,88','4,29','0,54','-2,5','-2,5','-1,5','0','4','0,92','0,93','1,86','1,86','1,39','45,1','16,8','40','57,5','14,4','30','30','30','0,04','0,04','0,45','1500','6','C-1');
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hidrofire'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-19 22:35:41
