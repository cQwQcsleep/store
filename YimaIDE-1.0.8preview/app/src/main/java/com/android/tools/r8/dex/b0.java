package com.android.tools.r8.dex;

import com.android.tools.r8.internal.Kk0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class b0 extends e0 {
    public b0(f0 f0Var, String str) {
        super(f0Var, str);
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean a(char c) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean b(int i, int i2) {
        char cCharAt;
        char cCharAt2;
        if (i <= 0 || !((cCharAt2 = this.a.charAt(i - 1)) == '.' || cCharAt2 == '-')) {
            return a(i2) || !((cCharAt = this.a.charAt(i2)) == '.' || cCharAt == '-');
        }
        return false;
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean a() {
        return false;
    }

    @Override // com.android.tools.r8.dex.e0
    public final boolean a(int i, int i2) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.dex.e0
    public final char b() {
        return '.';
    }
}
