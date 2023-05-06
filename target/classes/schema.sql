create table Category ( id  INTEGER PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50));
create table Product (
                         id  INTEGER PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(20),
                         category_id INT,
                         price DECIMAL(7,2),
                         FOREIGN KEY (category_id) references Category(id)
);
create table Customer (
                          id  INTEGER PRIMARY KEY AUTO_INCREMENT,
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          email VARCHAR(50),
                          phone VARCHAR(50)
);
create table Orders (
                       id  INTEGER PRIMARY KEY AUTO_INCREMENT,
                       customer_id INT,
                       product_id INT,
                       quantity INT,
                       FOREIGN KEY (customer_id) references Customer(id),
                       FOREIGN KEY (product_id) references Product(id)

);
create table Status (
	id  INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25)
);
create table Orders_history (
	id   INTEGER PRIMARY KEY AUTO_INCREMENT,
	status_id INT,
	order_id INT,
	modify_date DATE,
	FOREIGN KEY (status_id) references Status(id),
	FOREIGN KEY (order_id) references Orders(id)
);
COMMIT;