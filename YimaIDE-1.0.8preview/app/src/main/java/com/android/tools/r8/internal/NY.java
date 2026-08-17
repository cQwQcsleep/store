package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0346z5;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NY implements Iterable {
    public static final NY c = new NY(T40.i);
    public static final /* synthetic */ boolean d = true;
    public final Map b;

    public NY(Map map) {
        this.b = map;
    }

    public final boolean a(C0346z5 c0346z5) {
        C0346z5 c0346z6 = (C0346z5) this.b.put(c0346z5.getReference(), c0346z5);
        if (d || c0346z6 == null || (c0346z6.e() == c0346z5.e() && c0346z6.a() == c0346z5.a())) {
            return c0346z6 == null;
        }
        x1f.a();
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.b.values().iterator();
    }

    public final void a(Predicate predicate) {
        this.b.values().removeIf(predicate);
    }
}
