package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
final class UnboundClosed<T extends Comparable<T>> extends BaseRightBoundRange<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.UNBOUND,
					RangeEnd.RIGHT, EndClassifier.CLOSED);

	public UnboundClosed(@NotNull T right) {
		super(right);
	}

	@Override
	public boolean isRightClosed() {
		return true;
	}

	@Override
	public boolean contains(T value) {
		if(value==null) {
			return false;
		}
		return getRight().compareTo(value) >= 0;
	}
}
