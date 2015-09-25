package org.librucha.tree.node;

import javax.annotation.*;
import java.util.Collection;

public interface Node<T> {

	@Nonnull
	T getValue();

	@Nullable
	Node<T> getParent();

	@Nonnull
	Collection<Node<T>> getChildren();

	@Nonnegative
	int getLevel();

	void addChildNode(Node<T> childNode);

	boolean isLeaf();
}
