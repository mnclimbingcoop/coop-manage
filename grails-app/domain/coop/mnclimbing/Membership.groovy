package coop.mnclimbing

// marks a person as a member of a partcular group
// (Co-Op Member, Investor, Board, etc...)
class Membership extends Payment {

	Membership() {
		taxable = false
		taxRate = 0
	}
	
	MembershipType type
	String memberId
	// Date they became a member
	Date membershipFrom = new Date()
	Date membershipTo

	MembershipTerminationReason terminationReason

	static belongsTo = [ person : Person ]
	
    static constraints = {
		type()
		membershipFrom()
		membershipTo(nullable:true)
		terminationReason(nullable:true)
    }
}
