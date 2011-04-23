import coop.mnclimbing.AccessType
import coop.mnclimbing.AccessCardAssignment
import coop.mnclimbing.MembershipTerminationReason
import coop.mnclimbing.PaymentType
import coop.mnclimbing.StockClass
import coop.mnclimbing.MembershipType
import coop.mnclimbing.Instrument

import coop.mnclimbing.Address
import coop.mnclimbing.Person
import coop.mnclimbing.Membership
import coop.mnclimbing.AccessCard

import coop.mnclimbing.Role
import coop.mnclimbing.User
import coop.mnclimbing.UserRole

class BootStrap {

	def springSecurityService

    def init = { servletContext ->

		// Gym Access Types
		def accessDaily = AccessType.findByDuration(1)
		if ( ! accessDaily ) {
			accessDaily = new AccessType(name:"daily", duration:0).save()
		}
		def accessMonthly = AccessType.findByDuration(1)
		if ( ! accessMonthly ) {
			accessMonthly = new AccessType(name:"monthly", duration:1).save()
		}
		def accessQuarterly = AccessType.findByDuration(3)
		if ( ! accessQuarterly ) {
			accessQuarterly = new AccessType(name:"quarterly", duration:3).save()
		}
		def accessYearly = AccessType.findByDuration(12)
		if ( ! accessYearly ) {
			accessYearly = new AccessType(name:"annual", duration:12).save()
		}
		def accessStudent = AccessType.findByDuration(9)
		if ( ! accessStudent ) {
			accessStudent = new AccessType(name:"student", duration:9).save()
		}

		// Membership Termination Reasons
		def membershipTimeout = MembershipTerminationReason.findByName("timed out")
		if ( ! membershipTimeout ) {
			membershipTimeout = new MembershipTerminationReason(name:"timed out", allowReactivation:true).save()
		}
		def membershipRevoked = MembershipTerminationReason.findByName("revoked")
		if ( ! membershipRevoked ) {
			membershipRevoked = new MembershipTerminationReason(name:"revoked", allowReactivation:false).save()
		}

		// Payment Types
		def cashPayment = PaymentType.findByName("cash")
		if ( ! cashPayment ) {
			cashPayment = new PaymentType(name:"cash").save()
		}
		def checkPayment = PaymentType.findByName("check")
		if ( ! checkPayment ) {
			checkPayment = new PaymentType(name:"check").save()
		}
		def payPalPayment = PaymentType.findByName("PayPal Credit")
		if ( ! payPalPayment ) {
			payPalPayment = new PaymentType(name:"PayPal Credit").save()
		}
		def payPalCheck = PaymentType.findByName("PayPal Check")
		if ( ! payPalCheck ) {
			payPalCheck = new PaymentType(name:"PayPal Check").save()
		}
		def creditPayment = PaymentType.findByName("credit card")
		if ( ! creditPayment ) {
			creditPayment = new PaymentType(name:"credit card").save()
		}

		// Stock Classes
		def classCStock = StockClass.findByAbbreviation("C")
		if ( ! classCStock ) {
			classCStock = new StockClass(name:"Class C, Series A", abbreviation:"C").save()
		}

		def lifeTimeMember = MembershipType.findByName("lifetime")
		if ( ! lifeTimeMember ) {
			lifeTimeMember = new MembershipType(name:"lifetime").save()
		}


		def membershipAgreement = Instrument.findByAbbreviation("membagree")
		if ( ! membershipAgreement ) {
			membershipAgreement = new Instrument(abbreviation:"membagree", name:"Membership Agreement").save()
		}
		def gymWaiver = Instrument.findByAbbreviation("waiver")
		if ( ! gymWaiver ) {
			gymWaiver = new Instrument(abbreviation:"waiver", name:"Gym Waiver").save()
		}
		def accessPassForm = Instrument.findByAbbreviation("accpass")
		if ( ! accessPassForm ) {
			accessPassForm = new Instrument(abbreviation:"accpass", name:"Access Pass Form").save()
		}
		def dryToolForm = Instrument.findByAbbreviation("drytool")
		if ( ! dryToolForm ) {
			dryToolForm = new Instrument(abbreviation:"drytool", name:"Dry Tool Form").save()
		}
		def routeSettingForm = Instrument.findByAbbreviation("routeset")
		if ( ! routeSettingForm ) {
			routeSettingForm = new Instrument(abbreviation:"routeset", name:"Route Setter Form").save()
		}
		def stockCertificateCA = Instrument.findByAbbreviation("stockccsa")
		if ( ! stockCertificateCA ) {
			stockCertificateCA = new Instrument(abbreviation:"stockccsa", name:"Class C Series A Stock Certificate").save()
		}

		// Setup Roles

		// Board Member
		def roleBoard = Role.findByAuthority("ROLE_BOARD")
		if ( ! roleBoard ) {
			roleBoard = new Role(authority:"ROLE_BOARD").save()
		}

		// Financial Access
		def roleFinancial = Role.findByAuthority("ROLE_FINANCIAL")
		if ( ! roleFinancial ) {
			roleFinancial = new Role(authority:"ROLE_FINANCIAL").save()
		}

		// Data Export Access
		def roleExport = Role.findByAuthority("ROLE_EXPORT")
		if ( ! roleExport ) {
			roleExport = new Role(authority:"ROLE_EXPORT").save()
		}

		// Staff Member
		def roleStaff = Role.findByAuthority("ROLE_STAFF")
		if ( ! roleStaff ) {
			roleStaff = new Role(authority:"ROLE_STAFF").save()
		}
		
		def buchananAddress = Address.findByAddress1("3323 Buchanan St NE")
		if ( ! buchananAddress ) {
			// load test cases
			buchananAddress = new Address(address1:"3323 Buchanan St NE",
				city:"Minneapolis", state:"MN", zipcode:"55418").save()
		}


		def ajzUcard = AccessCard.findByCardIdentifier('00D50086C4')
		if ( ! ajzUcard ) {
			new AccessCard(facilityCode:'',
				cardIdentifier:"00D50086C4" ,
				label:"00D50086C4",
				facilityAssigned: false).save()
		}

		def aaronZirbes = Person.findByEmailAddress("aaron.zirbes@gmail.com")
		if ( ! aaronZirbes ) {
			aaronZirbes = new Person(title:"Mr", firstName:"Aaron",
				middleName:"J", lastName:"Zirbes", address:buchananAddress,
				emailAddress:"aaron.zirbes@gmail.com",
				phoneNumber:"612-296-3127").save()
				
			def aaronZirbesUcard = new AccessCardAssignment(accessCard:ajzUcard
				, person: aaronZirbes).save()
			
			aaronZirbes.addToCards(aaronZirbesUcard)
			aaronZirbes.save(flush:true)
			
			ajzUcard.addToAssignments(aaronZirbesUcard)
			ajzUcard.save(flush:true)
			
		}

		def ajz = User.findByUsername("ajz")
		if ( ! ajz ) {
				// Load testing user accounts

				String password = springSecurityService.encodePassword('tempP@ssw0rd')
				ajz = new User(person:aaronZirbes, username: 'ajz', enabled: true, password: password)
				ajz.save(flush: true)
				aaronZirbes.user = ajz
				aaronZirbes.addToCards(accessCard: ajzUcard)
				
				aaronZirbes.save(flush:true)

				UserRole.create ajz, roleBoard, true
		}

		environments {
			development {
				
				def fourthAddress = new Address(address1:"1131 4th St NE",
					city:"Minneapolis", state:"MN", zipcode:"55413").save()

				def adamsAddress = new Address(address1:"1519 Adams St NE Unit 3",
					city:"Minneapolis", state:"MN", zipcode:"55413").save()

				def lizHorn = new Person(title:"Ms", firstName:"Elizabeth",
					middleName:"", lastName:"Horn", address:fourthAddress,
					emailAddress:"liz.mpls@gmail.com",
					phoneNumber:"612-987-4863").save()

				def jacobGerber = new Person(title:"Mr", firstName:"Jacob",
					middleName:"A", lastName:"Gerber", address:adamsAddress,
					emailAddress:"jacob@jacobagerber.com",
					phoneNumber:"612-518-3956").save()
			
				
			}
		}

    }
    def destroy = {
    }
}
