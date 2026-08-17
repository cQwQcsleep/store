package com.android.tools.r8.dex;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0919Vz;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.naming.AbstractC3345r0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class e0 {
    public static final /* synthetic */ boolean h = true;
    public final String a;
    public final StringBuilder b = new StringBuilder();
    public boolean c = false;
    public int d = 0;
    public int e = 0;
    public final C0919Vz f;
    public final /* synthetic */ f0 g;

    public e0(f0 f0Var, String str) {
        this.g = f0Var;
        this.a = str;
        this.f = a() ? new C0919Vz(16) : null;
    }

    public abstract boolean a();

    public abstract boolean a(char c);

    public final boolean a(int i) {
        return i == this.a.length();
    }

    public abstract boolean a(int i, int i2);

    public abstract char b();

    public abstract boolean b(int i, int i2);

    public final boolean c(int i, int i2) {
        String strSubstring = this.a.substring(i, i2);
        if (b() != '.') {
            strSubstring = strSubstring.replace(b(), '.');
        }
        H2 h2 = (H2) this.g.b.c.get(new H2(C0929Wj.J(strSubstring)));
        I2 i3 = h2 != null ? (I2) this.g.b.d.get(h2) : null;
        if (i3 == null) {
            return false;
        }
        f0 f0Var = this.g;
        AbstractC3345r0 abstractC3345r0 = f0Var.d;
        AbstractC3148ys abstractC3148ys = f0Var.c;
        abstractC3148ys.getClass();
        H2 h2C = abstractC3345r0.c(abstractC3148ys.c(AbstractC3148ys.g(), i3));
        if (h2.equals(h2C)) {
            return false;
        }
        String strB = C0929Wj.b(h2C.m0());
        int i4 = this.d;
        if (i4 < i) {
            this.b.append((CharSequence) this.a, i4, i);
        }
        if (b() != '.') {
            strB = strB.replace('.', b());
        }
        this.b.append(strB);
        this.d = i2;
        this.c = true;
        return true;
    }

    public final boolean d() {
        while (true) {
            if (a(this.e) || Character.isJavaIdentifierPart(this.a.charAt(this.e))) {
                if (!a(this.e)) {
                    boolean z = h;
                    if (!z && a() && !this.f.isEmpty()) {
                        x1f.a();
                        return false;
                    }
                    if (!z && !Character.isJavaIdentifierPart(this.a.charAt(this.e))) {
                        x1f.a();
                        return false;
                    }
                    int i = this.e;
                    this.e = i + 1;
                    while (!a(this.e)) {
                        char cCharAt = this.a.charAt(this.e);
                        if (Character.isJavaIdentifierPart(cCharAt)) {
                            if (a() && a(cCharAt) && b(i, this.e)) {
                                this.f.add(this.e);
                            }
                            this.e++;
                        } else {
                            if (cCharAt != b() || a(this.e + 1) || !Character.isJavaIdentifierPart(this.a.charAt(this.e + 1))) {
                                break;
                            }
                            if (a() && a(cCharAt) && b(i, this.e)) {
                                this.f.add(this.e);
                            }
                            this.e += 2;
                        }
                    }
                    if (a() && a(this.e) && b(i, this.e)) {
                        this.f.add(this.e);
                    }
                    boolean zA = b(i, this.e) && c(i, this.e);
                    if (!zA && a()) {
                        while (!this.f.isEmpty() && !zA) {
                            C0919Vz c0919Vz = this.f;
                            if (c0919Vz.isEmpty()) {
                                z0e.a();
                                return false;
                            }
                            int iH = c0919Vz.h(c0919Vz.size() - 1);
                            if (!h && !b(i, iH)) {
                                x1f.a();
                                return false;
                            }
                            zA = a(i, iH);
                        }
                    }
                    if (a()) {
                        while (!this.f.isEmpty()) {
                            C0919Vz c0919Vz2 = this.f;
                            if (c0919Vz2.isEmpty()) {
                                z0e.a();
                                return false;
                            }
                            c0919Vz2.h(c0919Vz2.size() - 1);
                        }
                    }
                }
                if (a(this.e)) {
                    if (this.c) {
                        int i2 = this.d;
                        int length = this.a.length();
                        if (i2 < length) {
                            this.b.append((CharSequence) this.a, i2, length);
                        }
                    } else {
                        boolean z2 = h;
                        if (!z2 && this.d != 0) {
                            x1f.a();
                            return false;
                        }
                        if (!z2 && !this.b.toString().isEmpty()) {
                            x1f.a();
                            return false;
                        }
                    }
                    return this.c;
                }
            } else {
                this.e++;
            }
        }
    }

    public final String c() {
        if (h || this.c) {
            return this.b.toString();
        }
        x1f.a();
        return null;
    }
}
