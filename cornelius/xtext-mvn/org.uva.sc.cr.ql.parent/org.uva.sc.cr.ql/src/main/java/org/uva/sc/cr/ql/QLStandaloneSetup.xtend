/*
 * generated by Xtext 2.14.0-SNAPSHOT
 */
package org.uva.sc.cr.ql


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class QLStandaloneSetup extends QLStandaloneSetupGenerated {

	def static void doSetup() {
		new QLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}