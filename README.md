# A-Mazing Journey

Welcome to the A-Mazing Journey! This project consists of a maze-solving game built with Spring Boot for the backend and React for the frontend. Players can navigate through a maze to reach the destination while visualizing their path and the optimal path. The project is deployed on Amazon Web Service ( AWS Elastic Beanstalk for backend and AWS S3 for frontend. Moreover, it manages continuous integration and deployment (CI/CD) using AWS CodePipeline and AWS CodeBuild.

## Table of Contents

- [Website](#website)
- [Technologies](#technologies)
- [Local Development](#local-development)
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

  #### Algortihm: 


  
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
git clone https://github.com/d3nnyyy/ToDoListFullstack.git
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

## Usage

You can access the A-Mazing Journey game through the provided URL. You can interact with the maze, navigate through it using keyboard inputs, and observe your chosen path alongside the optimal path. The game offers a visual representation of the maze-solving process, providing an engaging and educational experience.

## Authors

- [Tsebulia Denys](https://github.com/d3nnyyy): Backend developer.
- [Horak Maksym](https://github.com/hurrr1cane): Backend developer.
- [Soloviy Nazariy](https://github.com/N1tingale): Frontend developer.

## Contributing

If you're interested in contributing to A-Mazing Journey, feel free to submit pull requests or issues on our GitHub repository. We welcome your feedback and contributions to enhance the game.
