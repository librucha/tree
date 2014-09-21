package cz.librucha.tree;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

public class SimpleTreeTest {
  @Test
  public void testCreate() throws Exception {
	Tree<Object> tree = SimpleTree.create();

	assertThat(tree).isNotNull();
  }

  @Test
  public void testAdd() throws Exception {
	fail("Not implemented yet");
  }

  @Test
  public void testIterator() throws Exception {
	fail("Not implemented yet");
  }
}
