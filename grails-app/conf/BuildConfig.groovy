grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.war.file = "mncc.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		// MySQL Database Connector
        runtime 'mysql:mysql-connector-java:5.1.18'
		// GPars Parallelizaion Library
		compile "org.codehaus.gpars:gpars:0.12"
    }
	plugins {
		build ":tomcat:$grailsVersion"

		runtime ":hibernate:$grailsVersion"
		runtime ":jquery:1.7.1"

		// compile ":cloud-foundry:1.2.1"
		compile ":database-migration:1.0"
		compile ":spring-security-core:1.2.7.3"

		// Joda Time
		compile ":joda-time:1.4"

		// App Info
		//provided ":app-info:0.4.3"
		//compile ":dynamic-controller:0.3"
		//compile ":google-visualization:0.5.2"

		// Email
		compile ":mail:1.0"

		// Testing only
		test ":code-coverage:1.2.5"
		test ":codenarc:0.16.1"

	}
}


codenarc.reports = {
	JenkinsXmlReport('xml') {
		outputFile = 'target/test-reports/CodeNarcReport.xml'
		title = 'CodeNarc Report for Minnesota Climbing Coop Management Site'
	}
	JenkinsHtmlReport('html') {
		outputFile = 'CodeNarcReport.html'
		title = 'CodeNarc Report for Minnesota Climbing Coop Management Site'
	}
}
codenarc.propertiesFile = 'grails-app/conf/codenarc.properties'
