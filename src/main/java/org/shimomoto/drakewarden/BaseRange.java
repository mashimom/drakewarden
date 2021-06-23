package org.shimomoto.drakewarden;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
abstract class BaseRange<T extends Comparable<T>> implements Range<T> {
	@NotNull T left;
	@NotNull T right;
}
