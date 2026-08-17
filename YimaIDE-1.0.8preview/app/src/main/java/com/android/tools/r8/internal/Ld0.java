package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3041xd0;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ld0 extends Id0 {
    public final boolean a(String str, C3041xd0 c3041xd0, Matcher matcher) {
        int length;
        int iStart = matcher.start(str);
        if (iStart == -1) {
            return false;
        }
        String strGroup = matcher.group(str);
        for (int length2 = strGroup.length(); length2 > 0; length2--) {
            length = length2 - 1;
            char cCharAt = strGroup.charAt(length);
            if (cCharAt != ':' || length2 >= strGroup.length()) {
                if (!Character.isDigit(cCharAt)) {
                    length = strGroup.length();
                }
            }
            int i = length + iStart;
            c3041xd0.e(iStart, i);
            int iEnd = matcher.end(str);
            c3041xd0.a(Integer.min(i, iEnd), iEnd, true);
            return true;
        }
        length = strGroup.length();
        int i2 = length + iStart;
        c3041xd0.e(iStart, i2);
        int iEnd2 = matcher.end(str);
        c3041xd0.a(Integer.min(i2, iEnd2), iEnd2, true);
        return true;
    }

    @Override // com.android.tools.r8.internal.Id0
    public final Jd0 a(final String str) {
        return new Jd0() { // from class: fy8
            @Override // com.android.tools.r8.internal.Jd0
            public final boolean a(C3041xd0 c3041xd0, Matcher matcher) {
                return this.a.a(str, c3041xd0, matcher);
            }
        };
    }

    @Override // com.android.tools.r8.internal.Id0
    public final String a() {
        return ".*";
    }
}
