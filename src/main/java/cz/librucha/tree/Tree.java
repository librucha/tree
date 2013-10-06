package cz.librucha.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author librucha <librucha@gmail.com>
 */
public class Tree<T> {

	private final TreeNode<T> rootNode;
	private final Map<String, TreeNode<T>> nodesMap = new HashMap<String, TreeNode<T>>();

	public Tree() {
		this("ROOT", null);
	}

	public Tree(String key, T data) {
		this.rootNode = new TreeNode<T>(key, data);
	}

	public Tree(TreeNode<T> root) {
		this.rootNode = root;
		Map<String, TreeNode<T>> nodes = walk(new RefreshNodesMapCallback());
		nodesMap.clear();
		nodesMap.putAll(nodes);
	}

	public TreeNode<T> getRoot() {
		return rootNode;
	}

	public int size() {
		return nodesMap.size();
	}

	public boolean isEmpty() {
		return nodesMap.isEmpty();
	}

	public boolean containsKey(String key) {
		return nodesMap.containsKey(key);
	}

	public boolean containsData(T data) {
		for (TreeNode<T> node : nodesMap.values()) {
			if (data.equals(node.getData())) {
				return true;
			}
		}
		return false;
	}

	public TreeNode<T> add(String key, T data) {
		return add(key, data, rootNode);
	}

	public TreeNode<T> add(String key, T data, TreeNode<T> parent) {
		checkNotNull(key, "key must not be null");
		TreeNode<T> node = parent.addNode(key, data);
		nodesMap.put(node.getKey(), node);
		return node;
	}

	public void removeKey(String key) {
		checkNotNull(key, "key must not be null");
		TreeNode<T> node = nodesMap.get(key);
		node.getParent().removeNode(key);
	}

	public void removeData(T data) {
		checkNotNull(data, "data must not be null");
		for (Entry<String, TreeNode<T>> entry : nodesMap.entrySet()) {
			if (data.equals(entry.getValue().getData())) {
				removeKey(entry.getKey());
			}
		}
	}

	public void clear() {
		rootNode.getChildren().clear();
	}

	public List<T> getData() {
		return Lists.newArrayList(Iterables.transform(nodesMap.values(), new Function<TreeNode<T>, T>() {
			@Override
			public T apply(TreeNode<T> node) {
				return node.getData();
			}
		}));
	}

	public List<T> getData(final int... levels) {
		List<T> data = new ArrayList<T>();
		for (TreeNode<T> node : nodesMap.values()) {
			for (int level : levels) {
				if (node.getLevel() == level) {
					data.add(node.getData());
				}
			}
		}
		return data;
	}

	public T getData(String key) {
		TreeNode<T> node = nodesMap.get(key);
		return node != null ? node.getData() : null;
	}

	public List<T> getBranchData(String key) {
		List<T> data = new ArrayList<T>();
		TreeNode<T> node = nodesMap.get(key);
		while (node.getParent() != rootNode) {
			data.add(node.getData());
			node = node.getParent();
		}
		return data;
	}

	public TreeNode<T> getNode(String key) {
		return nodesMap.get(key);
	}

	public List<TreeNode<T>> getNodes(final int... levels) {
		return Lists.newArrayList(Iterables.filter(nodesMap.values(), new Predicate<TreeNode<T>>() {
			@Override
			public boolean apply(TreeNode<T> node) {
				return ArrayUtils.contains(levels, node.getLevel());
			}
		}));
	}

	public <R> R walk(WalkingCallback<R, T> callback) {
		return walk(rootNode, callback);
	}

	public <R> R walk(TreeNode<T> node, WalkingCallback<R, T> callback) {
		checkNotNull(node, "node must not be null");
		checkNotNull(callback, "callback must not be null");
		R result = callback.doWithNode(node);
		for (TreeNode<T> child : node.getChildren()) {
			walk(child, callback);
		}
		return result;
	}

	public interface WalkingCallback<R, T> {
		public R doWithNode(TreeNode<T> node);
	}

	private class RefreshNodesMapCallback implements WalkingCallback<Map<String, TreeNode<T>>, T> {
		private Map<String, TreeNode<T>> map = new HashMap<String, TreeNode<T>>();

		@Override
		public Map<String, TreeNode<T>> doWithNode(TreeNode<T> node) {
			map.put(node.getKey(), node);
			return map;
		}
	}

}
