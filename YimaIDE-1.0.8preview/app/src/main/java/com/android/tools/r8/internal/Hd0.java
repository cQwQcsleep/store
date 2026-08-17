package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3041xd0;
import com.android.tools.r8.internal.Hd0;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Hd0 extends Id0 {
    public static /* synthetic */ boolean a(String str, C3041xd0 c3041xd0, Matcher matcher) {
        int iStart = matcher.start(str);
        if (iStart == -1) {
            return false;
        }
        c3041xd0.d(iStart, matcher.end(str));
        return true;
    }

    @Override // com.android.tools.r8.internal.Id0
    public final Jd0 a(final String str) {
        return new Jd0() { // from class: q76
            @Override // com.android.tools.r8.internal.Jd0
            public final boolean a(C3041xd0 c3041xd0, Matcher matcher) {
                return Hd0.a(str, c3041xd0, matcher);
            }
        };
    }

    @Override // com.android.tools.r8.internal.Id0
    public final String a() {
        return "(?:([^\\d\\s\\[\\];:()<>][^\\s\\[\\];:()<>]*|\\<init\\>|\\<clinit\\>))";
    }
}
