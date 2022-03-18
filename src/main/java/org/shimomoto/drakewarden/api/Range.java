package org.shimomoto.drakewarden.api;

import java.util.Objects;

@FunctionalInterface
public interface Range<T extends Comparable<T>> {
	boolean contains(T value);

	default Range<T> union(Range<T> other) {
		Objects.requireNonNull(other);
		return (t) -> contains(t) || other.contains(t);
	}

	default Range<T> intersection(Range<T> other) {
		Objects.requireNonNull(other);
		return (t) -> contains(t) && other.contains(t);
	}

	default Range<T> difference(Range<T> other) {
		Objects.requireNonNull(other);
		return (t) -> contains(t) && !other.contains(t);
	}

	default Range<T> symmetricDifference(Range<T> other) {
		Objects.requireNonNull(other);
		return (t) -> (contains(t) && !other.contains(t)) || (!contains(t) && other.contains(t));
	}

	default Range<T> complement() {
		return (t) -> !contains(t);
	}
}
