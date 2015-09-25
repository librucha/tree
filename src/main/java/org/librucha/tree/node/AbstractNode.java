package org.librucha.tree.node;

import javax.annotation.*;
import java.util.*;

import static java.util.Objects.requireNonNull;

public abstract class AbstractNode<T> implements Node<T> {

	private final T value;

	private final Node<T> parent;

	private final List<Node<T>> children;

	private final int level;

	protected AbstractNode(@Nonnull T value) {
		this(value, null);
	}

	protected AbstractNode(@Nonnull T value, @Nullable Node<T> parent) {
		requireNonNull(value, "value must not be null");
		this.value = value;
		this.parent = parent;
		children = new ArrayList<>(0);
		level = parent == null ? 0 : parent.getLevel() + 1;
	}

	@Nonnull
	@Override
	public T getValue() {
		return value;
	}

	@Nullable
	@Override
	public Node<T> getParent() {
		return parent;
	}

	@Nonnull
	@Override
	public Collection<Node<T>> getChildren() {
		return children;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void addChildNode(Node<T> childNode) {
		getChildren().add(childNode);
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}
