package com.android.tools.r8.internal;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Ce0 {
    public static AbstractC1643hD a(C2327pD c2327pD) {
        boolean z;
        try {
            try {
                c2327pD.p();
                z = false;
                try {
                    return (AbstractC1643hD) AbstractC2197nj0.z.a(c2327pD);
                } catch (EOFException e) {
                    e = e;
                    if (z) {
                        return C1813jD.b;
                    }
                    throw new C2413qD(e);
                }
            } catch (EOFException e2) {
                e = e2;
                z = true;
            }
        } catch (BM e3) {
            throw new C2413qD(e3);
        } catch (IOException e4) {
            throw new C1729iD(e4);
        } catch (NumberFormatException e5) {
            throw new C2413qD(e5);
        }
    }
}
