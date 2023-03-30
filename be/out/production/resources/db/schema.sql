CREATE TABLE Product(pId BIGINT not null auto_increment primary key,pName VARCHAR(455),price INT,barcode VARCHAR(455));
CREATE TABLE Warehouse(wId bigint not null auto_increment primary key,sId BIGINT,wName VARCHAR(455),wLoc VARCHAR(455));
CREATE TABLE Prod_Release(rId bigint not null auto_increment primary key,sId BIGINT,quantity INT,total INT,release_at DATETIME);
CREATE TABLE Stock(sId bigint not null auto_increment primary key,pId BIGINT,stock_count INT,receive_at DATETIME);

alter table Warehouse add foreign key(sId) references Stock(sId);
alter table Stock add foreign key(pId) references Product(pId);
alter table Prod_Release add foreign key(sId) references Stock(sId);