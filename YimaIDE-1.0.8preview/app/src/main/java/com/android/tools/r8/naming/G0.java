package com.android.tools.r8.naming;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class G0 extends RuntimeException {
    public final int b;
    public final int c;
    public final boolean d;
    public final String e;

    public G0(H0 h0, String str, boolean z) {
        super(str);
        this.b = h0.i;
        this.c = h0.j;
        this.d = z;
        this.e = str;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        boolean z = this.d;
        int i = this.b;
        if (z) {
            return "Parse error [" + i + ":eol] " + this.e;
        }
        return "Parse error [" + i + ":" + this.c + "] " + this.e;
    }

    public G0(H0 h0, String str) {
        this(h0, str, false);
    }
}
