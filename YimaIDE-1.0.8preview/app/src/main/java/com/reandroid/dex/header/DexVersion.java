package com.reandroid.dex.header;

import com.reandroid.arsc.item.ByteArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexVersion extends HeaderPiece {
    public static final int ClassDefinitionOrderEnforcedVersion = 37;
    private static final byte[] DEFAULT_BYTES = {48, 51, 53, 0};
    public static final int MinimumMultiLayoutVersion = 41;
    private int mCache;

    public DexVersion() {
        super.set((byte[]) DEFAULT_BYTES.clone());
    }

    public int getVersionAsInteger() {
        int i = this.mCache;
        if (i != 0) {
            return i;
        }
        byte[] bytesInternal = getBytesInternal();
        if (bytesInternal == null || bytesInternal.length != 4) {
            return -1;
        }
        int i2 = (((bytesInternal[0] & 255) - 48) * 100) + (((bytesInternal[1] & 255) - 48) * 10) + ((bytesInternal[2] & 255) - 48);
        this.mCache = i2;
        return i2;
    }

    public boolean isClassDefinitionOrderEnforced() {
        return getVersionAsInteger() >= 37;
    }

    public boolean isDefault() {
        return ByteArray.equals(getBytesInternal(), DEFAULT_BYTES);
    }

    public boolean isMultiLayoutVersion() {
        return getVersionAsInteger() >= 41;
    }

    public void onBytesChanged() {
        this.mCache = 0;
    }

    public void setVersionAsInteger(int i) {
        setSize(4);
        byte[] bytesInternal = getBytesInternal();
        if (i < 0 || i > 999) {
            i = 0;
        }
        int i2 = i / 100;
        bytesInternal[0] = (byte) ((i2 & 255) + 48);
        int i3 = i - (i2 * 100);
        int i4 = i3 / 10;
        bytesInternal[1] = (byte) ((i4 & 255) + 48);
        bytesInternal[2] = (byte) (((i3 - (i4 * 10)) & 255) + 48);
        bytesInternal[3] = 0;
        this.mCache = 0;
    }

    @Override // com.reandroid.dex.header.HeaderPiece, com.reandroid.arsc.item.ByteArray
    public String toString() {
        int versionAsInteger = getVersionAsInteger();
        return versionAsInteger <= 0 ? super.toString() : String.valueOf(versionAsInteger);
    }
}
