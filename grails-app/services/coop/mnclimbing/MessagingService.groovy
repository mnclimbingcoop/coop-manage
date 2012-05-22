package coop.mnclimbing

class MessagingService {

    static transactional = true

	def mailService

	def sendMonthlyPassReminders(accessInstanceList) {
		def recipients = ['help@ncs.umn.edu']
		def now = new Date()

		mailService.sendMail {
			to recipients.toArray()
			from "info@mnclimbingcoop.com"
			subject "Renew Your MNCC Access Pass"
			body( view:"/accessPassExpiring",
			model:[ errorMessage: errorMessage, now: now, 
			remoteAddr: remoteAddr, username: username])
		}
	}
}
