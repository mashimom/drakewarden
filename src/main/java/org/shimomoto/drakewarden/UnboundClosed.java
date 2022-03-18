package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

public class UnboundClosed<T extends Comparable<T>> extends BaseRightBoundRange<T> {

	public UnboundClosed(@NotNull T right) {
		super(right);
	}

	@Override
	public boolean isRightClosed() {
		return true;
	}

	@Override
	public boolean contains(T value) {
		return getRight().compareTo(value) >= 0;
	}
}
