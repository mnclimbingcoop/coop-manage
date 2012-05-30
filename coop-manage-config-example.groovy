// EXAMPLE CONFIGURATION FILE
// This file should be named:
//   coop-manage-config.groovy
// And places in the ${HOME}/.grails/ folder on the machine
// you are running it from.

// Data Source Configuration
dataSource {
	//dbCreate = "create-drop" // one of 'create', 'create-drop','update'
	//dbCreate = "update"
	dbCreate = "validate"
	username = "coop-sql-user"
	password = "some-long-complicated-password"
	//url = "jdbc:mysql://localhost/mncc_devel?noAccessToProcedureBodies=true"
	//url = "jdbc:mysql://localhost/mncc_test?noAccessToProcedureBodies=true"
	url = "jdbc:mysql://localhost/mncc?noAccessToProcedureBodies=true"
}

// Email Sending Configuration
grails {
	mail {
		host = "smtp.gmail.com"
		port = 465
		username = "some-user@gmail.com"
		password = "password"
		props = ["mail.smtp.auth":"true", 					   
			"mail.smtp.socketFactory.port":"465",
			"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback":"false"]
	}
}

// Mail setting for development/testing
//grails.mail.overrideAddress="your.email@example.org"

//grails.serverURL = "https://manage.mnclimbingcoop.com/mncc"
grails.serverURL = "http://localhost:8080/coop-manage"

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

	// For a production environment, you should uncomment the following
	//appenders {
	//	rollingFile name: "stacktrace", maxFileSize: 2048,
	//	file: "/var/log/tomcat6/mncc.log"
	//}
	// You will also need to create the file: $CATALINA_BASE\lib\log4j.properties
	//   ( typically /usr/share/tomcat6/lib/log4j.properties )
	//   ...containing the following...
	//
	// log4j.rootLogger=WARN, CA                                                          
	// log4j.appender.CA=org.apache.log4j.ConsoleAppender                                  
	// log4j.appender.CA.layout=org.apache.log4j.PatternLayout                             
	// log4j.appender.CA.layout.ConversionPattern=%d{MMM dd, yyyy hh:mm:ss aa} %c%n%p: %m%n


    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Added by the Spring Security Core plugin:
//grails.plugins.springsecurity.auth.forceHttps = true
//grails.plugins.springsecurity.secureChannel.definition = [ '/**':'REQUIRES_SECURE_CHANNEL' ]

