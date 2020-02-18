FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD springboot-2-mybatis-seed-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]