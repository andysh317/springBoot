pipeline {
    agent any

    parameters {
        choice(name: 'SERVICE', choices: ['eureka-server', 'order-service', 'user-service', 'auth-service', 'api-gateway'], description: 'Choose microservice to build')
    }

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials-id'
        IMAGE_NAME = "andigalappa/${SERVICE}"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/andysh317/springBoot.git'
            }
        }

        stage('Build Maven') {
            steps {
                dir("${SERVICE}") {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${SERVICE}") {
                    script {
                        docker.build("${IMAGE_NAME}:latest", '.')
                    }
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        docker.image("${IMAGE_NAME}:latest").push()
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ ${SERVICE} build & push success!"
        }
        failure {
            echo "❌ ${SERVICE} failed."
        }
    }
}
