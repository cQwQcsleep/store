package com.reandroid.dex.common;

import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.program.AccessibleProgram;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IdDefinition<T extends IdItem> extends AccessibleProgram, IdUsageIterator {
    T getId();

    boolean isRemoved();

    @Override // com.reandroid.dex.common.IdUsageIterator
    default boolean uses(Key key) {
        IdItem id = getId();
        Iterator<IdItem> itUsedIds = usedIds();
        while (itUsedIds.hasNext()) {
            IdItem next = itUsedIds.next();
            if (!id.equals(next) && key.equals(next.getKey())) {
                return true;
            }
        }
        return false;
    }
}
