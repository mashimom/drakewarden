package org.shimomoto.drakewarden;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.Range;

@UtilityClass
public class Ranges {

	public static <T extends Comparable<T>> Range<T> closedOpen(@NotNull T left, @NotNull T right) {
		return new ClosedOpen<>(left, right);
	}

	public static <T extends Comparable<T>> Range<T> closedClosed(@NotNull T left, @NotNull T right) {
		return new ClosedClosed<>(left, right);
	}

	public static <T extends Comparable<T>> Range<T> openOpen(@NotNull T left, @NotNull T right) {
		return new OpenOpen<>(left, right);
	}
	public static <T extends Comparable<T>> Range<T> openClosed(@NotNull T left, @NotNull T right) {
		return new OpenClosed<>(left, right);
	}
}
