package coop.mnclimbing

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Secured(['ROLE_BOARD'])
class SignInController {

	// TODO: Graphs by type, time, daily/weekly/monthly
	// 
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
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
		
		// println "${prevDay} > signInDate > ${signInInstance.signInDate}"
		
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
