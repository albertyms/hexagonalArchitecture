# Test hexagonal architecture

## Description

This is project to test the hexagonal architecture with Spring boot

### Stack used
- **Java**
- **H2 Database**
- **Apache Maven 4.0**
- **Spring Boot 3.3.5**

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed [Java JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- You have installed [Maven](https://maven.apache.org/download.cgi) as your build tool.
- You have a working installation of [Git](https://git-scm.com/downloads) (if you plan to clone the repository).
- You have an IDE or text editor of your choice (e.g., IntelliJ IDEA, Eclipse).

## Cloning the Repository

To clone this repository, run the following command:

```
git clone https://github.com/albertyms/hexagonalArchitecture.git

cd hexagonalArchitecture
```

Install dependencies:

```
mvn clean install
```

## Running the application

```
mvn spring-boot:run
```

## Running Tests 🧪

```
mvn test
```

API Get prices
-----

Get prices by parameters

Request:
```
GET localhost:8080/api/prices?brandId=1&productId=35455&date=2020-06-15T16:00:00
```

Response:
```
{
    "id": 4,
    "brandId": 1,
    "price": 38.95,
    "startDate": "2020-06-15T16:00:00",
    "endDate": "2020-12-31T23:59:59"
}
```