package cz.librucha.tree.node;

import cz.librucha.tree.TreeNode;
import org.apache.commons.lang3.builder.*;

public class SimpleTreeNode<T> implements TreeNode<T> {

  private final TreeNode<T> parent;
  private final T value;

  public SimpleTreeNode() {
	this(null, null);
  }

  public SimpleTreeNode(T value) {
	this(null, value);
  }

  public SimpleTreeNode(TreeNode<T> parent, T value) {
	this.parent = parent;
	this.value = value;
  }

  @Override
  public boolean isRoot() {
	return parent == null;
  }

  @Override
  public TreeNode<T> getParent() {
	return parent;
  }

  @Override
  public T getValue() {
	return value;
  }

  @Override
  public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
