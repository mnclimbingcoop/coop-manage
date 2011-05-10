package coop.mnclimbing

class DayPassController {

    static allowedMethods = [list:['GET', 'POST'], save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if (! params?.sort ) {
			params.sort = "passDate"
			params.order = "desc"
		}
		params.max = 50

		def dayPassEndDate = new Date()
		def maxPasses = 20
		
		if (params?.dayPassEndDate) {
			dayPassEndDate = params.dayPassEndDate
			println "End Date: ${dayPassEndDate}"
		}
		if (params?.maxPasses){
			maxPasses = Integer.parseInt(params.maxPasses)
		}
		

		def dayPassInstanceList = DayPass.createCriteria().list{
			lt("passDate", dayPassEndDate)
			order("passDate")
			maxResults(maxPasses)
		}
		
        [dayPassInstanceList: dayPassInstanceList, 
			dayPassEndDate: dayPassEndDate,
			maxPasses: maxPasses]
    }

    def create = {
		def sponsorList = Person.createCriteria().list {
			order("firstName")
			order("lastName")
		}

		def memberNames = Person.list().collect{ "'${it.firstName} ${it.lastName}'" }
		def dayPassInstance = new DayPass()
		def dpi = null
		dayPassInstance.properties = params
		
		if (params.id) {
			dpi = DayPass.read(params.id)
		}

		if (dpi) {
			// use the date from the previous pass
			dayPassInstance?.passDate = dpi.passDate
		} else {
			// get last day pass entered date and set that as the default date for the next one
			def dayPassInstanceList = DayPass.createCriteria().list{
				order("passDate")
				maxResults(1)
			}
			dayPassInstanceList.each{
				dayPassInstance.passDate = it.passDate
			}
		}
		
        return [dayPassInstance: dayPassInstance, 
			sponsorList: sponsorList,
			memberNames: memberNames ]
    }

    def save = {
        def dayPassInstance = new DayPass(params)
		
		// purchase date is always the same as access date
		dayPassInstance.paymentDate = dayPassInstance.passDate
		
        if (dayPassInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'dayPass.label', default: 'DayPass'), dayPassInstance.id])}"
            redirect(action: "create", id: dayPassInstance.id)
        }
        else {
            render(view: "create", model: [dayPassInstance: dayPassInstance])
        }
    }

    def edit = {
        def dayPassInstance = DayPass.get(params.id)
		
		def c = Person.createCriteria()
		def sponsorList = c.list {
			order("firstName")
			order("lastName")
		}

		def memberNames = Person.list().collect{ "'${it.firstName} ${it.lastName}'" }
		
        if (!dayPassInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dayPass.label', default: 'DayPass'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [dayPassInstance: dayPassInstance, 
				sponsorList: sponsorList,
				memberNames: memberNames ]
        }
    }

    def update = {
        def dayPassInstance = DayPass.get(params.id)
        if (dayPassInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dayPassInstance.version > version) {
                    
                    dayPassInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'dayPass.label', default: 'DayPass')] as Object[], "Another user has updated this DayPass while you were editing")
                    render(view: "edit", model: [dayPassInstance: dayPassInstance])
                    return
                }
            }
            dayPassInstance.properties = params

			// purchase date is always the same as access date
			dayPassInstance.paymentDate = dayPassInstance.passDate
	
            if (!dayPassInstance.hasErrors() && dayPassInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'dayPass.label', default: 'DayPass'), dayPassInstance.id])}"
                redirect(action: "list", id: dayPassInstance.id)
            }
            else {
                render(view: "edit", model: [dayPassInstance: dayPassInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dayPass.label', default: 'DayPass'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def dayPassInstance = DayPass.get(params.id)
        if (dayPassInstance) {
            try {
                dayPassInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'dayPass.label', default: 'DayPass'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'dayPass.label', default: 'DayPass'), params.id])}"
                redirect(action: "list", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dayPass.label', default: 'DayPass'), params.id])}"
            redirect(action: "list")
        }
    }
}
