DROP TABLE resident IF EXISTS;
CREATE TABLE resident (
  RES_ID INT(10) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  ADDRESS VARCHAR(100) NOT NULL,
  PRIMARY KEY (RES_ID)
);