package com.android.tools.r8.internal;

import defpackage.v36;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U5 {
    public final Set a(Object obj) {
        Sm0 sm0 = new Sm0(1);
        sm0.b(obj);
        while (sm0.b()) {
            a(new v36(sm0), sm0.d());
        }
        return sm0.a();
    }

    public abstract void a(Consumer consumer);

    public abstract void a(Consumer consumer, Object obj);

    public final ArrayList a() {
        final HashSet hashSet = new HashSet();
        final ArrayList arrayList = new ArrayList();
        a(new Consumer() { // from class: bye
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(hashSet, arrayList, obj);
            }
        });
        return arrayList;
    }

    public final /* synthetic */ void a(Set set, List list, Object obj) {
        if (set.contains(obj)) {
            return;
        }
        Set setA = a(obj);
        list.add(setA);
        set.addAll(setA);
    }
}
