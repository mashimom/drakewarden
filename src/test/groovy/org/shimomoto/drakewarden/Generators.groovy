package org.shimomoto.drakewarden

import com.codepoetics.protonpack.StreamUtils

import java.util.stream.Collectors
import java.util.stream.Stream

class Generators {
	final long seed

	Generators() {
		this(new Random().nextLong())
	}

	Generators(final long seed) {
		println "Seed being used: $seed"
		this.seed = seed
	}

	Stream<Integer> ints(final Long seed = this.seed, final int start, final int end) {
		new Random(seed).ints(start, end).boxed()
	}

	Stream<Long> longs(final Long seed = this.seed, final long start, final long end) {
		new Random(seed).longs(start, end).boxed()
	}

	Stream<BigInteger> bigIntegers(final Long seed = this.seed, final BigInteger start, final BigInteger end) {
		new Random(seed).longs(start.longValue(), end.longValue()).mapToObj(BigInteger::valueOf)
	}

	Stream<BigDecimal> bigDecimals(final Long seed = this.seed, final BigDecimal start, final BigDecimal end) {
		StreamUtils.zip(
				new Random(seed).longs(start.unscaledValue().toLong(), end.unscaledValue().toLong()).boxed(),
				new Random(seed).ints(start.scale(), end.scale()).boxed(),
				BigDecimal::valueOf)
	}
}
