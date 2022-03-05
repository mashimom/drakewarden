package org.shimomoto.drakewarden.api;

import java.util.function.Predicate;

public interface Range<T extends Comparable<T>> extends Predicate<T> {
	default boolean contains(T value) {
		return test(value);
	}
	T getLeft();
	T getRight();

	boolean isLeftClosed();
	default boolean isLeftOpen(){return !isLeftClosed();}

	boolean isRightClosed();
	default boolean isRightOpen(){return !isRightClosed();}

	boolean isDegenerate();
}
