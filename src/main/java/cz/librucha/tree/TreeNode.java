package cz.librucha.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeNode<T> {

	private final String key;
	private final T data;
	private final int level;
	private final List<TreeNode<T>> children = new ArrayList<TreeNode<T>>(0);
	private final TreeNode<T> parent;

	public TreeNode(String key, T data) {
		this(key, data, null);
	}

	public TreeNode(String key, T data, TreeNode<T> parent) {
		this.key = key;
		this.data = data;
		this.parent = parent;
		this.level = parent == null ? 0 : parent.getLevel() + 1;
	}

	TreeNode<T> addNode(String key, T data) {
		TreeNode<T> node = new TreeNode<T>(key, data, this);
		this.getChildren().add(node);
		return node;
	}

	void removeNode(String key) {
		int idx = findChild(key);
		getChildren().remove(idx);
	}

	private int findChild(String key) {
		for (int i = 0; i < getChildren().size(); i++) {
			if (key.equals(getChildren().get(i).getKey())) {
				return i;
			}
		}
		return -1;
	}

	public String getKey() {
		return key;
	}

	public T getData() {
		return data;
	}

	public int getLevel() {
		return level;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TreeNode)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		TreeNode<T> other = (TreeNode<T>) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		}
		else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("TreeNode [key=%s, data=%s, childrens=%d]", key, data, children.size());
	}

}
