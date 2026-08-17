package com.reandroid.arsc.item;

import com.reandroid.utils.StringsUtil;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class StringBlock extends BlockItem implements StringReference {
    public static final CharsetDecoder UTF8_DECODER = StandardCharsets.UTF_8.newDecoder();
    private String mCache;

    public StringBlock() {
        super(0);
        this.mCache = StringsUtil.EMPTY;
    }

    public int compareTo(StringBlock stringBlock) {
        if (stringBlock == null) {
            return -1;
        }
        return StringsUtil.compareStrings(get(), stringBlock.get());
    }

    public abstract String decodeString(byte[] bArr);

    public abstract byte[] encodeString(String str);

    public String get() {
        return this.mCache;
    }

    @Override // com.reandroid.arsc.item.BlockItem
    public void onBytesChanged() {
        this.mCache = decodeString(getBytesInternal());
    }

    public void onStringChanged(String str, String str2) {
    }

    public void set(String str, boolean z) {
        if (str == null || str.length() == 0) {
            str = StringsUtil.EMPTY;
        }
        String str2 = this.mCache;
        if (countBytes() == 0) {
            str2 = null;
        } else if (str.equals(str2)) {
            return;
        }
        writeStringBytes(str);
        if (z) {
            onStringChanged(str2, str);
        }
    }

    public String toString() {
        return get();
    }

    public void writeStringBytes(String str) {
        this.mCache = str;
        setBytesInternal(encodeString(str), false);
    }

    public void set(String str) {
        set(str, true);
    }
}
