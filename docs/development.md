# Development(local)

### Prerequisites

- Jave 17 or above
- Gradle 8.5 or above
- Docker Desktop (You can also use [Colima](https://formulae.brew.sh/formula/colima) as alternative if you're on MacOS)


### Build and run the application

```
# Run the app
./gradlew bootRun

# Generate Jacoco report
./gradlew jacocoTestReport

# Apply spotless
./gradlew spotlessApply
./gradlew spotlessJavaApply

# Fix code style violations
./gradlew :spotlessJavaCheck
./gradlew :spotlessApply

# Build the app
./gradlew build
```

### Build a docker image and run as containers

Open a terminal, and change the current directory to the root of this repo,

1. Set your `DB_URL`, `DB_USER`, `DB_PASSWORD` and export them as environment variables
```
export DB_URL=<postgres-url>
export DB_USER=<postgres-user>
export DB_PASSWORD=<postgres-pwd>
```

2. Build a Docker image

You will need a docker artifactory, such as docker hub. The command below will use the [Dockerfile](../Dockerfile) to build the image.

```
docker build --build-arg JAR_FILE=build/libs/money-laundering-detector-0.0.1.jar --build-arg DB_URL=$DB_URL --build-arg DB_USER=$DB_USER --build-arg DB_PASSWORD=$DB_PASSWORD -t <your-docker-artifactory-location>/money-laundering-detector:0.0.1 .

# Push the image to the docker artifactory
docker push <your-docker-artifactory-location>/money-laundering-detector:0.0.1
```

3. Run the application as a container

Please create a `.env` file in the current directory. it should contain variables below,

```
POSTGRES_DB=<postgres-db-name>
POSTGRES_USER=<postgres-user-name>
POSTGRES_PASSWORD=<postgres-password>
DB_URL=<postgres-db-url>
DB_USER=<postgres-user-name>
DB_PASSWORD=<postgres-password>
```

This `.env` will be used in [compose.yaml](../compose.yaml). And run docker compose

```
docker compose up
docker compose up -d # Run as background
```
