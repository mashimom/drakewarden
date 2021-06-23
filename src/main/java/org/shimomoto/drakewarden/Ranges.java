package org.shimomoto.drakewarden;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Ranges {

	public static <T extends Comparable<T>> Range<T> closedOpen(@NotNull T left, @NotNull T right) {
		return new ClosedOpen<>(left, right);
	}

}
