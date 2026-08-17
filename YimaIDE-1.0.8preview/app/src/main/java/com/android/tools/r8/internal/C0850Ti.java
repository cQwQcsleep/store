package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ti, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0850Ti extends AbstractC0963Xr {
    public int g;
    public boolean h;
    public List i;
    public C2401q50 j;

    public C0850Ti() {
        this.i = Collections.EMPTY_LIST;
    }

    public final C0850Ti a(C0876Ui c0876Ui) {
        if (c0876Ui == C0876Ui.j) {
            return this;
        }
        if ((c0876Ui.f & 1) != 0) {
            boolean z = c0876Ui.g;
            this.g |= 1;
            this.h = z;
            p();
        }
        C2401q50 c2401q50 = this.j;
        List list = c0876Ui.h;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.i.isEmpty()) {
                    this.i = c0876Ui.h;
                    this.g &= -3;
                } else {
                    if ((this.g & 2) == 0) {
                        this.i = new ArrayList(this.i);
                        this.g |= 2;
                    }
                    this.i.addAll(c0876Ui.h);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.j.b.isEmpty();
            C2401q50 c2401q51 = this.j;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.i = c0876Ui.h;
                this.g &= -3;
                this.j = null;
            } else {
                c2401q51.a(c0876Ui.h);
            }
        }
        a((AbstractC1102as) c0876Ui);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0876Ui) {
            return a((C0876Ui) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0876Ui c0876UiI = i();
        if (c0876UiI.a()) {
            return c0876UiI;
        }
        throw H0.c(c0876UiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0850Ti a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0876Ui c0876Ui = null;
        try {
            try {
                a((C0876Ui) C0876Ui.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0876Ui c0876Ui2 = (C0876Ui) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0876Ui = c0876Ui2;
                    if (c0876Ui != null) {
                        a(c0876Ui);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0876Ui != null) {
                a(c0876Ui);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0850Ti) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.I;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0876Ui c0876UiI = i();
        if (c0876UiI.a()) {
            return c0876UiI;
        }
        throw H0.c(c0876UiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.J.a(C0876Ui.class, C0850Ti.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0876Ui i() {
        int i;
        C0876Ui c0876Ui = new C0876Ui(this);
        int i2 = this.g;
        if ((i2 & 1) != 0) {
            c0876Ui.g = this.h;
            i = 1;
        } else {
            i = 0;
        }
        C2401q50 c2401q50 = this.j;
        if (c2401q50 == null) {
            if ((i2 & 2) != 0) {
                this.i = Collections.unmodifiableList(this.i);
                this.g &= -3;
            }
            c0876Ui.h = this.i;
        } else {
            c0876Ui.h = c2401q50.b();
        }
        c0876Ui.f = i;
        o();
        return c0876Ui;
    }

    public C0850Ti(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.i = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C0850Ti) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0876Ui.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C0850Ti) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0876Ui) {
            return a((C0876Ui) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0850Ti) c(c2712tk0);
    }
}
