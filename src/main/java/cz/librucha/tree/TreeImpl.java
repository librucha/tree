package cz.librucha.tree;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeImpl<T> implements Tree<T> {

	private final TreeNode<T> rootNode;
	private final Map<String, TreeNode<T>> nodesMap = new HashMap<String, TreeNode<T>>();

	public TreeImpl() {
		this(null, null);
	}

	public TreeImpl(String key, T data) {
		this.rootNode = new TreeNode<T>(key, data);
	}

	@Override
	public TreeNode<T> getRoot() {
		return rootNode;
	}

	@Override
	public int size() {
		return nodesMap.size();
	}

	@Override
	public boolean isEmpty() {
		return nodesMap.isEmpty();
	}

	@Override
	public boolean containsKey(String key) {
		return nodesMap.containsKey(key);
	}

	@Override
	public boolean containsData(T data) {
		for (TreeNode<T> node : nodesMap.values()) {
			if (data.equals(node.getData())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TreeNode<T> add(String key, T data) {
		return add(key, data, rootNode);
	}

	@Override
	public TreeNode<T> add(String key, T data, TreeNode<T> parent) {
		checkNotNull(key, "key must not be null");
		checkArgument(!nodesMap.containsKey(key), "key is alredy presented in tree");
		TreeNode<T> node = parent.addNode(key, data);
		nodesMap.put(key, node);
		return node;
	}

	@Override
	public void removeKey(String key) {
		checkNotNull(key, "key must not be null");
		TreeNode<T> node = nodesMap.get(key);
		node.getParent().removeNode(key);
	}

	@Override
	public void removeData(T data) {
		checkNotNull(data, "data must not be null");
		for (Entry<String, TreeNode<T>> entry : nodesMap.entrySet()) {
			if (data.equals(entry.getValue().getData())) {
				removeKey(entry.getKey());
			}
		}
	}

	@Override
	public void clear() {
		rootNode.getChildren().clear();
	}

	@Override
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

}
