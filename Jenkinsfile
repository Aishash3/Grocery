pipeline {
    agent any

    stages {
        stage ('Build, Test and Analysis') {
            steps {
                withMaven(maven: 'maven') {
                sh 'mvn clean package'
                sh 'mvn jacoco:report'
                    withSonarQubeEnv('Local') {
                        sh './mvnw sonar:sonar'
                    }
                }

            }
            post {
                success {
                    jacoco(
                        execPattern: '**/target/jacoco/*.exec',
                        classPattern: '**/target/classes/java/main',
                        sourcePattern: '**/src/main'
                    )
                }
            }
        }
        stage ('Quality gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('Deploy') {
            steps {
                sh 'ls -la build/libs'
                sh 'mv -f target/*.war /usr/local/tomcat/webapps'
            }
        }
    }
}