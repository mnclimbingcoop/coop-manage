package coop.mnclimbing

class AccessCardAssignment {

	AccessCard accessCard
	Date issueDate = new Date()
	Date returnDate = null
	Boolean lost = false
	
	static belongsTo = [ person : Person ]
	static transients = [ 'active', 'status' ]

	/** is this assigned card active? */
	Boolean isActive() {
		def now = new Date()
		if ( lost || returnDate || !accessCard.active ) {
			false
		} else { true }
	}

	/** a textual representation of the active status of this card assignment */
	String getStatus() {
		this.active ? 'active' : 'inactive'
	}

	String toString() {
		"issued to ${person} on ${issueDate}"
	}

    static constraints = {
		accessCard()
		person()
		issueDate()
		returnDate(nullable:true)
		lost()

    }
}
