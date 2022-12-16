def PROJECT_NAME = "WEB-Java-SeleniumWebDriver"
def LABEL_ID = "js-slave-${PROJECT_NAME}-${UUID.randomUUID().toString()}"
def GIT_COMMIT
def TAG = "regression"
def ENVIRONMENT = "environmentA"
def DEPLOY_CREDENTIALS = 'credendiatls'
def AWS_CREDENTIALS_ID = 'aws-jenkins-dev'
podTemplate(label: LABEL_ID, yaml: """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: docker-container
    image: docker
    command: ['cat']
    tty: true
    env:
    - name: REGISTRY
      value: ????
    volumeMounts:
    - name: dockersock
      mountPath: /var/run/docker.sock
  - name: sdk-container
    image: imagem jdk8
    command: ['cat']
    tty: true
  - name: ansible-container
    image: sdk:ansible
    command: ['cat']
    tty: true
  - name: selenium-container
    image: selenium/standalone-chrome:102.0-chromedriver-102.0
    ports:
      - containerPort: 4444
  - name: sonar-container
    image: sonar:jdk8
    command: ['cat']
    tty: true
    env:
    - name: ENVIRONMENT
      value: "${ENVIRONMENT}"
    - name: TAG
      value: "${TAG}"
  nodeSelector:
    nodegroup-type: jenkins-nodes
  tolerations:
  - key: "jenkins-nodes"
    operator: "Exists"
    effect: "NoSchedule"
  resources:
    requests:
      memory: "4Gi"
      cpu: "2"
  imagePullSecrets:
  - name: dockerhub
  volumes:
    - name: dockersock
      hostPath:
        path: /var/run/docker.sock
"""
) {
  node(LABEL_ID){
    checkout scm
    currentBuild.displayName += " ${env.ENVIRONMENT} - ${env.TAG}"
    currentBuild.description = "${env.ENVIRONMENT} - ${env.TAG}"
    stage('Integration-tests'){
      notifyStarted()
      try {
        container('sdk-container'){
        sh "mvn clean test -Dtest=CucumberRunner -Dbrowser=chromeHeadless -Denvironment=${env.ENVIRONMENT} -Dcucumber.options="-t @${env.TAG}\""
        }

              // cucumber reports collection
               cucumber buildStatus: null, fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target', sortingMethod: 'ALPHABETICAL'
               publishHTML(target:[
                              		    			allowMissing: true,
                              					alwaysLinkToLastBuild: true,
                              					keepAll: true,
                              					reportDir: "${WORKSPACE}",
                              					reportFiles: 'target/cucumber/index.html',
                              					reportName: 'cucumber-html-reports',
                              					reportTitles: 'cucumber-html-reports'
                              					])
        notifySuccessful()
      }
      catch(e) {
        notifyFailed()
      }
    }
  }//fim node label
}//fim pod template
def notifyStarted() {
    slackSend (channel: '#reports', color: '#FFFF00', message: "STARTING WEB Tests : ${env.ENVIRONMENT}, TAG:${env.TAG} \n By ${env.BUILD_USER}")
}
def notifySuccessful() {
    slackSend (channel: '#reports', color: '#00FF00', message: "SUCCESSFUL WEB Tests : ${env.ENVIRONMENT}, TAG: ${env.TAG} \n ${env.BUILD_URL} \n Build Report: ${env.BUILD_URL}cucumber-html-reports/overview-features.html \n Cucumber Report: ${env.BUILD_URL}cucumber-html-reports/overview-features.html \n Cucumber steps: ${env.BUILD_URL}cucumber-html-reports/overview-steps.html")
}
def notifyFailed() {
	slackSend (channel: '#reports', color: '#FF0000', message: "FAILED WEB Tests : ${env.ENVIRONMENT}, TAG: ${env.TAG} \n ${env.BUILD_URL} \n Build Report: ${env.BUILD_URL}cucumber-html-reports/overview-features.html \n Cucumber Report: ${env.BUILD_URL}cucumber-html-reports/overview-features.html \n Cucumber steps: ${env.BUILD_URL}cucumber-html-reports/overview-steps.html")
}