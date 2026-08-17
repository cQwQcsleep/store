package com.android.tools.r8.internal;

import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Bd0 implements Jd0 {
    public final /* synthetic */ String a;
    public final /* synthetic */ Cd0 b;

    public Bd0(Cd0 cd0, String str) {
        this.b = cd0;
        this.a = str;
    }

    @Override // com.android.tools.r8.internal.Jd0
    public final boolean a(C3041xd0 c3041xd0, Matcher matcher) {
        int iStart = matcher.start(this.a);
        if (iStart == -1) {
            return false;
        }
        String strGroup = matcher.group(this.a);
        if (strGroup.equals("Suppressed")) {
            return false;
        }
        if (this.b.b() == EnumC2869vd0.c) {
            iStart += strGroup.lastIndexOf(47) + 1;
        }
        c3041xd0.a(iStart, matcher.end(this.a), this.b.b());
        return true;
    }

    @Override // com.android.tools.r8.internal.Jd0
    public final boolean a() {
        return true;
    }
}
