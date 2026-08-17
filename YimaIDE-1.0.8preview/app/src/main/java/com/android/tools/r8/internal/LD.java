package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LD extends AbstractC0574Ir implements UN {
    public int c;
    public Object d = XmlPullParser.NO_NAMESPACE;
    public InterfaceC3186zJ e;
    public List f;
    public InterfaceC3186zJ g;
    public InterfaceC3186zJ h;
    public List i;
    public List j;

    public LD() {
        Hk0 hk0 = C3017xJ.c;
        this.e = hk0;
        List list = Collections.EMPTY_LIST;
        this.f = list;
        this.g = hk0;
        this.h = hk0;
        this.i = list;
        this.j = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final LD a(MD md) {
        if (md == MD.p) {
            return this;
        }
        if ((md.c & 1) == 1) {
            this.c |= 1;
            this.d = md.d;
        }
        if (!md.e.isEmpty()) {
            if (this.e.isEmpty()) {
                this.e = md.e;
                this.c &= -3;
            } else {
                if ((this.c & 2) != 2) {
                    this.e = new C3017xJ(this.e);
                    this.c |= 2;
                }
                this.e.addAll(md.e);
            }
        }
        if (!md.f.isEmpty()) {
            if (this.f.isEmpty()) {
                this.f = md.f;
                this.c &= -5;
            } else {
                if ((this.c & 4) != 4) {
                    this.f = new ArrayList(this.f);
                    this.c |= 4;
                }
                this.f.addAll(md.f);
            }
        }
        if (!md.h.isEmpty()) {
            if (this.g.isEmpty()) {
                this.g = md.h;
                this.c &= -9;
            } else {
                if ((this.c & 8) != 8) {
                    this.g = new C3017xJ(this.g);
                    this.c |= 8;
                }
                this.g.addAll(md.h);
            }
        }
        if (!md.i.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = md.i;
                this.c &= -17;
            } else {
                if ((this.c & 16) != 16) {
                    this.h = new C3017xJ(this.h);
                    this.c |= 16;
                }
                this.h.addAll(md.i);
            }
        }
        if (!md.j.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = md.j;
                this.c &= -33;
            } else {
                if ((this.c & 32) != 32) {
                    this.i = new ArrayList(this.i);
                    this.c |= 32;
                }
                this.i.addAll(md.j);
            }
        }
        if (!md.l.isEmpty()) {
            if (this.j.isEmpty()) {
                this.j = md.l;
                this.c &= -65;
            } else {
                if ((this.c & 64) != 64) {
                    this.j = new ArrayList(this.j);
                    this.c |= 64;
                }
                this.j.addAll(md.l);
            }
        }
        this.b = this.b.a(md.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        MD mdE = e();
        if (mdE.a()) {
            return mdE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new LD().a(e());
    }

    public final MD e() {
        MD md = new MD(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        md.d = this.d;
        if ((i & 2) == 2) {
            this.e = this.e.f();
            this.c &= -3;
        }
        md.e = this.e;
        if ((this.c & 4) == 4) {
            this.f = Collections.unmodifiableList(this.f);
            this.c &= -5;
        }
        md.f = this.f;
        if ((this.c & 8) == 8) {
            this.g = this.g.f();
            this.c &= -9;
        }
        md.h = this.g;
        if ((this.c & 16) == 16) {
            this.h = this.h.f();
            this.c &= -17;
        }
        md.i = this.h;
        if ((this.c & 32) == 32) {
            this.i = Collections.unmodifiableList(this.i);
            this.c &= -33;
        }
        md.j = this.i;
        if ((this.c & 64) == 64) {
            this.j = Collections.unmodifiableList(this.j);
            this.c &= -65;
        }
        md.l = this.j;
        md.c = i2;
        return md;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        MD md = null;
        try {
            try {
                MD.q.getClass();
                a(new MD(c0638Ld));
                return this;
            } catch (QB e) {
                MD md2 = (MD) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    md = md2;
                    if (md != null) {
                        a(md);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (md != null) {
                a(md);
            }
            throw th;
        }
    }
}
