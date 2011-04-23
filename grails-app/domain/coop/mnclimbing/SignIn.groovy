package coop.mnclimbing

class SignIn {
	
	String fullName
	Person person
	
	Boolean member = false
	Boolean guest = false
	Boolean visitor = false
	
	Date signInDate
	
	String toString() {
		"${fullName} on ${signInDate}"
	}
	
	String getMemberVisitorGuest() {
		if (member) {
			"Member"
		} else if (guest) {
			"Guest"
		} else if (visitor) {
			"Visitor"
		} else {
			"Unknown"
		}
	}
	
	static transients = ['memberVisitorGuest']
	
    static constraints = {
		fullName()
		person(nullable:true)
		member()
		guest()
		visitor()
		signInDate()
    }
}
