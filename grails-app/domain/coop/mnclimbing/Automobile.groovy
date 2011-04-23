package coop.mnclimbing

class Automobile {

	String licencePlateNumber
	String licencePlateState = 'MN'
	String make
	String model
	String color
	Integer year
	
	String toString() {
		return "${color} ${year} ${make} ${model}, ${licencePlateState} plates: ${licencePlateNumber}"
	}
	
	static belongsTo = [ person : Person ]
		
    static constraints = {
		licencePlateNumber()
		licencePlateState()
		make()
		model()
		color()
		year()
    }
}
