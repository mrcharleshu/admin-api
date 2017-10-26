CREATE TABLE order (
  id          INTEGER NOT NULL AUTO_INCREMENT,
  create_time DATETIME,
  update_time DATETIME,
  customer    VARCHAR(255),
  phone       VARCHAR(255),
  product     VARCHAR(255),
  PRIMARY KEY (id)
)