FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR / 
COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# FROM openjdk:jre-alpine AS build
# COPY . /src
# WORKDIR /src
# RUN mvn clean -DskipTests package

# FROM eclipse-temurin:17-jdk-alpine
# VOLUME /tmp
# RUN mvn -DskipTests package
# COPY --from=build target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

