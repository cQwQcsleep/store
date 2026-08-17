package org.bouncycastle.pqc.legacy.crypto.qtesla;

import defpackage.qf1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class QTESLASecurityCategory {
    public static final int PROVABLY_SECURE_I = 5;
    public static final int PROVABLY_SECURE_III = 6;

    private QTESLASecurityCategory() {
    }

    public static String getName(int i) {
        if (i == 5) {
            return "qTESLA-p-I";
        }
        if (i == 6) {
            return "qTESLA-p-III";
        }
        qf1.a("unknown security category: ", i);
        return null;
    }

    public static int getPrivateSize(int i) {
        if (i == 5) {
            return 5224;
        }
        if (i == 6) {
            return 12392;
        }
        qf1.a("unknown security category: ", i);
        return 0;
    }

    public static int getPublicSize(int i) {
        if (i == 5) {
            return 14880;
        }
        if (i == 6) {
            return 38432;
        }
        qf1.a("unknown security category: ", i);
        return 0;
    }

    public static int getSignatureSize(int i) {
        if (i == 5) {
            return 2592;
        }
        if (i == 6) {
            return 5664;
        }
        qf1.a("unknown security category: ", i);
        return 0;
    }

    public static void validate(int i) {
        if (i == 5 || i == 6) {
            return;
        }
        qf1.a("unknown security category: ", i);
    }
}
