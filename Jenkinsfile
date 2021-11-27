pipeline {


environment {
registry = "ahmedesprit/timesheet"
registryCredential = 'docker_hub'
dockerImage = 'ahmedesprit/timesheet'
            }



agent any
stages {
stage('Cloning Project from Git') {
steps {
     
   sh  "git clone 'https://github.com/amdesprit/espritDevopsProject'" 

    }}

stage("Build") {
steps {
sh "mvn compile"
}}

stage("Unit tests") {
steps {
sh "mvn test"
}}

stage("Static tests") {
steps {
sh "mvn sonar:sonar -Dsonar.projectKey=test -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d8621a77b34a8277fd5fba7d56dd1b3fdecf7d07"
}}

stage("clean and packaging") {
steps {
sh "mvn clean package "
}}

stage("DEPLOY with Nexus") {
steps {
sh "mvn clean package deploy:deploy-file -DgroupId=com.esprit.spring -DartifactId=Timesheet -Dversion=1.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-1.0.war -DskipTests"
    }}


stage('Building our image') {
steps {
script {
dockerImage = docker.build registry + ":$BUILD_NUMBER"
}
}
}

stage('Deploy our image') {
steps {
script {
docker.withRegistry( '', registryCredential ) {
dockerImage.push()
}
}
}
}

stage('Cleaning up') {
steps {
sh "docker rmi $registry:$BUILD_NUMBER"
}
}
}
}