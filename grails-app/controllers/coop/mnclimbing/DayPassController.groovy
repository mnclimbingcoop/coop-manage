package coop.mnclimbing

import org.joda.time.*

class DayPassController {

    static allowedMethods = [list:['GET', 'POST'], save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		
		def daySpan = 20

		def dayPassEndDate = new Date()
		def dayPassStartDate = dayPassEndDate - (daySpan * 2)
		def midnight = new LocalTime(0,0)
		def today = new Date()
		def dayPassHistogram = []
		
		if (params?.dayPassRefDate) {
			def dayPassRefDate = ( new LocalDate(params.dayPassEndDate) ).toDateTime(midnight).toCalendar().time
			dayPassStartDate = dayPassRefDate - daySpan
			dayPassEndDate = dayPassRefDate + daySpan
		}
		if (params?.dayPassEndDate) {
			dayPassEndDate = ( new LocalDate(params.dayPassEndDate) ).toDateTime(midnight).toCalendar().time
			println "End Date: ${dayPassEndDate}"
		}
		if (params?.dayPassStartDate) {
			dayPassStartDate = ( new LocalDate(params.dayPassStartDate) ).toDateTime(midnight).toCalendar().time
			println "Start Date: ${dayPassStartDate}"
		} else {
			dayPassStartDate = dayPassEndDate - (daySpan * 2)
		}
		
		if (dayPassStartDate > dayPassEndDate) {
			dayPassStartDate = dayPassEndDate - (daySpan * 2)
		}
		
		// shift the range if the end date is in the future
		if (dayPassEndDate > today) {
			dayPassStartDate = dayPassStartDate - (dayPassEndDate - today)
			dayPassEndDate = today
		}
		
		
		def dayPassInstanceList = DayPass.createCriteria().list{
			gt("passDate", dayPassStartDate)
			lt("passDate", dayPassEndDate)
			order("passDate")
		}
		
		def cursorDate = dayPassStartDate
		while (cursorDate <= dayPassEndDate) {
			def prevDay = cursorDate - 1
			def matchingPasses = DayPass.findAllByPassDateBetween(prevDay, cursorDate)
			def passCount = 0
			matchingPasses.each{
				passCount++
			}
			dayPassHistogram.add([date: cursorDate, count:passCount])
			
			cursorDate++
		}
		
        [dayPassInstanceList: dayPassInstanceList,
			dayPassStartDate: dayPassStartDate,
			dayPassEndDate: dayPassEndDate,
			dayPassHistogram: dayPassHistogram ]
    }

    def create = {
		
		def jodaToday = new LocalDate()		
		def yearRange = (2010..jodaToday.year)
		
		// list of members
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
				order("passDate", "desc")
				maxResults(1)
			}
			dayPassInstanceList.each{
				dayPassInstance.passDate = it.passDate
			}
		}
		
        return [dayPassInstance: dayPassInstance, 
			sponsorList: sponsorList,
			memberNames: memberNames,
			yearRange: yearRange ]
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
				memberNames: memberNames,
				yearRange: yearRange ]
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
