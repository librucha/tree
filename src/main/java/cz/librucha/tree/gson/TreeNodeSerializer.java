package cz.librucha.tree.gson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	public static void main(String[] args) {
		TreeNode<String> node = new TreeNode<String>("0_0", "sdasdasd");
		Gson gson = new GsonBuilder().registerTypeAdapter(TreeNode.class, new TreeNodeSerializer()).create();
		System.out.println(gson.toJson(node));
	}
}
