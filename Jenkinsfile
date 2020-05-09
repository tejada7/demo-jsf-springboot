pipeline {
    environment {
        registry = "tejada7/udacitycapstone"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
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
        stage('Creating war file') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Building image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
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