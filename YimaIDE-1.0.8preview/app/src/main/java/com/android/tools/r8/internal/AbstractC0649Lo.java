package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import defpackage.hkh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0649Lo extends AbstractC0493Fo {
    public static final /* synthetic */ boolean a = true;

    public static List a(C0322w2 c0322w2, C0322w2 c0322w3) {
        AbstractC0649Lo c0623Ko;
        if (!a && c0322w3.A0() < c0322w2.A0()) {
            x1f.a();
            return null;
        }
        int iA0 = c0322w3.A0() - c0322w2.A0();
        if (iA0 == 0) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(iA0);
        for (int iA1 = c0322w2.A0(); iA1 < c0322w3.B0().size(); iA1++) {
            com.android.tools.r8.graph.I2 i2K = c0322w3.k(iA1);
            if (i2K.T0()) {
                char cCharAt = i2K.Z0().charAt(0);
                if (cCharAt == 'B') {
                    c0623Ko = new C0545Ho();
                } else if (cCharAt == 'C') {
                    c0623Ko = new C0571Io();
                } else if (cCharAt == 'I') {
                    c0623Ko = new C0597Jo();
                } else if (cCharAt == 'S') {
                    c0623Ko = new C0700No();
                } else {
                    if (cCharAt != 'Z') {
                        hkh.a();
                        return null;
                    }
                    c0623Ko = new C0519Go();
                }
            } else {
                if (!a && !i2K.U0()) {
                    x1f.a();
                    return null;
                }
                c0623Ko = new C0623Ko(i2K);
            }
            arrayList.add(c0623Ko);
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC0493Fo
    public final boolean a() {
        return true;
    }
}
