package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1537g1 implements InterfaceC2346pW {
    public static final C0415Co a = C0415Co.a();

    public static TN a(TN tn) throws RB {
        if (tn == null || tn.a()) {
            return tn;
        }
        RB rb = new RB((tn instanceof J0 ? H0.c((J0) tn) : new C1601gk0()).getMessage());
        rb.b = tn;
        throw rb;
    }

    public final TN a(byte[] bArr) throws RB {
        C0415Co c0415Co = a;
        int length = bArr.length;
        C0586Jd c0586Jd = new C0586Jd(bArr, 0, length, false);
        try {
            c0586Jd.c(length);
            TN tn = (TN) a(c0586Jd, c0415Co);
            try {
                c0586Jd.a(0);
                return a(tn);
            } catch (RB e) {
                e.b = tn;
                throw e;
            }
        } catch (RB e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
