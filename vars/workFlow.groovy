#!/usr/bin/env groovy
import jenkins.model.*
//jenkins = Jenkins.instance

def call(Map pipelineParams) {

    pipeline {
        agent any

        /*agent {
            node {
                label 'agent-cloud-ec2'
            }
        }*/

        parameters {
            activeChoiceParam(name: 'ENV', choices: ['Stage','Production'], description: 'Select the Environment for build and deploy')
            /*activeChoiceParam("ENV"){
                description('Select the environment');
                choiceType('SINGLE_SELECT');
                groovyScript {
                    script('["Stage","Production"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
            activeChoiceReactiveParam('Family_Servers'){
                description('Select server for deploy')
                filterable()
                choiceType('SINGLE_SELECT')
                groovyScript {
                    fillChoices("../resources/${params.ENV}Server.txt")
                    fallbackScript('return ["Script error!"]')
                }
                referencedParameter('ENV')
            }*/
            /*choice(name: 'ENV', choices: ['Stage','Production'], description: 'Select the Environment for build and deploy')
            choice(name: 'PROJECT', choices: ['sl','inkpop'], description: 'Select the project to use')
            booleanParam(name:'PULL', defaultValue: true, description: 'git pull required')
            string(name: 'BRANCH', defaultValue: 'development', description: 'Add code branch')
            string(name: 'DOCKER_TAG', defaultValue: 'development', description: 'DOCKER tag to use to reference the image')
            booleanParam(name:'START_CONTAINER', defaultValue: true, description: 'Start the container on the target')
            choice(name: 'SERVER_FAMILY', choices: ['pajamas-all','pajamas-1'], description: 'Select servers')
            booleanParam(name:'BUILD', defaultValue: true, description: 'git pull required')*/
        }
        stages {
            stage("Pre-build") {
                steps {
                    echo "${params.ENV}"
                }
            }
            stage("Build") {
                steps{
                    echo "Build step"
                }
            }
            stage("Deploy") {
                steps {
                    echo "Do something"
                }
            }
        }
    }
}