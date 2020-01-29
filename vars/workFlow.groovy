#!/usr/bin/env groovy
import jenkins.model.*
import fillChoices
import get_resource_dir
//jenkins = Jenkins.instance

def call(Map pipelineParams) {

    properties([
            parameters([
                    [$class: 'ChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Select the environment',
                     filterLength: 1,
                     filterable: false,
                     name: 'Env',
                     randomName: 'choice-parameter-5631314439613978',
                     script: [
                             $class: 'GroovyScript',
                             fallbackScript: [
                                     classpath: [],
                                     sandbox: true,
                                     script:
                                             'return[\'Could not get Env\']'
                             ],
                             script: [
                                     classpath: [],
                                     sandbox: true,
                                     script:
                                             'return[\'Stage\',\'Production\']'
                             ]
                     ]
                    ],
                    [$class: 'CascadeChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Select the Server from the Dropdown List',
                     filterLength: 1,
                     filterable: true,
                     name: 'Server',
                     randomName: 'choice-parameter-5631314456178619',
                     referencedParameters: 'Env',
                     script: [
                             $class: 'GroovyScript',
                             fallbackScript: [
                                     classpath: [],
                                     sandbox: true,
                                     script:
                                             'return[\'Could not get Environment from Env Param\']'
                             ],
                             script: [
                                     classpath: [],
                                     sandbox: true,
                                     script:
                                             ''' if (Env.equals(\'Stage\')){
                                //return[\'devaaa001\',\'devaaa002\',\'devbbb001\',\'devbbb002\',\'devccc001\',\'devccc002\']
                                def stageServers = fillChoices("${get_resource_dir()}/StageServers.txt")
                                //'return[fillChoices("${get_resource_dir()}/StageServers.txt")]'
                                'return[stageServers.join(',')]'
                            }
                            else if(Env.equals(\'Production\')){
                                //return[\'praaa001\',\'prbbb002\',\'prccc003\']
                                'return[fillChoices("${get_resource_dir()}/ProductionServers.txt")]'
                            }
                        '''
                             ]
                     ]
                    ]
            ])
    ])


    pipeline {
        agent any

        /*agent {
            node {
                label 'agent-cloud-ec2'
            }
        }*/

        /*parameters {
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
        //}
        stages {
            stage("Pre-build") {
                steps {
                    sh "pwd"
                    sh "ls -l /var/lib/sp-blue-ocean-team/jobs/test/jobs/SharedLibrary/workspace"
                    sh "ls -l"
                    echo "${params.Env}"
                    script{
                        def listS = fillChoices("${get_resource_dir()}/${params.Env}Servers.txt")
                        def listF = listS.join(",")
                        println listF
                        //println "Here is the second line: ${listS[1]}"
                    }
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