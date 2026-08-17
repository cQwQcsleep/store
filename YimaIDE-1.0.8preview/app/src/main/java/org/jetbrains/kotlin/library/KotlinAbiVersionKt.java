package org.jetbrains.kotlin.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"parseKotlinAbiVersion", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "", "kotlin-util-klib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KotlinAbiVersionKt {
    public static final KotlinAbiVersion parseKotlinAbiVersion(String str) {
        str.getClass();
        List listSplit$default = StringsKt.split$default(str, new String[]{"."}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(Integer.parseInt((String) it.next())));
        }
        int size = arrayList.size();
        if (size == 1) {
            return new KotlinAbiVersion(((Number) arrayList.get(0)).intValue());
        }
        if (size == 3) {
            return new KotlinAbiVersion(((Number) arrayList.get(0)).intValue(), ((Number) arrayList.get(1)).intValue(), ((Number) arrayList.get(2)).intValue());
        }
        w04.a("Could not parse abi version: ", str);
        return null;
    }
}
