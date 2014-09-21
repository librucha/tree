package cz.librucha.tree;

public interface TreeNode<T> {

  boolean isRoot();

  TreeNode<T> getParent();

  T getValue();
}
