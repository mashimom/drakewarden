package org.shimomoto.drakewarden;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.Range;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@Getter
@RequiredArgsConstructor
abstract class BaseRange<T extends Comparable<T>> implements Range<T> {
	@NotNull T left;
	@NotNull T right;

	@Override
	public boolean isDegenerate() {
		return left.compareTo(right) == 0;
	}
}
