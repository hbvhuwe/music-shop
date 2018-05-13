/*create database*/
CREATE DATABASE music_shop_db;

/*create tables*/
/*create table group*/
CREATE TABLE Groups (
	GroupID int NOT NULL AUTO_INCREMENT,
	Name varchar(50) NOT NULL,
	Musician varchar(50) NOT NULL,
	Style varchar(50) DEFAULT NULL,
	PRIMARY KEY (GroupID),
	UNIQUE KEY Name_UNIQUE (Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table, representing musical group';

/*create table disk*/
CREATE TABLE Disk (
	DiskID int NOT NULL AUTO_INCREMENT,
	GroupID int NOT NULL,
	Name varchar(50) NOT NULL,
	PresentDate date DEFAULT NULL,
	Amount int DEFAULT NULL,
	Price double NOT NULL,
	PRIMARY KEY (DiskID),
	FOREIGN KEY (GroupID) REFERENCES Groups(GroupID),
	UNIQUE KEY Name_UNIQUE (Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table, representing album of one`s group';

/*create table client*/
CREATE TABLE Client (
	ClientID int NOT NULL AUTO_INCREMENT,
	Name varchar(50) NOT NULL,
	Discount double DEFAULT NULL,
	PRIMARY KEY (ClientID),
	UNIQUE KEY Name_UNIQUE (Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table, representing regular customer';

/*create table composition*/
CREATE TABLE Composition (
	CompositionID int NOT NULL AUTO_INCREMENT,
	DiskID int NOT NULL,
	Name varchar(50) NOT NULL,
	PresentDate date DEFAULT NULL,
	Duration time NOT NULL,
	PRIMARY KEY (CompositionID),
	FOREIGN KEY (DiskID) REFERENCES Disk(DiskID),
	UNIQUE KEY Name_UNIQUE (Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table, representing composition in disk';

/*create table check*/
CREATE TABLE Checks (
	CheckID int NOT NULL AUTO_INCREMENT,
	DiskID int NOT NULL,
	ClientID int NOT NULL,
	PurchaseValue double,
	PurchaseAmount int,
	PurchaseDate date NOT NULL,
	PurchaseType tinyint(1) default false,
	PRIMARY KEY (CheckID),
	FOREIGN KEY (DiskID) REFERENCES Disk(DiskID),
	FOREIGN KEY (ClientID) REFERENCES Client(ClientID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table, representing check';
