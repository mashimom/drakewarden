package org.shimomoto.drakewarden;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.RightBound;

@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@Getter
@RequiredArgsConstructor
abstract class BaseRightBoundRange<T extends Comparable<T>> implements RightBound<T> {
	@NotNull T right;
}
