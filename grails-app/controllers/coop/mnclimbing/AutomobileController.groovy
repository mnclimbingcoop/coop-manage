package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class AutomobileController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {        
        [automobileInstanceList: Automobile.list(params)]
    }

    def create = {
        def automobileInstance = new Automobile()
        automobileInstance.properties = params
        return [automobileInstance: automobileInstance]
    }

    def save = {
        def automobileInstance = new Automobile(params)
        if (automobileInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'automobile.label', default: 'Automobile'), automobileInstance.id])}"
			
			redirect(controller:"person", action: "edit", fragment:"tab-cars", id: automobileInstance.person.id)
        }
        else {
            render(view: "create", model: [automobileInstance: automobileInstance])
        }
    }

    def show = {
		redirect(action: "edit", id: automobileInstance.id)
    }

    def edit = {
        def automobileInstance = Automobile.get(params.id)
        if (!automobileInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'automobile.label', default: 'Automobile'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [automobileInstance: automobileInstance]
        }
    }

    def update = {
        def automobileInstance = Automobile.get(params.id)
        if (automobileInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (automobileInstance.version > version) {
                    
                    automobileInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'automobile.label', default: 'Automobile')] as Object[], "Another user has updated this Automobile while you were editing")
                    render(view: "edit", model: [automobileInstance: automobileInstance])
                    return
                }
            }
            automobileInstance.properties = params
            if (!automobileInstance.hasErrors() && automobileInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'automobile.label', default: 'Automobile'), automobileInstance.id])}"

				redirect(controller:"person", action: "edit", fragment:"tab-cars", id: automobileInstance.person.id)
            }
            else {
                 render(view: "edit", model: [automobileInstance: automobileInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'automobile.label', default: 'Automobile'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def automobileInstance = Automobile.get(params.id)
        if (automobileInstance) {
			
			def personInstance = automobileInstance.person
			
            try {
                automobileInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'automobile.label', default: 'Automobile'), params.id])}"
				
				redirect(controller:"person", action: "edit", fragment:"tab-cars", id: personInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'automobile.label', default: 'Automobile'), params.id])}"
                redirect(action: "edit", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'automobile.label', default: 'Automobile'), params.id])}"
            redirect(controller:"person", action: "list")
        }
    }
}
