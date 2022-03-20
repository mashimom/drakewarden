package org.shimomoto.drakewarden;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.ProperBoundRange;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
final class OpenClosed<T extends Comparable<T>> extends BaseProperBoundRange<T> implements ProperBoundRange<T> {
	private final Map<RangeEnd, EndClassifier> classifiedEnds =
			Map.of(RangeEnd.LEFT, EndClassifier.OPEN,
					RangeEnd.RIGHT, EndClassifier.CLOSED);

	public OpenClosed(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean contains(T value) {
		if(value==null) {
			return false;
		}
		return left.compareTo(value) < 0
						&& right.compareTo(value) >= 0;
	}

	@Override
	public boolean isLeftClosed() {
		return false;
	}

	@Override
	public boolean isRightClosed() {
		return true;
	}
}
