package com.reandroid.arsc.item;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.CompareUtil;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FixedLengthString extends StringItem {
    private final int bytesLength;

    public FixedLengthString(int i) {
        super(true);
        this.bytesLength = i;
        setBytesLength(i);
    }

    private static String decodeUtf16Bytes(byte[] bArr) {
        if (StringItem.isNullBytes(bArr)) {
            return null;
        }
        return new String(bArr, 0, getEndNullPosition(bArr), StandardCharsets.UTF_16LE);
    }

    private static int getEndNullPosition(byte[] bArr) {
        int length = bArr.length;
        boolean z = false;
        int i = 0;
        for (int i2 = 1; i2 < length; i2++) {
            int i3 = i2 - 1;
            byte b = bArr[i3];
            byte b2 = bArr[i2];
            if (b != 0 || b2 != 0) {
                z = false;
            } else if (!z) {
                z = true;
                i = i2;
            } else if (i < i3) {
            }
        }
        return !z ? length : i;
    }

    @Override // com.reandroid.arsc.item.StringItem
    public int calculateReadLength(BlockReader blockReader) {
        return this.bytesLength;
    }

    @Override // com.reandroid.arsc.item.StringItem, java.lang.Comparable
    public int compareTo(StringItem stringItem) {
        if (stringItem == null) {
            return -1;
        }
        return CompareUtil.compare(get(), stringItem.get());
    }

    @Override // com.reandroid.arsc.item.StringItem, com.reandroid.arsc.item.StringBlock
    public String decodeString(byte[] bArr) {
        return decodeUtf16Bytes(bArr);
    }

    @Override // com.reandroid.arsc.item.StringItem, com.reandroid.arsc.item.StringBlock
    public byte[] encodeString(String str) {
        if (str == null) {
            return new byte[this.bytesLength];
        }
        byte[] utf16Bytes = StringItem.getUtf16Bytes(str);
        int i = this.bytesLength;
        byte[] bArr = new byte[i];
        int length = utf16Bytes.length;
        if (length <= i) {
            i = length;
        }
        System.arraycopy(utf16Bytes, 0, bArr, 0, i);
        return bArr;
    }

    @Override // com.reandroid.arsc.item.StringItem
    public StyleItem getOrCreateStyle() {
        return null;
    }

    @Override // com.reandroid.arsc.item.StringItem, com.reandroid.arsc.item.StringBlock
    public void onStringChanged(String str, String str2) {
    }

    @Override // com.reandroid.arsc.item.StringItem, com.reandroid.arsc.item.StringBlock
    public String toString() {
        return "FIXED-" + this.bytesLength + " {" + get() + "}";
    }
}
