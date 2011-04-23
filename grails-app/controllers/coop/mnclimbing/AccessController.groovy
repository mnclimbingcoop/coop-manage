package coop.mnclimbing
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class AccessController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index = {
        redirect(action: "list", params: params)
    }
	
	// TODO: Daily Pass Entry
	
	def daily = {
		flash.message = "TODO: Daily Pass Entry"
	}

    def list = {

		// Figure out total sales
		def accessSalesTotal = 0

		def accessInstanceList = Access.list()
		accessInstanceList.each{ a ->
			if ( a.paymentType.name == "PayPal") {
				accessSalesTotal += (a.amount * 0.971)
			} else {
				accessSalesTotal += a.amount
			}
		}

		def accessSalesTax = accessSalesTotal * 0.07775
		def accessSalesAmount = accessSalesTotal - accessSalesTax

        [ accessInstanceList: Access.list(params)
			, accessInstanceTotal: Access.count()
			, accessSalesTotal: accessSalesTotal
			, accessSalesTax: accessSalesTax
			, accessSalesAmount: accessSalesAmount ]
    }

	def missing = {

		def c = Person.createCriteria()
		// everyone with a membership
		def personInstanceList = c.list{
			memberships {
				isNotNull("id")
			}
		}

		// everyone with an access pass
		c = Person.createCriteria()
		def personAccessInstanceList = c.list{
			passes {
				isNotNull("id")
			}
		}

		personInstanceList.removeAll(personAccessInstanceList)

		def personInstanceTotal = 0
		personInstanceList.each{
			personInstanceTotal++
		}

		flash.message = "Members without an access pass"

		return [ personInstanceList: personInstanceList
			, personInstanceTotal: personInstanceTotal ]

	}

	def active = {
		flash.message = "Active Access Passes"

		def c = Access.createCriteria()
		def now = new Date()

		def accessInstanceList = c.list{
			and {
				lt("startDate", now)
				gt("endDate", now)
			}
		}
		
		def accessInstanceTotal = Access.count()

		render(view: "list", model: [accessInstanceList: accessInstanceList
			, accessInstanceTotal: accessInstanceTotal] )

	}

	def expired = {

		flash.message = "Expired Access Passes"

		def c = Access.createCriteria()
		def now = new Date()

		def accessInstanceList = c.list{
			lt("endDate", now)
		}

		def accessInstanceTotal = Access.count()

		render(view: "list", model: [accessInstanceList: accessInstanceList
			, accessInstanceTotal: accessInstanceTotal] )

	}

	def expiring = {

		flash.message = "Expiring Access Passes"

		def c = Access.createCriteria()
		def now = new Date()

		def later = new Date()
		// 31 days from now
		later = later + 31

		// starts before today, but expires in 31 days
		def accessInstanceList = c.list{
			and {
				lt("startDate", now)
				lt("endDate", later)
			}
		}

		def accessInstanceTotal = Access.count()

		render(view: "list", model: [accessInstanceList: accessInstanceList
			, accessInstanceTotal: accessInstanceTotal] )

	}

    def create = {
        def accessInstance = new Access()
        accessInstance.properties = params
        return [accessInstance: accessInstance]
    }

    def save = {
        def accessInstance = new Access(params)

		accessInstance.accessDuration = accessInstance.accessType.duration

		// TODO: This needs work
		def endDate = new GregorianCalendar()

		Integer i = 0
		i = ( (accessInstance.accessDuration / 12) * 365)
		accessInstance.endDate = accessInstance.startDate + i

        if (accessInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'access.label', default: 'Access'), accessInstance.id])}"
            redirect(controller:"person", action: "edit", fragment:"tab-passes", id: accessInstance.person.id)
        }
        else {
            render(view: "create", model: [accessInstance: accessInstance])
        }
    }

    def show = {
        def accessInstance = Access.get(params.id)
        if (!accessInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
            redirect(action: "list")
        }
        else {
            [accessInstance: accessInstance]
        }
    }

    def edit = {
        def accessInstance = Access.get(params.id)
        if (!accessInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [accessInstance: accessInstance]
        }
    }

    def update = {
        def accessInstance = Access.get(params.id)
        if (accessInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (accessInstance.version > version) {
                    
                    accessInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'access.label', default: 'Access')] as Object[], "Another user has updated this Access while you were editing")
                    render(view: "edit", model: [accessInstance: accessInstance])
                    return
                }
            }
            accessInstance.properties = params
            if (!accessInstance.hasErrors() && accessInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'access.label', default: 'Access'), accessInstance.id])}"
                redirect(controller:"person", action: "edit", id: accessInstance.person.id)
            }
            else {
                render(view: "edit", model: [accessInstance: accessInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def accessInstance = Access.get(params.id)
        if (accessInstance) {
            try {
                accessInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'access.label', default: 'Access'), params.id])}"
            redirect(action: "list")
        }
    }
}
