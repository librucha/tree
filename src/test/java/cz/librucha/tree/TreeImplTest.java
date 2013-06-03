package cz.librucha.tree;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeImplTest extends TreeTest {

	@Test
	public void testSize() {
		Tree<String> tree = new TreeImpl<String>();
		assertThat(tree.size()).isEqualTo(0);
		tree.add(key(), data());
		assertThat(tree.size()).isEqualTo(1);
		tree.add(key(), data());
		tree.add(key(), data());
		tree.add(key(), data());
		tree.add(key(), data());
		assertThat(tree.size()).isEqualTo(5);
	}

	@Test
	public void testIsEmpty() {
		Tree<String> tree = new TreeImpl<String>();
		assertThat(tree.isEmpty()).isTrue();
		tree.add(key(), data());
		assertThat(tree.isEmpty()).isFalse();
	}

	@Test
	public void testContains() {
		Tree<String> tree = new TreeImpl<String>();
		String checkedKey = key();
		String checkedData = data();
		tree.add(key(), data());
		tree.add(key(), checkedData);
		tree.add(key(), data());
		tree.add(checkedKey, data());
		tree.add(key(), data());
		assertThat(tree.containsKey(checkedKey)).isTrue();
		assertThat(tree.containsKey(checkedKey + "_")).isFalse();
		assertThat(tree.containsData(checkedData)).isTrue();
		assertThat(tree.containsData(checkedData + "_")).isFalse();
	}

	@Test
	public void testAddNode() {
		fail("Not yet implemented");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddExistingKey() {
		Tree<String> tree = new TreeImpl<String>();
		tree.add("existing", data());
		tree.add("existing", data());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

}
