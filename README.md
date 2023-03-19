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

Run the two containers from docker-compose.yaml <br />
```docker-compose up --build```

Remember to signup your own account through postman to access springboot through your frontend!


___
### Using the PDF Generator
Currently, PDFGenerator is only accessible through Postman via this link `http://localhost:8080/api/form/generateForm/{formID}}` where the `formID` must reside inside your MongoDB. You will need to either be authorised as an `admin` or a `mod` inorder to use it. To get authorised, the steps are
1. Create an account with roles ["admin", "mod"] via `localhost:8080/api/auth/signup` with the following JSON body.
```
{
    "username": "abc",
    "password":12345,
    "email":"test123@email.com",
    "roles" : ["admin", "mod"]
}
```

2. Sign in on postman via `localhost:8080/api/auth/signin` with following JSON body. You should see a response with your `accessToken` key-value field.
```
{
    "username" : "abc",
    "password":12345
}
```

3. Copy the contents of `accessToken` into Authorization tab. Switch from whatever it is to bearer token and paste in your access token. 

4. Send a GET request to `http://localhost:8080/api/form/generateForm/{formID}}`, making sure the `formID` exists in your database. After that is done, the generated word file will be INSIDE your Docker container. In order to access it, open up `cli` and paste in the following `docker cp smartform:/form.docx ~/Desktop/form.docx`. Replace the second argument with the output destination.
