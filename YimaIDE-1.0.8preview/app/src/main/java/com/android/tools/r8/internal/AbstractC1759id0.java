package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.id0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1759id0 implements Iterator {
    public String c;
    public final CharSequence d;
    public final AbstractC2352pb e;
    public final boolean f;
    public int h;
    public int b = 2;
    public int g = 0;

    public AbstractC1759id0(C1928kd0 c1928kd0, CharSequence charSequence) {
        this.e = c1928kd0.a;
        this.f = c1928kd0.b;
        this.h = c1928kd0.d;
        this.d = charSequence;
    }

    public abstract int a(int i);

    public abstract int b(int i);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String string;
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
            int i2 = this.g;
            while (true) {
                int i3 = this.g;
                if (i3 == -1) {
                    this.b = 3;
                    string = null;
                    break;
                }
                int iB2 = b(i3);
                if (iB2 == -1) {
                    iB2 = this.d.length();
                    this.g = -1;
                } else {
                    this.g = a(iB2);
                }
                int i4 = this.g;
                if (i4 == i2) {
                    int i5 = i4 + 1;
                    this.g = i5;
                    if (i5 > this.d.length()) {
                        this.g = -1;
                    }
                } else {
                    while (i2 < iB2 && this.e.b(this.d.charAt(i2))) {
                        i2++;
                    }
                    while (iB2 > i2 && this.e.b(this.d.charAt(iB2 - 1))) {
                        iB2--;
                    }
                    if (!this.f || i2 != iB2) {
                        int i6 = this.h;
                        if (i6 == 1) {
                            iB2 = this.d.length();
                            this.g = -1;
                            while (iB2 > i2 && this.e.b(this.d.charAt(iB2 - 1))) {
                                iB2--;
                            }
                        } else {
                            this.h = i6 - 1;
                        }
                        string = this.d.subSequence(i2, iB2).toString();
                        break;
                    }
                    i2 = this.g;
                }
            }
            this.c = string;
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
        String str = this.c;
        this.c = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
