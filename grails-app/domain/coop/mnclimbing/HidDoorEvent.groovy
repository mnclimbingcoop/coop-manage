package coop.mnclimbing

class HidDoorEvent {
	
	Date uploadDate = new Date()
	String ipAddress
	String doorName
	Integer eventCode
	Date eventDate
	String eventSubject
	Boolean imported = false
	
	String toString() {
		"${eventCode},${eventDate},${eventSubject}"
	}
	
    static constraints = {
		uploadDate()
		ipAddress()
		doorName(index:'door_name_idx,door_date_idx')
		eventCode(nullable:true)
		eventDate(index:'even_date_idx,door_date_idx')
		eventSubject(nullable:true)
		imported()
    }
}
