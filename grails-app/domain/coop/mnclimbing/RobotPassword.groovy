package coop.mnclimbing

class RobotPassword {
	
	String ipAddressStartsWith = ""
	String secret = "changeme"

    static constraints = {
		ipAddressStartsWith()
		secret()
    }
}
