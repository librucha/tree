package org.librucha.tree.internal.utils;

import javax.annotation.Nonnull;
import java.util.*;

import static java.util.Objects.requireNonNull;

public final class CollectionUtils {

	private CollectionUtils() {
		throw new AssertionError("No org.librucha.tree.internal.utils.CollectionUtils instances for you!");
	}

	public static boolean notContainsReference(Iterable<?> iterable, Object object) {
		return !containsReference(iterable, object);
	}

	public static boolean containsReference(Iterable<?> iterable, Object object) {
		for (Object checkedItem : iterable) {
			if (checkedItem == object) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... elements) {
		ArrayList<E> list = new ArrayList<>(elements.length);
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> ArrayList<E> newArrayList(@Nonnull List<E> list, @Nonnull E element) {
		requireNonNull(list, "list must not be null");
		requireNonNull(element, "element must not be null");
		ArrayList<E> newList = new ArrayList<>(list);
		Collections.addAll(newList, element);
		return newList;
	}
}
