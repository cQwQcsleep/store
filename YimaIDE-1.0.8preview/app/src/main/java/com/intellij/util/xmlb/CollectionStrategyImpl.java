package com.intellij.util.xmlb;

import com.intellij.serialization.ClassUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0016J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0012HÖ\u0001¨\u0006\u001a"}, d2 = {"Lcom/intellij/util/xmlb/CollectionStrategyImpl;", "Lcom/intellij/util/xmlb/CollectionStrategy;", "<init>", "()V", "deserializeList", "", "T", "currentValue", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "binding", "Lcom/intellij/util/xmlb/CollectionBinding;", "getCollection", "", "bean", "getCollectionTagName", "", "target", "equals", "", "other", "hashCode", "", "toString", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class CollectionStrategyImpl implements CollectionStrategy {
    public static final CollectionStrategyImpl INSTANCE = new CollectionStrategyImpl();

    private CollectionStrategyImpl() {
    }

    @Override // com.intellij.util.xmlb.CollectionStrategy
    public <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter, CollectionBinding binding) {
        Collection hashSet;
        elements.getClass();
        adapter.getClass();
        binding.getClass();
        if (currentValue == null || !ClassUtil.isMutableCollection(currentValue)) {
            hashSet = currentValue instanceof Set ? new HashSet() : new ArrayList();
        } else {
            currentValue.getClass();
            hashSet = TypeIntrinsics.asMutableCollection(currentValue);
            hashSet.clear();
        }
        Iterator<? extends T> it = elements.iterator();
        while (it.hasNext()) {
            hashSet.add(binding.deserializeItem$intellij_platform_util(it.next(), adapter, currentValue));
        }
        return hashSet;
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof CollectionStrategyImpl);
    }

    @Override // com.intellij.util.xmlb.CollectionStrategy
    public String getCollectionTagName(Object target) {
        if (target instanceof Set) {
            return "set";
        }
        return target instanceof List ? "list" : "collection";
    }

    public int hashCode() {
        return 724792626;
    }

    public String toString() {
        return "CollectionStrategyImpl";
    }
}
