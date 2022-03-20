package org.shimomoto.drakewarden;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.ProperBoundRange;

@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@Getter
@RequiredArgsConstructor
abstract class BaseProperBoundRange<T extends Comparable<T>> implements ProperBoundRange<T> {
	@NotNull T left;
	@NotNull T right;
}
