create table Category ( id INT, name VARCHAR(50) );
create table Product (
                         id INT,
                         name VARCHAR(17),
                         category_id INT,
                         price DECIMAL(7,2)
);
create table Customer (
                          id INT,
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          email VARCHAR(50),
                          phone VARCHAR(50)
);
create table Order (
                       id INT,
                       customer_id VARCHAR(2),
                       product_id VARCHAR(2),
                       quantity VARCHAR(1)
);