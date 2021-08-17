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
        agent none
        stages {
            stage("Stage 1") {

                sleep 3
            
                scripts {
                    log.info 'Stage 1'
                }
            }
        }
    }

}