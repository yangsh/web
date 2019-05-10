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
                sh "docker build -t web:${env.BUILD_NUMBER} ."
            }
        }

        stage('') {
            steps {
                sh "docker run -d -p 8181:8181 web:${env.BUILD_NUMBER}"
            }
        }
    }
}