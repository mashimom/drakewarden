//file:noinspection GroovyVariableCanBeFinal
//file:noinspection GroovyAssignabilityCheck
package org.shimomoto.drakewarden

import org.shimomoto.drakewarden.api.ProperBoundRange

import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

import spock.lang.Specification

class ClosedClosedSpec extends Specification {

	def "sanity constructor, hash and equals"() {
		when:
		def r1 = Ranges.closedClosed(0i, 5i)
		def r2 = Ranges.closedClosed(0i, 5i)
		def other = Ranges.closedClosed(0i, 1i)
		def another = Ranges.openOpen(0i, 5i)

		then:
		r1.right == r2.right
		r1.left == r2.left
		and:
		r1 == r2
		r1 != other
		r2 != other
		r1 != another
		and:
		r1.hashCode() == r2.hashCode()
		r1.hashCode() != other.hashCode()
		r2.hashCode() != other.hashCode()
		r1.hashCode() != another.hashCode()
	}

	def "left checks"() {
		given: 'range [0,1]'
		final ProperBoundRange<Integer> r = Ranges.closedClosed(0i, 1i)

		expect:
		r.left == 0i
		r.leftClosed
		!r.leftOpen
	}

	def "right checks"() {
		given: 'range [0,1]'
		final ProperBoundRange<Integer> r = Ranges.closedClosed(0i, 1i)

		expect:
		r.right == 1i
		r.rightClosed
		!r.rightOpen
	}

	def "Simple range test"() {
		given: 'range [0,1]'
		final ProperBoundRange<Integer> r = Ranges.closedClosed(0i, 1i)

		expect: 'contains 0'
		r.contains(0i)
		and: 'contains 1'
		r.contains(1i)
		and: 'does not contain any other integer'
		!r.contains(Integer.MIN_VALUE)
		!r.contains(-1i)
		!r.contains(10i)
		!r.contains(Integer.MAX_VALUE)
		and: 'also does not contain null'
		!r.contains(null)
	}

	def "LocalDate range test"() {
		given: 'range [2021-06-01,2021-06-23)'
		final LocalDate left = LocalDate.of(2021, 6, 1)
		final LocalDate right = LocalDate.of(2021, 6, 23)
		final ProperBoundRange<ChronoLocalDate> r = Ranges.closedClosed(left, right)

		expect: 'contains '
		r.contains(left)
		r.contains(left + 1)
		r.contains(left + 12)
		r.contains(left + 22)
		and: 'does not contain any other integer'
		!r.contains(left - 1)
		!r.contains(left + 23)
		!r.contains(LocalDate.of(2020, 1, 1))
		!r.contains(LocalDate.of(2022, 1, 1))
		and: 'also does not contain null'
		!r.contains(null)
	}

	def "isDegenerate accepts"() {
		given:
		final def rg = Ranges.closedClosed(l, l)

		expect:
		rg.isDegenerate()

		where:
		_ | l
		0 | 0i
		1 | LocalDate.of(2021, 6, 1)
		2 | 123456L
		3 | 9.9 //BigDecimal
	}

	def "isDegenerate refuses"() {
		given:
		final def rg = Ranges.closedClosed(l, r)

		expect:
		!rg.isDegenerate()

		where:
		_ | l                        | r
		0 | 0i                       | 1i
		1 | LocalDate.of(2021, 6, 1) | LocalDate.of(2021, 6, 23)
		2 | 123456L                  | 654321L
		3 | 9.9                      | 10.1 //BigDecimal
	}
}
