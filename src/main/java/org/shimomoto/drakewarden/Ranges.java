package org.shimomoto.drakewarden;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.ProperBoundRange;

@UtilityClass
public class Ranges {

	public static <T extends Comparable<T>> ProperBoundRange<T> closedOpen(@NotNull T left, @NotNull T right) {
		return new ClosedOpen<>(left, right);
	}

	public static <T extends Comparable<T>> ProperBoundRange<T> closedClosed(@NotNull T left, @NotNull T right) {
		return new ClosedClosed<>(left, right);
	}

	public static <T extends Comparable<T>> ProperBoundRange<T> openOpen(@NotNull T left, @NotNull T right) {
		return new OpenOpen<>(left, right);
	}
	public static <T extends Comparable<T>> ProperBoundRange<T> openClosed(@NotNull T left, @NotNull T right) {
		return new OpenClosed<>(left, right);
	}
}
