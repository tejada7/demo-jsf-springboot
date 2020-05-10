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
        stage ("Lint dockerfile") {
            agent {
                docker {
                    image 'hadolint/hadolint:latest-debian'
                }
            }
            steps {
                sh 'hadolint dockerfiles/* | tee -a hadolint_lint.txt'
            }
            post {
                always {
                    archiveArtifacts 'hadolint_lint.txt'
                }
            }
        }
        stage('Create war file') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Build image') {
            steps{
                script {
                    dockerImage = docker.build(registry + ":latest", "--build-arg WAR_FILE=target/demojsfspringboot-1.0-SNAPSHOT.war .")
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
        stage('Update capstone-cluster config') {
			steps {
				withAWS(region:'us-west-2', credentials:'AWS') {
					sh 'aws eks --region us-west-2 update-kubeconfig --name capstone-cluster'
				}
			}
		}
        stage('Set Kubectl Context to Cluster') {
            steps{
                withAWS(region:'us-west-2', credentials:'AWS') {
                    sh 'kubectl config use-context arn:aws:eks:us-west-2:589247310786:cluster/capstone-cluster'
                }
           }
        }
        stage('Deploy to Blue Zone') {
            steps {
                withAWS(region:'us-west-2', credentials:'AWS') {
                    sh 'kubectl apply -f blue-deployment.json'
                    sh 'kubectl apply -f blue-lb-service.json'
                }
            }
        }
        stage('Redirect Traffic To Green Zone?') {
		    steps {
			    input "Redirect Traffic To Green Zone ?"
		    }
        }
        stage('Deploy Green Zone') {
            steps {
                withAWS(region:'us-west-2', credentials:'AWS') {
                    sh 'kubectl apply -f green-deployment.json'
                }
            }
        }
        stage('Direct to Green Zone') {
		    steps {
                withAWS(region:'us-west-2', credentials:'AWS') {
                    sh 'kubectl apply -f green-lb-service.json'
			    }
		    }
	    }
        stage('Remove unused docker image') {
            steps{
                sh "docker rmi $registry:latest"
            }
        }
    }
}