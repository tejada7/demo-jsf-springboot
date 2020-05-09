pipeline {
    agent any
    stages {
        stage('Lint pom file') {
              steps {
                  sh 'mvn lint:check'
              }
        }
        stage('Lint Java checkstyle') {
              steps {
                  sh 'mvn checkstyle:check'
              }
         }
        stage('Run unit tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('build docker image') {
            steps {
                sh 'mvn package dockerfile:build'
            }
        }
        // stage('Upload to AWS') {
        //     steps {
        //         withAWS(region:'us-east-2',credentials:'aws-static') {
        //         sh 'echo "Uploading content with AWS creds"'
        //           s3Upload(pathStyleAccessEnabled: true, payloadSigningEnabled: true, file:'project-3/index.html', bucket:'my-s3-bucket-jenkins')
        //         }
        //     }
        // }
    }
}