#!/usr/bin/env groovy

import dev.jenkins.*
import static dev.jenkins.BuildUtility.*


def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    
    def LoadVarRes = libraryResource pipelineParams.team + '/vars.json'
    def LoadEnvRes = libraryResource pipelineParams.team + '/environment.json'

    println("SCM URL is " + pipelineParams.scmUrl)
    Build_information = new envVariable(this, LoadEnvRes, LoadVarRes, "${env.BRANCH_NAME}")
    
    

    pipeline {
        agent none
        options {
            disableConcurrentBuilds()
        }
        environment
                {
                    COMPLIANCEENABLED = true
                   
                    GIT_TOKEN = credentials('GIT_TOKEN')
                    
                }

       
        stages {

  


            stage('Building Source Code') {


                agent {
                    

                        docker {
                                image 'maven:3.8.1-adoptopenjdk-11'
                                label 'my-defined-label'
                                args  '-v /tmp:/tmp'
                            }
                                        

                }


                steps {


                    retry(1) {
                        checkout(
                                changelog: true,
                                poll: false,
                                scm: [
                                        $class                           : 'GitSCM',
                                        branches                         : [[name: "${env.BRANCH_NAME}"]],
                                        doGenerateSubmoduleConfigurations: false,
                                        poll                             : false,
                                        extensions                       : [
                                                [$class: 'CheckoutOption', timeout: 10000],
                                                [$class: 'CloneOption', depth: 0, noTags: true, reference: '', shallow: true, timeout: 100]],
                                        submoduleCfg                     : [],
                                        userRemoteConfigs                : [[credentialsId: 'GitHub', url: pipelineParams.scmUrl]]])


                        script {

                            buildCode this, 'arguments', pipelineParams, Build_information, false, 'CODE'

                            
                        }
                       
                    }
                    timeout(time: 18000, unit: 'MINUTES') {
                        sh 'echo Timing Out after 2hr. Retry...'
                    }

                  

                } // Steps


            } // Stage

            

        }

        post {
            failure {
                echo "Your pipeline failed sending notification...."
                
            }
            success {
                echo "Your pipeline failed sending notification...."
                
            }


            
        }

    }


}
