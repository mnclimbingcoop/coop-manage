package coop.mnclimbing

import groovyx.gpars.GParsPool

class MessagingService {

    static transactional = true

	def mailService

	/**
	 * This is used to send out email reminders to people who's access
	 * passes are expiring soon.
	 */
	def sendMonthlyPassReminders(accessInstanceList) {
		def emailFrom = 'passes@mnclimbingcoop.com'
		def now = new Date()

		// We're going to run this in parallel to make the emails go out faster...
		GParsPool.withPool {

			// With each access pass, in parallel
			accessInstanceList.eachParallel{ access ->

				// wrap it in a transaction just to be safe
				Access.withTransaction {
					// let's load the access instance for read/write
					def accessInstance = Access.get(access.id)
					// get the email recipient
					def recipient = accessInstance.person?.emailAddress ?: 'passes@mnclimbingcoop.com'

					if ( ! accessInstance.expirationNotificationSent ) {
						mailService.sendMail {
							to recipient
							from emailFrom
							subject 'Renew Your MNCC Access Pass'
							body( view:"/accessPassExpiring",
								model:[ accessInstance: accessInstance, now: now ] )
						}

						// Mark the notification as sent, and save the record
						accessInstance.expirationNotificationSent = new Date()
						accessInstance.save(flush:true)
					}
				}
			}
		}
	}
}
