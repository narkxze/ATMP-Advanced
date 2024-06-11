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
        BROWSER = "${params.BROWSER}"
        RP_URL = "${params.RP_URL}"
        MODULE = "${params.MODULE}"
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
                bat '''
                 gradlew clean test allureReport "-Dcucumber.filter.tags=%MODULE%" "-Dbrowser=%BROWSER%" "-DRP_URL=%RP_URL%" "-Dadmin_username=%admin_username%" "-Dadmin_password=%admin_password%" "-DAPI_TOKEN=%API_TOKEN%" "-DEPAM_URL=%EPAM_URL%" "-DEPAM_USERNAME=%EPAM_USERNAME%" "-DEPAM_PASSWORD=%EPAM_PASSWORD%" "-DVALID_USERNAME=%VALID_USERNAME%" "-DVALID_PASSWORD=%VALID_PASSWORD%" "-Dusername=%username%" "-Ddataproviderthreadcount=1"
            '''
            }
        }

        stage('Report') {
            steps {
                echo "Generate Allure Report"
                allure([includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'build/allure-results']]])
                echo "Allure Report Generated"
            }
        }
    }
}