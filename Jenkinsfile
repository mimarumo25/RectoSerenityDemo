#!groovy
import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def CORREOS = "mrubidem@choucairtesting.com"

pipeline {
    agent any
    stages {
        stage('Obtener Fuentes') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "master"]],
                          wdoGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "GitPerso1", url: "https://github.com/mimarumo25/RectoSerenityDemo.git"]]])
            }
        }

        stage('Ejecutar Pruebas') {
            steps {
                script {
                    try {
                        //bat ("gradle clean test -DRunner=\"${Runner}\" aggregate") //Ejecución en agente Windows con parametro jenkins
                        //sh ("gradle clean test -DRunner=\"${Runner}\" aggregate") //Ejecución en agente Linux con parametro jenkins
                        bat("gradle clean test aggregate") //Ejecución en agente windows sin parametro jenkins
                        echo 'Test Ejecutados sin Fallo'
                        currentBuild.result = 'SUCCESS'
                    } catch (ex) {
                        echo 'Test Ejecutados con Fallo'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Generar evidencia') {
            steps {
                script {
                    try {
                        bat " rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                        echo 'Backup de evidencias realizado con exito'

                        publishHTML([allowMissing         : false,
                                     alwaysLinkToLastBuild: true,
                                     keepAll              : true,
                                     reportDir            : "${WORKSPACE}//serenity_${timestamp}/site/serenity",
                                     reportFiles          : 'index.html',
                                     reportName           : 'Evidencias Serenity Demo ',
                                     reportTitles         : 'Proyecto Serenity Demo SCREEMPLAY'])
                        echo 'Reporte Html realizado con exito'
                    } catch (e) {
                        echo 'No se realizo el Backup de evidencias'
                        publishHTML([allowMissing         : false,
                                     alwaysLinkToLastBuild: true,
                                     keepAll              : true,
                                     reportDir            : "${WORKSPACE}//target/serenity_${timestamp}",
                                     reportFiles          : 'index.html',
                                     reportName           : 'Evidencias Serenity Demo ',
                                     reportTitles         : 'Proyecto Serenity Demo SCREEMPLAY'])
                        echo 'Reporte Html realizado con exito'
                        currentBuild.result = 'SUCCESS'
                    }
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                script {
                    scannerHome = tool 'SonarQubeScanner'
                    //mismo nombre del servidor configurado en las Global Tools Jenkins
                }
                withSonarQubeEnv('sonarQube')//mismo nombre del servidor configurado en la configuracion del sistema jenkins
                        {
                            bat 'sonar-scanner'
                        }
            }
        }
    }
}