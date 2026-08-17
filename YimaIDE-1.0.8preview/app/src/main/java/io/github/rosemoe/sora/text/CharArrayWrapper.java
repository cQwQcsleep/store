package io.github.rosemoe.sora.text;

import android.text.GetChars;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CharArrayWrapper implements CharSequence, GetChars {
    private int count;
    private final char[] data;
    private final int offset;

    public CharArrayWrapper(char[] cArr, int i, int i2) {
        this.data = cArr;
        this.count = i2;
        this.offset = i;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.data[this.offset + i];
    }

    @Override // android.text.GetChars
    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (i2 > this.count) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(this.data, this.offset + i, cArr, i3, i2 - i);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.count;
    }

    public void setDataCount(int i) {
        this.count = i;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return CharBuffer.wrap(this.data, this.offset + i, i2 - i);
    }

    public CharArrayWrapper(char[] cArr, int i) {
        this(cArr, 0, i);
    }
}
