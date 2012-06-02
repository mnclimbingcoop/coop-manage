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

	/**
	Find all instruments due for all active members.
	*/
	def due = {
		//def requiredInstruments = Instrument.findAllByRequiredAndObsoletionDate(true,null)
		def requiredInstruments = Instrument.findAllByRequired(true)
		// remove obsolete forms
		requiredInstruments = requiredInstruments.findAll{!it.obsolete}

		def instrumentHaves = [:]
		def instrumentHaveNots = [:]

		// For each instrument required...
		requiredInstruments.each{ instrumentInstance ->
			def now = new Date()

			// How long is this thing valid for?
			// default everthing to good for ~30 years
			def validSigDate = now - 9999
			if (instrumentInstance.daysValidFor) {
				 validSigDate = now - instrumentInstance.daysValidFor
			}

			// find out who has one
			def thoseWhoHave = InstrumentReceipt.createCriteria().list{
				and {
					instrument {
						eq("id", instrumentInstance.id)
					}
					eq("outgoing", false)
					gt("transactionDate", validSigDate)
				}
			}
			def theHaves = thoseWhoHave.collect{ it.person }
			// save it to the map
			instrumentHaves[instrumentInstance.id] = theHaves

			////////////////////////////////
			// now find out who needs it. //
			////////////////////////////////

			// retreive all people with memberships
			def allMemberz = Membership.list().collect{ it.person }
			def allMembers = allMemberz.sort{ it.lastName }

			// remove those who don't have it
			allMembers.removeAll(theHaves)

			// save it to the map
			instrumentHaveNots[instrumentInstance.id] = allMembers.sort{ it.lastName }
		}

		[ requiredInstruments: requiredInstruments,
	   		instrumentHaves: instrumentHaves,
			instrumentHaveNots: instrumentHaveNots 	]
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
