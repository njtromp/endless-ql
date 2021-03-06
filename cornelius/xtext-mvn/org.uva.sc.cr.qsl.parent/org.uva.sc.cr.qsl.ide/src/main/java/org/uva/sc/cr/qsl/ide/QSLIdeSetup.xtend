/*
 * generated by Xtext 2.14.0-SNAPSHOT
 */
package org.uva.sc.cr.qsl.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.uva.sc.cr.qsl.QSLRuntimeModule
import org.uva.sc.cr.qsl.QSLStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class QSLIdeSetup extends QSLStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new QSLRuntimeModule, new QSLIdeModule))
	}
	
}
