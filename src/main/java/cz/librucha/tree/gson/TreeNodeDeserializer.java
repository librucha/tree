package cz.librucha.tree.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import cz.librucha.tree.TreeNode;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeNodeDeserializer implements JsonDeserializer<TreeNode<?>> {

	@Override
	public TreeNode<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		if (json.isJsonObject()) {
			Type dataType = ((ParameterizedType) type).getActualTypeArguments()[0];
			JsonObject jsonObject = json.getAsJsonObject();
			JsonElement keyElement = jsonObject.get("key");
			JsonElement dataElement = jsonObject.get("data");
			TreeNode<Object> node = new TreeNode<Object>(keyElement.getAsString(), context.deserialize(dataElement, dataType));
			JsonElement children = jsonObject.get("children");
			if (children.isJsonArray()) {
				for (Iterator<JsonElement> i = children.getAsJsonArray().iterator(); i.hasNext();) {
					TreeNode<Object> child = context.deserialize(i.next(), type);
					node.getChildren().add(child);
				}
			}
			return node;
		}
		return null;
	}

	public static void main(String[] args) {
		String json = "{\"key\":\"0\",\"data\":\"XIj\",\"children\":[{\"key\":\"0_0\",\"data\":\"VmF\",\"children\":[{\"key\":\"0_0_0\",\"data\":\"NFG\",\"children\":[]},{\"key\":\"0_0_1\",\"data\":\"HCG\",\"children\":[{\"key\":\"0_0_1_0\",\"data\":\"pPR\",\"children\":[{\"key\":\"0_0_1_0_0\",\"data\":\"Hxs\",\"children\":[]}]}]}]},{\"key\":\"0_1\",\"data\":\"BxP\",\"children\":[{\"key\":\"0_1_0\",\"data\":\"lzZ\",\"children\":[]}]}]}";
		Gson gson = new GsonBuilder().registerTypeAdapter(TreeNode.class, new TreeNodeDeserializer()).create();
		Type type = new TypeToken<TreeNode<String>>() {
		}.getType();
		TreeNode<String> node = gson.fromJson(json, type);
		System.out.println(node);
	}

}
