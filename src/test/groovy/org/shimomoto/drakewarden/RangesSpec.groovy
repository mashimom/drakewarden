//file:noinspection GroovyVariableCanBeFinal
//file:noinspection GroovyAssignabilityCheck
package org.shimomoto.drakewarden

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

class RangesSpec extends Specification {

	def "check range factories"() {
		when: 'All 9 factory methods are invoked'
		def co = Ranges.closedOpen(l, r)
		def cc = Ranges.closedClosed(l, r)
		def oo = Ranges.openOpen(l, r)
		def oc = Ranges.openClosed(l, r)
		def cu = Ranges.closedUnbound(l)
		def ou = Ranges.openUnbound(l)
		def uo = Ranges.unboundOpen(r)
		def uc = Ranges.unboundClosed(r)
		def uu = Ranges.unbound()

		then: 'closed-open is assembled correctly'
		co.left == l
		co.right == r
		and: 'closed-closed is assembled correctly'
		cc.left == l
		cc.right == r
		and: 'open-open is assembled correctly'
		oo.left == l
		oo.right == r
		and: 'open-closed is assembled correctly'
		oc.left == l
		oc.right == r
		and: 'closed-unbound is assembled correctly'
		cu.left == l
		and: 'open-unbound is assembled correctly'
		ou.left == l
		and: 'unbound-open is assembled correctly'
		uo.right == r
		and: 'unbound-closed is assembled correctly'
		uc.right == r
		and: 'unbound-unbound is assembled correctly'
		uu != null
		uu === Ranges.unbound()
		and: 'quick hash check'
		[co, cc, oo, oc, cu, ou, uo, uc, uu].toSet().size() == 9

		where: 'different range boundaries types'
		_ | l                          | r
		0 | 10i                        | 1000i
		1 | 123.2D                     | 987.0D
		2 | LocalDate.of(2020, 02, 01) | LocalDate.of(2022, 02, 01)
		3 | LocalTime.of(14, 12, 15)   | LocalTime.of(18, 35)
	}
}
