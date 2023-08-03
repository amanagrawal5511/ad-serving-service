FROM openjdk:17
EXPOSE 8080
ADD target/ad-serving-service-0.0.1-SNAPSHOT.jar app.jar

#CMD pwd

COPY docker-entrypoint-initdb.d /docker-entrypoint-initdb.d

ENTRYPOINT ["java","-jar","/app.jar"]
