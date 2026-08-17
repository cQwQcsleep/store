package com.android.tools.r8.internal;

import java.io.BufferedWriter;
import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2828v6 {
    public static final /* synthetic */ boolean c = true;
    public final int a;
    public final int b;

    public C2828v6(String str) {
        int iIndexOf = str.indexOf(32);
        int i = Integer.parseInt(str.substring(0, iIndexOf).trim());
        this.a = i;
        int i2 = Integer.parseInt(str.substring(iIndexOf + 1).trim());
        this.b = i2;
        if (c || i <= i2) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final C2828v6 a(C2828v6 c2828v6) {
        if (a()) {
            return c2828v6;
        }
        if (c2828v6.a()) {
            return this;
        }
        if (c || this.a == c2828v6.b || this.b == c2828v6.a) {
            return new C2828v6(Integer.min(this.a, c2828v6.a), Integer.max(this.b, c2828v6.b));
        }
        x1f.a();
        return null;
    }

    public final int b() {
        return this.b - this.a;
    }

    public final C2828v6 c() {
        int i = this.b;
        int i2 = this.a;
        return new C2828v6(i2, ((i - i2) / 2) + i2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2828v6)) {
            return false;
        }
        C2828v6 c2828v6 = (C2828v6) obj;
        return this.a == c2828v6.a && this.b == c2828v6.b;
    }

    public final int hashCode() {
        return (this.a * 31) + this.b;
    }

    public final String toString() {
        return "[" + this.a + ";" + this.b + "]";
    }

    public C2828v6(int i, int i2) {
        this.a = i;
        this.b = i2;
        if (c || i <= i2) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final boolean a() {
        return this.a == this.b;
    }

    public final void a(BufferedWriter bufferedWriter) throws IOException {
        int i = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        bufferedWriter.write(sb.toString());
        bufferedWriter.write(" ");
        int i2 = this.b;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i2);
        bufferedWriter.write(sb2.toString());
    }
}
