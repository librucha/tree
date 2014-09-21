package cz.librucha.tree.node;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SimpleTreeNodeTest {

  @Test
  public void testIsRoot_DefaultConstructor() throws Exception {
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>();

	assertThat(treeNode).isNotNull();
	assertThat(treeNode.isRoot()).isTrue();
  }

  @Test
  public void testIsRoot_ValueConstructor() throws Exception {
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>("TEST");

	assertThat(treeNode).isNotNull();
	assertThat(treeNode.isRoot()).isTrue();
  }

  @Test
  public void testIsRoot_ParentConstructor() throws Exception {
	SimpleTreeNode<String> rootNode = new SimpleTreeNode<>("ROOT");
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>(rootNode, "TEST");

	assertThat(rootNode).isNotNull();
	assertThat(treeNode).isNotNull();
	assertThat(treeNode.isRoot()).isFalse();
  }

  @Test
  public void testGetParent_RootNode() throws Exception {
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>();

	assertThat(treeNode).isNotNull();
	assertThat(treeNode.getParent()).isNull();
  }

  @Test
  public void testGetParent_ChildNode() throws Exception {
	SimpleTreeNode<String> rootNode = new SimpleTreeNode<>("ROOT");
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>(rootNode, "TEST");

	assertThat(rootNode).isNotNull();
	assertThat(treeNode).isNotNull();
	assertThat(treeNode.getParent()).isSameAs(rootNode);
  }

  @Test
  public void testGetValue_NoValue() throws Exception {
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>();

	assertThat(treeNode).isNotNull();
	assertThat(treeNode.getValue()).isNull();
  }

  @Test
  public void testGetValue_TextValue() throws Exception {
	SimpleTreeNode<String> treeNode = new SimpleTreeNode<>("TEST");

	assertThat(treeNode).isNotNull();
	assertThat(treeNode.getValue()).isEqualTo("TEST");
  }
}