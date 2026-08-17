package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1279d0 extends Ck0 {
    public int b = 2;
    public Object c;

    public abstract Object a();

    public final void b() {
        this.b = 3;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.b;
        if (i == 4) {
            g33.a();
            return false;
        }
        int iB = AbstractC0007c.b(i);
        if (iB == 0) {
            return true;
        }
        if (iB != 2) {
            this.b = 4;
            this.c = a();
            if (this.b != 3) {
                this.b = 1;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        this.b = 2;
        Object obj = this.c;
        this.c = null;
        return obj;
    }
}
