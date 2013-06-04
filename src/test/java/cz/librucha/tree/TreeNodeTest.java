package cz.librucha.tree;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeNodeTest extends AbstractTreeTest {

	@Test
	public void testAddNode() throws Exception {
		TreeNode<String> rootNode = new TreeNode<String>(key(), "data");
		TreeNode<String> node = rootNode.addNode(key(), data());
		assertThat(node).isNotNull();
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(node.getParent()).isNotNull().isSameAs(rootNode);
	}

	@Test
	public void testAddMoreNodes() throws Exception {
		TreeNode<String> rootNode = new TreeNode<String>(key(), "data");
		TreeNode<String> child1 = rootNode.addNode(key(), data());
		TreeNode<String> child2 = rootNode.addNode(key(), data());
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(2);
	}

	@Test
	public void testRemoveNode() throws Exception {
		String keyForRemove = key();
		TreeNode<String> rootNode = new TreeNode<String>(key(), "data");
		rootNode.addNode(key(), data());
		TreeNode<String> nodeForRemove = rootNode.addNode(keyForRemove, data());
		rootNode.addNode(key(), data());
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(3);
		rootNode.removeNode(keyForRemove);
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(2).excludes(nodeForRemove);
	}
}
