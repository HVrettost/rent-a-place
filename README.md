## BLUEGROUND ASSIGNMENT OVERVIEW

Technologies
- Java 14
- Groovy
- Gradle 7.2
- Docker
- Flyway
- Spring Boot 2.5.0
- Spring Security 2.5.0
- Java JWT
- Postgres 42.3.1
- Lombock
- Spring JPA
- Spock

Problem Solving Explanation
- Problem 1 - Infinite scrolling when fetching the Units according to search value criteria  
The user passes the `search value`, the `page number` and the `page size`. Slice was used over Page because in infinite scrolling we don't need to know the number of pages and the total elements
so this way we are having better performance


