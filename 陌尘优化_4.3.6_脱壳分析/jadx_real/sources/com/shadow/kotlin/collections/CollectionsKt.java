package com.shadow.kotlin.collections;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.functions.Function1;
import com.shadow.kotlin.text.StringsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class CollectionsKt extends CollectionsKt__MutableCollectionsKt {
    public static void a(Collection collection, Iterable iterable) {
        CloseableKt.checkNotNullParameter(collection, "<this>");
        CloseableKt.checkNotNullParameter(iterable, "elements");
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static int b(Iterable iterable) {
        CloseableKt.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }

    public static String c(Collection collection, String str, String str2, Function1 function1, int i) {
        if ((i & 32) != 0) {
            function1 = null;
        }
        Function1 function12 = function1;
        StringBuilder sb = new StringBuilder();
        joinTo(collection, sb, ", ", str, str2, -1, "...", function12);
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static List d(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        CloseableKt.checkNotNullExpressionValue(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static ArrayList e(Collection collection, Iterable iterable) {
        CloseableKt.checkNotNullParameter(collection, "<this>");
        CloseableKt.checkNotNullParameter(iterable, "elements");
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            a(arrayList, iterable);
            return arrayList;
        }
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection2.size() + collection.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    public static void f(ArrayList arrayList) {
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
    }

    public static List g(Iterable iterable) {
        ArrayList arrayList;
        CloseableKt.checkNotNullParameter(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (!z) {
            if (z) {
                arrayList = new ArrayList((Collection) iterable);
            } else {
                arrayList = new ArrayList();
                toCollection(iterable, arrayList);
            }
            int size = arrayList.size();
            return size != 0 ? size != 1 ? arrayList : d(arrayList.get(0)) : EmptyList.INSTANCE;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return EmptyList.INSTANCE;
        }
        if (size2 != 1) {
            return new ArrayList(collection);
        }
        return d(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final void joinTo(Collection collection, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1) {
        CloseableKt.checkNotNullParameter(charSequence, "separator");
        CloseableKt.checkNotNullParameter(charSequence2, "prefix");
        CloseableKt.checkNotNullParameter(charSequence3, "postfix");
        CloseableKt.checkNotNullParameter(charSequence4, "truncated");
        sb.append(charSequence2);
        int i2 = 0;
        for (Object obj : collection) {
            i2++;
            if (i2 > 1) {
                sb.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            } else {
                StringsKt.b(sb, obj, function1);
            }
        }
        if (i >= 0 && i2 > i) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static final void toCollection(Iterable iterable, java.util.AbstractCollection abstractCollection) {
        CloseableKt.checkNotNullParameter(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }
}
