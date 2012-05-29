package coop.mnclimbing

import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin

class AppTuningService {

    static transactional = true

	def grailsApplication
	def propertyInstanceMap = DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

	/** Flushes cache from GORM session.
	 * See: 
	 * <a href="http://naleid.com/blog/2009/10/01/batch-import-performance-with-grails-and-mysql/">This blog post</a>.
	 */
	def cleanUpGorm() {
		def session = grailsApplication.mainContext.sessionFactory.currentSession
		session.flush()
		session.clear()
		propertyInstanceMap.get().clear()
	}
}
