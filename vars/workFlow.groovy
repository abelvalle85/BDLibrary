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
                                     script: '''def type = ["Stage", "Production"]
                                             return type'''
                             ]
                     ]
                    ],
                    [$class: 'ChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Select the Project',
                     filterLength: 1,
                     filterable: false,
                     name: 'Project',
                     randomName: 'choice-parameter-5631314456178629',
                     script: [
                             $class: 'GroovyScript',
                             fallbackScript: [
                                     classpath: [],
                                     sandbox: true,
                                     script: '''
                                            return ["Error"]
                                            '''
                             ],
                             script: [
                                     classpath: [],
                                     sandbox: true,
                                     script: """source="${get_resource_dir()}/Projects.txt"
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                return servers"""
                             ]
                     ]
                    ],
                    [$class: 'CascadeChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Select the Service from the Dropdown List',
                     filterLength: 1,
                     filterable: true,
                     name: 'Service',
                     randomName: 'choice-parameter-5631314456138619',
                     referencedParameters: 'Project',
                     script: [
                             $class: 'GroovyScript',
                             fallbackScript: [
                                     classpath: [],
                                     sandbox: true,
                                     script: '''return ["Error"]'''
                             ],
                             script: [
                                     classpath: [],
                                     sandbox: true,
                                     script: """if (Project.equals('sl')){
                                                source="${get_resource_dir()}/sl-service.txt"
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                return servers 
                                                } else if (Project.equals('pls')) {
                                                source="${get_resource_dir()}/pls-service.txt"
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                return  servers
                                                } else if (Project.equals('oo')) {
                                                source="${get_resource_dir()}/oo-service.txt"
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                return  servers
                                                }       
                                    """
                             ]
                     ]
                    ],
                    [$class: 'CascadeChoiceParameter',
                     choiceType: 'PT_CHECKBOX',
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
                                     script: '''
                                            return ["Error"]
                                            '''
                                             //'return[\'Could not get Environment from Env Param\']'
                                        //     'return ["fillChoices(\"${get_resource_dir()}/${params.Env}Servers.txt\")"]'

                             ],
                             script: [
                                     classpath: [],
                                     sandbox: true,
                                     script: """if (Env.equals('Stage')){
                                                source="${get_resource_dir()}/StageServers.txt"
                                                //def f = new fillChoice()
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                //servers=fillChoice(source)
                                                return servers //f(source) // servers
                                                } else if (Env.equals('Production')) {
                                                source="${get_resource_dir()}/ProductionServers.txt"
                                                def servers=[]
                                                new File(source).eachLine{ line->
                                                servers << line
                                                }
                                                return  servers
                                                }       
                                    """
                             ]
                     ]
                    ],
                    [$class: 'DynamicReferenceParameter',
                     choiceType: 'ET_UNORDERED_LIST',
                     description: '',
                     name: 'Show_info',
                     omitValueField: false,
                     randomName: 'choice-parameter-1440364011873359',
                     referencedParameters: 'Env, Project',
                     script: [
                              $class: 'GroovyScript',
                              fallbackScript: [
                                      classpath: [],
                                      sandbox: true,
                                      script: ''
                              ],
                              script: [
                                      classpath: [],
                                      sandbox: true,
                                      script: """return ["Environment : ${params.Env} ","Project : ${params.Project}","Service : ${params.Service}"]"""
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
                    sh "ls -l"
                    echo "${params.Env}"
                    echo "${params.Project}"
                   /* script{
                        def listS = fillChoices(ENV:"${params.Env}")
                        //def listF = listS.join(",")
                       // println fillChoices(ENV:"{params.Env}")
                        println listS
                        //println listF
                        //println listS.class
                        //println listF.class
                        //println "Here is the second line: ${listS[1]}"
                    }*/
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