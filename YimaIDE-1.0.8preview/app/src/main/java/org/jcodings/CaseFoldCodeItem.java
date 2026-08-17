package org.jcodings;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CaseFoldCodeItem {
    public static final CaseFoldCodeItem[] EMPTY_FOLD_CODES = new CaseFoldCodeItem[0];
    public final int byteLen;
    public final int[] code;

    private CaseFoldCodeItem(int i, int[] iArr) {
        this.byteLen = i;
        this.code = iArr;
    }

    public static CaseFoldCodeItem create(int i, int i2) {
        return new CaseFoldCodeItem(i, new int[]{i2});
    }

    public static CaseFoldCodeItem create(int i, int i2, int i3) {
        return new CaseFoldCodeItem(i, new int[]{i2, i3});
    }

    public static CaseFoldCodeItem create(int i, int i2, int i3, int i4) {
        return new CaseFoldCodeItem(i, new int[]{i2, i3, i4});
    }
}
