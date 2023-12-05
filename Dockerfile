FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY build/libs/*.jar code-sharing-platform-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/code-sharing-platform-0.0.1-SNAPSHOT.jar"]
