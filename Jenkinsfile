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
    }
}