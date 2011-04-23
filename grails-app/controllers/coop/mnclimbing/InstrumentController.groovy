package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class InstrumentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
	
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [instrumentInstanceList: Instrument.list(params), instrumentInstanceTotal: Instrument.count()]
    }

    def create = {
        def instrumentInstance = new Instrument()
        instrumentInstance.properties = params
        return [instrumentInstance: instrumentInstance]
    }

    def save = {
        def instrumentInstance = new Instrument(params)
        if (instrumentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'instrument.label', default: 'Instrument'), instrumentInstance.id])}"
            redirect(action: "show", id: instrumentInstance.id)
        }
        else {
            render(view: "create", model: [instrumentInstance: instrumentInstance])
        }
    }

    def show = {
        def instrumentInstance = Instrument.get(params.id)
        if (!instrumentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
            redirect(action: "list")
        }
        else {
			// Those who have
			def c = InstrumentReceipt.createCriteria()
			def instrumentReceiptInstanceList = c.list{
				and {
					instrument {
						eq("id", instrumentInstance.id)
					}
					eq("outgoing", false)
				}
			}.sort{it.person.lastName}
			
			def haveInstrument = instrumentReceiptInstanceList.collect{ it.person }
			
			// Members who don't have
			def needInstrumentReceiptInstanceList = Membership.list().collect{ it.person }
			
			needInstrumentReceiptInstanceList.removeAll(haveInstrument)
			
            [instrumentInstance: instrumentInstance, 
				instrumentReceiptInstanceList: instrumentReceiptInstanceList,
				needInstrumentReceiptInstanceList: needInstrumentReceiptInstanceList ]
        }
    }

    def edit = {
        def instrumentInstance = Instrument.get(params.id)
        if (!instrumentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [instrumentInstance: instrumentInstance]
        }
    }

    def update = {
        def instrumentInstance = Instrument.get(params.id)
        if (instrumentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (instrumentInstance.version > version) {
                    
                    instrumentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'instrument.label', default: 'Instrument')] as Object[], "Another user has updated this Instrument while you were editing")
                    render(view: "edit", model: [instrumentInstance: instrumentInstance])
                    return
                }
            }
            instrumentInstance.properties = params
            if (!instrumentInstance.hasErrors() && instrumentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'instrument.label', default: 'Instrument'), instrumentInstance.id])}"
                redirect(action: "show", id: instrumentInstance.id)
            }
            else {
                render(view: "edit", model: [instrumentInstance: instrumentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def instrumentInstance = Instrument.get(params.id)
        if (instrumentInstance) {
            try {
                instrumentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'instrument.label', default: 'Instrument'), params.id])}"
            redirect(action: "list")
        }
    }
}
