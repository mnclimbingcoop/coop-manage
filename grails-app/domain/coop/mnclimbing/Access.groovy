package coop.mnclimbing

class Access extends Payment {

	Access() {
		taxable = true
		taxRate = 7.775
	}
	
	AccessType accessType
	// start and end of access paid for
	Date startDate
	Date endDate
	// Duration in months
	Integer accessDuration
	
	static mapping = {
		accessDuration(min:0)
	}
}
