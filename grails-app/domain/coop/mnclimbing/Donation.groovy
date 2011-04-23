package coop.mnclimbing

class Donation extends Payment {

	String comments
	
	Donation() {
		taxable = false
		taxRate = 0
	}

	String toString() {
		"\$${amount} donation"
	}

    static constraints = {
		comments()
    }
}
