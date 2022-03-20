//file:noinspection GroovyVariableCanBeFinal
//file:noinspection GroovyAssignabilityCheck
package org.shimomoto.drakewarden.api

import org.shimomoto.drakewarden.Ranges
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.IntStream
import java.util.stream.Stream

@Subject(Range)
class RangeSpec extends Specification {
	def r0_10 = Ranges.closedOpen(0i, 10i)
	def r20_30 = Ranges.closedOpen(20i, 30i)
	def r5_11 = Ranges.closedOpen(5i, 11i)

	def "sanity constructor"() {
		def range = Ranges.closedOpen(1L, 1000L)

		expect:
		range.left == 1L
		range.leftClosed
		!range.leftOpen
		range.right == 1000L
		!range.rightClosed
		range.rightOpen

	}

	def "complement still considers null out of range"() {
		when:
		def r0_10_line = r0_10.complement()

		then:
		!r0_10_line.contains(null)
	}

	def "complement accepts"() {
		when:
		def r0_10_line = r0_10.complement()

		then:
		r0_10_line.contains(v)

		where:
		v << (-5i..<0i).toList() + (10i..<15i).toList()
	}

	def "complement rejects"() {
		when:
		def r0_10_line = r0_10.complement()

		then:
		!r0_10_line.contains(v)

		where:
		v << (0i..<10i).toList()
	}

	def "union still considers null out of range"() {
		when:
		def r0_10_r20_30 = r0_10.union(r20_30)

		then:
		!r0_10_r20_30.contains(null)
	}

	def "union accepts"() {
		when:
		def r0_10r20_30 = r0_10.union(r20_30)

		then:
		r0_10r20_30.contains(v)

		where:
		v << IntStream.concat(
				IntStream.range(0i, 9i),
				IntStream.range(20i, 29i)
		)
	}

	def "union rejects"() {
		when:
		def r0_10r20_30 = r0_10.union(r20_30)

		then:
		!r0_10r20_30.contains(v)

		where:
		v << Stream.of(
				IntStream.range(-5i, 0i),
				IntStream.range(10i, 20i),
				IntStream.range(30i, 35i))
				.flatMapToInt(i -> i)
	}


	def "intersection still considers null out of range"() {
		when:
		def r0_10_r20_30 = r0_10.intersection(r20_30)

		then:
		!r0_10_r20_30.contains(null)
	}

	def "intersection accepts"() {
		when:
		def r0_10r5_11 = r0_10.intersection(r5_11)

		then:
		r0_10r5_11.contains(v)

		where:
		v << IntStream.range(5i, 10i)
	}

	def "intersection rejects"() {
		when:
		def r0_10r5_11 = r0_10.intersection(r5_11)

		then:
		!r0_10r5_11.contains(v)

		where:
		v << IntStream.concat(
				IntStream.range(-5i, 5i),
				IntStream.range(10i, 13i)
		)
	}


	def "difference still considers null out of range"() {
		when:
		def r0_10_r20_30 = r0_10.difference(r20_30)

		then:
		!r0_10_r20_30.contains(null)
	}

	def "difference accepts"() {
		when:
		def r0_10r5_11 = r0_10.difference(r5_11)

		then:
		r0_10r5_11.contains(v)

		where:
		v << IntStream.range(0i, 5i)
	}

	def "difference rejects"() {
		when:
		def r0_10r5_11 = r0_10.difference(r5_11)

		then:
		!r0_10r5_11.contains(v)

		where:
		v << IntStream.concat(
				IntStream.range(-5i, 0i),
				IntStream.range(5i, 15i)
		)
	}


	def "symmetricDifference still considers null out of range"() {
		when:
		def r0_10_r20_30 = r0_10.symmetricDifference(r20_30)

		then:
		!r0_10_r20_30.contains(null)
	}

	def "symmetricDifference accepts"() {
		when:
		def r0_10r5_11 = r0_10.symmetricDifference(r5_11)

		then:
		r0_10r5_11.contains(v)

		where:
		v << (0i..<5i)
	}

	def "symmetricDifference rejects"() {
		when:
		def r0_10r5_11 = r0_10.symmetricDifference(r5_11)

		then:
		!r0_10r5_11.contains(v)

		where:
		v << Stream.of(
				IntStream.range(-5i, 0i),
				IntStream.range(5i, 9i),
				IntStream.range(11i, 15i)
		).flatMapToInt(i -> i)
				.toArray()
	}
}
