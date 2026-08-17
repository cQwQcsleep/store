package com.android.tools.r8.ir.desugar.desugaredlibrary.lint;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class i {
    public final String a = System.lineSeparator();
    public final StringBuilder b = new StringBuilder();
    public String c = XmlPullParser.NO_NAMESPACE;

    public final void a(String str) {
        this.b.append(str);
    }

    public final void b(String str) {
        this.b.append(this.c);
        this.b.append(str);
        this.b.append(this.a);
    }

    public final void c(String str) {
        this.b.append(str);
        this.b.append(this.a);
    }

    public final void d(String str) {
        this.b.append(this.c);
        this.b.append(str);
    }

    public final String toString() {
        return this.b.toString();
    }
}
