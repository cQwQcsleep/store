package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Rg0 {
    public final Appendable a;
    public final StringBuilder b = new StringBuilder();
    public boolean c = false;

    public Rg0(StringBuilder sb) {
        this.a = sb;
    }

    public final void a(CharSequence charSequence) throws IOException {
        if (this.c) {
            this.c = false;
            this.a.append(this.b);
        }
        this.a.append(charSequence);
    }

    public final void a() throws IOException {
        this.a.append("\n");
        this.c = true;
    }
}
