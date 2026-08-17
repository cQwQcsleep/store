package com.android.tools.r8.internal;

import java.util.LinkedHashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kf0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1932kf0 {
    public static final /* synthetic */ boolean d = true;
    public final LinkedHashSet a = new LinkedHashSet();
    public final LinkedHashSet b = new LinkedHashSet();
    public boolean c = false;

    public Xe0 g() {
        return null;
    }

    public Ze0 h() {
        return null;
    }

    public C1082af0 i() {
        return null;
    }

    public InterfaceC1168bf0 j() {
        return null;
    }

    public C1506ff0 k() {
        return null;
    }

    public InterfaceC1762if0 l() {
        return null;
    }

    public final AbstractC1932kf0 m() {
        if (d || o()) {
            return (AbstractC1932kf0) this.b.iterator().next();
        }
        x1f.a();
        return null;
    }

    public final AbstractC1932kf0 n() {
        if (d || p()) {
            return (AbstractC1932kf0) this.a.iterator().next();
        }
        x1f.a();
        return null;
    }

    public final boolean o() {
        return this.b.size() == 1;
    }

    public final boolean p() {
        return this.a.size() == 1;
    }

    public boolean q() {
        return this instanceof C1082af0;
    }

    public boolean r() {
        return this instanceof Xe0;
    }

    public boolean s() {
        return this instanceof Xe0;
    }

    public final void t() {
        for (AbstractC1932kf0 abstractC1932kf0 : this.a) {
            abstractC1932kf0.b.remove(this);
            abstractC1932kf0.b.addAll(this.b);
        }
        for (AbstractC1932kf0 abstractC1932kf1 : this.b) {
            abstractC1932kf1.a.remove(this);
            abstractC1932kf1.a.addAll(this.a);
        }
        this.c = true;
    }
}
