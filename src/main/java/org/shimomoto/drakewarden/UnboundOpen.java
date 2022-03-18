package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

public class UnboundOpen<T extends Comparable<T>> extends BaseRightBoundRange<T> {

	public UnboundOpen(@NotNull T right) {
		super(right);
	}

	@Override
	public boolean isRightClosed() {
		return false;
	}

	@Override
	public boolean contains(T value) {
		return getRight().compareTo(value) > 0;
	}
}
