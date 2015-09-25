package org.librucha.tree;

import org.librucha.tree.node.Node;

import javax.annotation.Nonnull;
import java.util.List;

public interface Tree<T> {

	@Nonnull
	Node<T> getRootNode();

	@Nonnull
	Node<T> add(@Nonnull T value);

	@Nonnull
	Node<T> add(@Nonnull T value, @Nonnull Node<T> parentNode);

	@Nonnull
	List<T> inOrder();

	@Nonnull
	List<T> preOrder();

	@Nonnull
	List<T> postOrder();

	int getHeight();

	@Nonnull
	List<List<Node<T>>> getBranches();
}
