package coop.mnclimbing

class AccessCard {

	String facilityCode = '178'
	String cardIdentifier
	Boolean facilityAssigned = true
	Boolean lost = false
	String label

	String toString() {
		if (facilityAssigned) {
			"MNCC Keyfob ID: ${label}"
		} else {
			"Personal Card #${cardIdentifier} (${label})"
		}
	}

	static hasMany = [ assignments : AccessCardAssignment ]

    static constraints = {
		facilityCode(nullable:true)
		cardIdentifier(unique:true)
		facilityAssigned()
		label(nullable:true)
		lost()
    }
}
