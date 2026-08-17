package com.android.apksig.internal.apk.v1;

import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public enum DigestAlgorithm {
    SHA1("SHA-1"),
    SHA256("SHA-256");

    public static Comparator<DigestAlgorithm> BY_STRENGTH_COMPARATOR = new StrengthComparator();
    private final String mJcaMessageDigestAlgorithm;

    public static class StrengthComparator implements Comparator<DigestAlgorithm> {
        private StrengthComparator() {
        }

        @Override // java.util.Comparator
        public int compare(DigestAlgorithm digestAlgorithm, DigestAlgorithm digestAlgorithm2) {
            int iOrdinal = digestAlgorithm.ordinal();
            if (iOrdinal == 0) {
                int iOrdinal2 = digestAlgorithm2.ordinal();
                if (iOrdinal2 == 0) {
                    return 0;
                }
                if (iOrdinal2 == 1) {
                    return -1;
                }
                y04.a("Unsupported algorithm: ", digestAlgorithm2);
                return 0;
            }
            if (iOrdinal != 1) {
                y04.a("Unsupported algorithm: ", digestAlgorithm);
                return 0;
            }
            int iOrdinal3 = digestAlgorithm2.ordinal();
            if (iOrdinal3 == 0) {
                return 1;
            }
            if (iOrdinal3 == 1) {
                return 0;
            }
            y04.a("Unsupported algorithm: ", digestAlgorithm2);
            return 0;
        }
    }

    DigestAlgorithm(String str) {
        this.mJcaMessageDigestAlgorithm = str;
    }

    public String getJcaMessageDigestAlgorithm() {
        return this.mJcaMessageDigestAlgorithm;
    }
}
