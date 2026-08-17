package com.intellij.util.xmlb;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H&J@\u0010\u0005\u001a\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000b2\u0006\u0010\f\u001a\u00020\rH&J \u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH&J$\u0010\u0011\u001a\u00020\u00012\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0016\u0082\u0001\u0002\u0015\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lcom/intellij/util/xmlb/CollectionStrategy;", "", "getCollectionTagName", "", "target", "deserializeList", "T", "currentValue", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "binding", "Lcom/intellij/util/xmlb/CollectionBinding;", "getCollection", "", "bean", "transformJsonValue", "value", "itemType", "Ljava/lang/Class;", "Lcom/intellij/util/xmlb/ArrayStrategy;", "Lcom/intellij/util/xmlb/CollectionStrategyImpl;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CollectionStrategy {
    <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter, CollectionBinding binding);

    String getCollectionTagName(Object target);
}
