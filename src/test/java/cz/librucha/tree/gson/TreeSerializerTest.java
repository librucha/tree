package cz.librucha.tree.gson;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.librucha.tree.Tree;
import cz.librucha.tree.TreeImpl;
import cz.librucha.tree.TreeNode;
import cz.librucha.tree.TreeTest;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeSerializerTest extends TreeTest {

	/**
	 * [0]
	 * .+-[0_0]
	 * .|...+-[0_0_0]
	 * .|...+-[0_0_1]
	 * .|........+-[0_0_1_0]
	 * .|..............+-[0_0_1_0_0]
	 * .+-[0_1]
	 * .....+-[0_1_0]
	 */

	private Tree<String> tree;
	private Gson gson;

	@Before
	public void setUp() {
		tree = new TreeImpl<String>("0", null);
		TreeNode<String> n00 = tree.add("0_0", data());
		tree.add("0_0_0", data(), n00);
		TreeNode<String> n001 = tree.add("0_0_1", data(), n00);
		TreeNode<String> n0010 = tree.add("0_0_1_0", data(), n001);
		tree.add("0_0_1_0_0", data(), n0010);
		TreeNode<String> n01 = tree.add("0_1", data());
		tree.add("0_1_0", data(), n01);

		gson = new GsonBuilder().registerTypeAdapter(TreeImpl.class, new TreeSerializer()).create();
	}

	@Test
	public void testSerialize() {
		String json = gson.toJson(tree);
		System.out.println(json);
	}

}
