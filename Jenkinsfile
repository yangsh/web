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
                sh "sh /var/jenkins_home/workspace/${env.JOB_NAME}_${env.GIT_BRANCH}/stop.sh || true"
                sleep 5
                sh "docker rm web_${env.GIT_BRANCH} || true"
                sh "docker build -t web_${env.GIT_BRANCH}:${env.BUILD_NUMBER} ."
            }
        }

        stage('deploy to test') {
            when {
                branch 'test'
            }

            steps {
                echo "deploy to test env"
                sh "docker run -d -p 8181:8181 --name web_${env.GIT_BRANCH} web_${env.GIT_BRANCH}:${env.BUILD_NUMBER}"
            }
        }

        stage('deploy to prod') {
            when {
                branch 'master'
            }

            steps {
                echo "deploy to prod env"
                sh "docker run -d -p 8182:8181 --name web_${env.GIT_BRANCH} web_${env.GIT_BRANCH}:${env.BUILD_NUMBER}"
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
            cleanWs()
        }
    }
}