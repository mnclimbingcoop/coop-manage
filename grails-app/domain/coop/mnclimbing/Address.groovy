package coop.mnclimbing

class Address {

	String address1
	String address2
	String city
	String state
	String zipcode
	String zip4

	String toString() {
		return "${address1}, ${city}, ${state}"
	}

	static transients = [ 'cityStateZip', 'fullZipcode' ]
	
	String getCityStateZip = {
		return "${city}, ${state} ${fullZipcode}"
	}

	String getFullZipcode = {
		if (zip4) {
			return "${zipcode}-${zip4}"
		} else {
			return zipcode
		}
	}

    static constraints = {
		address1(maxSize:40)
		address2(nullable:true, maxSize:40)
		city(maxSize:30)
		state(maxSize:2)
		zipcode(nullable:true, maxSize:5, matches:"[0-9][0-9][0-9][0-9][0-9]")
		zip4(nullable:true, maxSize:4, matches:"[0-9][0-9][0-9][0-9]")
    }
}
