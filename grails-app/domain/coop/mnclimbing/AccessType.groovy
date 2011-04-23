package coop.mnclimbing

class AccessType {
	
	String name
	Integer duration

	String toString() { name }

    static constraints = {
		name()
		duration()
    }
}
