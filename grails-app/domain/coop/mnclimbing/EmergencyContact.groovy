package coop.mnclimbing

class EmergencyContact {


	String title
	String firstName
	String middleName
	String lastName
	String suffix

	String relation

	Address address
	String phoneNumber

	static belongsTo = [ person: Person ]

	String toString() {
		fullName
	}

	String getFullName() {
        String retString = ""
        if (title != null) {
            retString += title + ". "
        }
        if (firstName != null) {
            retString += firstName
        }
        if (middleName != null) {
            if (firstName != null) {
                if (firstName.length() == 1) {
                    retString += " " + middleName + "."
                } else {
                    retString += " " + middleName.substring(0,1) + "."
                }
            }
        }
        if (lastName != null) {
            retString += " " + lastName
        }
        if (suffix != null) {
            retString += " " + suffix
        }
        return retString.trim().replace("  ", " ")
    }

	static transients = [ 'fullName' ]

    static constraints = {

		title(nullable:true, maxSize:10)
		firstName(maxSize:30)
		middleName(nullable:true, maxSize:20)
		lastName(nullable:true, maxSize:30)
		suffix(nullable:true, maxSize:10)
		relation(nullable:true)
		address(nullable:true)
		phoneNumber(nullable:true)

    }
}
