package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class AccessCardController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def needed = {
		render "TODO"
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        [accessCardInstanceList: AccessCard.list(params), accessCardInstanceTotal: AccessCard.count()]
    }

	def find = {
		def searchString = params.id

		if ( ! searchString ) {
			searchString = params.value
		}

		def c = AccessCard.createCriteria()

		def accessCardInstanceList = []

		if ( searchString ) {

			// println "Search String: ${searchString}"

			accessCardInstanceList = c.list{
				or {
					ilike("cardIdentifier", "%${searchString}%")
					ilike("label", "%${searchString}%")
				}
				maxResults(10)
			}
		}

		def accessCardInstanceTotal = accessCardInstanceList.size()

		[accessCardInstanceList: accessCardInstanceList
			, accessCardInstanceTotal: accessCardInstanceTotal ]
	}

	def create = {
		def accessCardInstance = new AccessCard()
		accessCardInstance.properties = params
		return [accessCardInstance: accessCardInstance]
	}

	def save = {
		def accessCardInstance = new AccessCard(params)
		if (accessCardInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), accessCardInstance.id])}"
			redirect(action: "show", id: accessCardInstance.id)
		}
		else {
			render(view: "create", model: [accessCardInstance: accessCardInstance])
		}
	}

	def show = {
		def accessCardInstance = AccessCard.get(params.id)
		if (!accessCardInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
			redirect(action: "list")
		}
		else {
			[accessCardInstance: accessCardInstance]
		}
	}

	def edit = {
		def accessCardInstance = AccessCard.get(params.id)
		if (!accessCardInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [accessCardInstance: accessCardInstance]
		}
	}

	def update = {
		def accessCardInstance = AccessCard.get(params.id)
		if (accessCardInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (accessCardInstance.version > version) {
                    
					accessCardInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'accessCard.label', default: 'AccessCard')] as Object[], "Another user has updated this AccessCard while you were editing")
					render(view: "edit", model: [accessCardInstance: accessCardInstance])
					return
				}
			}
			accessCardInstance.properties = params
			if (!accessCardInstance.hasErrors() && accessCardInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), accessCardInstance.id])}"
				redirect(action: "show", id: accessCardInstance.id)
			}
			else {
				render(view: "edit", model: [accessCardInstance: accessCardInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def accessCardInstance = AccessCard.get(params.id)
		if (accessCardInstance) {
			try {
				accessCardInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCard.label', default: 'AccessCard'), params.id])}"
			redirect(action: "list")
		}
	}
}
