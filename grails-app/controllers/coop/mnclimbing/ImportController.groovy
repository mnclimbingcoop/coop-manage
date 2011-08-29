package coop.mnclimbing

import grails.converters.XML

class ImportController {

	static def debug = true
	
    def events = {
		def robotPassword = RobotPassword.read(1)
		def doorName = params.id
		def ipAddress = request.getRemoteAddr() ?: request.getHeader("X-Forwarded-For") ?: request.getHeader("Client-IP")
		def now = new Date()
		def records = [total:0, created:0, existing:0, errors:0]
		
		if (debug) { println "Door Name: ${doorName}" }
		if (debug) { println "IP Address: ${ipAddress}" }

		if (params.secret == robotPassword.secret) {
			if (doorName && ipAddress) {
				
				def xmlData
				
				try {
					xmlData = request.XML
				} catch (Exception ex) {
					response << " ! Invalid XML:\n"
					response << "	${ex.cause}\n"
					response << "	${ex.message}\n"
				}
		
				if (xmlData) {
					
					xmlData.event?.each{ e ->
						
						records.total++
						
						Integer eventCode = e?.code?.toInteger() 
						Date eventDate = Date.parse("yyyy-MM-dd hh:mm:ss", e?.date?.toString())
						String eventSubject = e?.subject.toString()
	
						if (eventCode && eventDate && eventSubject) {
							
							def hidDoorEvent = HidDoorEvent.findByDoorNameAndEventDate(doorName, eventDate)
							
							if (hidDoorEvent) {
								records.existing++
								//println "Event: ${hidDoorEvent} already exists."
							} else {
								hidDoorEvent = new HidDoorEvent(
									uploadDate: now, 
									ipAddress: ipAddress,
									doorName: doorName,
									eventCode: eventCode,
									eventDate: eventDate,
									eventSubject: eventSubject)
								
								if ( hidDoorEvent.save() ) {
									records.created++
									//println "Saved door event from ${hidDoorEvent.eventDate}."
								} else {
									records.errors++
									//println "Error Saving event."
								}
							}
						}
					}
				}
			} else {
				println "Door Name not given from ip: ${ipAddress}"
			}
			
			render records as XML
		} else {
			println "ACCESS DENIED. ${now} IP: ${ipAddress}, didn't pass the correct secret, but used: ${params.secret}"
			render "ACCESS DENIED! Your are not a robot."			
		}
	}
}
