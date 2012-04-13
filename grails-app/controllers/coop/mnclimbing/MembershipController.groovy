package coop.mnclimbing

import grails.plugins.springsecurity.Secured

class MembershipController {

    static allowedMethods = [ save: "POST", update: "POST", delete: "POST" ]

	@Secured(['ROLE_BOARD'])
    def index = {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_BOARD'])
    def list = {
		// Figure out total sales
		def membershipSalesTotal = 0

		def membershipInstanceList = Membership.list()
		membershipInstanceList.each{ a ->
			membershipSalesTotal += a.amount
		}

        [membershipInstanceList: Membership.list(params)
			, membershipSalesTotal: membershipSalesTotal ]
    }
	
	@Secured(['ROLE_BOARD','ROLE_STAFF'])
	def find = {

		def searchString = params.id

		if ( ! searchString ) {
			searchString = params.value
		}

		def c = Person.createCriteria()

		Long idSearch = 0
		try {
			idSearch = searchString.toLong()
		} catch (Exception ex) {
			idSearch = -1
		}

		def personInstanceList = []

		if ( searchString ) {
			personInstanceList = c.list{
				and {
					or {
						ilike("firstName", "%${searchString}%")
						ilike("lastName", "%${searchString}%")
						like("phoneNumber", "${searchString}")
						idEq(idSearch)
					}
					memberships {
						and {
							isNull("membershipTo")
							type {
								eq("name", "lifetime")
							}
						}
					}
				}
				maxResults(20)
			}
		}

		def personInstanceTotal = personInstanceList.size()

		[personInstanceList: personInstanceList
			, personInstanceTotal: personInstanceTotal ]

	}

	@Secured(['ROLE_BOARD'])
	def missing = {

		def personInstanceList = Person.list()

		def c = Person.createCriteria()

		def personMembershipInstanceList = c.list{
			memberships {
				isNotNull("id")
			}
		}

		personInstanceList.removeAll(personMembershipInstanceList)

		def personInstanceTotal = 0
		personInstanceList.each{
			personInstanceTotal++
		}

		flash.message = "Contacts without an active membership"

		return [ personInstanceList: personInstanceList
			, personInstanceTotal: personInstanceTotal ]

	}

	@Secured(['ROLE_BOARD'])
    def create = {

        def membershipInstance = new Membership()

		// Negative amount means repayment/reiumbursement
		membershipInstance.amount = 20

        membershipInstance.properties = params

		if (params?.person?.id) {
			def personInstance = Person.get(params?.person?.id)
			
			if (personInstance) {
				membershipInstance.person = personInstance
			}
		}

        return [membershipInstance: membershipInstance]
    }

	@Secured(['ROLE_BOARD'])
    def save = {
        def membershipInstance = new Membership(params)
		membershipInstance.memberId = ""
		
		if (params.receiptMembershipForm) {
			// Look up the most recent Membership Form
			def membershipForm = Instrument.findByNameIlikeAndObsoletionDateIsNull('%membership%')
			def personInstance = Person.read(params?.person?.id)
			if (membershipForm && personInstance) {
				def instrumentReceiptInstance = new InstrumentReceipt(person:personInstance, 
					instrument:membershipForm, outgoing:false, 
					transactionDate: membershipInstance.paymentDate)
				if (! instrumentReceiptInstance.save(flush:true) ) {
					flash.message = "Error Receipting Membership Form."
					render(view: "create", model: [membershipInstance: membershipInstance])
				}
			}
		}
				
        if (membershipInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'membership.label', default: 'Membership'), membershipInstance.id])}"
            redirect(controller:"person", action: "edit", fragment:"tab-membership", id: membershipInstance.person.id)
        }
        else {
            render(view: "create", model: [membershipInstance: membershipInstance])
        }
    }

	@Secured(['ROLE_BOARD'])
    def show = {
        def membershipInstance = Membership.get(params.id)
        if (!membershipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
            redirect(action: "list")
        }
        else {
            [membershipInstance: membershipInstance]
        }
    }

	@Secured(['ROLE_BOARD'])
    def edit = {
        def membershipInstance = Membership.get(params.id)
        if (!membershipInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [membershipInstance: membershipInstance]
        }
    }

	@Secured(['ROLE_BOARD'])
    def update = {
        def membershipInstance = Membership.get(params.id)
        if (membershipInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (membershipInstance.version > version) {
                    
                    membershipInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'membership.label', default: 'Membership')] as Object[], "Another user has updated this Membership while you were editing")
                    render(view: "edit", model: [membershipInstance: membershipInstance])
                    return
                }
            }
            membershipInstance.properties = params
			
			
            if (!membershipInstance.hasErrors() && membershipInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'membership.label', default: 'Membership'), membershipInstance.id])}"

	            redirect(controller:"person", action: "edit", fragment:"tab-membership", id: membershipInstance.person.id)
            }
            else {
                render(view: "edit", model: [membershipInstance: membershipInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
            redirect(action: "list")
        }
    }

	@Secured(['ROLE_BOARD'])
    def delete = {
        def membershipInstance = Membership.get(params.id)
        if (membershipInstance) {
			
			def personInstance = membershipInstance.person
			
            try {
                membershipInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
                redirect(controller:"person", action: "edit", id:personInstance.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
				redirect(controller:"person", action: "edit", id:personInstance.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membership.label', default: 'Membership'), params.id])}"
            redirect(action: "list")
        }
    }
}
