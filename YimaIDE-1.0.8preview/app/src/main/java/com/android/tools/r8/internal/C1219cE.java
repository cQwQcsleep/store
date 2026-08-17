package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1219cE extends AbstractC0574Ir implements UN {
    public int c;
    public int e;
    public List h;
    public List i;
    public int d = 1;
    public Object f = XmlPullParser.NO_NAMESPACE;
    public EnumC1303dE g = EnumC1303dE.c;

    public C1219cE() {
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.i = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1219cE a(C1387eE c1387eE) {
        if (c1387eE == C1387eE.n) {
            return this;
        }
        int i = c1387eE.c;
        if ((i & 1) == 1) {
            int i2 = c1387eE.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = c1387eE.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        if ((i & 4) == 4) {
            this.c |= 4;
            this.f = c1387eE.f;
        }
        if ((i & 8) == 8) {
            EnumC1303dE enumC1303dE = c1387eE.g;
            enumC1303dE.getClass();
            this.c = 8 | this.c;
            this.g = enumC1303dE;
        }
        if (!c1387eE.h.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = c1387eE.h;
                this.c &= -17;
            } else {
                if ((this.c & 16) != 16) {
                    this.h = new ArrayList(this.h);
                    this.c |= 16;
                }
                this.h.addAll(c1387eE.h);
            }
        }
        if (!c1387eE.j.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = c1387eE.j;
                this.c &= -33;
            } else {
                if ((this.c & 32) != 32) {
                    this.i = new ArrayList(this.i);
                    this.c |= 32;
                }
                this.i.addAll(c1387eE.j);
            }
        }
        this.b = this.b.a(c1387eE.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C1387eE c1387eEE = e();
        if (c1387eEE.a()) {
            return c1387eEE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1219cE().a(e());
    }

    public final C1387eE e() {
        C1387eE c1387eE = new C1387eE(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c1387eE.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c1387eE.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c1387eE.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        c1387eE.g = this.g;
        if ((i & 16) == 16) {
            this.h = Collections.unmodifiableList(this.h);
            this.c &= -17;
        }
        c1387eE.h = this.h;
        if ((this.c & 32) == 32) {
            this.i = Collections.unmodifiableList(this.i);
            this.c &= -33;
        }
        c1387eE.j = this.i;
        c1387eE.c = i2;
        return c1387eE;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C1387eE c1387eE = null;
        try {
            try {
                C1387eE.o.getClass();
                a(new C1387eE(c0638Ld));
                return this;
            } catch (QB e) {
                C1387eE c1387eE2 = (C1387eE) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c1387eE = c1387eE2;
                    if (c1387eE != null) {
                        a(c1387eE);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1387eE != null) {
                a(c1387eE);
            }
            throw th;
        }
    }
}
