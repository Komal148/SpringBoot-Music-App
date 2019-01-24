FROM openjdk:11
ADD ./target/MuzixApp-0.0.1-SNAPSHOT.jar /usr/src/MuzixApp-0.0.1-SNAPSHOT.jar
EXPOSE 8091
WORKDIR usr/src
ENTRYPOINT ["java","-jar","MuzixApp-0.0.1-SNAPSHOT.jar"]
