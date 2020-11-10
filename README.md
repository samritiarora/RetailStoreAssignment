# RetailStoreAssignment

### Running the service on local ###
$ mvn spring-boot:run
Run the above command to run the application.
The application exposed an endpoint ~/api/retailstore/calculate-cart, which returns the value of cart according to
given user
Some use cases are mentioned in the test cases

## Build the code
mvn clean install

## Run tests
mvn test

### Initialize sonarqube server ###
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube
## Run the following command to analyze the code
mvn sonar:sonar -Dsonar.projectKey=RetailStore  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=885ead0b7ba59f047e6bdb1abf22569308e018f1

