//file:noinspection GroovyVariableCanBeFinal
package org.shimomoto.drakewarden

import spock.lang.Specification

class UnboundedRangeSpec extends Specification {

	def "simple test"() {
		def r1 = new UnboundedRange()
		def r2 = new UnboundedRange()

		expect:
		r1 == r2
		and:
		r1.contains(1i)
		r1.contains(2309274981)
		!r1.contains(null)

	}
}
