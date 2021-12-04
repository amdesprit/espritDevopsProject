pipeline { 
    environment { 
        EMAIL_RECIPIENTS = "donia.azib@esprit.tn"
		registry = "dockerhubapp/timesheetdockerimage"
		registryCredential = 'dockerHub'
		dockerImage = ''
	}

	agent any
	stages {
		stage('Cloning Project from Git') {
			steps { git branch:'donia_branch_02', credentialsId: 'dockerHub', url:"https://github.com/amdesprit/espritDevopsProject.git" }
		}
		stage("Build") {
			steps { bat "mvn compile" }
		}
		stage("Unit tests") {
			steps { bat "mvn test" }
		}
		stage("Static tests") {
			steps { bat "mvn clean verify sonar:sonar -Dsonar.projectKey=Timesheet  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=dd56aae231c2b64b3baefd59e016292d43257b2a" }
		}
		stage("clean and packaging") {
			steps { bat "mvn clean package " }
		}
		stage("DEPLOY with Nexus") {
			steps { bat "mvn clean package deploy:deploy-file -DgroupId=com.esprit.spring -DartifactId=Timesheet -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-1.0.war -DskipTests" }
		}
		stage('Building our image') {
			steps {
				script { dockerImage = docker.build registry + ":$BUILD_NUMBER" }
			}
		}
		stage('Deploy our image') {
			steps {
				script { docker.withRegistry( '', registryCredential ) { dockerImage.push() } }
			}
		}
		stage('Cleaning up') {
			steps { bat "docker rmi $registry:$BUILD_NUMBER" }
		}
	}
}