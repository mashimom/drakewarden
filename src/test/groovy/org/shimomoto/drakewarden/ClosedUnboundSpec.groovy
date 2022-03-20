//file:noinspection GroovyVariableCanBeFinal
package org.shimomoto.drakewarden

import spock.genesis.Gen
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors

class ClosedUnboundSpec extends Specification {
	@Shared
	def random = new Random()
	@Shared
	def seed = random.nextLong()

	@Shared
	static BigDecimal bd = 3.69871255G


	void setupSpec() {
		println "Seed used: $seed"

		println bd
	}

	def "sanity constructor"() {
		when:
		def result = new ClosedUnbound<Integer>(1i)

		then:
		result.left == 1i
		result.leftClosed
		!result.leftOpen
	}

	def "int range accepts"() {
		when:
		def closedUnbound = new ClosedUnbound(1i)

		then:
		closedUnbound.contains(v)

		where:
		v << Gen.these(1i, Integer.MAX_VALUE)
				.then(Gen.integer(1i, Integer.MAX_VALUE).seed(seed).take(10).realized)
	}

	def "int range rejects"() {
		when:
		def closedUnbound = new ClosedUnbound(1i)

		then:
		!closedUnbound.contains(v)

		where:
		v << Gen.these(null, 0i, Integer.MIN_VALUE)
				.then(Gen.integer(Integer.MIN_VALUE, 0i).seed(seed).take(10).realized)
	}

	def "long range accepts"() {
		when:
		def closedUnbound = new ClosedUnbound(256L)

		then:
		closedUnbound.contains(v)

		where:
		v << Gen.these(256L, Long.MAX_VALUE)
				.then(new Random(seed)
						.longs(256L, Long.MAX_VALUE)
						.limit(10)
						.boxed()
						.collect(Collectors.toList()))
	}

	def "long range rejects"() {
		when:
		def closedUnbound = new ClosedUnbound(256L)

		then:
		!closedUnbound.contains(v)

		where:
		v << Gen.these(null, 255L, Long.MIN_VALUE)
				.then(new Random(seed)
						.longs(Long.MIN_VALUE, 255L)
						.limit(10)
						.boxed()
						.collect(Collectors.toList()))
	}

	def "BigDecimal range accepts"() {
		when:
		def closedUnbound = new ClosedUnbound(bd)

		then:
		closedUnbound.contains(v)

		where:
		v << Gen.these(bd, 480982366.1234G)
				.then(new Random(seed)
						.doubles(bd, Double.MAX_VALUE)
						.limit(10)
						.boxed()
						.map(BigDecimal::valueOf)
						.collect(Collectors.toList()))
	}

	def "BigDecimal range rejects"() {
		when:
		def closedUnbound = new ClosedUnbound(bd)

		then:
		!closedUnbound.contains(v)

		where:
		v << Gen.these(null, bd-0.000005G, 2.32165498G)
				.then(new Random(seed)
						.doubles(Double.MIN_NORMAL, bd-0.000005G)
						.limit(10)
						.boxed()
						.map(BigDecimal::valueOf)
						.collect(Collectors.toList()))
	}
}
