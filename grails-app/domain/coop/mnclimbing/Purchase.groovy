package coop.mnclimbing

class Purchase {
	Date purchaseDate
	String merchant
	Person person
	String purchaser
	String comments
	
	BigDecimal tax = 0.0
	BigDecimal shipping = 0.0
	BigDecimal total = 0.0

	Date paid
	
	BigDecimal getSubTotal() {
		total - (tax + shipping)
	}
	
	String toString() {
		"${merchant} on ${purchaseDate}"
	}
	
	static hasMany = [ items: PurchaseItem ]
	
	static transients = [ 'subTotal' ]

    static constraints = {
		purchaseDate()
		merchant(nullable:true)
		person(nullable:true)
		purchaser(nullable:true)
		shipping()
		tax()
		total()
		paid(nullable:true)
		comments(nullable:true)
    }
}
