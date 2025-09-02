FROM openjdk:17-jdk-slim
ARG DB_URL
ARG DB_USER
ARG DB_PASSWORD

ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}

ARG JAR_FILE=target/money-laundering-detector-0.0.1.jar
COPY ${JAR_FILE} app.jar

# CMD ["sh", "-c", "java -jar app.jar --env=$DB_URL --env=$DB_USER --env=$DB_PASSWORD"]
ENTRYPOINT ["java","-jar", "/app.jar", "--env=$DB_URL", "--env=$DB_USER", "--env=$DB_PASSWORD"]
