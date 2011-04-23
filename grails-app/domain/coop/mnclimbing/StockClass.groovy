package coop.mnclimbing

class StockClass {

	String name
	String abbreviation

	String toString() { name }

    static constraints = {
		name()
		abbreviation(maxSize:1)
    }
}
