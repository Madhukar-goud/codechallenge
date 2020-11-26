## Technologies Used

Java, Spring boot, H2 Database that persists to file, Swagger-ui, Maven
## Building the application
mvn clean install

## How to run the application

mvn spring-boot:run

By default, the application run on port 8080. You can modify this in application.properties file located  at src/main/resources

Swagger Endpoint is available at

http://localhost:8080/swagger-ui/

One advantage with swagger is it provides some default json that might work.

Here are the list of available endpoints:

You can create the addressbook using this endpoint
http://localhost:8080/swagger-ui/#/address-book-controller/createAddressBookUsingPOST

You can check for unique names from this endpoint
http://localhost:8080/swagger-ui/#/address-book-controller/getUniqueNamesFromAddressBooksUsingGET

You can check for unique numbers from this endpoint
http://localhost:8080/swagger-ui/#/address-book-controller/getUniqueNumbersFromAddressBooksUsingGET

