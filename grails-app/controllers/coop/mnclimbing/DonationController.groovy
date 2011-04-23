package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class DonationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		// Figure out total sales
		def donationTotal = 0

		def donationInstanceList = Donation.list()
		donationInstanceList.each{ a ->
			if ( a.paymentType.name == "PayPal") {
				donationTotal += (a.amount * 0.971)
			} else {
				donationTotal += a.amount
			}
		}


        [donationInstanceList: Donation.list(params)
			, donationInstanceTotal: Donation.count()
			, donationTotal: donationTotal ]
    }

    def create = {
        def donationInstance = new Donation()
        donationInstance.properties = params
        return [donationInstance: donationInstance]
    }

    def save = {
        def donationInstance = new Donation(params)
        if (donationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'donation.label', default: 'Donation'), donationInstance.id])}"
            redirect(controller:"person", action: "edit", fragment:"tab-donate", id: donationInstance.person.id)
		} else {
            render(view: "create", model: [donationInstance: donationInstance])
        }
    }

    def show = {
        def donationInstance = Donation.get(params.id)
        if (!donationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
            redirect(action: "list")
        }
        else {
            [donationInstance: donationInstance]
        }
    }

    def edit = {
        def donationInstance = Donation.get(params.id)
        if (!donationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [donationInstance: donationInstance]
        }
    }

    def update = {
        def donationInstance = Donation.get(params.id)
        if (donationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (donationInstance.version > version) {
                    
                    donationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'donation.label', default: 'Donation')] as Object[], "Another user has updated this Donation while you were editing")
                    render(view: "edit", model: [donationInstance: donationInstance])
                    return
                }
            }
            donationInstance.properties = params
            if (!donationInstance.hasErrors() && donationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'donation.label', default: 'Donation'), donationInstance.id])}"
	            redirect(controller:"person", action: "edit", fragment:"tab-donate", id: donationInstance.person.id)
            }
            else {
                render(view: "edit", model: [donationInstance: donationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def donationInstance = Donation.get(params.id)
        if (donationInstance) {
            try {
                donationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])}"
            redirect(action: "list")
        }
    }
}
