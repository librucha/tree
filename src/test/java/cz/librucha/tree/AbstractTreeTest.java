package cz.librucha.tree;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author librucha <librucha@gmail.com>
 */
public abstract class AbstractTreeTest {

	protected String key() {
		return RandomStringUtils.randomAlphabetic(6);
	}

	protected String data() {
		return RandomStringUtils.randomAlphabetic(3);
	}
}
