package org.shimomoto.drakewarden;

import java.util.function.Predicate;

public interface Range<T extends Comparable<T>> extends Predicate<T> {
	default boolean contains(T value) {
		return test(value);
	}
}
