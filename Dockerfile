FROM openjdk:18-oracle
COPY ./target/newsApplication-0.0.1-SNAPSHOT.jar /testapp.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/testapp.jar"]