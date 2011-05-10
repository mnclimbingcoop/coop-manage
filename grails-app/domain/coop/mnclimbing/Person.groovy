package coop.mnclimbing

class Person {


	// TODO: Parent/Guardian??
	String title
	String firstName
	String middleName
	String lastName
	String suffix
	
	String nickName

	Date birthDate

	Address address
	String phoneNumber
	String emailAddress

	// Link to their user account
	User user

	static hasMany = [ memberships: Membership
		, automobiles: Automobile
		, passes: Access
		, stock: Stock
		, donations: Donation
		, cards: AccessCardAssignment
		, forms: InstrumentReceipt
		, emergencyContacts: EmergencyContact ]

	String toString() {
		fullName
	}

	AccessCardAssignment getAccessCardAssignment() {
		def now = new Date()

		def c = AccessCardAssignment.createCriteria()

		def accessCardInstanceList = c.list{
			and {
				person {
					eq("id", id)
				}
				lt("issueDate", now)
				or {
					gt("returnDate", now)
					isNull("returnDate")
				}
				eq("lost", false)
			}
		}

		if (accessCardInstanceList) {
			return accessCardInstanceList[0]
		} else {
			return null
		}
		
	}

	Access getActivePass() {

		def now = new Date()

		def c = Access.createCriteria()
		def accessInstaceList = c.list{
			and {
				gt("endDate", now)
				lt("startDate", now)
				person{
					eq("id",id)
				}
			}
		}

		// if there was a match, return the first one
		if (accessInstaceList) {
			return accessInstaceList[0]
		} else {
			return null
		}

	}

	String getFullName() {
        String retString = ""
        if (title != null) {
            retString += title + ". "
        }
        if (firstName) {
            retString += firstName
        }
		if (nickName) {
			retString += " (${nickName})"
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

	static transients = [ 'fullName', 'activePass', 'accessCardAssignment' ]
	
    static constraints = {

		title(nullable:true, maxSize:10)
		firstName(maxSize:30)
		middleName(nullable:true, maxSize:20)
		lastName(nullable:true, maxSize:30)
		suffix(nullable:true, maxSize:10)
		nickName(nullable:true)
		birthDate(nullable:true)
		address(nullable:true)
		emailAddress(email:true)
		phoneNumber(nullable:true)
		user(nullable:true)

    }
}
