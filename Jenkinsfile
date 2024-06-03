pipeline {
    agent any

    parameters {
        string(name: 'RP_URL', defaultValue: 'http://localhost:8080')
        string(name: 'MODULE', defaultValue: '@reportportal')
        choice(name: 'BROWSER', choices: ['Chrome', 'Firefox'])
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Load Credentials') {
            steps {
                env.admin_username = 'superadmin'
                env.admin_password = 'erebus'
                env.API_TOKEN = 'admin-naresh-key_RcIR4hl3TIO7FIZsujR5Y2WV9b1tAKxKCzDbBrGWY5KGYQh1-m3YRddCevnkvAM9'
                env.EPAM_PASSWORD = 12345
                env.EPAM_URL = 'https://rp.epam.com'
                env.EPAM_USERNAME = 'nareshkarthi_sakthivel@epam.com'
                env.VALID_USERNAME = 'default'
                env.VALID_PASSWORD = '1q2w3e'
                env.username = 'default'
            }
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
}

stage('Load Jenkins Parameters') {
    steps {
        script {
            env.browser = params.BROWSER
            env.RP_URL = params.RP_URL
            env.MODULE = params.MODULE

            currentBuild.displayName = "${env.browser}| ${MODULE}"
        }
    }
}

stage('Test Run') {
    steps {
        try {
            sh 'gradle clean test "-Dcucumber.filter.tags=%MODULE" "-Dbrowser=%BROWSER" "-DRP_URL="%RP_URL" "-Dadmin_username=%admin_username" "-Dadmin_password=%admin_password" "-DAPI_TOKEN=%API_TOKEN" "-DEPAM_URL=%EPAM_URL" "-DEPAM_USERNAME=%EPAM_USERNAME" "-DEPAM_PASSWORD=%EPAM_PASSWORD" "-DVALID_USERNAME=%VALID_USERNAME" "-DVALID_PASSWORD=%VALID_PASSWORD" "-Dusername=%username" "-Ddataproviderthreadcount=1"'
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
