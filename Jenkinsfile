pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar',
                                     fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat '"C:/Users/kiran/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" build -t lab5-java-webapp:1.0 .'
            }
        }
        stage('Kubernetes Deploy') {
            steps {
                bat 'set "KUBECONFIG=C:\\Users\\kiran\\.kube\\config" && "C:/Users/kiran/AppData/Local/Programs/DockerDesktop/resources/bin/kubectl.exe" apply -f k8s-deployment.yaml && "C:/Users/kiran/AppData/Local/Programs/DockerDesktop/resources/bin/kubectl.exe" apply -f k8s-service.yaml'
            }
        }
    }
}