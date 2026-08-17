package com.intellij.util.xmlb;

import java.lang.reflect.Array;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J@\u0010\b\u001a\u00020\u0007\"\b\b\u0000\u0010\t*\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0014\u001a\u00020\u00072\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00122\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0016J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001¨\u0006\u001e"}, d2 = {"Lcom/intellij/util/xmlb/ArrayStrategy;", "Lcom/intellij/util/xmlb/CollectionStrategy;", "<init>", "()V", "getCollectionTagName", "", "target", "", "deserializeList", "T", "currentValue", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "binding", "Lcom/intellij/util/xmlb/CollectionBinding;", "getCollection", "", "bean", "transformJsonValue", "value", "itemType", "Ljava/lang/Class;", "equals", "", "other", "hashCode", "", "toString", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class ArrayStrategy implements CollectionStrategy {
    public static final ArrayStrategy INSTANCE = new ArrayStrategy();

    private ArrayStrategy() {
    }

    @Override // com.intellij.util.xmlb.CollectionStrategy
    public <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter, CollectionBinding binding) {
        elements.getClass();
        adapter.getClass();
        binding.getClass();
        int size = elements.size();
        Object objNewInstance = Array.newInstance(binding.itemType, size);
        objNewInstance.getClass();
        Object[] objArr = (Object[]) objNewInstance;
        for (int i = 0; i < size; i++) {
            Object objDeserializeItem$intellij_platform_util = binding.deserializeItem$intellij_platform_util(elements.get(i), adapter, currentValue);
            objDeserializeItem$intellij_platform_util.getClass();
            objArr[i] = objDeserializeItem$intellij_platform_util;
        }
        return objArr;
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof ArrayStrategy);
    }

    @Override // com.intellij.util.xmlb.CollectionStrategy
    public String getCollectionTagName(Object target) {
        return "array";
    }

    public int hashCode() {
        return -488452981;
    }

    public String toString() {
        return "ArrayStrategy";
    }
}
