pipeline {
    agent any

    tools {
        maven 'mvn-3.6.1'
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('create docker image') {
            steps {
                echo ">>> container clean ..."
                sh "docker kill $(docker ps -a | grep web | awk '{print $1}')"
                sh "docker rm $(docker ps -a | grep web | awk '{print $1}')"
                sh "docker rmi $(docker images -a | grep web | awk '{print $3}')"
                sh "docker build -t web:${env.BUILD_NUMBER} ."
            }
        }

        stage('start container') {
            steps {
                sh "docker run -d -p 8181:8181 web:${env.BUILD_NUMBER}"
            }
        }
    }
}