package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class AccessCardAssignmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [accessCardAssignmentInstanceList: AccessCardAssignment.list(params), accessCardAssignmentInstanceTotal: AccessCardAssignment.count()]
    }

	def missing = {
		def now = new Date()
		def beginningOfNextMonth = now + 31

		def c = Person.createCriteria()
		def accessPassHoldersWithoutCards = c.list{
			and {
				passes {
					and {
						le("startDate", beginningOfNextMonth)
						ge("endDate", now)
					}
				}
				cards {
					isNull("id")
				}
			}
		}

		[ accessPassHoldersWithoutCards: accessPassHoldersWithoutCards ]
	}
	
    def create = {
		// Find all cards that are not lost
		def unassignedAccessCardInstanceList = AccessCard.createCriteria().list {
			eq("lost", false)
		}
		
		// find assigned cards
		def assignedAccessCardInstanceList = AccessCard.createCriteria().list {
			assignments {
				and {
					isNull("returnDate")
					eq("lost", false)
				}
			}
		}
				
		assignedAccessCardInstanceList.each{
			unassignedAccessCardInstanceList.remove(it)
		}
				
        def accessCardAssignmentInstance = new AccessCardAssignment()
        accessCardAssignmentInstance.properties = params
		
        return [accessCardAssignmentInstance: accessCardAssignmentInstance,
			unassignedAccessCardInstanceList: unassignedAccessCardInstanceList ]
    }

    def save = {
        def accessCardAssignmentInstance = new AccessCardAssignment(params)
        if (accessCardAssignmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), accessCardAssignmentInstance.id])}"
			redirect(controller:"person", action: "edit", fragment:"tab-cards", id: accessCardAssignmentInstance.person.id)
        }
        else {
            render(view: "create", model: [accessCardAssignmentInstance: accessCardAssignmentInstance])
        }
    }

    def show = {
        def accessCardAssignmentInstance = AccessCardAssignment.get(params.id)
        if (!accessCardAssignmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [accessCardAssignmentInstance: accessCardAssignmentInstance]
        }
    }

    def edit = {
        def accessCardAssignmentInstance = AccessCardAssignment.get(params.id)
        if (!accessCardAssignmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [accessCardAssignmentInstance: accessCardAssignmentInstance]
        }
    }

    def update = {
        def accessCardAssignmentInstance = AccessCardAssignment.get(params.id)
        if (accessCardAssignmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (accessCardAssignmentInstance.version > version) {
                    
                    accessCardAssignmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment')] as Object[], "Another user has updated this AccessCardAssignment while you were editing")
                    render(view: "edit", model: [accessCardAssignmentInstance: accessCardAssignmentInstance])
                    return
                }
            }
            accessCardAssignmentInstance.properties = params
            if (!accessCardAssignmentInstance.hasErrors() && accessCardAssignmentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), accessCardAssignmentInstance.id])}"
                redirect(controller:"person", action: "edit", fragment:"tab-cards", id: accessCardAssignmentInstance.person.id)
            }
            else {
                render(view: "edit", model: [accessCardAssignmentInstance: accessCardAssignmentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def accessCardAssignmentInstance = AccessCardAssignment.get(params.id)
        if (accessCardAssignmentInstance) {
            try {
                accessCardAssignmentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment'), params.id])}"
            redirect(action: "list")
        }
    }
}
