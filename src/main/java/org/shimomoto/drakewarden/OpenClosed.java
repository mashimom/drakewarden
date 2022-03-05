package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.Range;

final class OpenClosed<T extends Comparable<T>> extends BaseRange<T> implements Range<T> {
	public OpenClosed(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean test(T value) {
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
