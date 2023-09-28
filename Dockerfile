FROM maven:3.9.4-amazoncorretto-21

WORKDIR /app

COPY . .

ENV MAVEN_CONFIG=''

RUN chmod +x mvnw

RUN ./mvnw install

ENTRYPOINT ["java","-jar","target/blackjack-0.0.1-SNAPSHOT.jar"]
