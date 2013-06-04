package cz.librucha.tree;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author librucha <librucha@gmail.com>
 */
public class Tree<T> {

	private final TreeNode<T> rootNode;
	private final Map<String, TreeNode<T>> nodesMap = new HashMap<String, TreeNode<T>>();

	public Tree() {
		this(null, null);
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
		checkArgument(!nodesMap.containsKey(key), "key is alredy presented in tree");
		TreeNode<T> node = parent.addNode(key, data);
		nodesMap.put(key, node);
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
