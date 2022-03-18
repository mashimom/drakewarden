package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.ProperBoundRange;

final class ClosedClosed<T extends Comparable<T>> extends BaseProperBoundRange<T> implements ProperBoundRange<T> {
	public ClosedClosed(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean contains(T value) {
		return left.compareTo(value) <= 0
						&& right.compareTo(value) >= 0;
	}

	@Override
	public boolean isLeftClosed() {
		return true;
	}

	@Override
	public boolean isRightClosed() {
		return true;
	}
}
