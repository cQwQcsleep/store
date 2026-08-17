package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3041xd0;
import com.android.tools.r8.internal.Fd0;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Fd0 extends Id0 {
    public static boolean a(String str, C3041xd0 c3041xd0, Matcher matcher) {
        int iStart = matcher.start(str);
        boolean z = false;
        if (iStart == -1) {
            return false;
        }
        if (iStart > 0) {
            int i = iStart - 1;
            if (c3041xd0.a.charAt(i) == ':') {
                z = true;
                iStart = i;
            }
        }
        c3041xd0.a(iStart, matcher.end(str), z);
        return true;
    }

    @Override // com.android.tools.r8.internal.Id0
    public final Jd0 a(final String str) {
        return new Jd0() { // from class: go4
            @Override // com.android.tools.r8.internal.Jd0
            public final boolean a(C3041xd0 c3041xd0, Matcher matcher) {
                return Fd0.a(str, c3041xd0, matcher);
            }
        };
    }

    @Override // com.android.tools.r8.internal.Id0
    public final String a() {
        return "\\d*";
    }
}
