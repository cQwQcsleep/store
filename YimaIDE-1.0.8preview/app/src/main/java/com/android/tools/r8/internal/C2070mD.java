package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.StringReader;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2070mD {
    public static AbstractC1643hD a(C2327pD c2327pD) {
        boolean z = c2327pD.b;
        c2327pD.b = true;
        try {
            try {
                try {
                    AbstractC1643hD abstractC1643hDA = Ce0.a(c2327pD);
                    c2327pD.b = z;
                    return abstractC1643hDA;
                } catch (StackOverflowError e) {
                    throw new C1984lD("Failed parsing JSON source: " + c2327pD + " to Json", e);
                }
            } catch (OutOfMemoryError e2) {
                throw new C1984lD("Failed parsing JSON source: " + c2327pD + " to Json", e2);
            }
        } catch (Throwable th) {
            c2327pD.b = z;
            throw th;
        }
    }

    public static AbstractC1643hD a(String str) {
        try {
            C2327pD c2327pD = new C2327pD(new StringReader(str));
            AbstractC1643hD abstractC1643hDA = a(c2327pD);
            abstractC1643hDA.getClass();
            if (!(abstractC1643hDA instanceof C1813jD) && c2327pD.p() != 10) {
                throw new C2413qD("Did not consume the entire document.");
            }
            return abstractC1643hDA;
        } catch (BM e) {
            throw new C2413qD(e);
        } catch (IOException e2) {
            throw new C1729iD(e2);
        } catch (NumberFormatException e3) {
            throw new C2413qD(e3);
        }
    }
}
