## RENT A PLACE OVERVIEW

Technologies
- Java 14
- Groovy (is used only for testing and scripting for gradle)
- Gradle 7.2
- Docker (Postgres, Flyway and the Application run in containers)
- Flyway as a data migration tool
- Spring Boot 2.5.0
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
- Run `./gradlew mStart` (This command will run the docker compose tool and create the images for Flyway, Postgres and the mars rental application in order to run them in containers)

### Stop the application
- Run `./gradlew mStop`

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


### Architectural Decisions / Explanation Points
- Problem 1 - Infinite scrolling when fetching the Units according to search value criteria

Spring Data's Slice was used over Page because in infinite scrolling we don't need to know the number of pages and the total elements so this way we are having better performance as Page is doing an extra call to retrieve this kind of data and Slice does not 

- Problem 2 - Allow searching based on region or title sorted by score  

For this functionality I used PostgreSQL's full text search functionality with TSVECTOR and TSQUERY types. On top of that I created an index on the tsvector type for fetch data optimization by using the GIN algorithm which is preferred in the Text Search functionality.  
Another option would be to search by region or title and create indexes on these types for better querying, but the search text functionality that is given by Postgres resolves the problem far better and we don't need to create two indexes that will slow down the insertion process.

- Problemn 3 & 4 - Authentication and Authorization with JWT  

Spring Security was used to implement the Authentication/Authorization functionality. The flow:
- User calls the corresponding endpoint by passing username/password over http (I have not implemented a secure solution through https in this assignment).
User is retrieved from DB and authenticated and then a JWT access and refresh token is created to be sent to the user with certain permissions. The access token lasts for 5 minutes and the refresh token lasts for 30  minutes.
Also the refresh token is saved in the database along with the user agent. Then a cookie is sent back to the user containing the access and refresh token.
- The cookie approach was created in order not to have to store the access and refresh token in the local storage. Of course the session approach has its vulnerabilites but most of them are handled through other means.
- The access token is not stored in the database in order to maintain its stateless-ness. The reason that I have added an expiration time of 5 minutes is to avoid the use
of a storage database like Redis. Each time the user called to an endpoint to get a resource the system would have to check the existance of the token in the memory storage db. 
Yes, the call would be fast because it is a memory database but along with the checks for token validity(expiration, permissions etc) it would take more time. It is a tradeoff and it depends on the system. So in order to avoid this extra functionality
I took the approach in having a token that expires fast to optimize speed. In case an attacker manages to intercept the cookie in a man in the middle attack he would have only 5 minutes to make his attack. But in any case when transfering the cookie over https the man in the middle attack is pretty hard.
Of course there are many other ways to intercept a cookie but all can be handled by applying other measures.
I also created some other endpoints for invalidation and refresh the access token for some more complete functionality

#### Some other basic points
- Indexes where used for query optimization where I thought was necessary(based on the select queries that I had to do to implement the functionality)
- Not too many in order to not have slow insertions
- Used foreign keys to reference the primary keys. In case of primary key value deletion the referenced values would by deleting by applying `ON DELETE CASCADE`
- Layered architecture in Controller/Service/Dao layers
- Transactionality to ensure Atomicity.
- I think that locks was not necessary to apply. In my opinion this functionality works in concurrency well as is.
- Split the requirements in domains
- Did not have time to write Unit Tests for everything
- Also, a module test approach would be good to have been implemented to test database stuff







  
  


