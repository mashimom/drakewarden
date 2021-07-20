package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

final class ClosedClosed<T extends Comparable<T>> extends BaseRange<T> implements Range<T> {
	public ClosedClosed(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean test(T value) {
		return left.compareTo(value) <= 0
						&& right.compareTo(value) >= 0;
	}
}
