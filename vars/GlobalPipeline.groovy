import java.text.SimpleDateFormat

def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def agentDocker = config.agent == null ? 'maven:3.6.3-jdk-8' : config.agent.toString()

    println 'agentDocker 2' + agentDocker

    def dateFormat = new SimpleDateFormat('yyyyMMdd')
    def date = new Date()
    fechaConFormato = dateFormat.format(date)
    println fechaConFormato
    echo fechaConFormato

    pipeline {
        agent {
            docker {
                image agentDocker
            }
        }
        stages {
            stage("Stage 1") {
                steps {
                    sleep 3
            
                    script {
                        log.info 'Stage 1'
                    }
                }
            }
            stage("Stage 2") {
                parallel {
                    stage("Stage 2 A") {
                        steps {
                            sleep 3
                    
                            script {
                                log.info 'Stage 2 A'
                            }
                        }
                    }
                    stage("Stage 2 B") {
                        steps {
                            sleep 3
                    
                            script {
                                log.info 'Stage 2 B'
                            }
                        }
                    }
                }
            }
        }
    }

}