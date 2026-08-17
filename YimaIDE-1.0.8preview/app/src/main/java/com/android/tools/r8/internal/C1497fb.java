package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1497fb extends AbstractC2131n implements Serializable {
    public transient char[] b = new char[3];
    public int c;

    public final boolean a(char c) {
        int i;
        int i2 = this.c;
        while (true) {
            i = i2 - 1;
            if (i2 == 0) {
                i = -1;
                break;
            }
            if (this.b[i] == c) {
                break;
            }
            i2 = i;
        }
        if (i != -1) {
            return false;
        }
        int i3 = this.c;
        if (i3 == this.b.length) {
            char[] cArr = new char[i3 == 0 ? 2 : i3 * 2];
            while (true) {
                int i4 = i3 - 1;
                if (i3 == 0) {
                    break;
                }
                cArr[i4] = this.b[i4];
                i3 = i4;
            }
            this.b = cArr;
        }
        char[] cArr2 = this.b;
        int i5 = this.c;
        this.c = i5 + 1;
        cArr2[i5] = c;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c = 0;
    }

    public final Object clone() {
        try {
            C1497fb c1497fb = (C1497fb) super.clone();
            c1497fb.b = (char[]) this.b.clone();
            return c1497fb;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1411eb(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c;
    }
}
