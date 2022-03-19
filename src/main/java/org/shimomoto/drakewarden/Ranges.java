package org.shimomoto.drakewarden;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.drakewarden.api.LeftBound;
import org.shimomoto.drakewarden.api.ProperBoundRange;
import org.shimomoto.drakewarden.api.Range;
import org.shimomoto.drakewarden.api.RightBound;

/**
 * This is a static factory methods allows creation of any one of the 11 range classifications.
 *
 * <p>The only ranges that have no specific factory methods are <i>empty</i> and <i>degenerate</i>,
 * since they depend on the given bounds.
 *
 * <p>A {@code null} value is always outside range</p>
 *
 * <p>The methods allow explicit creation of:
 * <ul>
 *     <li>Proper/Bounded (both ends defined):</li>
 *     <ul>
 *         <li><i>left</i> Closed - <i>right</i> Open: {@link #closedOpen(Comparable, Comparable)}</li>
 *         <li><i>left</i> Closed - <i>right</i> Closed: {@link #closedClosed(Comparable, Comparable)}</li>
 *         <li><i>left</i> Open - <i>right</i> Open: {@link #openOpen(Comparable, Comparable)}</li>
 *         <li><i>left</i> Open - <i>right</i> Closed: {@link #openClosed(Comparable, Comparable)}</li>
 *     </ul>
 *     <li>Left bounded and Right unbounded</li>
 *     <ul>
 *         <li><i>left</i> Closed - <i>right</i> unbounded: {@link #closedUnbound(Comparable)}</li>
 *         <li><i>left</i> Open - <i>right</i> unbounded: {@link #openUnbound(Comparable)}</li>
 *     </ul>
 *     <li>Left unbounded and Right bounded</li>
 *     <ul>
 *         <li><i>left</i> unbounded - <i>right</i> Closed: {@link #unboundClosed(Comparable)}</li>
 *         <li><i>left</i> unbounded - <i>right</i> Open: {@link #unboundOpen(Comparable)}</li>
 *     </ul>
 *     <li>Left unbounded and Right unbounded: {@link #unbound()}</li>
 * </ul>
 */
@UtilityClass
public class Ranges {

	@SuppressWarnings("rawtypes")
	public static final UnboundedRange UNBOUNDED_RANGE = new UnboundedRange();

	/**
	 * A left closed - right open range.
	 *
	 * @param left left bound limit of range inclusive
	 * @param right right bound limit of range exclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range that could be empty, degenerate or proper depending on provided values.
	 */
	public static <T extends Comparable<T>> ProperBoundRange<T> closedOpen(@NotNull T left, @NotNull T right) {
		return new ClosedOpen<>(left, right);
	}

	/**
	 * A left closed - right closed range.
	 *
	 * @param left left bound limit of range inclusive
	 * @param right right bound limit of range inclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range that could be empty, degenerate or proper depending on provided values.
	 */
	public static <T extends Comparable<T>> ProperBoundRange<T> closedClosed(@NotNull T left, @NotNull T right) {
		return new ClosedClosed<>(left, right);
	}

	/**
	 * A left open - right open range.
	 *
	 * @param left left bound limit of range exclusive
	 * @param right right bound limit of range exclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range that could be empty, degenerate or proper depending on provided values.
	 */
	public static <T extends Comparable<T>> ProperBoundRange<T> openOpen(@NotNull T left, @NotNull T right) {
		return new OpenOpen<>(left, right);
	}

	/**
	 * A left open - right closed range.
	 *
	 * @param left left bound limit of range exclusive
	 * @param right right bound limit of range inclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range that could be empty, degenerate or proper depending on provided values.
	 */
	public static <T extends Comparable<T>> ProperBoundRange<T> openClosed(@NotNull T left, @NotNull T right) {
		return new OpenClosed<>(left, right);
	}

	/**
	 * A left closed - right unbound (♾) range.
	 *
	 * @param left left bound limit of range inclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range.
	 */
	public static <T extends Comparable<T>> LeftBound<T> closedUnbound(@NotNull T left) {
		return new ClosedUnbound<>(left);
	}

	/**
	 * A left open - right unbound (♾) range.
	 *
	 * @param left left bound limit of range exclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range.
	 */
	public static <T extends Comparable<T>> LeftBound<T> openUnbound(@NotNull T left) {
		return new OpenUnbound<>(left);
	}

	/**
	 * A left unbound (♾) - right closed range.
	 *
	 * @param right right bound limit of range inclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range.
	 */
	public static <T extends Comparable<T>> RightBound<T> unboundClosed(@NotNull T right) {
		return new UnboundClosed<>(right);
	}

	/**
	 * A left unbound (♾) - right open range.
	 *
	 * @param right right bound limit of range exclusive
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range.
	 */
	public static <T extends Comparable<T>> RightBound<T> unboundOpen(@NotNull T right) {
		return new UnboundOpen<>(right);
	}

	/**
	 * A left unbound (♾) - right unbound (♾) range.
	 *
	 * @param <T> type the range represents, must be Comparable
	 * @return a valid Range.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> Range<T> unbound() {
		return (Range<T>) UNBOUNDED_RANGE;
	}
}
