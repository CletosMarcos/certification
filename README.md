# Certification Project

## Introduction
The **Certification Platform** is a Spring-based web application that enables users to take certification tests in various technology stacks. Users can input their personal details, select a stack, and take the quiz . Upon completion, the platform scores their responses and provides feedback.

### Features
- User registration
- Technology stack selection for certifications
- Score calculation and result display

### Live Demo
not deployed yet

### Blog Article
Read more about the development process in our blog post: [Final Project Blog Article]()

### Author
- [Cletos Marcos](https://www.linkedin.com/in/cletos-marcos/)

---

## Installation

### Prerequisites
- Java JDK 17+
- Maven
- Docker (for running PostgreSQL in a container)

### Steps to Install

1. Clone the repository:
    ```bash
    git clone https://github.com/CletosMarcos/certification.git
    ```

2. Navigate to the project directory:
    ```bash
    cd certification
    ```

3. Set up the PostgreSQL database using Docker:
    - Make sure Docker is installed and running.
    - Make sure to run docker-compose.yml file to set up the database.
    - Start container `postgres_certif`:
      ```bash
      docker-compose up -d
      ```
    - This will run a PostgreSQL container with the database `pg_certif`.


4. Build the project using Maven:
    ```bash
    mvn clean install
    ```

5. Run the project:
    ```bash
    mvn spring-boot:run
    ```

   The application will now be running at `http://localhost:8085`.

### Accessing the Application
Once the application is running, you can:
1. Register a new user or log in if already registered.
2. Select a certification stack to start a test.
3. Answer the provided questions and submit your answers to get a score.

## Usage

Access the platform at `http://localhost:8085`.

## Contributing

Contributions or suggestions to improve this project are welcome. Follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix:
    ```bash
    git checkout -b feature-or-fix
    ```
3. Make your changes and commit them:
    ```bash
    git commit -m "Added new feature or fixed bug"
    ```
4. Push the branch:
    ```bash
    git push origin feature-or-fix
    ```
5. Open a Pull Request.

## Related Projects

Related projects will be added here

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
