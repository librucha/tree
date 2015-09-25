package org.librucha.tree.common.node;

import org.librucha.tree.node.*;

import javax.annotation.*;

public class CommonNode<T> extends AbstractNode<T> {

	public CommonNode(@Nonnull T value) {
		super(value);
	}

	public CommonNode(@Nonnull T value, @Nullable Node<T> parent) {
		super(value, parent);
	}
}
