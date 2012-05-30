package coop.mnclimbing

import grails.plugins.springsecurity.Secured

class PersonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def appTuningService

	@Secured(['ROLE_BOARD'])
    def index = {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_BOARD'])
    def list = {

        params.max = Math.min(params.max ? params.int('max') : 20, 100)

		// If there's no sort parameter, then we'll sort by lastname
		if ( ! params?.sort ) {
			params.sort = 'lastName'
			params.order = 'asc'
		}

		def personInstanceList = Person.list(params)

		def personInstanceTotal = Person.count()

        [personInstanceList: personInstanceList
			, personInstanceTotal: personInstanceTotal ]
    }

	@Secured(['ROLE_BOARD'])
    def create = {
        def personInstance = new Person()
		def addressInstance = new Address()

        personInstance.properties = params
		
        return [personInstance: personInstance, addressInstance: addressInstance]
    }

	@Secured(['ROLE_BOARD'])
	def form = {}

	@Secured(['ROLE_BOARD'])
	def find = {

		def searchString = params.id

		if ( ! searchString ) {
			searchString = params.value
		}

		def c = Person.createCriteria()

		def personInstanceList = []

		if ( searchString ) {
			personInstanceList = c.list{
				or {
					ilike("firstName", "%${searchString}%")
					ilike("lastName", "%${searchString}%")
				}
				maxResults(20)
			}
		}

		def personInstanceTotal = personInstanceList.size()

		[personInstanceList: personInstanceList
			, personInstanceTotal: personInstanceTotal
		    , searchString: searchString ]
	}
	
	@Secured(['ROLE_BOARD'])
    def save = {

		// println "PersonController.save:params:: ${params}"

		// Flush the memory first
		appTuningService.cleanUpGorm()

		// Create new person and address
        def personInstance = new Person(params)
        def addressInstance = new Address(params.address)
		// find the lifetime membership
		def lifeTimeMember = MembershipType.findByExpires(false)

		// if that didn't work, search by name
		if ( ! lifeTimeMember ) {
			lifeTimeMember = MembershipType.findByName("lifetime")
		}

		// Hmm...
		if ( ! lifeTimeMember ) {
			println "PersonController.save::Could not find lifeTimeMember!!!"
		}

		// If at least the state was specified, create an address
		if (addressInstance.state && addressInstance.save(flush: true)) {
			personInstance.address = addressInstance
		} else {
			personInstance.address = null
		}

        if (personInstance.save(flush: true)) {

			def memberId = params?.membership?.memberId

			if (memberId) {
				// this person is a member
				def membershipInstance = new Membership(type:lifeTimeMember, memberId:memberId )

				personInstance.addToMemberships(membershipInstance)

				if ( ! membershipInstance.save(flush:true) ) {
					println "PersonController.save::failed to save membership!"
					println "${membershipInstance.errors}"
				}
			}

            flash.message = "${message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])}"
            redirect(action: "edit", fragment:"tab-contact", id: personInstance.id)
        }
        else {
            render(view: "create", model: [personInstance: personInstance])
        }
    }

	@Secured(['ROLE_BOARD','ROLE_STAFF'])
    def show = {

		def now = new Date()
        def personInstance = Person.get(params.id)
		
		def hidDoorEventInstanceList = HidDoorEvent.createCriteria().list{
			or {
				ilike('eventSubject', personInstance.fullName)
				ilike('eventSubject', "${personInstance.firstName}% ${personInstance.lastName}")
			}
			order 'eventDate', 'desc'
			maxResults(100)
		}

		//List of instruments not yet received
		def c = Instrument.createCriteria()
		def instrumentInstanceList = c.list{
			and {
				or {
					isNull("obsoletionDate")
					gt("obsoletionDate", now)
				}
				eq("required", true)
			}
		}
		
		personInstance.forms.each{
			if ( ( it.incoming ) && ( instrumentInstanceList.contains( it.instrument ) ) ) {
				instrumentInstanceList.remove(it.instrument)
			}
		}

        if (!personInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
            redirect(controller:"mainMenu", action: "menu")
        } else {
            return [personInstance: personInstance, 
				hidDoorEventInstanceList: hidDoorEventInstanceList,
				instrumentInstanceList: instrumentInstanceList ]
        }
    }

	@Secured(['ROLE_BOARD'])
    def edit = {
		def now = new Date()
		
        def personInstance = Person.get(params.id)

        def addressInstance = personInstance?.address
		
		//List of instruments not yet received
		def c = Instrument.createCriteria()
		def instrumentInstanceList = c.list{
			and {
				or {
					isNull("obsoletionDate")
					gt("obsoletionDate", now)
				}
				eq("required", true)
			}
		}
		
		personInstance.forms.each{
			if ( ( it.incoming ) && ( instrumentInstanceList.contains( it.instrument ) ) ) {
				instrumentInstanceList.remove(it.instrument)
			}
		}

        if (!personInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
            redirect(action: "list")
        } else {
            return [personInstance: personInstance, 
				addressInstance: addressInstance,
				instrumentInstanceList: instrumentInstanceList ]
        }
    }

	@Secured(['ROLE_BOARD'])
    def update = {
        def personInstance = Person.get(params.id)

		// If the person was found
        if (personInstance) {

			def addressInstance = null

			if ( ! personInstance.address ) {

				addressInstance = new Address(params.address)

				// If at least the state was specified, create an address
				if (addressInstance.state && addressInstance.save(flush: true)) {
					personInstance.address = addressInstance
				} else {
					addressInstance = null
					personInstance.address = null
				}
			}

            if (params.version) {
                def version = params.version.toLong()
                if (personInstance.version > version) {
                    
                    personInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'person.label', default: 'Person')] as Object[], "Another user has updated this Person while you were editing")
                    render(view: "edit", model: [personInstance: personInstance])
                    return
                }
            }
            personInstance.properties = params

			// if we created a new address, then...
			if ( addressInstance ) {
				personInstance.address = addressInstance
			}

			if (!personInstance.hasErrors() && personInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])}"
				redirect(action: "edit", fragment:"tab-contact", id: personInstance.id)
			}
			else {
				render(view: "edit", model: [personInstance: personInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
			redirect(action: "list")
		}
	}

	@Secured(['ROLE_BOARD'])
	def delete = {
		def personInstance = Person.get(params.id)
		if (personInstance) {
			try {
				personInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
			redirect(action: "list")
		}
	}
}
