## RENT A PLACE OVERVIEW

TL;DR;  
A small application that you can rent a place / server side - `ONGOING`


Technologies
- Java 17 - Oracle flavour
- Groovy (is used only for testing and scripting for gradle)
- Gradle 7.3.3
- Docker (Postgres, Flyway and the Application run in containers)
- Flyway as a data migration tool
- Spring Boot 2.6.2
- Spring Security 2.5.0
- Java JWT (Java implementation for JWT)
- Postgres 42.3.1
- Lombock (to reduce the written code in Java classes)
- Spring JPA
- Spock for testing
- Codenarc for Groovy static code analysis
- Checkstyle for Java static code analysis

### Run the application  
- Run `./gradlew clean build` this will clean the app(if already built) and then build the app and produce the needed jar in order to incorporate it in the Docker image(Dockerfile is provided in the root folder)
- Run `./gradlew appStart` (This command will run the docker compose tool and create the images for Flyway, Postgres and the rent-a-place application in order to run them in containers)

### Stop the application
- Run `./gradlew appStop`

### Run the functional tests 
- Under the module functional-tests I have created an FT suite. In order for the FT suite to run we need to have the application up and running by following the instruction in `Run the application` section
- Run `./gradlew rFT`

### Postman manual approach
- If we want to call the endpoints manually we just need to import the given Postman collection in our Postman tool, that resides in the `postman-collection` folder under the root folder
- Data are already inserted through sql scripts when flyway ran in the containerization process by running the `./gradlew mStart` command
- username: `unclebob`, password: `password`
- Notice!!! functional tests suite is doing some cleanup at the end of each test class case in order to achieve isolation, so it will erase the created user along with the units.
- In order to recreate the user and achieve manual testing we need to remove stop and remove the containers of postgres and flyway and re run the application in order for the flyway scripts to run again 
- This can be achieved also by running the test endpoint of user creation used by the FTs(in the test endpoints I have disabled the authorization process for speed)  
`Steps:`  
- First call the auth request in order to authenticate and retrieve the access and refresh token
- Call any other requests(I have already created a functionality in Tests tab that extracts the cookie values and passes them in the next requests in order to authorize properly)
