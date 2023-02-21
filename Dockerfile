FROM openjdk
EXPOSE 8080
ADD target/TacoCloudProject1-0.0.1-SNAPSHOT.jar TacoCloudProject1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TacoCloudProject1-0.0.1-SNAPSHOT.jar"]