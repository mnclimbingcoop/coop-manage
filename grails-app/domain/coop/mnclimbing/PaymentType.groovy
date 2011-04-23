package coop.mnclimbing

class PaymentType {
	String name
	
	Boolean processFeeCharge = false
	BigDecimal processingRate = 0.0
	BigDecimal processingFee = 0.0

	String toString() { name }

    static constraints = {
		name()
		processingRate(min:0.0, max:100.0)
		processFeeCharge()
		processingFee()
    }
}
