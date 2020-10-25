# CasesApi

This project is an API application that should fetch all cases from External API.
The application should store the data for 15 minutes, 
and then to refrash the data by another call to the external api.

## Getting Started

### Requirements
- [Git](https://git-scm.com/downloads)
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [postgreSQL](https://www.postgresql.org/)

### Clone

To get started you can simply clone this repository using git:

```shell
$ git clone https://github.com/ehud91/CasesApi.git
$ cd CasesApi
```

### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.casesapi.execute.Application` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse / IntelliJ IDEA
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/ehud91/CasesApi.git
$ cd CasesApi
$ mvn spring-boot:run
```

### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/ehud91/CasesApi.git
$ cd SupportAggregationHub
$ mvn package -DskipTests
$ java -jar target/CasesApi-1.0-SNAPSHOT.jar
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **tasklist** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command


## Configure your own application properties

### /src/main/resources/application.properties

### Port configurations

Configure your own port on your machine.

```properties
server.port=1020
```
### Database configurations

You may change the datasource configuration to fit your machine.
Please find these configurations for edit:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/support_manager
```

```properties
spring.datasource.username=postgres
```

The password is encrypted by the use of [jasypt](https://mvnrepository.com/artifact/org.jasypt) plugin with application own private key.
1. In order to create your own encrypted password with [jasypt](https://mvnrepository.com/artifact/org.jasypt) you can find [here](https://www.geeksforgeeks.org/how-to-encrypt-passwords-in-a-spring-boot-project-using-jasypt/).
2. In order to generate a password with salt key you can find [here](https://medium.com/engineering-jio-com/a-secure-way-to-encrypt-any-password-in-the-config-file-in-a-spring-boot-project-20d12436b4b9).

```properties
spring.datasource.username=postgres
jasypt.encryptor.bean=encryptorBean
spring.datasource.password=ENC(NFve0DWvb5Nh/kfA8+D/xA==)
```

```properties
# database configuration

spring.jpa.hibernate.ddl-auto=none
spring.datasource.initialization-mode=always
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/support_manager
spring.datasource.username=postgres
jasypt.encryptor.bean=encryptorBean
spring.datasource.password=ENC(NFve0DWvb5Nh/kfA8+D/xA==)
```

In order to change the salt (private) key of the application you can find it in:

under the follow sub packages go the Spring boot execute Application class.

```shell
com.casesapi.execute.Application.java
```

And under this Application class, go to the method and change the config.setPassword() property 

```java
public StringEncryptor stringEncryptor() {
}
```

```java
config.setPassword("postgreSqlEb@91");
```

```java
    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("postgreSqlEb@91");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
```

### Install the PostgreSQL

You can find how to install the PostgreSQL in [here](https://www.guru99.com/download-install-postgresql.html).

### Create the Database and tables

Please run the 2 scripts under -> /CasesApi/sql/ in order to create the application database and tables:

In order to create the application database:

```shell
sql_query_create_db.sql
```

In order to create the application table:

```shell
sql_query_create_table_cases.sql
```

### Test the application

1. Go to your browser / [Postman](https://www.postman.com/downloads/) and run the service URI - http://localhost:1020/api/getAllCasesApi
2 The results from the service should contains this json content:

```json
{
  "requestUuid": "9328a25b-4d31-42fc-bad4-f70a750949fe",
  "status": 200,
  "desc": "Get all cases service success",
  "cases": [
    {
      "caseId": 1,
      "customerId": 818591,
      "provider": 6111,
      "createErrorCode": 324,
      "status": "Closed",
      "ticketCreatedDate": 1552573800000,
      "lastModifiedDate": 1552786860000,
      "productName": "BLUE"
    },
    {
      "caseId": 2,
      "customerId": 790521,
      "provider": 26241,
      "createErrorCode": 0,
      "status": "Closed",
      "ticketCreatedDate": 1551684600000,
      "lastModifiedDate": 1551746820000,
      "productName": "BLUE"
    },
    {
      "caseId": 3,
      "customerId": 738081,
      "provider": 1211,
      "createErrorCode": 101,
      "status": "Closed",
      "ticketCreatedDate": 1549319400000,
      "lastModifiedDate": 1549756320000,
      "productName": "BLUE"
    },
    {
      "caseId": 4,
      "customerId": 729841,
      "provider": 10416,
      "createErrorCode": 101,
      "status": "Closed",
      "ticketCreatedDate": 1548909060000,
      "lastModifiedDate": 1549186920000,
      "productName": "BLUE"
    },
    {
      "caseId": 5,
      "customerId": 827331,
      "provider": 11016,
      "createErrorCode": 324,
      "status": "Closed",
      "ticketCreatedDate": 1553002200000,
      "lastModifiedDate": 1553151240000,
      "productName": "BLUE"
    },
    {
      "caseId": 6,
      "customerId": 831011,
      "provider": 2811,
      "createErrorCode": 108,
      "status": "Closed",
      "ticketCreatedDate": 1553142780000,
      "lastModifiedDate": 1553157300000,
      "productName": "BLUE"
    },
    {
      "caseId": 7,
      "customerId": 831071,
      "provider": 12,
      "createErrorCode": 101,
      "status": "Closed",
      "ticketCreatedDate": 1553146260000,
      "lastModifiedDate": 1553204100000,
      "productName": "BLUE"
    },
    {
      "caseId": 8,
      "customerId": 831831,
      "provider": 10896,
      "createErrorCode": 101,
      "status": "Closed",
      "ticketCreatedDate": 1553171520000,
      "lastModifiedDate": 1553178900000,
      "productName": "BLUE"
    },
    {
      "caseId": 9,
      "customerId": 792661,
      "provider": 18121,
      "createErrorCode": 195,
      "status": "Closed",
      "ticketCreatedDate": 1551706320000,
      "lastModifiedDate": 1551736440000,
      "productName": "BLUE"
    },
    {
      "caseId": 10,
      "customerId": 723441,
      "provider": 11990,
      "createErrorCode": 103,
      "status": "Open",
      "ticketCreatedDate": 1548682200000,
      "lastModifiedDate": 1548734820000,
      "productName": "BLUE"
    }
  ]
}
```
