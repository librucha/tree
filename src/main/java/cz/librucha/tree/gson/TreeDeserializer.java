package cz.librucha.tree.gson;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import cz.librucha.tree.Tree;
import cz.librucha.tree.TreeNode;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeDeserializer implements JsonDeserializer<Tree<?>> {

	@Override
	public Tree<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		Type nodeType = new TypeToken<TreeNode<Object>>() {
		}.getType();
		TreeNode<Object> root = context.deserialize(json, nodeType);
		Tree<Object> tree = new Tree<Object>(root);
		return tree;
	}

}
