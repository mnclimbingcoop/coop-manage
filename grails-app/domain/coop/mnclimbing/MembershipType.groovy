package coop.mnclimbing

class MembershipType {

	String name
	Boolean expires = false

	String toString() { name }

    static constraints = {
		name()
		expires()
    }
}
