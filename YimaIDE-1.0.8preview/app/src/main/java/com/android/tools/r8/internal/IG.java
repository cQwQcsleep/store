package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IG {
    public static final IG b = new IG(C1988lH.d);
    public static final IG c = new IG(C1988lH.a("<init>"));
    public static final IG d = new IG(C1988lH.a("<clinit>"));
    public static final /* synthetic */ boolean e = true;
    public final C1988lH a;

    public IG(C1988lH c1988lH) {
        if (e || c1988lH != null) {
            this.a = c1988lH;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static IG a(C1988lH c1988lH) {
        c1988lH.getClass();
        if (C1988lH.d == c1988lH) {
            return b;
        }
        String str = c1988lH.a;
        if (str != null) {
            if ("<init>".equals(str)) {
                return c;
            }
            if ("<clinit>".equals(str)) {
                return d;
            }
        }
        return new IG(c1988lH);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IG) {
            return this.a.equals(((IG) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
