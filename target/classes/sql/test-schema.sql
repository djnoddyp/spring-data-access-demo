DROP TABLE resident IF EXISTS;
CREATE TABLE resident (
  res_id INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  PRIMARY KEY (res_id)
);