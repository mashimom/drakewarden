package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.ProperBoundRange;

final class OpenOpen<T extends Comparable<T>> extends BaseProperBoundRange<T> implements ProperBoundRange<T> {
	public OpenOpen(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean contains(T value) {
		return left.compareTo(value) < 0
						&& right.compareTo(value) > 0;
	}

	@Override
	public boolean isLeftClosed() {
		return false;
	}

	@Override
	public boolean isRightClosed() {
		return false;
	}
}
