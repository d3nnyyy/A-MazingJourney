# A-Mazing Journey

Welcome to the A-Mazing Journey! This project consists of a maze-solving game built with Spring Boot for the backend and React for the frontend. Players can navigate through a maze to reach the destination while visualizing their path and the optimal path. The project is deployed on Amazon Web Service ( AWS Elastic Beanstalk for backend and AWS S3 for frontend. Moreover, it manages continuous integration and deployment (CI/CD) using AWS CodePipeline and AWS CodeBuild.

## Table of Contents

- [Website](#website)
- [Technologies](#technologies)
- [Local Development](#local-development)
- [Dockerization](#dockerization)
- [API Documentation with Swagger](#api-documentation-with-swagger)
- [Usage](#usage)
- [Authors](#authors)
- [Contributing](#contributing)

## Website

Feel free to test the game at our [website](http://a-mazing-journey.s3-website.eu-central-1.amazonaws.com/)

## Technologies

  ### Backend

  - Java 17
  - Spring Boot
  - JUnit
  - Mockito
  - AWS Elastic Beanstalk

  ### Algorithms: 

  There are two main algorithms used:
  1. The first algorithm is used for maze generating. It is a randomized depth-first search.
     It goes through all the possible cells in unfilled maze, creating paths in random directions, until it fills every single cell. After that there is an algorithm that creates 'circles', which are actually just for making more possible paths from start to finish.
  2. The second algorithm is used for maze solving. It is a breadth-first search.
     It goes through all the cells and all the passages one-by-one, filling cells with distances from current cell to start. It does that until it reaches the finish. After that the algorithm goes from finish to start, choosing the cell with the shortest distance to the start each time. That's how the shortest path is found.

  
  ### Frontend

  - React.js
  - Material UI
  - Emotion
  - Axios

  ### Continuous Integration and Deployment (CI/CD)

  - AWS CodePipeline
  - AWS CodeBuild

## Local Development

To run the project locally for development, follow these steps:

### Clone the repository to your local machine:

```bash
git clone https://github.com/d3nnyyy/A-MazingJourney.git
```

### Backend

1. Open the backend project located in the `backend` directory using an IDE or code editor.
2. Run `mvn clean install` to install dependencies.
3. Run `mvn spring-boot:run` to start the development server.

### Frontend

1. Open the frontend project located in the `frontend` directory using an IDE or code editor.
2. Run `npm install` to install dependencies.
3. Run `npm start` to start the development server.

*Note! Dont forget to change backend url for post requests as you want to test it locally.*

## Dockerization

To containerize the backend Spring Boot application using Docker, follow these steps:

1. Navigate to the `backend` directory of the project.
2. Build the Docker image using the provided Dockerfile:
   ```bash
   docker build -t backend-image:latest .
   ```
3. Run the Docker container using the following command:
   ```bash
   docker run -p 8080:8080 backend-image:latest
   ```
    This will map port 8080 from the container to port 8080 on your local machine.

4. Access the backend API at `http://localhost:8080`.

    Make sure to customize the image name and tag according to your preferences.

## API Documentation with Swagger

We use Swagger to provide detailed documentation for the backend API. Swagger UI allows you to explore and interact with the API endpoints directly from a web interface.

After running the backend Docker container (as explained in the Dockerization section), you can access the Swagger UI at `http://localhost:8080/swagger-ui.html`.

Swagger UI provides a user-friendly way to visualize and test the API endpoints. It's a great tool for developers and testers to understand the available routes and their inputs/outputs.

## Usage

You can access the A-Mazing Journey game through the provided URL. You can interact with the maze, navigate through it using keyboard inputs, and observe your chosen path alongside the optimal path. The game offers a visual representation of the maze-solving process, providing an engaging and educational experience.

## Authors

- [Tsebulia Denys](https://github.com/d3nnyyy): Backend developer.
- [Horak Maksym](https://github.com/hurrr1cane): Backend developer.
- [Soloviy Nazariy](https://github.com/N1tingale): Frontend developer.

## Contributing

If you're interested in contributing to A-Mazing Journey, feel free to submit pull requests or issues on our GitHub repository. We welcome your feedback and contributions to enhance the game.
