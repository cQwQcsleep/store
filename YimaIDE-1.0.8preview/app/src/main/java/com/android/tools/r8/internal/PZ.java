package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PZ extends AbstractC0600Jr {
    public int e;
    public int f;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final PZ a(QZ qz) {
        if (qz == QZ.h) {
            return this;
        }
        if ((qz.d & 1) == 1) {
            int i = qz.e;
            this.e = 1 | this.e;
            this.f = i;
        }
        a((Lr) qz);
        this.b = this.b.a(qz.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        QZ qz = new QZ(this);
        int i = (this.e & 1) != 1 ? 0 : 1;
        qz.e = this.f;
        qz.d = i;
        if (qz.a()) {
            return qz;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        PZ pz = new PZ();
        QZ qz = new QZ(this);
        int i = (this.e & 1) != 1 ? 0 : 1;
        qz.e = this.f;
        qz.d = i;
        return pz.a(qz);
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return QZ.h;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        QZ qz = null;
        try {
            try {
                QZ.i.getClass();
                a(new QZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                QZ qz2 = (QZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    qz = qz2;
                    if (qz != null) {
                        a(qz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (qz != null) {
                a(qz);
            }
            throw th;
        }
    }
}
