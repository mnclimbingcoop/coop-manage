package coop.mnclimbing

class PurchaseItem {

	Integer count = 1
	String name
	BigDecimal amount = 0.0
	
	String toString() {
		"${count} ${name} @ \$${amount}"
	}
	
	static belongsTo = [ purchase : Purchase ]
	
    static constraints = {
		count()
		name()
		amount()
    }
}
