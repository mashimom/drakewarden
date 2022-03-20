package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
final class ClosedUnbound<T extends Comparable<T>> extends BaseLeftBoundRange<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.CLOSED,
					RangeEnd.RIGHT, EndClassifier.UNBOUND);

	public ClosedUnbound(@NotNull T left) {
		super(left);
	}

	@Override
	public boolean isLeftClosed() {
		return true;
	}

	@Override
	public boolean contains(T value) {
		if(value==null) {
			return false;
		}
		return getLeft().compareTo(value) <= 0;
	}
}
