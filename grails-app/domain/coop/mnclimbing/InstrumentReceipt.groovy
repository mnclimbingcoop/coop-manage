package coop.mnclimbing

class InstrumentReceipt {

	Instrument instrument
	Date transactionDate
	Boolean outgoing = true
	Integer version

	static transients = [ 'incoming' ]

	Boolean getIncoming() {
		return ( ! outgoing )
	}

	static belongsTo = [ person : Person ]

    static constraints = {
		instrument()
		transactionDate()
		outgoing()

    }
}
