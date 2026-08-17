package org.jetbrains.kotlin.js.translate.utils;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aH\u0010\u0000\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00040\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006¨\u0006\u0007"}, d2 = {"splitToRanges", "", "Lkotlin/Pair;", "T", "S", "classifier", "Lkotlin/Function1;", "org.jetbrains.kotlin:js.translator"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UtilsKt {
    public static final <T, S> List<Pair<List<T>, S>> splitToRanges(List<? extends T> list, Function1<? super T, ? extends S> function1) {
        list.getClass();
        function1.getClass();
        if (list.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        int i = 0;
        Object objInvoke = function1.invoke(list.get(0));
        ArrayList arrayList = new ArrayList();
        for (IndexedValue indexedValue : SequencesKt.drop(SequencesKt.withIndex(CollectionsKt.asSequence(list)), 1)) {
            int iComponent1 = indexedValue.component1();
            Object objInvoke2 = function1.invoke(indexedValue.component2());
            if (!Intrinsics.areEqual(objInvoke2, objInvoke)) {
                arrayList.add(new Pair(list.subList(i, iComponent1), objInvoke));
                objInvoke = objInvoke2;
                i = iComponent1;
            }
        }
        arrayList.add(new Pair(list.subList(i, list.size()), objInvoke));
        return arrayList;
    }
}
