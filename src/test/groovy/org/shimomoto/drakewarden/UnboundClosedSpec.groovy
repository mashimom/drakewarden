package org.shimomoto.drakewarden

import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

class UnboundClosedSpec extends Specification {
	@Shared
	def random = new Random()
	@Shared
	def seed = random.nextLong()

	void setupSpec() {
		println "Seed used: $seed"
	}

	def "constructor sanity"() {
		when:
		def result = new UnboundClosed(1i)

		then:
		result.right == 1i
		!result.rightOpen
		result.rightClosed
	}

	def "simple accepts"() {
		when:
		def range = new UnboundClosed(1i)

		then:
		range.contains(v)

		where:
		v << Stream.concat(
				Stream.of(1i, Integer.MIN_VALUE),
				random.ints(10, Integer.MIN_VALUE, 0i).boxed()
		).collect(Collectors.toList())
	}

	def "simple rejects"() {
		when:
		def range = new UnboundClosed(1i)

		then:
		!range.contains(v)

		where:
		v << Stream.concat(
				Stream.of(2i, Integer.MAX_VALUE),
				random.ints(10,2i, Integer.MAX_VALUE).boxed()
		).collect(Collectors.toList())
	}
}
