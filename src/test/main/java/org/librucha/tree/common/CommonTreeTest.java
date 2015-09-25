package org.librucha.tree.common;

import org.junit.Test;
import org.librucha.tree.Tree;
import org.librucha.tree.node.Node;

public class CommonTreeTest {

	@Test
	public void testInOrderCommon() throws Exception {

		Tree<Integer> tree = new CommonTree<>(0);
		Node<Integer> node1 = tree.add(1);
		Node<Integer> node4 = tree.add(4);

		tree.add(2, node1);
		tree.add(3, node1);

		tree.add(3, node4);
		tree.add(5, node4);

		System.out.println(tree.getBranches());

		System.out.println(tree.preOrder());
	}
}