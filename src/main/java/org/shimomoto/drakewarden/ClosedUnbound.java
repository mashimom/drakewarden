package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

public class ClosedUnbound<T extends Comparable<T>> extends BaseLeftBoundRange<T> {
	public ClosedUnbound(@NotNull T left) {
		super(left);
	}

	@Override
	public boolean isLeftClosed() {
		return true;
	}

	@Override
	public boolean contains(T value) {
		return getLeft().compareTo(value) <= 0;
	}
}
