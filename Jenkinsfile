pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQubeServer'   // Jenkins SonarQube config name
        NEXUS_REPO = 'maven-releases'   // Nexus repo ID
        NEXUS_URL = 'http://nexus:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS = credentials('nexus-credentials-id')
        TOMCAT_USER = credentials('tomcat-user')
        TOMCAT_PASS = credentials('tomcat-pass')
        TOMCAT_URL = 'http://tomcat:8080/manager/text'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/fahimnzeyimana/Expense-Tracker--.git', branch: 'main'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Code Quality - SonarQube') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=expense-tracker'
                }
            }
        }

        stage('Publish to Nexus') {
            steps {
                sh """
                    mvn deploy -DaltDeploymentRepository=nexus::default::${NEXUS_URL} \
                    -Dnexus.user=${NEXUS_CREDENTIALS_USR} \
                    -Dnexus.password=${NEXUS_CREDENTIALS_PSW}
                """
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh """
                    curl -u ${TOMCAT_USER}:${TOMCAT_PASS} --upload-file \
                    target/expense-tracker.war \
                    "${TOMCAT_URL}/deploy?path=/expense-tracker&update=true"
                """
            }
        }
    }

    post {
        always {
            node {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
