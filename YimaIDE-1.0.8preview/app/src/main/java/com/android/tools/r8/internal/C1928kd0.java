package com.android.tools.r8.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1928kd0 {
    public final AbstractC2352pb a;
    public final boolean b;
    public final InterfaceC1843jd0 c;
    public final int d;

    public C1928kd0(InterfaceC1843jd0 interfaceC1843jd0, boolean z, AbstractC2352pb abstractC2352pb, int i) {
        this.c = interfaceC1843jd0;
        this.b = z;
        this.a = abstractC2352pb;
        this.d = i;
    }

    public static C1928kd0 a(String str) {
        XW.a.getClass();
        C1134bD c1134bD = new C1134bD(Pattern.compile(str));
        Matcher matcher = c1134bD.b.matcher(XmlPullParser.NO_NAMESPACE);
        matcher.getClass();
        DX.a(!matcher.matches(), "The pattern may not match the empty string: %s", c1134bD);
        return new C1928kd0(new C1673hd0(c1134bD), false, C2180nb.c, Integer.MAX_VALUE);
    }
}
