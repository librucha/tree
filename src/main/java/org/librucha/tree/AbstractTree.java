package org.librucha.tree;

import org.librucha.tree.common.node.CommonNode;
import org.librucha.tree.internal.utils.CollectionUtils;
import org.librucha.tree.node.Node;

import javax.annotation.Nonnull;
import java.util.*;

import static java.util.Collections.singletonList;

public abstract class AbstractTree<T> implements Tree<T> {

	private final Node<T> rootNode;

	private final List<Node<T>> nodes;

	public AbstractTree(T value) {
		Node<T> node = new CommonNode<>(value);
		rootNode = node;
		nodes = new ArrayList<>();
		nodes.add(node);
	}

	protected AbstractTree(Node<T> rootNode) {
		this.rootNode = rootNode;
		nodes = new ArrayList<>();
		nodes.add(this.rootNode);
	}

	@Nonnull
	@Override
	public Node<T> getRootNode() {
		return rootNode;
	}

	@Nonnull
	@Override
	public Node<T> add(@Nonnull T value) {
		CommonNode<T> node = new CommonNode<>(value);
		rootNode.addChildNode(node);
		nodes.add(node);
		return node;
	}

	@Nonnull
	@Override
	public Node<T> add(@Nonnull T value, @Nonnull Node<T> parentNode) {
		if (CollectionUtils.notContainsReference(getNodes(), parentNode)) {
			throw new IllegalArgumentException("Parent node must be contained in this tree");
		}

		CommonNode<T> node = new CommonNode<>(value);
		parentNode.addChildNode(node);
		nodes.add(node);
		return node;
	}

	@Nonnull
	@Override
	public List<T> inOrder() {
		return null;
	}

	@Nonnull
	@Override
	public List<T> preOrder() {
		final List<T> result = new ArrayList<>(getNodes().size());
		walk(rootNode, new WalkCallback<Node<T>>() {
			@Override
			public void doWithNode(Node<T> node) {
				result.add(node.getValue());
			}
		});
		return result;
	}

	@Nonnull
	@Override
	public List<T> postOrder() {
		return null;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Nonnull
	@Override
	public List<List<Node<T>>> getBranches() {
		if (getRootNode().isLeaf()) {
			return singletonList(singletonList(getRootNode()));
		}
		List<List<Node<T>>> branches = new ArrayList<>();
		for (Node<T> node : getNodes()) {
			if (!node.isLeaf()) {
				List<Node<T>> branch = getBranchContainsNodeInside(branches, node);
				if (branch == null) {
					for (Node<T> child : node.getChildren()) {
						branches.add(CollectionUtils.newArrayList(node, child));
					}
				} else {
					for (Node<T> child : node.getChildren()) {
						branches.add(CollectionUtils.newArrayList(branch, child));
					}
					branches.remove(branch);
				}
			}
		}
		return branches;
	}

	private List<Node<T>> getBranchContainsNodeInside(List<List<Node<T>>> branches, Node<T> search) {
		for (List<Node<T>> branch : branches) {
			if (branch != null) {
				for (Node<T> node : branch) {
					if (node == search) {
						return branch;
					}
				}
			}
		}
		return null;
	}

	protected Collection<Node<T>> getNodes() {
		return nodes;
	}

	void walk(Node<T> node, WalkCallback<Node<T>> callback) {
		callback.doWithNode(node);
		if (!node.getChildren().isEmpty()) {
			for (Node<T> childNode : node.getChildren()) {
				walk(childNode, callback);
			}
		}
	}

	private interface WalkCallback<Node> {

		void doWithNode(Node node);
	}

	@Override
	public String toString() {

		return getRootNode().toString() + "\n" +
				"├─ " + "1\n" +
				"│  └─ " + "2\n" +
				"└─ " + "6\n";
	}

	// ┬
}
