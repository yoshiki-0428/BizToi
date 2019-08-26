FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN /usr/src/app/gradlew build
RUN mv /usr/src/app/build/libs/*.jar /usr/src/app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/src/app/app.jar"]
