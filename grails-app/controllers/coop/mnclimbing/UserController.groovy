package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class UserController {

	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create = {
        def userInstance = new User()
        userInstance.properties = params

		def personInstance = Person.read(params?.person?.id)
		if (personInstance) {
			userInstance.username = "${personInstance.firstName[0]}${personInstance.lastName}".toLowerCase()
		}

        return [userInstance: userInstance]
    }

	def validatePassword = { params ->
		def password = null
		def newPassword = params.newPassword
		def confirmPassword = params.confirmPassword

		if ( newPassword && confirmPassword ) {
			if (newPassword != confirmPassword) {
				flash.message = "Passwords do not match."
			} else if ( newPassword.length() < 8 ) {
				flash.message = "Password must be 8 characters or longer"
			} else if ( newPassword ) {
				password = springSecurityService.encodePassword(newPassword)
			}
		}

		return password
	}

    def save = {
		
        def userInstance = new User(params)

		flash.message = ""

		def personInstance = Person.get(params.person.id)

		userInstance.password = validatePassword(params)

		userInstance.person = personInstance

        if (personInstance && userInstance.save(flush: true)) {

			personInstance.user = userInstance
			personInstance.save()

			if (flash.message) {
				flash.message = "${flash.message}<br/>"
			}
            flash.message = "${flash.message}User ${userInstance.username} created."
            redirect(action: "edit", id: userInstance.id)
        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def edit = {
        def userInstance = User.read(params.id)

        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "edit", id: userInstance.id)
        }
        else {
            return [userInstance: userInstance]
        }
    }

    def update = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }
            userInstance.properties = params

			// update password
			def newPass = validatePassword(params)
			if (newPass) {
				userInstance.password = newPass
			}

			// save roles.
			Role.list().each{ roleInstance ->
				if ( params["authority_${roleInstance.id}"] ) {
					UserRole.create userInstance, roleInstance, true

				} else {
					UserRole.remove userInstance, roleInstance, true
				}
			}
						
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "edit", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "edit", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }
}
