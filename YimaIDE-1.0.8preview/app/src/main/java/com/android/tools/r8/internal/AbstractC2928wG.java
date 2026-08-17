package com.android.tools.r8.internal;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2928wG {
    public final HashSet a;
    public final HashSet b;
    public AbstractC1484fP c;
    public AbstractC1484fP d;
    public AbstractC1484fP e;

    public AbstractC2928wG() {
        X1 x1 = X1.b;
        this.a = new HashSet();
        this.b = new HashSet();
        C1230cP c1230cP = AbstractC1484fP.a;
        this.c = c1230cP;
        this.d = c1230cP;
        this.e = c1230cP;
    }

    public final Set a() {
        if (this.a.isEmpty() && this.b.isEmpty()) {
            return X1.f;
        }
        X1 x1 = X1.b;
        HashSet hashSet = new HashSet();
        if (this.a.isEmpty()) {
            hashSet.addAll(X1.f);
        } else {
            hashSet.addAll(this.a);
        }
        hashSet.removeAll(this.b);
        if (!hashSet.isEmpty()) {
            return hashSet;
        }
        defpackage.l0.a("Empty access visibility pattern will never match a member");
        return null;
    }

    public abstract AbstractC2928wG b();

    public final AbstractC2928wG a(C3014xG c3014xG) {
        this.a.clear();
        this.b.clear();
        this.a.addAll(c3014xG.a);
        this.c = c3014xG.b;
        this.d = c3014xG.c;
        return b();
    }
}
