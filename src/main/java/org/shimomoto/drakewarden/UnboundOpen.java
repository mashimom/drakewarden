package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
final class UnboundOpen<T extends Comparable<T>> extends BaseRightBoundRange<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.UNBOUND,
					RangeEnd.RIGHT, EndClassifier.OPEN);

	public UnboundOpen(@NotNull T right) {
		super(right);
	}

	@Override
	public boolean isRightClosed() {
		return false;
	}

	@Override
	public boolean contains(T value) {
		if(value==null) {
			return false;
		}
		return getRight().compareTo(value) > 0;
	}
}
