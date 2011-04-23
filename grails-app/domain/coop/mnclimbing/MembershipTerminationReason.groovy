package coop.mnclimbing

class MembershipTerminationReason {

	String name
	Boolean allowReactivation

	String toString() { name }

    static constraints = {
		name()
		allowReactivation()
    }
}
