FROM adoptopenjdk/openjdk8
RUN mkdir /work
COPY . /work
WORKDIR /work
#RUN mv /work/build/libs/*.jar /work/app.jar
#CMD /work/gradlew bootRun --stacktrace
ENTRYPOINT ["sh", "/work/gradlew", "bootRun"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/app.jar"]
