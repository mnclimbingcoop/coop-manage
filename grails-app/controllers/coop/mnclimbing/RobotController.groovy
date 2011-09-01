package coop.mnclimbing

import grails.converters.*;
import org.codehaus.groovy.grails.commons.ApplicationHolder

class RobotController {

	def transformerService
	
	static def debug = true

	private Boolean isRobot() {
		def robotPassword = RobotPassword.read(1)
		def ipAddress = request?.remoteAddress
		def now = new Date()
		
		if (params.secret == robotPassword.secret) {
			// secret is OK
			if (robotPassword.ipAddressStartsWith) {
				if ( ( ! ipAddress ) || ipAddress?.startsWith(robotPassword?.ipAddressStartsWith) ) {
					return true
				} else {
					println "ACCESS DENIED. ${now} IP: ${ipAddress}, didn't starts with ${robotPassword.ipAddressStartsWith}."
					return false
				}
			} else {
				return true
			}
		} else {
			println "ACCESS DENIED. ${now} IP: ${ipAddress}, didn't pass the correct secret, but used: ${params.secret}"
			if (debug) {
				println "secret: ${robotPassword.secret}"
			}
			return false
		}
	}
	
	// master action
	def xml = {
		// only allow access to robots!
		if ( ! isRobot() ) { render "ACCESS DENIED! Your are not a robot." }
		
		response.setHeader("Content-disposition", "attachment;filename=backupxml.xml")
		//response.setHeader("Content-disposition", "filename=backupxml.xml")
	}

	// included actions
	
	def alertActions = { }
	def cardFormats = { }
		
	def cardHolders = {
		// only allow access to robots!
		if ( ! isRobot() ) { render "ACCESS DENIED! Your are not a robot." }

		transformerService.generateHidEdgeProData()
	}

	def credentials = {
		// only allow access to robots!
		if ( ! isRobot() ) { render "ACCESS DENIED! Your are not a robot." }
		transformerService.generateHidEdgeProData()
	}

	def doors = { }
	def edgeSoloParameters = { }
	
	def eventMessages = {
		// only allow access to robots!
		if ( ! isRobot() ) { render "ACCESS DENIED! Your are not a robot." }

		// define data to store the data in
		def data
		// Get the absolute path of the images folder
		File imagesFolder = ApplicationHolder.application.parentContext.getResource("/images").file
		// Get the absolute path of the images folder
		def imagesPath = imagesFolder.absolutePath
		// the name of the data file
		def dataFileName = 'eventMessage.data'
		// open the data file
		def dataFile = new File(imagesPath, dataFileName)
		// grab the first line, and save it to the data variable
		dataFile.eachLine{ line ->
			data = line
		}
		
		def message = [ data: data ]

		[ message: message ]
	}
	
	def readers = { }

	def roleSets = {
		// only allow access to robots!
		if ( ! isRobot() ) { render "ACCESS DENIED! Your are not a robot." }

		transformerService.generateHidEdgeProData()
	}

	def schedules = { }

}
