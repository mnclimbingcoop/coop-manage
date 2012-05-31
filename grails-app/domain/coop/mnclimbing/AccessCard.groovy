package coop.mnclimbing

class AccessCard {

	String facilityCode = '178'
	String cardIdentifier
	Boolean facilityAssigned = true
	Boolean lost = false
	String label

	static transients = [ 'active', 'status' ]

	/** is this card active? */
	Boolean isActive() {
		def now = new Date()
		if (lost ) {
			false
		} else { true }
	}

	/** a textual representation of the active status of this card */
	String getStatus() {
		this.active ? 'active' : 'inactive'
	}

	String toString() {
		if (facilityAssigned) {
			"MNCC Keyfob: ${label} (HID#: ${cardIdentifier})"
		} else {
			"Personal Card #${cardIdentifier} (${label})"
		}
	}

	static hasMany = [ assignments : AccessCardAssignment ]

    static constraints = {
		facilityCode(nullable:true)
		cardIdentifier(unique:'facilityCode')
		facilityAssigned()
		label(nullable:true)
		lost()
    }
}
