package cz.librucha.tree.gson;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.librucha.tree.AbstractTreeTest;
import cz.librucha.tree.Tree;
import cz.librucha.tree.TreeNode;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeSerializerTest extends AbstractTreeTest {

	/**
	 * [0]
	 * .+-[0:0]
	 * .|...+-[0:0:0]
	 * .|...+-[0:0:1]
	 * .|........+-[0:0:1:0]
	 * .|..............+-[0:0:1:0:0]
	 * .+-[0:1]
	 * .....+-[0:1:0]
	 */

	private Tree<String> tree;
	private Gson gson;

	@Before
	public void setUp() {
		tree = new Tree<String>("0", data());
		TreeNode<String> n00 = tree.add("0", data());
		tree.add("0", data(), n00);
		TreeNode<String> n001 = tree.add("1", data(), n00);
		TreeNode<String> n0010 = tree.add("0", data(), n001);
		tree.add("0", data(), n0010);
		TreeNode<String> n01 = tree.add("1", data());
		tree.add("0", data(), n01);

		gson = new GsonBuilder()
				.registerTypeAdapter(Tree.class, new TreeSerializer())
				.registerTypeAdapter(TreeNode.class, new TreeNodeSerializer())
				.create();
	}

	@Test
	public void testSerialize() {
		String json = gson.toJson(tree);
		System.out.println(json);
		assertThat(json).isNotNull().isNotEmpty();
	}

}
