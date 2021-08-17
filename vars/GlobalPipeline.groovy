def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def agentDocker = config.agent == null ? 'maven:3.6.3-jdk-8' : config.agent.toString()

    println 'agentDocker 2' + agentDocker

    pipeline {
        agent none
        stages {
            stage("Build") {
                agent {
                    docker {
                        image agentDocker
                    }
                }
                steps {
                    sh "mvn clean package -B -ntp -DskipTests -Dcheckstyle.skip"
                }
            }
        }
    }

}