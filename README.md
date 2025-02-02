# Crypto Alert System

A real-time cryptocurrency alert system that fetches the latest prices and changes in pricing from the CoinGecko API and sends user-defined alerts via email. Built using Kotlin and Spring Boot.

## Features

### General
- OAuth2-based authentication.
- Scheduled tasks to fetch the latest cryptocurrency prices.
- Kafka integration for event-driven communication.
- Email notifications for price change alerts, sent once per 24 hours.
- PostgreSQL database for structured and efficient data management.

### User Features
- Set alerts for specific cryptocurrencies with threshold conditions (e.g., price increase or decrease by a percentage).
- Receive email notifications when alert conditions are met.

## Technologies Used

- **Backend**: Kotlin, Spring Boot (REST API, Spring Security, Spring Data JPA, Spring Scheduler).
- **Database**: PostgreSQL.
- **Messaging**: Apache Kafka.
- **Testing**: JUnit, Mockito, Testcontainers.
- **Build Tool**: Gradle.
- **Authentication**: OAuth2.
- **Email Sending**: Spring Boot Mail.

## System Architecture

- Event-driven architecture using Kafka.
- PostgreSQL for persistent storage.
- Scheduled tasks for periodic data fetching.
- REST APIs for client-server interaction.

## Setup and Installation

### Prerequisites
- Java 23 or later
- Gradle
- Docker (for running PostgreSQL, Kafka, and Zookeeper)

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/crypto-alert-system.git
   cd crypto-alert-system
   ```

2. Configure the application properties:
   Update the `application.properties` file:
   ```properties
   spring.application.name=crypto-alert-system
   spring.config.import=optional:file:.env
   spring.sql.init.mode=always
   spring.jpa.hibernate.ddl-auto=update
   spring.kafka.bootstrap-servers=localhost:9092
   spring.kafka.consumer.group-id=alert-group
   spring.kafka.consumer.auto-offset-reset=earliest
   spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
   spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
   spring.security.oauth2.client.registration.github.client-id=<client-id>
   spring.security.oauth2.client.registration.github.client-secret=<client-secret>

   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=<gmail-email>
   spring.mail.password=<gmail-password>
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```

3. Configure Docker Compose:
   Create a `compose.yaml` file with the following content:
   ```yaml
   services:
     postgres:
       image: 'postgres:latest'
       container_name: postgres
       environment:
         - 'POSTGRES_DB=crypto-alert'
         - 'POSTGRES_PASSWORD=secret'
         - 'POSTGRES_USER=borys'
       ports:
         - '5432:5432'
     zookeeper:
       image: 'confluentinc/cp-zookeeper:latest'
       container_name: 'zookeeper'
       environment:
         ZOOKEEPER_CLIENT_PORT: 2181
         ZOOKEEPER_TICK_TIME: 2000
       ports:
         - '2181:2181'
     kafka:
       image: 'confluentinc/cp-kafka:latest'
       container_name: 'kafka'
       depends_on:
         - zookeeper
       ports:
         - '9092:9092'
         - '29092:29092'
       environment:
         KAFKA_BROKER_ID: 1
         KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
         KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_HOST://localhost:29092
         KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
         KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
         KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
     lgtm:
       image: 'grafana/otel-lgtm'
       ports:
          - '4317:4317'
          - '4318:4318'
          - '3000:3000'
   ```

4. Start the Docker containers:
   ```bash
   docker-compose -f compose.yaml up -d
   ```

5. Build the application:
   ```bash
   ./gradlew clean build
   ```

6. Run the application:
   ```bash
   java -jar build/libs/crypto-alert-system-0.0.1-SNAPSHOT.jar
   ```

### Running Tests

Run unit and integration tests:
```bash
./gradlew test
```

## Testing

- **Unit Tests**: Written using JUnit and Mockito to test individual components.
- **Integration Tests**: Testcontainers is used to create isolated PostgreSQL and Kafka environments for reliable testing.

## Challenges and Solutions

### Real-Time Price Updates
- Implemented scheduled tasks using Spring Scheduler to periodically fetch cryptocurrency prices from CoinGecko.

### Secure OAuth2 Authentication
- Integrated Spring Security with OAuth2 for secure login via GitHub.

### Reliable Messaging
- Configured Kafka producers and consumers for efficient and asynchronous data communication.

### Email Notifications
- Utilized Spring Boot Mail for sending email alerts to users, ensuring alerts are sent only once per day per user.

## Future Improvements
- Add support for multiple alert types.
- Enhance the user interface for better interaction (e.g., dashboard for alert management).
- Introduce Push notifications.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes and push the branch.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any inquiries or feedback, please reach out to me at [hello@boryskaczmarek.pl].
