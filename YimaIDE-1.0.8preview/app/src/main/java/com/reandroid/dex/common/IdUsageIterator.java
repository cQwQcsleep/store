package com.reandroid.dex.common;

import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.IterableIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IdUsageIterator {
    Iterator<IdItem> usedIds();

    default Iterator<Key> usedKeys() {
        return new IterableIterator<IdItem, Key>(usedIds()) { // from class: com.reandroid.dex.common.IdUsageIterator.1
            public Iterator<Key> iterator(IdItem idItem) {
                return idItem.getKey().mentionedKeys();
            }
        };
    }

    default boolean uses(IdItem idItem) {
        return CollectionUtil.contains(usedIds(), idItem);
    }

    default boolean uses(Key key) {
        return CollectionUtil.contains(usedKeys(), key);
    }
}
