package cz.librucha.tree;

import cz.librucha.tree.node.SimpleTreeNode;

import java.util.HashMap;
import java.util.Map;

public class SimpleTree<T> implements Tree<T> {

  private final Map<String, TreeNode<T>> nodes = new HashMap<>();

  public static <T> Tree<T> create() {
	return new SimpleTree<T>();
  }

  private SimpleTree() {
	SimpleTreeNode<T> rootNode = new SimpleTreeNode<>();
	nodes.put("ROOT", rootNode);
  }

  @Override
  public void add(T value) {

  }

//    @Override
//    public Iterator<T> iterator() {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
}
