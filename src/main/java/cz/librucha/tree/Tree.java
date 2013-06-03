package cz.librucha.tree;

import java.util.Collection;

/**
 * @author librucha <librucha@gmail.com>
 */
public interface Tree<T> extends Collection<T> {
	
	public void addNode(T data);

}
