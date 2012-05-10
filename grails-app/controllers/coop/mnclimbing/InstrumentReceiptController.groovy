package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class InstrumentReceiptController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def needMemebership = {
		render "TODO"
	}
	def needWaiver = {
		render "TODO"
	}
	def needAccessForm = {
		render "TODO"
	}

	def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [instrumentReceiptInstanceList: InstrumentReceipt.list(params), instrumentReceiptInstanceTotal: InstrumentReceipt.count()]
    }

    def create = {
		def now = new Date()
        def instrumentReceiptInstance = new InstrumentReceipt()
		
		def c = Instrument.createCriteria()
		def instrumentInstanceList = c.list{
			or {
				isNull("obsoletionDate")
				gt("obsoletionDate", now)
			}
		}
		
		/*
		if (params?.person?.id) {
			def personInstance = Person.read(params?.person?.id)
			personInstance.forms.each{
				if ( ( it.incoming ) && ( instrumentInstanceList.contains( it.instrument ) ) ) {
					instrumentInstanceList.remove(it.instrument)
				}
			}
		}*/
		
        instrumentReceiptInstance.properties = params
		
        return [ instrumentReceiptInstance: instrumentReceiptInstance, 
			instrumentInstanceList: instrumentInstanceList ]
    }

    def save = {
        def instrumentReceiptInstance = new InstrumentReceipt(params)
        if (instrumentReceiptInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), instrumentReceiptInstance.id])}"
			redirect(controller:"person", action: "edit", fragment:"tab-forms", id: instrumentReceiptInstance.person.id)
        }
        else {
            render(view: "create", model: [instrumentReceiptInstance: instrumentReceiptInstance])
        }
    }

    def show = {
        def instrumentReceiptInstance = InstrumentReceipt.get(params.id)
        if (!instrumentReceiptInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
            redirect(action: "list")
        }
        else {
            [instrumentReceiptInstance: instrumentReceiptInstance]
        }
    }

    def edit = {
        def instrumentReceiptInstance = InstrumentReceipt.get(params.id)
        if (!instrumentReceiptInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [instrumentReceiptInstance: instrumentReceiptInstance]
        }
    }

    def update = {
        def instrumentReceiptInstance = InstrumentReceipt.get(params.id)
        if (instrumentReceiptInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (instrumentReceiptInstance.version > version) {
                    
                    instrumentReceiptInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt')] as Object[], "Another user has updated this InstrumentReceipt while you were editing")
                    render(view: "edit", model: [instrumentReceiptInstance: instrumentReceiptInstance])
                    return
                }
            }
            instrumentReceiptInstance.properties = params
            if (!instrumentReceiptInstance.hasErrors() && instrumentReceiptInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), instrumentReceiptInstance.id])}"
                redirect(controller:"person", action: "edit", fragment:"tab-forms", id: instrumentReceiptInstance.person.id)
            }
            else {
                render(view: "edit", model: [instrumentReceiptInstance: instrumentReceiptInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def instrumentReceiptInstance = InstrumentReceipt.get(params.id)
		
        
        if (instrumentReceiptInstance) {
			def personInstance = instrumentReceiptInstance.person
            try {
                instrumentReceiptInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
				redirect(controller:"person", action: "edit", id:personInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
				redirect(controller:"person", action: "edit", id:personInstance.id)            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt'), params.id])}"
            redirect(action: "list")
        }
    }
}
