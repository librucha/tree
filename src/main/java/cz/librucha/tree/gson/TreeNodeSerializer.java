package cz.librucha.tree.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import cz.librucha.tree.TreeNode;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeNodeSerializer implements JsonSerializer<TreeNode<?>> {

	@Override
	public JsonElement serialize(TreeNode<?> node, Type type, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("key", node.getKey());
		json.add("data", context.serialize(node.getData()));
		json.add("children", context.serialize(node.getChildren()));
		return json;
	}

}
