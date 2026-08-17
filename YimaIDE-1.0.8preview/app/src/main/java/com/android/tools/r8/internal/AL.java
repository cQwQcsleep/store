package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AL {
    public static ArrayList a(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return new ArrayList((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        ArrayList arrayList = new ArrayList();
        NC.a(arrayList, it);
        return arrayList;
    }

    public static int a(int i) {
        AbstractC0871Ud.a(i, "arraySize");
        return MB.a(((long) i) + 5 + ((long) (i / 10)));
    }
}
