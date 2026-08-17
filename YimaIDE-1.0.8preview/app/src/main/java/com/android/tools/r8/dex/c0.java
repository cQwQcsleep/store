package com.android.tools.r8.dex;

import com.android.tools.r8.DataResource;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class c0 extends e0 {
    public c0(f0 f0Var, String str) {
        super(f0Var, str);
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean a(int i, int i2) {
        if (!a(i2) && this.a.charAt(i2) != '/') {
            return c(i, i2);
        }
        String strSubstring = this.a.substring(i, i2);
        if (b() != '/') {
            strSubstring = strSubstring.replace(b(), DataResource.SEPARATOR);
        }
        String strA = this.g.d.a(this.g.a.A().a(strSubstring));
        if (strSubstring.equals(strA)) {
            return false;
        }
        int i3 = this.d;
        if (i3 < i) {
            this.b.append((CharSequence) this.a, i3, i);
        }
        if (b() != '/') {
            strA = strA.replace(DataResource.SEPARATOR, b());
        }
        this.b.append(strA);
        this.d = i2;
        this.c = true;
        return true;
    }

    @Override // com.android.tools.r8.dex.e0
    public char b() {
        return DataResource.SEPARATOR;
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean a(char c) {
        return !Character.isLetterOrDigit(c);
    }

    @Override // com.android.tools.r8.dex.e0
    public boolean a() {
        return !(this instanceof d0);
    }
}
