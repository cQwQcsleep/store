package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3033xZ extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public List e = Collections.EMPTY_LIST;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C3033xZ a(C3117yZ c3117yZ) {
        if (c3117yZ == C3117yZ.h) {
            return this;
        }
        if ((c3117yZ.c & 1) == 1) {
            int i = c3117yZ.d;
            this.c = 1 | this.c;
            this.d = i;
        }
        if (!c3117yZ.e.isEmpty()) {
            if (this.e.isEmpty()) {
                this.e = c3117yZ.e;
                this.c &= -3;
            } else {
                if ((this.c & 2) != 2) {
                    this.e = new ArrayList(this.e);
                    this.c |= 2;
                }
                this.e.addAll(c3117yZ.e);
            }
        }
        this.b = this.b.a(c3117yZ.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C3117yZ c3117yZE = e();
        if (c3117yZE.a()) {
            return c3117yZE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C3033xZ().a(e());
    }

    public final C3117yZ e() {
        C3117yZ c3117yZ = new C3117yZ(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c3117yZ.d = this.d;
        if ((i & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
            this.c &= -3;
        }
        c3117yZ.e = this.e;
        c3117yZ.c = i2;
        return c3117yZ;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001a  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C3117yZ c3117yZ = null;
        try {
            try {
                C3117yZ.i.getClass();
                a(C2349pZ.b(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C3117yZ c3117yZ2 = (C3117yZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c3117yZ = c3117yZ2;
                    if (c3117yZ != null) {
                        a(c3117yZ);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3117yZ != null) {
                a(c3117yZ);
            }
            throw th;
        }
    }
}
