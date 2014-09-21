package cz.librucha.tree;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SimpleTreeTest {

  @Test
  public void testCreate_WithoutRoot() throws Exception {
	Tree<Object> tree = SimpleTree.create();

	assertThat(tree).isNotNull().isEmpty();
  }

  @Test
  public void testCreate_WithRoot() throws Exception {
	Tree<Integer> tree = SimpleTree.create(0);

	assertThat(tree).isNotNull().hasSize(1);
  }

  @Test
  public void testAdd_ToEmpty() throws Exception {
	Tree<Integer> tree = SimpleTree.create();
	tree.add(10);

	assertThat(tree).hasSize(1).containsOnly(10);
	TreeNode<Integer> rootNode = tree.getRootNode();
	assertThat(rootNode.isRoot()).isTrue();
	assertThat(rootNode.getValue()).isSameAs(10);

	System.out.println(tree);
  }
}
