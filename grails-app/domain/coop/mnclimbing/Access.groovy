package coop.mnclimbing

class Access extends Payment {

	Access() {
		taxable = true
		taxRate = 7.775
	}
	
	/** The type of access pass (month, quarter, year, etc...) */
	AccessType accessType
	/** Start date of the access pass */
	Date startDate
	/** End date of the access pass */
	Date endDate
	/** Date the expiration notification email was sent */
	Date expirationNotificationSent
	/** Duration in months */
	Integer accessDuration
	
	static mapping = {
		accessDuration(min:0)
	}

	/** Named Queries */
	static namedQueries = {
		/*
			Business Rules for Expiring Pass notifications:

			* Monthly Pass Holders: One notice, 7 days before
			* Quarterly, Bi-Annual, and Annual Passes: 14 days before
			* Record notifications all sent, and when.

			Send email through Gmail's servers
			Send email from:
				noreply@mnclimbingcoop.com

		*/
		expringInTheNextMonth { ->
			def now = new Date()
			def endThreshold = now + 31
			and {
				lt("startDate", now)
				gt("endDate", now)
				lt("endDate", endThreshold)
			}
			order("endDate","desc")
		}

		needNotification { ->
			def now = new Date()
			def sevenDays = now + 7
			def twoWeeks = now + 30
			def monthPass = AccessType.findByDuration(1)

			or {
				and {
					accessType { idEq(monthPass.id) }
					lt("startDate", now)
					gt("endDate", now)
					lt("endDate", sevenDays)
				}
				and {
					accessType { ne('id', monthPass.id) }
					lt("startDate", now)
					gt("endDate", now)
					lt("endDate", twoWeeks)
				}
			}	
		}
	}
}
