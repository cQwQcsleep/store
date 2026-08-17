package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T00 {
    public static final Object a(Lr lr, C0703Nr c0703Nr) {
        KB.c(lr, "<this>");
        KB.c(c0703Nr, "extension");
        if (c0703Nr.a != lr.b()) {
            w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            return null;
        }
        C0494Fp c0494Fp = lr.b;
        C0677Mr c0677Mr = c0703Nr.d;
        c0494Fp.getClass();
        if (c0677Mr.d) {
            w01.a("hasField() can only be called on non-repeated fields.");
            return null;
        }
        if (c0494Fp.a.get(c0677Mr) != null) {
            return lr.a(c0703Nr);
        }
        return null;
    }
}
