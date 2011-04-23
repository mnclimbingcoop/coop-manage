package coop.mnclimbing

class Instrument {


	String abbreviation
	String name
	Integer currentVersion = 1
	Date versionDate = new Date()
	Date obsoletionDate
	Boolean required = false

	static transients = ['obsolete']
	
	String toString() {
		"${name} (v${currentVersion})"
	}
	
	Boolean getObsolete() {
		if ( ! obsoletionDate ) {
			return false
		} else {
			def now = new Date()
			if ( now > obsoletionDate ) {
				return true
			} else {
				return false
			}
		}
	}
	 
    static constraints = {
		abbreviation()
		name()
		currentVersion()
		versionDate(nullable:true)
		required()
		obsoletionDate(nullable:true)
    }
}
