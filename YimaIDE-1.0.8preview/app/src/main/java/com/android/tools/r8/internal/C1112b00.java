package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1112b00 extends AbstractC0600Jr {
    public int e;
    public List f;
    public List g;
    public List h;
    public G00 i;
    public R00 j;

    public C1112b00() {
        List list = Collections.EMPTY_LIST;
        this.f = list;
        this.g = list;
        this.h = list;
        this.i = G00.h;
        this.j = R00.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1112b00 a(C1197c00 c1197c00) {
        R00 r00;
        G00 g00;
        if (c1197c00 == C1197c00.l) {
            return this;
        }
        if (!c1197c00.e.isEmpty()) {
            if (this.f.isEmpty()) {
                this.f = c1197c00.e;
                this.e &= -2;
            } else {
                if ((this.e & 1) != 1) {
                    this.f = new ArrayList(this.f);
                    this.e |= 1;
                }
                this.f.addAll(c1197c00.e);
            }
        }
        if (!c1197c00.f.isEmpty()) {
            if (this.g.isEmpty()) {
                this.g = c1197c00.f;
                this.e &= -3;
            } else {
                if ((this.e & 2) != 2) {
                    this.g = new ArrayList(this.g);
                    this.e |= 2;
                }
                this.g.addAll(c1197c00.f);
            }
        }
        if (!c1197c00.g.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = c1197c00.g;
                this.e &= -5;
            } else {
                if ((this.e & 4) != 4) {
                    this.h = new ArrayList(this.h);
                    this.e |= 4;
                }
                this.h.addAll(c1197c00.g);
            }
        }
        if ((c1197c00.d & 1) == 1) {
            G00 g01 = c1197c00.h;
            if ((this.e & 8) != 8 || (g00 = this.i) == G00.h) {
                this.i = g01;
            } else {
                this.i = G00.a(g00).a(g01).e();
            }
            this.e |= 8;
        }
        if ((c1197c00.d & 2) == 2) {
            R00 r01 = c1197c00.i;
            if ((this.e & 16) != 16 || (r00 = this.j) == R00.f) {
                this.j = r01;
            } else {
                this.j = new Q00().a(r00).a(r01).e();
            }
            this.e |= 16;
        }
        a((Lr) c1197c00);
        this.b = this.b.a(c1197c00.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C1197c00 c1197c00E = e();
        if (c1197c00E.a()) {
            return c1197c00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1112b00().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return C1197c00.l;
    }

    public final C1197c00 e() {
        C1197c00 c1197c00 = new C1197c00(this);
        int i = this.e;
        if ((i & 1) == 1) {
            this.f = Collections.unmodifiableList(this.f);
            this.e &= -2;
        }
        c1197c00.e = this.f;
        if ((this.e & 2) == 2) {
            this.g = Collections.unmodifiableList(this.g);
            this.e &= -3;
        }
        c1197c00.f = this.g;
        if ((this.e & 4) == 4) {
            this.h = Collections.unmodifiableList(this.h);
            this.e &= -5;
        }
        c1197c00.g = this.h;
        int i2 = (i & 8) != 8 ? 0 : 1;
        c1197c00.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 2;
        }
        c1197c00.i = this.j;
        c1197c00.d = i2;
        return c1197c00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C1197c00 c1197c00 = null;
        try {
            try {
                C1197c00.m.getClass();
                a(new C1197c00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C1197c00 c1197c01 = (C1197c00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c1197c00 = c1197c01;
                    if (c1197c00 != null) {
                        a(c1197c00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1197c00 != null) {
                a(c1197c00);
            }
            throw th;
        }
    }
}
