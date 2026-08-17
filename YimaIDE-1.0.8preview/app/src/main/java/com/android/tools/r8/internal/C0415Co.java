package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Co, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0415Co {
    public static volatile C0415Co b;
    public static final C0415Co c = new C0415Co();
    public final Map a;

    public C0415Co(C0415Co c0415Co) {
        if (c0415Co == c) {
            this.a = Collections.EMPTY_MAP;
        } else {
            this.a = Collections.unmodifiableMap(c0415Co.a);
        }
    }

    public static C0415Co a() {
        C0415Co c0415Co;
        C0415Co c0415Co2 = b;
        if (c0415Co2 != null) {
            return c0415Co2;
        }
        synchronized (C0415Co.class) {
            c0415Co = b;
            if (c0415Co == null) {
                Class cls = AbstractC3230zo.a;
                C0415Co c0415Co3 = null;
                if (cls != null) {
                    try {
                        c0415Co3 = (C0415Co) cls.getDeclaredMethod("getEmptyRegistry", null).invoke(null, null);
                    } catch (Exception unused) {
                    }
                }
                c0415Co = c0415Co3 != null ? c0415Co3 : c;
                b = c0415Co;
            }
        }
        return c0415Co;
    }

    public C0415Co() {
        this.a = Collections.EMPTY_MAP;
    }
}
