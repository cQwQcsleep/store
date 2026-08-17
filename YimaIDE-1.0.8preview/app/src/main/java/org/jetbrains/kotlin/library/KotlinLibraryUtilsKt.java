package org.jetbrains.kotlin.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"toUnresolvedLibraries", "", "Lorg/jetbrains/kotlin/library/RequiredUnresolvedLibrary;", "", "getToUnresolvedLibraries", "(Ljava/util/List;)Ljava/util/List;", "kotlin-util-klib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KotlinLibraryUtilsKt {
    public static final List<RequiredUnresolvedLibrary> getToUnresolvedLibraries(List<String> list) {
        list.getClass();
        List<String> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new RequiredUnresolvedLibrary((String) it.next()));
        }
        return arrayList;
    }
}
