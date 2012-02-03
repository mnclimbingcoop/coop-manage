package coop.mnclimbing

class CoopEvent {
	String name
	Date eventDate
	Date earlyRegistrationDeadline
	Integer maxRegistrants
	
	String toString() { name }
	
	static hasMany = [ attendees : CoopEventAttendee ]
	
    static constraints = {
		name()
		eventDate()
		earlyRegistrationDeadline(nullable:true)
		
    }
}
