package cz.librucha.tree.node;

import cz.librucha.tree.TreeNode;

public class SimpleTreeNode<T> implements TreeNode<T> {

  private final TreeNode<T> parent;

  public SimpleTreeNode() {
	this.parent = null;
  }

  public SimpleTreeNode(TreeNode<T> parent) {
	this.parent = parent;
  }

  @Override
  public boolean isRoot() {
	return parent == null;
  }
}
