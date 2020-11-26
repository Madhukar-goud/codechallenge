## Technologies Used

Java, Spring boot, H2 Database that persists to file, Swagger-ui, Maven, Jacoco
## Building the application
You can use: `make build` or alternatively
`mvn clean install`

To run unit tests, you can do `make test` or alternatively
`mvn clean test`

The code coverage will be available at target/site/jacoco/index.html

## How to run the application
You can use `make run` or alternatively
`mvn spring-boot:run`

By default, the application run on port 8080. You can modify this in application.properties file located  at src/main/resources

## Swagger Endpoint is available at

http://localhost:8080/swagger-ui/

One advantage with swagger is it provides some default json that might work.

Here are the list of available endpoints:

You can create the addressbook using this endpoint
### Add Address book
http://localhost:8080/swagger-ui/#/address-book-controller/createAddressBookUsingPOST

### Get Unique names
You can check for unique names from this endpoint
http://localhost:8080/swagger-ui/#/address-book-controller/getUniqueNamesFromAddressBooksUsingGET

### Get Unique numbers
You can check for unique numbers from this endpoint
http://localhost:8080/swagger-ui/#/address-book-controller/getUniqueNumbersFromAddressBooksUsingGET

