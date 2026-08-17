package com.intellij.util.text;

import com.intellij.openapi.util.text.CharSequenceWithStringHash;
import com.intellij.openapi.util.text.Strings;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ByteArrayCharSequence implements CharSequenceWithStringHash {
    private transient int hash;
    private final byte[] myChars;
    private final int myEnd;
    private final int myStart;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chars", "com/intellij/util/text/ByteArrayCharSequence", "<init>"));
    }

    public ByteArrayCharSequence(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        this.myChars = bArr;
        this.myStart = i;
        this.myEnd = i2;
    }

    public char charAt(int i) {
        return (char) (this.myChars[i + this.myStart] & 255);
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        for (int i4 = i; i4 < i2; i4++) {
            cArr[(i4 - i) + i3] = (char) (this.myChars[this.myStart + i4] & 255);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.hash;
        if (i != 0) {
            return i;
        }
        int iStringHashCode = Strings.stringHashCode(this, 0, length());
        this.hash = iStringHashCode;
        return iStringHashCode;
    }

    public int length() {
        return this.myEnd - this.myStart;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CharSequence subSequence(int i, int i2) {
        if (i == 0 && i2 == length()) {
            return this;
        }
        byte[] bArr = this.myChars;
        int i3 = this.myStart;
        return new ByteArrayCharSequence(bArr, i + i3, i3 + i2);
    }

    public String toString() {
        return new String(this.myChars, this.myStart, length(), StandardCharsets.ISO_8859_1);
    }
}
