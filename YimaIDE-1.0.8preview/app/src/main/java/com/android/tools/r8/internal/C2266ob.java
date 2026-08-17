package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ob, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2266ob extends AbstractC2009lb {
    public static final int c = Integer.numberOfLeadingZeros(31);
    public static final C2266ob d = new C2266ob();

    public C2266ob() {
        super("CharMatcher.whitespace()");
    }

    @Override // com.android.tools.r8.internal.AbstractC2352pb
    public final boolean b(char c2) {
        return "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000".charAt((48906 * c2) >>> c) == c2;
    }
}
