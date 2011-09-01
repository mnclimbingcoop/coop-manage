package coop.mnclimbing

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.joda.time.LocalDateTime
import org.joda.time.LocalDate
import org.joda.time.LocalTime

@Secured(['ROLE_BOARD'])
class SignInController {

	// TODO: Graphs by type, time, daily/weekly/monthly
	static def debug = false
	// midnight
	def midnight = new LocalTime(0,0,0)
	// end of day
	def endOfDay = new LocalTime(23,59,59)
	def couldntFind = [] as Set
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def importDoor = {
		// import door upload date into the sign-in "stuff"
		
		// find max sign-in date from source hid
		
		// find all import records greater than sign-
		def hidDoorEventInstance = HidDoorEvent.createCriteria().get{
			and {
				eq('imported', true)
				eq('eventCode', 2020)
				ne('eventSubject', 'Skateboard O Coop')
			}
			maxResults(1)
			order('eventDate', 'desc')
		}
		
		println "last imported event: ${hidDoorEventInstance?.eventDate}"
		
		def hidDoorEventInstanceList = []
		if (! hidDoorEventInstance) {
			hidDoorEventInstanceList = HidDoorEvent.createCriteria().list{
				eq('imported', false)
				eq('eventCode', 2020)
				ne('eventSubject', 'Skateboard O Coop')
			}
		} else {
			def minDate = hidDoorEventInstance.eventDate
			hidDoorEventInstanceList = HidDoorEvent.createCriteria().list{
				and {
					eq('imported', false)
					eq('eventCode', 2020)
					gt('eventDate', minDate)
					ne('eventSubject', 'Skateboard O Coop')
				}
			}
		}
		
		hidDoorEventInstanceList?.each{ doorEvent ->
			// importing events

			// get the sign in date and time, Joda style
			def signInDateTime = new LocalDateTime(doorEvent.eventDate.time)
			// Yank the start of the day out 
			def signInDate = signInDateTime.toLocalDate().toDateTime(midnight).toCalendar().time
			def endOfSignInDate = signInDateTime.toLocalDate().toDateTime(endOfDay).toCalendar().time
			
			// Looking up Member
			def nameParts = doorEvent.eventSubject.split(' ')
			def firstName = nameParts[0]
			def middleInit = nameParts[1]
			def lastName = nameParts[2]
			
			def personInstance = Person.createCriteria().get{
					and {
						ilike('firstName', firstName)
						or {
							ilike('middleName', middleInit + '%')
							isNull('middleName')
						}
						ilike('lastName', lastName)
					}
					maxResults(1)
				}
			
			if (personInstance) {
				// Looking for existing sign in event
				def signInInstanceList = SignIn.createCriteria().list{
					and {
						eq('member', true)
						person {
							idEq(personInstance.id)
						}
						between('signInDate', signInDate, endOfSignInDate)
					}
				}
				if (! signInInstanceList ) {
					// not signed in yet
					def signInInstance = new SignIn(fullName: personInstance.fullName,
						person: personInstance, member: true, 
						signInDate: doorEvent.eventDate, source: 'keyfob')
					if ( ! signInInstance.save(flush:true) ) {
						println "Failed to save Sign-In for ${personInstance} on ${doorEvent.eventDate}!"
					}
				} else {
					if (debug) {
						println "~ ${personInstance} Already Signed In."
					}
					doorEvent.imported = true
					doorEvent.save()
				}
			} else {
				couldntFind.add(doorEvent.eventSubject)
				println "! Couldn't find: ${doorEvent.eventSubject}"
			}
			
		}
		
		if (couldntFind) {
			println "! Couldn't find the following people: "
			couldntFind.each {
				println "!\t${it}"
			}
			flash.message = "Couldn't find the following people: " + couldntFind.join(', ')
		} else {
			flash.message = "Imported Keyfobs as Sign-Ins."
		}
		
		redirect(action: 'list')
	}
	
    def list = {
		
		def signInInstance = new SignIn()
		signInInstance.properties = params
		
		def memberNames = Person.list().collect{ "'${it.firstName} ${it.lastName}'" }
		
		// Read the date from a referenced sign-in, or get today's date
		def refSignInInstance = SignIn.read(params?.id)
		if (refSignInInstance) {
			signInInstance.signInDate = refSignInInstance.signInDate
		} else if ( ! signInInstance.signInDate) {
			signInInstance.signInDate = new Date()
		}
		
		def prevDay = signInInstance.signInDate - 1
		
		def c = SignIn.createCriteria()
		def signInInstanceList = c.list{
			le("signInDate", signInInstance.signInDate)
			order("signInDate", "desc")
			maxResults(20)
		}
		
		def amPm = g.formatDate(date:signInInstance.signInDate, format:"a")
		
        [ signInInstanceList: signInInstanceList, 
			signInInstance: signInInstance,
			memberNames: memberNames,
			amPm: amPm ]
    }

    def save = {
        def signInInstance = new SignIn(params)
		
		if (params?.signInTime) {
			def time = params?.signInTime?.split(':')
			def timeHours = Integer.parseInt(time[0])
			def timeMinutes = Integer.parseInt(time[1])
			if (params?.amPm == "PM") {
				timeHours += 12
			}
		 		
			signInInstance.signInDate.setHours(timeHours)
			signInInstance.signInDate.setMinutes(timeMinutes)
		} else {
			flash.message "Please enter a sign in time."
			redirect(view:"list")
		}

		if (params?.memberGuestVisitor == 'Member') {
			signInInstance.member = true
		} else if (params?.memberGuestVisitor == 'Guest') {
			signInInstance.guest = true
		} else if (params?.memberGuestVisitor == 'Visitor') {
			signInInstance.visitor = true
		}
		
		if (signInInstance.fullName) {
			def nameParts = signInInstance.fullName.split(' ')
			if (nameParts.size() == 2) {
				def personInstance = Person.findByFirstNameAndLastName(nameParts[0], nameParts[1])
				if (personInstance) {
					println "matched person ${personInstance}"
					signInInstance.person = personInstance
				}
			}
		} else {
			flash.message = "Please enter a sign in time."
			redirect(view:"list")
		}
		
        if (signInInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'signIn.label', default: 'SignIn'), signInInstance.id])}"
			redirect(view:"list", id: signInInstance.id)
        }
        else {
			redirect(view:"list")
        }
    }

    def delete = {
        def signInInstance = SignIn.get(params.id)
        if (signInInstance) {
            try {
				def message = "${signInInstance.fullName} at ${signInInstance.signInDate} removed."
                signInInstance.delete(flush: true)
                flash.message = "${message}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'signIn.label', default: 'SignIn'), params.id])}"
                redirect(action: "list")
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'signIn.label', default: 'SignIn'), params.id])}"
            redirect(action: "list")
        }
    }
}
