package cz.librucha.tree;

import javax.annotation.Nullable;
import java.util.*;

public interface Tree<T> extends Iterable<T> {

  TreeNode<T> getRootNode();

  int size();

  boolean isEmpty();

  boolean contains(T item);

  boolean containsAll(Collection<? extends T> items);

  @Override
  Iterator<T> iterator();

  Collection<T> toCollection();

  boolean add(@Nullable T item);

  boolean add(@Nullable TreeNode<T> parent, @Nullable T item);

  boolean remove(T item);

  boolean removeAll(Collection<? extends T> items);

  boolean replace(T oldItem, @Nullable T newItem);

  void clear();
}
