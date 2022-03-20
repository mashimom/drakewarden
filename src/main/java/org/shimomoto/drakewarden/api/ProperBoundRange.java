package org.shimomoto.drakewarden.api;

public interface ProperBoundRange<T extends Comparable<T>> extends Range<T>,LeftBound<T>,RightBound<T> {
	default boolean isDegenerate() {
		return getLeft().compareTo(getRight()) == 0;
	}
}
