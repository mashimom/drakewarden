package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.shimomoto.drakewarden.api.Range;

import java.util.Map;
import java.util.Objects;

/**
 * This Range is from ♾ to ♾, the only value not in the range is null.
 *
 * @param <T> type for the Range
 */
@NoArgsConstructor
@EqualsAndHashCode
public final class UnboundedRange<T extends Comparable<T>> implements Range<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.UNBOUND,
					RangeEnd.RIGHT, EndClassifier.UNBOUND);
	@Override
	public boolean contains(T value) {
		return Objects.nonNull(value);
	}
}
