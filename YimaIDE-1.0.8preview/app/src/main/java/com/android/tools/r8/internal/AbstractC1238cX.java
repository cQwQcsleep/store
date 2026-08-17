package com.android.tools.r8.internal;

import defpackage.ikg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1238cX {
    public static final /* synthetic */ boolean a = true;

    public final void a(boolean z, int i, Collection collection) {
        if (!a && i < 2) {
            x1f.a();
            return;
        }
        int i2 = i - 1;
        Iterator it = collection.iterator();
        int size = 0;
        while (it.hasNext()) {
            C1868jt c1868jt = (C1868jt) it.next();
            if (!c1868jt.k()) {
                size += c1868jt.b.size() - 1;
            }
        }
        if (a || i2 >= size) {
            synchronized (this) {
            }
        } else {
            x1f.a();
        }
    }

    public OP b() {
        return null;
    }

    public AbstractC1757ic0 c() {
        return null;
    }

    public AbstractC1096am0 d() {
        return null;
    }

    public void e() {
    }

    public abstract String f();

    public boolean g() {
        return this instanceof C1186bq;
    }

    public boolean h() {
        return false;
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public static Collection a(Collection collection) {
        if (a || !(collection instanceof ArrayList)) {
            collection.removeIf(new ikg());
            return collection;
        }
        x1f.a();
        return null;
    }

    public NP a() {
        return null;
    }
}
