package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class G0 {
    public static final AbstractC0706Nu b = AbstractC0706Nu.e().a("public", 1).a("private", 2).a("protected", 4).a("final", 16).a("abstract", Integer.valueOf(Fcntl.S_ISGID)).a("static", 8).a();
    public final com.android.tools.r8.graph.B1 a;

    public G0(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }

    public final int a(String[] strArr) {
        int i = 0;
        while (true) {
            AbstractC0706Nu abstractC0706Nu = b;
            if (!abstractC0706Nu.containsKey(strArr[i])) {
                return i;
            }
            a(((Integer) abstractC0706Nu.get(strArr[i])).intValue());
            i++;
        }
    }

    public abstract void a(int i);

    public com.android.tools.r8.graph.I2 a(String str) {
        return this.a.e(C0929Wj.I(str));
    }
}
