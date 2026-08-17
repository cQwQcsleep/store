package com.android.tools.r8.shaking;

import java.io.PrintStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class v4 {
    public final PrintStream a;
    public int b = -1;

    public v4(PrintStream printStream) {
        this.a = printStream;
    }

    public final void a() {
        for (int i = 0; i < this.b; i++) {
            this.a.print("  ");
        }
    }

    public final void a(String str) {
        a();
        this.a.print("|- ");
        this.a.println(str);
    }
}
