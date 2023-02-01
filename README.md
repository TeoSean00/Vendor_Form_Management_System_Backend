# smartform-backend
Requires Java 17 
Springboot Version 3.0.2
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html

## Getting Started
### Install Maven 
https://www.baeldung.com/install-maven-on-windows-linux-mac

To install project packages with maven <br />
```mvn clean install```

To run springboot on local server <br />
```mvn spring-boot:run```

### Run mongodb image on docker <br />

Pull the mongo image <br />
```docker pull mongo (automatically pulls latest version)```<br />

```docker run --name mongodb -d -p 27017:27017 mongo```


