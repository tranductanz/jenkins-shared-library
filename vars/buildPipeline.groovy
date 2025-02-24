// vars/buildPipeline.groovy
def call(Map config = [:]) {
    pipeline {
        agent any
        parameters {
            string(name: 'PROJECT_PATH', defaultValue: config.projectPath ?: 'default-path', description: 'Project path')
        }
        stages {
            stage('Checkout') {
                steps {
                    echo "Checking out project at ${params.PROJECT_PATH}"
                    git url: "git@github.com:your-org/${params.PROJECT_PATH}.git", branch: 'main'
                }
            }
            stage('Build') {
                steps {
                    echo "Building project at ${params.PROJECT_PATH}"
                    sh './build.sh'
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying project at ${params.PROJECT_PATH}"
                    sh './deploy.sh'
                }
            }
        }
        post {
            always {
                echo "Pipeline completed for ${params.PROJECT_PATH}"
            }
        }
    }
}
