package coop.mnclimbing

class Payment {
	// Payment amount in $USD
	// Negative amount means repayment/reiumbursement
	BigDecimal amount
	// Type of payment
	PaymentType paymentType
	// Date of payment
	Date paymentDate
	// Transaction / Check # / etc...
	String transactionId
	
	// Defaults for tax
	Boolean taxable = false
	BigDecimal taxRate = 7.775

	BigDecimal getProcessingFee() {
		// default processing fee is $0.00
		BigDecimal processingFee = 0.0
		
		if (paymentType?.processFeeCharge) {
			// processing fee is applied to the total amount
			processingFee = amount * ( paymentType.processingRate / 100 )
			processingFee += paymentType.processingFee ?: 0
		}
		return processingFee
	}
	
	BigDecimal getTaxFee() {
		// default tax fee is $0.00
		BigDecimal taxFee = 0.0
		// if the item is taxable, find out what the tax fee is.
		if (taxable) {
			def rate = taxRate / 100
			def price = amount / (1 + rate)
			taxFee = amount - price
		}
		return taxFee
	}
	
	BigDecimal getAmountAfterFees() {
		 amount - ( processingFee + taxFee)
	}

	static belongsTo = [ person : Person ]
	static transients = [ 'processingFee', 'taxFee', 'amountAfterFees' ]

    static constraints = {
		amount(nullable:true)
		paymentType(nullable:true)
		paymentDate()
		transactionId(nullable:true)
		taxable()
		taxRate(min:0.0, max:100.0)
    }
}
