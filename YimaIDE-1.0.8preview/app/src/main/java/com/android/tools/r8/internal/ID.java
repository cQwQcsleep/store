package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ID extends AbstractC0574Ir implements UN {
    public int c;
    public List d;
    public List e;
    public InterfaceC3186zJ f;
    public C2305p00 g;
    public C2048m00 h;
    public List i;
    public List j;

    public ID() {
        List list = Collections.EMPTY_LIST;
        this.d = list;
        this.e = list;
        this.f = C3017xJ.c;
        this.g = C2305p00.f;
        this.h = C2048m00.f;
        this.i = list;
        this.j = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final ID a(JD jd) {
        C2048m00 c2048m00;
        C2305p00 c2305p00;
        if (jd == JD.m) {
            return this;
        }
        if (!jd.d.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = jd.d;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(jd.d);
            }
        }
        if (!jd.e.isEmpty()) {
            if (this.e.isEmpty()) {
                this.e = jd.e;
                this.c &= -3;
            } else {
                if ((this.c & 2) != 2) {
                    this.e = new ArrayList(this.e);
                    this.c |= 2;
                }
                this.e.addAll(jd.e);
            }
        }
        if (!jd.f.isEmpty()) {
            if (this.f.isEmpty()) {
                this.f = jd.f;
                this.c &= -5;
            } else {
                if ((this.c & 4) != 4) {
                    this.f = new C3017xJ(this.f);
                    this.c |= 4;
                }
                this.f.addAll(jd.f);
            }
        }
        if ((jd.c & 1) == 1) {
            C2305p00 c2305p01 = jd.g;
            if ((this.c & 8) != 8 || (c2305p00 = this.g) == C2305p00.f) {
                this.g = c2305p01;
            } else {
                this.g = new C2219o00().a(c2305p00).a(c2305p01).e();
            }
            this.c |= 8;
        }
        if ((jd.c & 2) == 2) {
            C2048m00 c2048m01 = jd.h;
            if ((this.c & 16) != 16 || (c2048m00 = this.h) == C2048m00.f) {
                this.h = c2048m01;
            } else {
                this.h = new C1621h00().a(c2048m00).a(c2048m01).e();
            }
            this.c |= 16;
        }
        if (!jd.i.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = jd.i;
                this.c &= -33;
            } else {
                if ((this.c & 32) != 32) {
                    this.i = new ArrayList(this.i);
                    this.c |= 32;
                }
                this.i.addAll(jd.i);
            }
        }
        if (!jd.j.isEmpty()) {
            if (this.j.isEmpty()) {
                this.j = jd.j;
                this.c &= -65;
            } else {
                if ((this.c & 64) != 64) {
                    this.j = new ArrayList(this.j);
                    this.c |= 64;
                }
                this.j.addAll(jd.j);
            }
        }
        this.b = this.b.a(jd.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        JD jdE = e();
        if (jdE.a()) {
            return jdE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new ID().a(e());
    }

    public final JD e() {
        JD jd = new JD(this);
        int i = this.c;
        if ((i & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        jd.d = this.d;
        if ((this.c & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
            this.c &= -3;
        }
        jd.e = this.e;
        if ((this.c & 4) == 4) {
            this.f = this.f.f();
            this.c &= -5;
        }
        jd.f = this.f;
        int i2 = (i & 8) != 8 ? 0 : 1;
        jd.g = this.g;
        if ((i & 16) == 16) {
            i2 |= 2;
        }
        jd.h = this.h;
        if ((this.c & 32) == 32) {
            this.i = Collections.unmodifiableList(this.i);
            this.c &= -33;
        }
        jd.i = this.i;
        if ((this.c & 64) == 64) {
            this.j = Collections.unmodifiableList(this.j);
            this.c &= -65;
        }
        jd.j = this.j;
        jd.c = i2;
        return jd;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        JD jd = null;
        try {
            try {
                JD.n.getClass();
                a(new JD(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                JD jd2 = (JD) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    jd = jd2;
                    if (jd != null) {
                        a(jd);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (jd != null) {
                a(jd);
            }
            throw th;
        }
    }
}
