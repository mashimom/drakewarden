package org.shimomoto.drakewarden;

import org.jetbrains.annotations.NotNull;

public class OpenUnbound<T extends Comparable<T>> extends BaseLeftBoundRange<T> {
	public OpenUnbound(@NotNull T left) {
		super(left);
	}

	@Override
	public boolean isLeftClosed() {
		return false;
	}

	@Override
	public boolean contains(T value) {
		return getLeft().compareTo(value) < 0;
	}
}
