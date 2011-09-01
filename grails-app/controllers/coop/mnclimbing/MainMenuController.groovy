package coop.mnclimbing

import grails.plugins.springsecurity.Secured

class MainMenuController {

	def index = {
	
		def destination = "menu"
		
		def userAgent = request.getHeader('user-agent')
		if ( userAgent =~ /iphone/ || userAgent =~ /Android/ ) {
			destination = "mobile"
		}
		
		[ destination: destination ]
		
	}
	
	def dbping = {
		try {
			def dummy = Person.count()
			render "ok"
		} catch (Exception ex) {
			response.sendError(500)
			render "fail"
		}
	}

	@Secured(['ROLE_BOARD'])
	def mobile = {
	}
	
	@Secured(['ROLE_BOARD'])
    def menu = {

		def countMembers = 0
		def countSoldPasses = 0
		def countActivePasses = 0
		def stockTotal = 0
		
		def hidDoorEventInstanceList = HidDoorEvent.createCriteria().list{
			and {
				eq('eventCode', 2020)
			}
			maxResults(15)
			order('eventDate', 'desc')

		}
		

				
		params.max = Math.min(params.max ? params.int('max') : 30, 100)
		[hidDoorEventInstanceList: HidDoorEvent.list(params), hidDoorEventInstanceTotal: HidDoorEvent.count()]


		def now = new Date()

		def c = Access.createCriteria()

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

		def countExpiringPasses = accessInstanceList.count()


		countMembers = Membership.count()
		countSoldPasses = Access.count()

		Stock.list().each{
			stockTotal += it.amount
		}

		c = Access.createCriteria()
		def activeList = c.list{
			and {
				lt("startDate", now)
				gt("endDate", now)
			}
		}

		countActivePasses = activeList.size()

		[ countMembers: countMembers
			, countSoldPasses: countSoldPasses
			, countActivePasses: countActivePasses,
			, stockTotal: stockTotal
			, countExpiringPasses: countExpiringPasses
			, hidDoorEventInstanceList: hidDoorEventInstanceList ]

	}
}
