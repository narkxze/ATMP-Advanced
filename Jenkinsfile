pipeline {
    agent any

    parameters {
        string(name: 'RP_URL', defaultValue: 'http://localhost:8080')
        string(name: 'MODULE', defaultValue: '@reportportal')
        choice(name: 'BROWSER', choices: ['Chrome', 'Firefox'])
    }

    environment {
        admin_username = 'superadmin'
        admin_password = 'erebus'
        API_TOKEN = 'admin-naresh-key_RcIR4hl3TIO7FIZsujR5Y2WV9b1tAKxKCzDbBrGWY5KGYQh1-m3YRddCevnkvAM9'
        EPAM_PASSWORD = 12345
        EPAM_URL = 'https://rp.epam.com'
        EPAM_USERNAME = 'nareshkarthi_sakthivel@epam.com'
        VALID_USERNAME = 'default'
        VALID_PASSWORD = '1q2w3e'
        username = 'default'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: '*/jenkins-reportportal']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[credentialsId: 'naresh-credentials-id', url: 'https://github.com/narkxze/ATMP-Advanced.git']]
                ])
            }
        }

            stage('Setup Build') {
                steps {
                    script {
                        currentBuild.displayName = "${env.browser}| ${MODULE}"
                    }
                }
            }

            stage('Test Run') {
                steps {
                    env.browser = params.BROWSER
                    env.RP_URL = params.RP_URL
                    env.MODULE = params.MODULE
                    try {
                        sh './gradlew clean test "-Dcucumber.filter.tags=${env.MODULE}" "-Dbrowser=${env.BROWSER}" "-DRP_URL=${env.RP_URL}" "-Dadmin_username=${env.admin_username}" "-Dadmin_password=${env.admin_password}" "-DAPI_TOKEN=${env.API_TOKEN}" "-DEPAM_URL=${env.EPAM_URL}" "-DEPAM_USERNAME=${env.EPAM_USERNAME}" "-DEPAM_PASSWORD=${env.EPAM_PASSWORD}" "-DVALID_USERNAME=${env.VALID_USERNAME}" "-DVALID_PASSWORD=${env.VALID_PASSWORD}" "-Dusername=${env.username}" "-Ddataproviderthreadcount=1"'
                        currentBuild.result = 'SUCCESS'
                    } catch (Exception ex) {
                        println('Exception caught in test run' + ex.getMessage())
                        currentBuild.result = 'FAILED'
                    }
                }
            }

            stage('Report') {
                steps {
                    archiveArtifacts artifacts: '**/allure-results/reports/allure-report/allureReport/**/*.*', fingerprint: true
                    publishHTML(
                            reportName: 'Report Portal Allure Report',
                            reportDir: 'build/allure-results/reports/allure-report/allureReport',
                            reportFiles: 'index.html',
                            keepAll: true,
                            allowMissing: false,
                            alwaysLinkToLastBuild: true
                    )
                }
            }
        }
    }