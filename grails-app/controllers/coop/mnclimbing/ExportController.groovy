package coop.mnclimbing

import java.util.Date;

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD','ROLE_EXPORT'])
class ExportController {

    def index = {
		redirect "/"
	}

	def contacts = {
		def personInstanceList = Person.list()
		def now = new Date()
		def datestamp = g.formatDate(date:now, format:"yyyy-MM-dd")
		def fileName = "contact-list-${datestamp}.csv"


		response.setHeader("Content-disposition", "attachment; filename=${fileName}")
		response.contentType = "application/vnd.ms-excel"

		def out = response.outputStream

		out << '"id","title","first name","middle name","last name","suffix",'
		out << '"birth date","phone number","email",'
		out << '"address 1","address 2","city", "state","zip code","zip4",'
		out << '"username","membership start date","access pass start date",'
		out << '"access pass end date"'
		out << "\n"




		personInstanceList.each{ p ->

			def vals = []
			vals << p.title
			vals << p.firstName
			vals << p.middleName
			vals << p.lastName
			vals << p.suffix
			vals << g.formatDate(date:p.birthDate, format:"yyyy-MM-dd")
			vals << p.phoneNumber
			vals << p.emailAddress
			vals << p.address?.address1
			vals << p.address?.address2
			vals << p.address?.city
			vals << p.address?.state
			vals << p.address?.zipcode
			vals << p.address?.zip4
			vals << p.user?.username

			out << p.id
			vals.each{ v ->
				out << ","
				if (v) {
					out << '"' + v + '"'
				}
			}

			// membership start date
			def membershipFrom = ""
			p.memberships.each { m ->
				membershipFrom = m.membershipFrom
			}
			if (membershipFrom) {
				out << ',"' + membershipFrom + '"'
			} else {
				out << ',""'
			}

			// access pass
			p.passes.each{ a ->
				out << ',"' + a.startDate + '"'
				out << ',"' + a.endDate + '"'
			}


			out << "\n"
		}
		out.flush()
		out.close()

	}


	def payments = {

		def paymentInstanceList = Payment.list()
		def dayPassInstanceList = DayPass.list()
		def now = new Date()
		def datestamp = g.formatDate(date:now, format:"yyyy-MM-dd")
		def fileName = "payment-list-${datestamp}.csv"


		response.setHeader("Content-disposition", "attachment; filename=${fileName}")
		response.contentType = "application/vnd.ms-excel"

		def out = response.outputStream

		out << '"id","amount","processing fee"'
		out << ',"tax fee","what we get"'
		out << ',"payment method","payment date"'
		out << ',"transaction id","payee id","payee"'
		out << ',"payment type"' + "\n"

		paymentInstanceList.each{ p ->
			out << p.id + ','
			out << p.amount + ','
			out << p.processingFee + ','
			out << p.taxFee + ','
			out << p.amountAfterFees + ','
			out << '"' + p.paymentType.name + '",'
			out << '"' + g.formatDate(date:p.paymentDate, format:"yyyy-MM-dd") + '",'
			if (p.transactionId) {
				out << '"' + p.transactionId + '",'
			} else {
				out << ","
			}
			out << '"' + p.person.id + '",'
			out << '"' + p.person.fullName + '",'
			out << '"' + p.class.toString().replaceAll("class coop.mnclimbing.", "") + '"'

			out << "\n"
		}
		// Append Day Passes
		dayPassInstanceList.each{ dp ->
			out << dp.id + ','
			out << dp.amount + ','
			out << dp.processingFee + ','
			out << dp.taxFee + ','
			out << dp.amountAfterFees + ','
			out << '"' + dp.paymentType.name + '",'
			out << '"' + g.formatDate(date:dp.paymentDate, format:"yyyy-MM-dd") + '",'
			if (dp.transactionId) {
				out << '"' + dp.transactionId + '",'
			} else {
				out << ","
			}
			out << '"' + dp.sponsor?.id + '",'
			out << '"' + dp.name + '",'
			out << '"' + dp.class.toString().replaceAll("class coop.mnclimbing.", "") + '"'

			out << "\n"
		}
		out.flush()
		out.close()

	}
	
	def purchases = {
		
		def purchaseInstanceList = Purchase.list()
		def now = new Date()
		def datestamp = g.formatDate(date:now, format:"yyyy-MM-dd")
		def fileName = "purchase-list-${datestamp}.csv"

		response.setHeader("Content-disposition", "attachment; filename=${fileName}")
		response.contentType = "application/vnd.ms-excel"

		def out = response.outputStream

		out << '"id","purchase date","merchant"'
		out << ',"purchaser contact id","purchaser name"'
		out << ',"sub total","tax"'
		out << ',"shipping","total","pay date"'
		out << ',"comments"' + "\n"

		
		
		purchaseInstanceList.each{ p ->
			out << p.id + ','
			out << '"' + g.formatDate(date:p.purchaseDate, format:"yyyy-MM-dd") + '",'
			out << '"' + p.merchant + '",'
			if (p.person) {
				out << p.person.id + ','
				out << '"' + p.person.fullName + '",'
			} else {
				out << ','
				out << '"' + p.purchaser + '",'
			}
			out << p.subTotal + ','
			out << p.tax + ','
			out << p.shipping + ','
			out << p.total + ','
			out << '"' + g.formatDate(date:p.paid, format:"yyyy-MM-dd") + '",'
			out << '"' + p.comments + '"'
			out << "\n"
		}
		out.flush()
		out.close()
	}

	def signIn = {
		
		def signInList = SignIn.list()
		def now = new Date()
		def datestamp = g.formatDate(date:now, format:"yyyy-MM-dd")
		def fileName = "purchase-list-${datestamp}.csv"

		response.setHeader("Content-disposition", "attachment; filename=${fileName}")
		response.contentType = "application/vnd.ms-excel"

		def out = response.outputStream

		out << '"sign-in record","member id","name"'
		out << ',"type","time"' + "\n"

		
		
		signInList.each{ p ->
			out << p.id + ','
			out << p.person?.id + ','
			out << p.fullName + ','
			out << p.memberVisitorGuest + ','
			out << p.signInDate
			out << "\n"
		}
		out.flush()
		out.close()
	}
}