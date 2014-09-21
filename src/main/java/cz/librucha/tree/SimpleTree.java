package cz.librucha.tree;

import cz.librucha.tree.node.SimpleTreeNode;
import org.apache.commons.lang3.builder.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTree<T> implements Tree<T> {

  private final Map<Integer, SimpleTreeNode<T>> nodes = new HashMap<>();
  private final Map<T, Set<Integer>> index = new HashMap<>();

  private SimpleTreeNode<T> rootNode;
  private final AtomicInteger idGenerator = new AtomicInteger(0);

  public static <T> Tree<T> create() {
	return new SimpleTree<>();
  }

  public static <T> Tree<T> create(T rootValue) {
	return new SimpleTree<>(rootValue);
  }

  private SimpleTree() {
  }

  private SimpleTree(T rootValue) {
	SimpleTreeNode<T> rootNode = new SimpleTreeNode<>(rootValue);
	putNode(rootNode);
	this.rootNode = rootNode;
  }

  @Override
  public TreeNode<T> getRootNode() {
	return rootNode;
  }

  @Override
  public int size() {
	return nodes.size();
  }

  @Override
  public boolean isEmpty() {
	return nodes.isEmpty();
  }

  @Override
  public boolean contains(T item) {
	return index.containsKey(item);
  }

  @Override
  public boolean containsAll(Collection<? extends T> items) {
	return toCollection().containsAll(items);
  }

  @Override
  public Iterator<T> iterator() {
	return toCollection().iterator();
  }

  @Override
  public Collection<T> toCollection() {
	ArrayList<T> values = new ArrayList<>(size());
	for (SimpleTreeNode<T> treeNode : nodes.values()) {
	  values.add(treeNode.getValue());
	}
	return values;
  }

  @Override
  public boolean add(@Nullable T item) {
	TreeNode<T> parent = nodes.isEmpty() ? null : getRootNode();
	return add(parent, item);
  }

  @Override
  public boolean add(@Nullable TreeNode<T> parent, @Nullable T item) {
	SimpleTreeNode<T> treeNode = new SimpleTreeNode<>(parent, item);
	putNode(treeNode);
	if (parent == null) {
	  this.rootNode = treeNode;
	}
	return true;
  }

  @Override
  public boolean remove(T item) {
	throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public boolean removeAll(Collection<? extends T> item) {
	throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public boolean replace(T oldItem, @Nullable T newItem) {
	Set<Integer> oldItemIds = index.get(oldItem);
	if (oldItemIds == null) {
	  return false;
	} else {
	  for (Integer oldItemId : oldItemIds) {
		SimpleTreeNode<T> oldTreeNode = nodes.get(oldItemId);
		nodes.put(oldItemId, new SimpleTreeNode<>(oldTreeNode.getParent(), newItem));
	  }
	  return true;
	}
  }

  @Override
  public void clear() {
	nodes.clear();
	index.clear();
	rootNode = null;
	idGenerator.set(0);
  }

  private void putNode(SimpleTreeNode<T> treeNode) {
	int id = idGenerator.getAndIncrement();
	T value = treeNode.getValue();
	nodes.put(id, treeNode);
	Set<Integer> ids = index.get(value);
	if (ids != null) {
	  ids.add(id);
	} else {
	  TreeSet<Integer> newIds = new TreeSet<>();
	  newIds.add(id);
	  index.put(value, newIds);
	}
  }

  @Override
  public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
