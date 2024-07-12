FROM  maven:3-jdk-8
WORKDIR /eventservice
COPY . .
RUN ["mvn", "install", "-Dmaven.test.skip=true"]
EXPOSE 8085

CMD mvn spring-boot:run