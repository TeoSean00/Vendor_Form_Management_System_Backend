# smartform-backend
Requires Java 17 
Springboot Version 3.0.2
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html

## Getting Started
### Install Maven 
https://www.baeldung.com/install-maven-on-windows-linux-mac

To install project packages with maven <br />
```mvn clean install```

### Run mongodb image on docker <br />

Pull the mongo image <br />
```docker pull mongo: latest (automatically pulls latest version)```<br />

Build the springboot image <br />
```docker image build -t smartform .```

Run the two containers from docker-compose.yaml
```docker-compose up```
