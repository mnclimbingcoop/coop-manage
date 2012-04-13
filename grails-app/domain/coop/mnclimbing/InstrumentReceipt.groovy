package coop.mnclimbing

class InstrumentReceipt {

	Instrument instrument
	Date transactionDate
	Boolean outgoing = true
	Integer version

	static transients = [ 'incoming', 'expirationDate', 'isExpired', 'expiresIn' ]

	Boolean getIncoming() {
		return ( ! outgoing )
	}

	Date getExpirationDate() {
		if (instrument.daysValidFor) {
			transactionDate + instrument.daysValidFor
		} else {
			null
		}
	}

	Integer getExpiresIn() {
		if (instrument.daysValidFor) {
			Date now = new Date()
			Date expirationDate = this.getExpirationDate()
			return expirationDate - now
		} else {
			null
		}
	}

	Boolean getIsExpired() {
		if (instrument.daysValidFor) {
			Date expirationDate = this.getExpirationDate()
			Date today = new Date()
			if (expirationDate > today) {
				false
			} else {
				true
			}
		} else {
			false
		}
	}

	static belongsTo = [ person : Person ]

    static constraints = {
		instrument()
		transactionDate()
		outgoing()

    }
}
