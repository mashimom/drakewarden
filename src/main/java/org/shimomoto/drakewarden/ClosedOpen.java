package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

final class ClosedOpen<T extends Comparable<T>> extends BaseRange<T> implements Range<T> {
	public ClosedOpen(@NotNull T left, @NotNull T right) {
		super(left, right);
	}

	@Override
	public boolean contains(T value) {
		return left.compareTo(value) <= 0
						&& right.compareTo(value) > 0;
	}
}
