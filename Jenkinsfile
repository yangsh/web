pipeline {
    agent any

    tools {
        maven 'mvn-3.6.1'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '6'))
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
                jacoco(
                    execPattern: 'target/**/*.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java',
                    exclusionPattern: 'src/test*',
                    skipCopyOfSrcFiles: false,
                    changeBuildStatus: false,
                    minimumLineCoverage: '30', maximumLineCoverage: '70',
                    buildOverBuild: false
                )
            }
        }

        stage('create docker image') {
            steps {
                sh 'sh /var/jenkins_home/workspace/web/stop.sh'
                sleep 5
                sh 'docker rm web'
                sh "docker build -t web:${env.BUILD_NUMBER} ."
            }
        }

        stage('start container') {
            steps {
                sh "docker run -d -p 8181:8181 --name web web:${env.BUILD_NUMBER}"
            }
        }

        stage('deploy to test') {
            steps {
                script {
                    if (env.GIT_BRANCH == 'test') {
                        echo "deploy to test env"
                    }
                }
            }
        }

        stage('deploy to master') {
            steps {
                script {
                    if (env.GIT_BRANCH == 'master') {
                        echo "deploy to master env"
                    }
                }
            }
        }

    }

    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
            script {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}