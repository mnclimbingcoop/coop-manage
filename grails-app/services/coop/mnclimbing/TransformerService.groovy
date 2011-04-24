package coop.mnclimbing

class TransformerService {

    static transactional = true
	static debug = false
	
	String encodeWiegand(Integer facilityCode, Integer cardNumber) {
				
		def binaryFacilityCode = Integer.toBinaryString(facilityCode)
		def binaryCardNumber = Integer.toBinaryString(cardNumber)
		
		def rawCardNumber  = "${binaryFacilityCode}${binaryCardNumber}"
		
		def leftParity = false
		rawCardNumber[0..12].each{
			if (it == "1") {
				leftParity = ! leftParity
			}
		}
		
		def rightParity = true
		rawCardNumber[13..23].each{
			if (it == "1") {
				rightParity = ! rightParity
			}
		}
		
		def l = '0'
		def r = '0'
		
		if (leftParity) { l = '1' }
		if (rightParity) { r = '1' }
		
		String binaryData = "${l}${rawCardNumber}${r}"
		
		BigInteger cardData = Integer.parseInt(binaryData, 2)
		
		rawCardNumber = cardData.toString(16)
		
		return rawCardNumber.toUpperCase()
		
	}

    def generateHidEdgeProData() {
		
		def now = new Date()
		def beginningOfNextMonth = now + 31
		
		// get all people who have a valid access pass
		// Meaning:
		//	Hasn't expired yet.
		//	Starts sometime in the next month
		def cardHolders = []
		def roleSets = []
		
		def c = Person.createCriteria()
		def activeAccessPassHolders = c.list{
			passes {
				and {
					le("startDate", beginningOfNextMonth)
					ge("endDate", now)
				}
			}
		}
		
		
		
		// list all assigned access cards
		def assignedAccessCards = []
		
		activeAccessPassHolders.each{ p ->
			
			// setup the cardholder
			def cardHolder = [:]
			
			cardHolder.cardholderID = p.id
			cardHolder.forename = p.firstName ?: ''
			if (p.middleName) {
				cardHolder.middleName = p.middleName[0]
			} else {
				cardHolder.middleName = ''
			}
			cardHolder.surname = p.lastName ?: ''
			cardHolder.email = p.emailAddress ?: ''
			cardHolder.phone = p.phoneNumber ?: ''
			cardHolder.roleSetID = p.id
			cardHolder.pinCommands = false
			cardHolder.confirmingPinExempt = false
			cardHolder.exemptFromPassback = true
			cardHolder.extendedAccess = false
			
			// add the cardholder record
			cardHolders.add(cardHolder)
			
			// setup the "role set"
			def roleSet = [:]
			
			roleSet.roleSetID = p.id
			roleSet.roleID = p.id
			roleSet.scheduleID = '1'
			roleSet.resourceID = '0'
			
			// add the role set
			roleSets.add(roleSet)
			
			def accessExpireDate = now
			
			p.passes.each { pass ->
				if (pass.endDate > accessExpireDate) {
					accessExpireDate = pass.endDate
				}
			}
			
			// def accessEndTime = formatDate(date:accessExpireDate, format:"yyyy-MM-dd'T'HH:mm:ss")
			def accessEndTime = accessExpireDate
			
			// find all the assigned cards
			p.cards.each{ aca ->
				def credential = [:]
				// println "cardHolder.cardholderID: ${cardHolder.cardholderID}"
				credential.cardholderID = cardHolder.cardholderID
				if (aca.accessCard.facilityAssigned) {
					// MNCC assigned cards
					def facilityNumber = aca.accessCard.facilityCode ?: 178
					def cardNumber = aca.accessCard.cardIdentifier ?: 0
					def rawCardNumber = encodeWiegand(Integer.parseInt(facilityNumber), Integer.parseInt(cardNumber))
					
					credential.cardNumber = cardNumber ?: ''
					credential.formatID = 5
					credential.formatName = 'MNCC'
					credential.rawCardNumber = rawCardNumber ?: ''
				} else {
					// Raw format only cards (personal)
					credential.formatID = 0
					credential.rawCardNumber = aca.accessCard.cardIdentifier ?: ''
					credential.cardNumber = aca.accessCard.cardIdentifier ?: ''
				}
				credential.endTime = accessEndTime
				credential.extendedAccess = false
				credential.exemptFromPassback = true
				credential.pinCommands = false
				credential.confirmingPinExempt = false
				credential.isCard = true
				
				assignedAccessCards.add(credential)
			}
		}
		
		assignedAccessCards.each{
			if (debug) {
				def cardHolder = Person.read(it.cardholderID)
				println "TransformerService:generateHidEdgeProData:: (${cardHolder.fullName}) ${it.cardholderID} expires ${it.endTime}"
			}
		}
		
		// get all unassigned cards
		def unassignedAccessCards = []
		
		c = AccessCard.createCriteria()
		def unassignedAccessCardInstanceList = c.list {
			and {
				eq("lost", false)
				assignments {
					isNull("id")
				}
			}
		}
		// add them to the list
		unassignedAccessCardInstanceList.each{ ac ->
				def credential = [:]
				
				credential.cardholderID = ''
				if (ac.facilityAssigned) {
					// MNCC assigned cards
					def facilityNumber = ac.facilityCode ?: 178
					def cardNumber = ac.cardIdentifier ?: 0
					def rawCardNumber = encodeWiegand(Integer.parseInt(facilityNumber), Integer.parseInt(cardNumber))
					
					credential.cardNumber = cardNumber ?: ''
					credential.formatID = 5
					credential.formatName = 'MNCC'
					credential.rawCardNumber = rawCardNumber ?: ''
				} else {
					// Personal cards
					credential.formatID = 0
					credential.rawCardNumber = ac.cardIdentifier
					credential.cardNumber = ac.cardIdentifier ?: ''
					
				}
				credential.extendedAccess = false
				credential.exemptFromPassback = true
				credential.pinCommands = false
				credential.confirmingPinExempt = false
				credential.isCard = true
				
				unassignedAccessCards.add(credential)
		}
		
		def credentials = []
		credentials.addAll(assignedAccessCards)
		credentials.addAll(unassignedAccessCards)
		
		return [ cardHolders: cardHolders
			, credentials: credentials
			, roleSets: roleSets ]
    }
}
