package cz.librucha.tree.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import cz.librucha.tree.Tree;

/**
 * @author librucha <librucha@gmail.com>
 */
public class TreeSerializer implements JsonSerializer<Tree<?>> {

	@Override
	public JsonElement serialize(Tree<?> tree, Type type, JsonSerializationContext context) {
		return context.serialize(tree.getRoot());
	}

}
