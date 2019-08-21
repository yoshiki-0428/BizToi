FROM adoptopenjdk/openjdk8
WORKDIR /usr/src/app
COPY . /usr/src/app
#RUN mv /work/build/libs/*.jar /work/app.jar
#CMD /work/gradlew bootRun --stacktrace
CMD ["./gradlew", "bootRun"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/app.jar"]
