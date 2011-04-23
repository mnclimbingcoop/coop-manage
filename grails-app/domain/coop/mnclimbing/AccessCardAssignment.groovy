package coop.mnclimbing

class AccessCardAssignment {

	AccessCard accessCard

	static belongsTo = [ person : Person ]

	Date issueDate = new Date()
	
	Date returnDate = null

	Boolean lost = false
	
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
