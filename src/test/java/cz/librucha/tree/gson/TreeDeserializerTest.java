package cz.librucha.tree.gson;

import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cz.librucha.tree.Tree;
import cz.librucha.tree.TreeNode;
import cz.librucha.tree.AbstractTreeTest;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeDeserializerTest extends AbstractTreeTest {

	private Gson gson;

	@Before
	public void setUp() {

		gson = new GsonBuilder()
				.registerTypeAdapter(Tree.class, new TreeDeserializer())
				.registerTypeAdapter(TreeNode.class, new TreeNodeDeserializer())
				.create();
	}

	@Test
	public void testDeserialize() {
		String json = "{\"key\":\"0\",\"data\":\"XIj\",\"children\":[{\"key\":\"0_0\",\"data\":\"VmF\",\"children\":[{\"key\":\"0_0_0\",\"data\":\"NFG\",\"children\":[]},{\"key\":\"0_0_1\",\"data\":\"HCG\",\"children\":[{\"key\":\"0_0_1_0\",\"data\":\"pPR\",\"children\":[{\"key\":\"0_0_1_0_0\",\"data\":\"Hxs\",\"children\":[]}]}]}]},{\"key\":\"0_1\",\"data\":\"BxP\",\"children\":[{\"key\":\"0_1_0\",\"data\":\"lzZ\",\"children\":[]}]}]}";
		Type type = new TypeToken<Tree<String>>() {
		}.getType();
		Tree<String> tree = gson.fromJson(json, type);
		assertThat(tree).isNotNull();
		assertThat(tree.size()).isEqualTo(8);
	}

}
