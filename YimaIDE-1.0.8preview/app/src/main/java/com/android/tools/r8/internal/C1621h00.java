package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1621h00 extends AbstractC0574Ir implements UN {
    public int c;
    public List d = Collections.EMPTY_LIST;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1621h00 a(C2048m00 c2048m00) {
        if (c2048m00 == C2048m00.f) {
            return this;
        }
        if (!c2048m00.c.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = c2048m00.c;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(c2048m00.c);
            }
        }
        this.b = this.b.a(c2048m00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C2048m00 c2048m00E = e();
        if (c2048m00E.a()) {
            return c2048m00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1621h00().a(e());
    }

    public final C2048m00 e() {
        C2048m00 c2048m00 = new C2048m00(this);
        if ((this.c & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        c2048m00.c = this.d;
        return c2048m00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2048m00 c2048m00 = null;
        try {
            try {
                C2048m00.g.getClass();
                a(new C2048m00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C2048m00 c2048m01 = (C2048m00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2048m00 = c2048m01;
                    if (c2048m00 != null) {
                        a(c2048m00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2048m00 != null) {
                a(c2048m00);
            }
            throw th;
        }
    }
}
