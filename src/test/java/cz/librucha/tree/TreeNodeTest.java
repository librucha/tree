package cz.librucha.tree;

import static org.fest.assertions.Assertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeNodeTest {

	private String key() {
		return RandomStringUtils.randomAlphabetic(6);
	}

	private String data() {
		return RandomStringUtils.randomAlphabetic(3);
	}

	@Test
	public void testAddNode() throws Exception {
		TreeNode<String> rootNode = new TreeNode<String>(key(), "data");
		TreeNode<String> node = rootNode.addNode(key(), data());
		assertThat(node).isNotNull();
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(node.getParent()).isNotNull().isSameAs(rootNode);
		assertThat(node.getSiblings()).isNotNull().isEmpty();
	}

	@Test
	public void testAddMoreNodes() throws Exception {
		TreeNode<String> rootNode = new TreeNode<String>(key(), "data");
		TreeNode<String> child1 = rootNode.addNode(key(), data());
		TreeNode<String> child2 = rootNode.addNode(key(), data());
		assertThat(rootNode.getChildren()).isNotNull().isNotEmpty().hasSize(2);
		assertThat(child1.getSiblings()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(child2.getSiblings()).isNotNull().isNotEmpty().hasSize(1);
	}
}
