CREATE TABLE Clients(
email VARCHAR(128) NOT NULL,
first_name VARCHAR(64) NOT NULL,
last_name VARCHAR(64) NOT NULL,
zip_code VARCHAR(6) NOT NULL,
city VARCHAR(64),
street_address VARCHAR(128),
PRIMARY KEY(email)
);

CREATE TABLE Orders(
order_id INT NOT NULL,
product_id INT NOT NULL,
email_client VARCHAR(128) NOT NULL,
quantity INT NOT NULL,
order_date DATE DEFAULT (CURRENT_DATE),
shipping_status BOOLEAN DEFAULT FALSE,
CONSTRAINT PK_Order PRIMARY KEY (order_id, product_id)
);

CREATE TABLE Products(
product_id INT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(128) NOT NULL,
price FLOAT NOT NULL,
ean INT,
PRIMARY KEY(product_id)
);

-- CREATE TABLE Orders_Products(
-- order_id INT NOT NULL,
-- product_id INT NOT NULL,
-- CONSTRAINT PK_Product PRIMARY KEY (order_id, product_id)
-- );