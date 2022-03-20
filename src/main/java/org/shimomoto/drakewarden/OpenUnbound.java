package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
final class OpenUnbound<T extends Comparable<T>> extends BaseLeftBoundRange<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.OPEN,
					RangeEnd.RIGHT, EndClassifier.UNBOUND);

	public OpenUnbound(@NotNull T left) {
		super(left);
	}

	@Override
	public boolean isLeftClosed() {
		return false;
	}

	@Override
	public boolean contains(T value) {
		if(value==null) {
			return false;
		}
		return getLeft().compareTo(value) < 0;
	}
}
