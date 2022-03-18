package org.shimomoto.drakewarden


import spock.genesis.Gen
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

class OpenUnboundSpec extends Specification {
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
		def result = new OpenUnbound<Integer>(1i)

		then:
		result.left == 1i
		!result.leftClosed
		result.leftOpen
	}

	def "int range accepts"() {
		when:
		def openUnbound = new OpenUnbound(1i)

		then:
		openUnbound.contains(v)

		where:
		v << Gen.these(1i + 1, Integer.MAX_VALUE)
				.then(Gen.integer(1i + 1, Integer.MAX_VALUE).seed(seed).take(10).realized)
	}

	def "int range rejects"() {
		when:
		def openUnbound = new OpenUnbound(1i)

		then:
		!openUnbound.contains(v)

		where:
		v << Gen.these(1i - 1, 1i, Integer.MIN_VALUE)
				.then(Gen.integer(Integer.MIN_VALUE, 1i - 1).seed(seed).take(10).realized)
	}

	def "long range accepts"() {
		when:
		def openUnbound = new OpenUnbound(256L)

		then:
		openUnbound.contains(v)

		where:
		v << Gen.these(256L + 1, Long.MAX_VALUE)
				.then(new Random(seed)
						.longs(256L + 1, Long.MAX_VALUE)
						.limit(10)
						.boxed()
						.collect(Collectors.toList()))
	}

	def "long range rejects"() {
		when:
		def openUnbound = new OpenUnbound(256L)

		then:
		!openUnbound.contains(v)

		where:
		v << Stream.concat(
				Stream.of(256L - 1, 256L, Long.MIN_VALUE),
				new Random(seed)
						.longs(Long.MIN_VALUE, 256L - 1)
						.limit(10)
						.boxed())
				.collect(Collectors.toList())
	}

	def "BigDecimal range accepts"() {
		when:
		def openUnbound = new OpenUnbound(bd)

		then:
		openUnbound.contains(v)

		where:
		v << Gen.these(bd + 0.000005G, 480982366.1234G)
				.then(new Random(seed)
						.doubles(bd + 0.000005G, Double.MAX_VALUE)
						.limit(10)
						.boxed()
						.map(BigDecimal::valueOf)
						.collect(Collectors.toList()))
	}

	def "BigDecimal range rejects"() {
		when:
		def openUnbound = new OpenUnbound(bd)

		then:
		!openUnbound.contains(v)

		where:
		v << Gen.these(bd, 2.32165498G)
				.then(new Random(seed)
						.doubles(Double.MIN_NORMAL, bd)
						.limit(10)
						.boxed()
						.map(BigDecimal::valueOf)
						.collect(Collectors.toList()))
	}
}
