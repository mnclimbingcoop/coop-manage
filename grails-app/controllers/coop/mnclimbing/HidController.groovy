package coop.mnclimbing

import grails.converters.*;
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Secured(['ROLE_BOARD'])
class HidController {

	def transformerService

	// default action, XML.
    def index = { redirect(action:'xml') }
	
	// If you want to see the raw MNCC data
	def data = {
		def hidData = transformerService.generateHidEdgeProData()
		render hidData as XML
	}

	// helper script
	def buildBob = {
		response.setHeader("Content-disposition", "attachment;filename=build-bob.sh")
	}
	
	// master action
	def xml = {
		response.setHeader("Content-disposition", "attachment;filename=backupxml.xml")
		//response.setHeader("Content-disposition", "filename=backupxml.xml")
		
	}

	// included actions
	
	def alertActions = { }
	def cardFormats = { }
		
	def cardHolders = {
		transformerService.generateHidEdgeProData()
	}

	def credentials = {
		transformerService.generateHidEdgeProData()
	}

	def doors = { }
	def edgeSoloParameters = { }
	
	def eventMessages = {
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
		transformerService.generateHidEdgeProData()
	}

	def schedules = { }

}
