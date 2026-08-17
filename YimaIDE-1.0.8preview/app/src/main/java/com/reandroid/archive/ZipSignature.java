package com.reandroid.archive;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public enum ZipSignature {
    CENTRAL_FILE(33639248),
    LOCAL_FILE(67324752),
    DATA_DESCRIPTOR(134695760),
    ZIP64_RECORD(101075792),
    ZIP64_LOCATOR(117853008),
    END_RECORD(101010256);

    private static final ZipSignature[] VALUES = values();
    private final int value;

    ZipSignature(int i) {
        this.value = i;
    }

    public static ZipSignature valueOf(int i) {
        for (ZipSignature zipSignature : VALUES) {
            if (i == zipSignature.getValue()) {
                return zipSignature;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }
}
