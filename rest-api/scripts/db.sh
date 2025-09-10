export DB_URL=jdbc:postgresql://postgres:5432/postgres
export DB_USER=postgres
export DB_PASSWORD=xaq267408

# Build a Docker image
docker build --build-arg JAR_FILE=build/libs/money-laundering-detector-0.0.1.jar --build-arg DB_URL=$DB_URL --build-arg DB_USER=$DB_USER --build-arg DB_PASSWORD=$DB_PASSWORD -t angiexiong0627/money-laundering-detector:0.0.1 .
