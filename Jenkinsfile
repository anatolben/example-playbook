pipeline {
  agent {
    node {
      label 'ansible_docker'
    }
  }
  options {
    timestamps()
    disableConcurrentBuilds()
  }
  stages {
    stage('Setup credentials') {
      steps {
        sh 'mkdir -p ~/.ssh && chmod 700 ~/.ssh'
        withCredentials([file(credentialsId: 'key', variable: 'private')]) {
           sh 'cp $private ~/.ssh'
        }
        sh 'chmod 600 ~/.ssh/github_id_rsa'
        sh 'printf "$CNF" > ~/.ssh/config'
        sh 'ssh-keyscan -t rsa github.com >> ~/.ssh/known_hosts'
      }
    }
    stage('Setup ansible roles') {
      steps {
        sh 'ansible-galaxy install -r requirements.yml'
      }
    }
    stage('Ansible play') {
      steps {
        sh 'ansible-playbook -i inventory/prod.yml site.yml'
      }
    }
  }
}
