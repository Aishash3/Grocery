

# Grocery Rest

The Grocery rest service can provide various information related to grocery management, such as:

1. Customers: Information about the customers who have registered for the grocery service, such as name, address, email, phone number, etc.

2. Orders: Information about the orders placed by the customers, such as the order ID, the products ordered, the quantity, the total price, delivery information, etc.

3. Products: Information about the products that are available for purchase, such as the product name, description, price, category, etc.

4. Categories: Information about the different categories of products available, such as vegetables, fruits, bakery items, dairy products, etc.

The Grocery rest service allows users to interact with this information through a set of API endpoints. The users can retrieve, add, update, or delete the information using these endpoints, based on their roles and permissions. The service also provides documentation through Swagger page, which makes it easier for users to understand and use the available endpoints.
## Getting Started

To run the project, follow the steps below:

### Prerequisites

- Java 8 or above
- Maven

### Installing

1. Clone the repository to your local machine:

```
git clone https://github.com/Aishash3/Grocery.git
```

2. Navigate to the project directory:

```
cd Grocery
```

3. Build the project using Maven:

```
mvn clean install
```

### Running the Application

1. Run the application:

```
mvn spring-boot:run
```

2. Open your browser and go to the Swagger page, which shows all endpoints of the service:

```
http://localhost:8080/swagger-ui.html
```

3. Explore the available endpoints and test them using Swagger.

## Built With

- Spring Boot
- H2 Embedded Database
- Swagger

## API Endpoints

### Customer

- GET /customers - Get all customers
- GET /customers/{customerId} - Get a customer by ID
- POST /customers - Create a new customer
- PUT /customers/{customerId} - Update a customer by ID
- DELETE /customers/{customerId} - Delete a customer by ID

### Product

- GET /products - Get all products
- GET /products/{productId} - Get a product by ID
- POST /products - Create a new product
- PUT /products/{productId} - Update a product by ID
- DELETE /products/{productId} - Delete a product by ID

### Order

- GET /orders - Get all orders
- GET /orders/{orderId} - Get an order by ID
- POST /orders - Create a new order
- PUT /orders/{orderId} - Update an order by ID
- DELETE /orders/{orderId} - Delete an order by ID

### Category

- GET /categories - Get all categories
- GET /categories/{categoryId} - Get a category by ID
- POST /categories - Create a new category
- PUT /categories/{categoryId} - Update a category by ID
- DELETE /categories/{categoryId} - Delete a category by ID

## Authors

- Kazbek Moldir
- Shakhali Aisha
- Darzhanov Nurlan
