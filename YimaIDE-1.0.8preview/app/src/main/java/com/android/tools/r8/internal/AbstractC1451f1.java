package com.android.tools.r8.internal;

import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1451f1 implements InterfaceC2174nW {
    static {
        int i = C0389Bo.b;
    }

    public final L0 a(InputStream inputStream, C0389Bo c0389Bo) throws QB {
        C0638Ld c0638Ld = new C0638Ld(inputStream);
        L0 l0 = (L0) a(c0638Ld, c0389Bo);
        try {
            if (c0638Ld.f == 0) {
                return l0;
            }
            throw new QB("Protocol message end-group tag did not match expected tag.");
        } catch (QB e) {
            e.b = l0;
            throw e;
        }
    }

    public static L0 a(L0 l0) throws QB {
        if (l0 == null || l0.a()) {
            return l0;
        }
        QB qb = new QB(new C1516fk0().getMessage());
        qb.b = l0;
        throw qb;
    }
}
