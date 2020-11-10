# RetailStoreAssignment

### Running the service on local ###
Run the main java file RetailStoreApplication to run a sample use case
Other use cases are mentioned in the test cases

### Initialize sonarqube server ###
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube
## Run the following command to analyze the code
mvn sonar:sonar  -Dsonar.projectKey=RetailStore  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=885ead0b7ba59f047e6bdb1abf22569308e018f1

