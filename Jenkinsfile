pipeline {
    agent any
    stages {
        stage('Cloning Project from Git')
        {
            steps
            {
               git branch:'donia_branch_02', url:"https://github.com/amdesprit/espritDevopsProject.git"
            }
        }

        stage("Build")
        {
            steps
            {
                bat "mvn compile"
            }
        }

        stage("Unit tests")
        {
            steps
            {
                bat "mvn test"
            }
        }

        stage("Static tests") {
            steps
            {
                bat "mvn sonar:sonar  -Dsonar.projectKey=Timesheet  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=dd56aae231c2b64b3baefd59e016292d43257b2a"
            }
        }

        stage("clean and packaging") {
            steps
            {
                bat "mvn clean package "
            }
        }

        stage("DEPLOY with Nexus") {
            steps
            {
                bat "mvn clean package -DskipTests deploy:deploy-file -DgroupId=com.esprit.spring -DartifactId=Timesheet -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-1.0.war"
            }
        }
    }
}