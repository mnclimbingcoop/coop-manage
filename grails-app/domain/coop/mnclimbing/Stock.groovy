package coop.mnclimbing

class Stock extends Payment {

	Stock() {
		taxable = false
		taxRate = 0
	}
	
	StockClass classOfStock

    static constraints = {
		classOfStock()
    }
}
