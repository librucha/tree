package cz.librucha.tree;

/**
 * @author librucha <librucha@gmail.com>
 */
public interface Tree<T> {

	public interface WalkingCallback<R, T> {
		public R doWithNode(TreeNode<T> node);
	}

	public TreeNode<T> getRoot();

	public int size();

	public boolean isEmpty();

	public boolean containsKey(String key);

	public boolean containsData(T data);

	public TreeNode<T> add(String key, T data);

	public TreeNode<T> add(String key, T data, TreeNode<T> parent);

	public void removeKey(String key);

	public void removeData(T data);

	public void clear();

	public <R> R walk(WalkingCallback<R, T> callback);

	public <R> R walk(TreeNode<T> node, WalkingCallback<R, T> callback);
}
