## BLUEGROUND ASSIGNMENT OVERVIEW

Technologies
- Java 14
- Groovy
- Gradle 7.2
- Docker
- Flyway as a data migration tool
- Spring Boot 2.5.0
- Spring Security 2.5.0
- Java JWT
- Postgres 42.3.1
- Lombock
- Spring JPA
- Spock for testing
- Codenarc for Groovy static code analysis
- Checkstyle for Java static code analysis

Architectural Decisions Explanation / Points
- Problem 1 - Infinite scrolling when fetching the Units according to search value criteria
   
Spring Data's Slice was used over Page because in infinite scrolling we don't need to know the number of pages and the total elements so this way we are having better performance as Page is doing an extra call to retrieve this kind of data and Slice does not 

- Problem 2 - Allow searching based on region or title sorted by score  
For this functionality I used PostgreSQL's full text search functionality with TSVECTOR and TSQUERY types. On top of that I created an index on the tsvector type for fetch data optimization.  
Another option would be to search by region or title and create indexes on these types for better querying, but the search text functionality that is given by Postgres resolves the given problem far better

- Problemn 3
  
  


