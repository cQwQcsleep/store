package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1156bZ extends AbstractC1323dZ {
    public final InputStream i;
    public final byte[] j;
    public int k;
    public int l;
    public int m;
    public int n;

    public C1156bZ(InputStream inputStream, Predicate predicate, boolean z) {
        super(predicate, z);
        this.j = new byte[8192];
        this.k = 8192;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.i = inputStream;
    }

    @Override // com.android.tools.r8.internal.AbstractC1323dZ
    public final boolean b() {
        return this.n == this.m;
    }

    @Override // com.android.tools.r8.internal.AbstractC1323dZ
    public final int c() {
        int i = this.m;
        if (i > 0) {
            int i2 = i - 1;
            if (this.j[i2] == 13) {
                return i2;
            }
        }
        return i;
    }

    @Override // com.android.tools.r8.naming.P
    public final void close() throws IOException {
        this.i.close();
    }

    @Override // com.android.tools.r8.internal.AbstractC1323dZ
    public final int d() {
        return this.l;
    }

    @Override // com.android.tools.r8.internal.AbstractC1323dZ
    public final byte[] e() throws IOException {
        int i;
        boolean z = false;
        if (this.k >= this.n) {
            int i2 = this.i.read(this.j);
            this.n = i2;
            if (i2 == -1) {
                return null;
            }
            this.k = 0;
        }
        int i3 = this.k;
        this.l = i3;
        this.m = i3;
        while (true) {
            i = this.m;
            if (i >= this.n) {
                break;
            }
            if (this.j[i] == 10) {
                z = true;
                break;
            }
            this.m = i + 1;
        }
        this.k = i;
        if (z) {
            this.k = i + 1;
        }
        return this.j;
    }
}
