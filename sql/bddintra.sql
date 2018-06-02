#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
drop database IF exists bdd;
CREATE database bdd;
use bdd ;

#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: CLIENT
#------------------------------------------------------------
DROP TABLE IF EXISTS CLIENT;
CREATE TABLE CLIENT(
        uti_id      Int NOT NULL ,
        uti_nom    Varchar (25) ,
        uti_prenom Varchar (25) ,
        raison_cli  Varchar (45) ,
        commant_client varchar(150),
        adresse_cli Varchar (25) ,
        tel_cli     Char (10) ,
        PRIMARY KEY (uti_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: FOURNISSEURS
#------------------------------------------------------------
DROP TABLE IF EXISTS FOURNISSEURS;
CREATE TABLE FOURNISSEURS(
        id_four      Int NOT NULL ,
        nom_four     Varchar (25) ,
        adresse_four Varchar (25) ,
        tel_four     Char (10) ,
        PRIMARY KEY (id_four )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PRODUITS
#------------------------------------------------------------
DROP TABLE IF EXISTS PRODUITS;
CREATE TABLE PRODUITS(
        id_pro       Int NOT NULL ,
        nom_pro      Varchar (30) ,
        image_pro    Varchar(200),
        type_pro	 VARCHAR (30) ,
        marque_pro VARCHAR (10) ,
        stock_pro    Int ,
        prixunit_pro Float ,
        note		 int(2),
        PRIMARY KEY (id_pro )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: GROUPE_PRIVILEGE
#------------------------------------------------------------
DROP TABLE IF EXISTS GROUPE_PRIVILEGE;
CREATE TABLE GROUPE_PRIVILEGE(
        grp_id  Int NOT NULL ,
        grp_nom Varchar (25) ,
        PRIMARY KEY (grp_id )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: UTILISATEUR
#------------------------------------------------------------

DROP TABLE IF EXISTS UTILISATEUR;
CREATE TABLE UTILISATEUR(
        uti_id     Int Auto_increment NOT NULL ,
        uti_nom    Varchar (25) ,
        uti_prenom Varchar (25) ,
        uti_email  Varchar (25) ,
        login      Varchar (25) ,
        passwordd  Varchar (255) ,
        grp_id     Int ,
        PRIMARY KEY (uti_id ),
        FOREIGN KEY (grp_id) REFERENCES GROUPE_PRIVILEGE(grp_id)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: FACTURE
#------------------------------------------------------------
DROP TABLE IF EXISTS FACTURE;
CREATE TABLE FACTURE(
        fact_num       int (11) Auto_increment  NOT NULL ,
        fact_quantite  Int ,
        fact_prix      Int ,
        fact_reglement Date ,
        fact_adresse   Varchar (25),
        fact_codepost  char (5),
        fact_ville     Varchar(25),
        fact_pays      Varchar(25),
        fact_nom       Varchar (25) ,
        uti_id        Int ,
        PRIMARY KEY (fact_num),
        FOREIGN KEY (uti_id) REFERENCES UTILISATEUR(uti_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMMANDE
#------------------------------------------------------------
DROP TABLE IF EXISTS COMMANDE;
CREATE TABLE COMMANDE(
        com_num   int (11) Auto_increment  NOT NULL ,
        com_date  Date ,
        com_text  Varchar (25) ,
        com_prest Bool ,
        uti_id    Int ,
        fact_num  Int ,
        PRIMARY KEY (com_num),
        FOREIGN KEY (uti_id) REFERENCES UTILISATEUR(uti_id),
        FOREIGN KEY (fact_num) REFERENCES FACTURE(fact_num)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: archive
#------------------------------------------------------------
DROP TABLE IF EXISTS archive;
CREATE TABLE archive(
        id_arch int (11) Auto_increment NOT NULL,
        Date_archive Date ,
        uti_id Int,
        fact_num Int,
        com_num   int,
        PRIMARY KEY(id_arch),
        FOREIGN KEY (uti_id) REFERENCES UTILISATEUR(uti_id),
        FOREIGN KEY (fact_num) REFERENCES FACTURE(fact_num),
        FOREIGN KEY (com_num) REFERENCES COMMANDE(com_num)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PRESTATION
#------------------------------------------------------------

DROP TABLE IF EXISTS PRESTATION;
CREATE TABLE PRESTATION(
        id_prest   Int NOT NULL ,
        date_prest Date ,
        prix_prest Float ,
        com_num    Int ,
        PRIMARY KEY (id_prest ),
        FOREIGN KEY (com_num) REFERENCES COMMANDE(com_num)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: TECHNICIEN
#------------------------------------------------------------

DROP TABLE IF EXISTS TECHNICIEN;
CREATE TABLE TECHNICIEN(
        id_tech  Int NOT NULL ,
        uti_id   Int NOT NULL ,
        id_prest Int ,
        PRIMARY KEY (id_tech ,uti_id ),
        FOREIGN KEY (uti_id) REFERENCES UTILISATEUR(uti_id),
        FOREIGN KEY (id_prest) REFERENCES PRESTATION(id_prest)
)ENGINE=InnoDB;



#------------------------------------------------------------
# Table: RESSOURCES
#------------------------------------------------------------
DROP TABLE IF EXISTS RESSOURCES;
CREATE TABLE RESSOURCES(
        res_id   Int NOT NULL ,
        res_nom  Varchar (25) ,
        res_type Varchar (25) ,
        res_date Date ,
        grp_id   Int ,
        PRIMARY KEY (res_id ),
        FOREIGN KEY (grp_id) REFERENCES GROUPE_PRIVILEGE(grp_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: fournir
#------------------------------------------------------------
DROP TABLE IF EXISTS fournir;
CREATE TABLE fournir(
        id_four Int NOT NULL ,
        id_pro  Int NOT NULL ,
        PRIMARY KEY (id_four ,id_pro ),
        FOREIGN KEY (id_four) REFERENCES FOURNISSEURS(id_four),
        FOREIGN KEY (id_pro) REFERENCES PRODUITS(id_pro)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: comporter
#------------------------------------------------------------

DROP TABLE IF EXISTS comporter;
CREATE TABLE comporter(
        qte_livr Int ,
        id_pro   Int NOT NULL ,
        id_livr  Int NOT NULL ,
        PRIMARY KEY (id_pro ,id_livr ),
        FOREIGN KEY (id_pro) REFERENCES PRODUITS(id_pro)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: concerner
#------------------------------------------------------------
DROP TABLE IF EXISTS concerner;
CREATE TABLE concerner(
        qte_commande Int ,
        com_num      Int NOT NULL ,
        id_pro       Int NOT NULL ,
        PRIMARY KEY (com_num ,id_pro ),
        FOREIGN KEY (com_num) REFERENCES COMMANDE(com_num),
        FOREIGN KEY (id_pro) REFERENCES PRODUITS(id_pro)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: installer
#------------------------------------------------------------
DROP TABLE IF EXISTS installer;
CREATE TABLE installer(
        id_prest Int NOT NULL ,
        id_pro   Int NOT NULL ,
        PRIMARY KEY (id_prest ,id_pro ),
        FOREIGN KEY (id_prest) REFERENCES PRESTATION(id_prest),
        FOREIGN KEY (id_pro) REFERENCES PRODUITS(id_pro)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Les liaisons
#------------------------------------------------------------


#------------------------------------------------------------
# Trigger
#------------------------------------------------------------
#trigger1
#dropp l user grp_id = 3 dans la table client

DROP Trigger if exists ajoutclient;
DELIMITER //
CREATE Trigger ajoutclient
AFTER INSERT ON UTILISATEUR
FOR EACH ROW
BEGIN
IF new.grp_id = 3
THEN
INSERT INTO client(uti_id,uti_nom,uti_prenom)
  VALUES(new.uti_id,new.uti_nom,new.uti_prenom);
END IF ;
END //
DELIMITER ;



#trigger3
#droper les commandes dans la table archive

DROP Trigger if exists archiveCom;
DELIMITER //
CREATE Trigger archiveCom
AFTER INSERT ON COMMANDE
FOR EACH ROW
BEGIN
IF new.com_num >= 1
THEN
INSERT INTO archive(id_arch,Date_archive,uti_id,fact_num,com_num)
        VALUES(new.com_num,new.com_date,new.uti_id,new.fact_num,new.com_num);
END IF ;
END //
DELIMITER ;

#------------------------------------------------------------
# Table jeux dessai
#------------------------------------------------------------

insert into GROUPE_PRIVILEGE values
(1,'Administrateur'),
(2,'Technicien'),
(3,'Client');

INSERT INTO UTILISATEUR VALUES
(NULL, 'jean', 'vincent', 'maiden2164@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'marie', 'vincent', 'jesaispas@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'julien', 'vincent', 'test@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'louis', 'vincent', 'toto@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'antoine', 'vincent', 'testt@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'louise', 'vincent', 'maiden@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'olivier', 'vincent', '2163maiden@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'toto', 'vincent', 'test21@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'azerty', 'vincent', '123456aze@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'qwerty', 'vincent', '123aze@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'henrie', 'vincent', '123qwert@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'jaques', 'vincent', 'sdsdsdsd@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'nicolas', 'vincent', 'fdffdfdf@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'hugues', 'vincent', 'rtrtrtr@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3'),
(NULL, 'mathilde', 'vincent', 'fgfgfg@gmail.com', 'vincent', '87e1f221a672a14a323e57bb65eaea19d3ed3804', '3');

insert into PRODUITS (id_pro,nom_pro,image_pro,type_pro,marque_pro,stock_pro,prixunit_pro)
  values
  (1,'MSI GeForce GTX 1080 Ti','https://www.msi.com/asset/resize/image/global/product/product_0_20170323105218_58d33862572b9.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png','Carte-Graphique','MSI','20','599.99'),
  (2,'Intel Core i7-6700K','http://www.grooves.land/images/dgh/233368_00.png','CPU','Intel core','100','350'),
  (3,'ASUS ROG STRIX X99','https://s3.eu-west-2.amazonaws.com/hardwarepick/img/products/motherboards/asus-rog-strix-x99-gaming-2.png','Carte-Mere','ASUS-MB','50','349.99'),
  (4,'Intel Core i5 7400K','https://www.media-rdc.com/medias/8363c51a73c2399e9fc66ccceb55868d/corei5-processor-unlocked-box-j33801-002.png','CPU','Intel core','100','199.99'),
  (5,'Corsair RAM 16GO','http://www.corsair.com/~/media/corsair/product-images/dram/vengeance-led/red/large/veng_led_blk-red_2up.png','RAM','Corsair','40','100'),
  (6,'Geforce GTX 980','http://www.nvidia.fr/docs/IO/147347/geforce-gtx-980-front.png','Carte-Graphique','NVIDIA','20','300'),
  (7,'Kingston 4GO DDR4','http://pipertech.ca/media/catalog/product/cache/4/image/9df78eab33525d08d6e5fb8d27136e95/7/1/713zf-qnzcl._sl1500__1__1.png','RAM','Kingston','40','25'),
  (8,'ASRock 775i65G R3.0','http://www.asrock.com/mb/photo/775i65G%20R3.0(L4).png','Carte-Mere','ASRock','40','64'),
  (9,'ASUS X99-WS/IPMI','https://www.asus.com/websites/global/products/rgUoVLEGwnM3NTF2/img/hp/gaming/turboapp.png','Carte-Mere','ASUS-MB','20','449'),
  (10,'MSI A320M GAMING PRO','https://www.msi.com/asset/resize/image/global/product/product_6_20170407103908_58e6fbccf2ace.png62405b38c58fe0f07fcef2367d8a9ba1/600.png','Carte-Mere','MSI-MB','40','78'),
  (11,'AMD A10-7870K','https://avadirect-freedomusainc1.netdna-ssl.com/Pictures/big/10347023_2.png','CPU','AMD','40','78'),
  (12,'HyperX Fury 16 Go (2x 8Go)','http://cicindele.com.free.fr/Blogger/LD0001545856_2_0001545915.png','RAM','HyperX','40','152'),
  (13,'ASUS 210-SL-TC1GD3-L','http://picscdn.redblue.de/doi/pixelboxx-mss-57907779/fee_325_225_png/ASUS-210-SL-TC1GD3-L','Carte-Graphique','ASUS','40','32');


INSERT INTO `FACTURE` VALUES
(NULL, '1', '350', '2018-05-01', '9 rue ambroise thomas', '9200', 'Courbevoie', 'France', 'Intel Core i7-6700K',1);


 #-----------------------------------------------------------
 #VUES 
 #-----------------------------------------------------------

CREATE VIEW  infoArchive as
SELECT uti_nom, uti_prenom, fact_prix 
FROM archive, utilisateur, facture 
WHERE archive.fact_num = facture.fact_num 
AND facture.uti_id = utilisateur.uti_id;

