package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class EmergencyContactController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [emergencyContactInstanceList: EmergencyContact.list(params), emergencyContactInstanceTotal: EmergencyContact.count()]
    }

	def form = {}

    def create = {
        def emergencyContactInstance = new EmergencyContact()

		if (params?.person?.id) {
			def personInstance = Person.read(params?.person?.id)
		}

        emergencyContactInstance.properties = params
        return [emergencyContactInstance: emergencyContactInstance]
    }

    def save = {
        def emergencyContactInstance = new EmergencyContact(params)
        def addressInstance = new Address(params.address)

		if (addressInstance.address1 && addressInstance.save(flush: true)) {
			emergencyContactInstance.address = addressInstance
		} else {
			emergencyContactInstance.address = null
		}

        if (emergencyContactInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), emergencyContactInstance.id])}"
            redirect(controller:"person", action: "edit", fragment:"tab-emergency", id: emergencyContactInstance.person.id)

        }
        else {
            render(view: "create", model: [emergencyContactInstance: emergencyContactInstance])
        }
    }

    def show = {
        def emergencyContactInstance = EmergencyContact.read(params.id)
        if (!emergencyContactInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
            redirect(action: "list")
        }
        else {
            [emergencyContactInstance: emergencyContactInstance]
        }
    }

    def edit = {
        def emergencyContactInstance = EmergencyContact.read(params.id)

		def addressInstance = emergencyContactInstance?.address

        if (!emergencyContactInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [emergencyContactInstance: emergencyContactInstance
				, addressInstance: addressInstance ]
        }
    }

    def update = {
        def emergencyContactInstance = EmergencyContact.get(params.id)
        if (emergencyContactInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (emergencyContactInstance.version > version) {
                    
                    emergencyContactInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'emergencyContact.label', default: 'EmergencyContact')] as Object[], "Another user has updated this EmergencyContact while you were editing")
                    render(view: "edit", model: [emergencyContactInstance: emergencyContactInstance])
                    return
                }
            }
            emergencyContactInstance.properties = params
            if (!emergencyContactInstance.hasErrors() && emergencyContactInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), emergencyContactInstance.id])}"
                redirect(controller:"person", action: "edit", fragment:"tab-emergency", id: emergencyContactInstance.person.id)
            }
            else {
                render(view: "edit", model: [emergencyContactInstance: emergencyContactInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def emergencyContactInstance = EmergencyContact.get(params.id)
        if (emergencyContactInstance) {
			def personId = emergencyContactInstance.person.id


            try {
                emergencyContactInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
				redirect(controller:"person", action: "edit", id: personId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'emergencyContact.label', default: 'EmergencyContact'), params.id])}"
            redirect(action: "list")
        }
    }
}
