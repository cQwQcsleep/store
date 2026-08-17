package io.github.rosemoe.sora.text;

import android.text.GetChars;
import defpackage.b1e;
import defpackage.we3;
import io.github.rosemoe.sora.text.bidi.BidiRequirementChecker;
import io.github.rosemoe.sora.text.bidi.TextBidi;
import io.github.rosemoe.sora.util.ShareableData;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ContentLine implements CharSequence, GetChars, BidiRequirementChecker, ShareableData<ContentLine> {
    private int length;
    private LineSeparator lineSeparator;
    private AtomicInteger refCount;
    private int rtlAffectingCount;
    private char[] value;

    public ContentLine(ContentLine contentLine) {
        this(contentLine.length + 16);
        int i = contentLine.length;
        this.length = i;
        this.rtlAffectingCount = contentLine.rtlAffectingCount;
        this.lineSeparator = contentLine.lineSeparator;
        System.arraycopy(contentLine.value, 0, this.value, 0, i);
    }

    private void checkIndex(int i) {
        if (i < 0 || i > this.length) {
            throw new StringIndexOutOfBoundsException("index = " + i + ", length = " + this.length);
        }
    }

    private void ensureCapacity(int i) {
        char[] cArr = this.value;
        if (cArr.length < i) {
            char[] cArr2 = new char[cArr.length * 2 < i ? i + 2 : cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, this.length);
            this.value = cArr2;
        }
    }

    public ContentLine append(CharSequence charSequence) {
        return insert(this.length, charSequence);
    }

    public void appendTo(StringBuilder sb) {
        sb.append(this.value, 0, this.length);
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        if (i < this.length) {
            return this.value[i];
        }
        if (getLineSeparator().getLength() > 0) {
            return getLineSeparator().getContent().charAt(i - this.length);
        }
        return '\n';
    }

    public ContentLine copy() {
        ContentLine contentLine = new ContentLine(false);
        contentLine.length = this.length;
        char[] cArr = new char[this.value.length];
        contentLine.value = cArr;
        System.arraycopy(this.value, 0, cArr, 0, this.length);
        contentLine.rtlAffectingCount = this.rtlAffectingCount;
        contentLine.lineSeparator = this.lineSeparator;
        return contentLine;
    }

    public ContentLine delete(int i, int i2) {
        char[] cArr;
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        }
        int i3 = this.length;
        if (i2 > i3) {
            i2 = i3;
        }
        if (i > i2) {
            throw new StringIndexOutOfBoundsException();
        }
        int i4 = i2 - i;
        if (i4 > 0) {
            int i5 = i;
            while (true) {
                cArr = this.value;
                if (i5 >= i2) {
                    break;
                }
                if (TextBidi.couldAffectRtl(cArr[i5])) {
                    this.rtlAffectingCount--;
                }
                i5++;
            }
            System.arraycopy(cArr, i + i4, cArr, i, this.length - i2);
            this.length -= i4;
        }
        return this;
    }

    public char[] getBackingCharArray() {
        return this.value;
    }

    @Override // android.text.GetChars
    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        }
        if (i2 < 0 || i2 > this.length) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        if (i > i2) {
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        }
        System.arraycopy(this.value, i, cArr, i3, i2 - i);
    }

    public LineSeparator getLineSeparator() {
        LineSeparator lineSeparator = this.lineSeparator;
        return lineSeparator == null ? LineSeparator.NONE : lineSeparator;
    }

    public ContentLine insert(int i, CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i < 0 || i > length()) {
            b1e.a("dstOffset ", i);
            return null;
        }
        if (i2 < 0 || i3 < 0 || i2 > i3 || i3 > charSequence.length()) {
            we3.a("start ", i2, ", end ", i3, ", s.length() ", charSequence.length());
            return null;
        }
        int i4 = i3 - i2;
        ensureCapacity(this.length + i4);
        char[] cArr = this.value;
        System.arraycopy(cArr, i, cArr, i + i4, this.length - i);
        while (i2 < i3) {
            char cCharAt = charSequence.charAt(i2);
            int i5 = i + 1;
            this.value[i] = cCharAt;
            if (TextBidi.couldAffectRtl(cCharAt)) {
                this.rtlAffectingCount++;
            }
            i2++;
            i = i5;
        }
        this.length += i4;
        return this;
    }

    @Override // io.github.rosemoe.sora.util.ShareableData
    public boolean isMutable() {
        AtomicInteger atomicInteger = this.refCount;
        return atomicInteger == null || atomicInteger.get() == 1;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // io.github.rosemoe.sora.text.bidi.BidiRequirementChecker
    public boolean mayNeedBidi() {
        return this.rtlAffectingCount > 0;
    }

    @Override // io.github.rosemoe.sora.util.ShareableData
    public void release() {
        AtomicInteger atomicInteger = this.refCount;
        if (atomicInteger != null && atomicInteger.decrementAndGet() < 0) {
            k2d.a("illegal operation. There is no active owner");
        }
    }

    @Override // io.github.rosemoe.sora.util.ShareableData
    public void retain() {
        AtomicInteger atomicInteger = this.refCount;
        if (atomicInteger == null) {
            this.refCount = new AtomicInteger(2);
        } else {
            atomicInteger.incrementAndGet();
        }
    }

    public void setLineSeparator(LineSeparator lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    @Override // java.lang.CharSequence
    public ContentLine subSequence(int i, int i2) {
        checkIndex(i);
        checkIndex(i2);
        if (i2 < i) {
            throw new StringIndexOutOfBoundsException("start is greater than end");
        }
        int i3 = i2 - i;
        char[] cArr = new char[i3 + 16];
        System.arraycopy(this.value, i, cArr, 0, i3);
        ContentLine contentLine = new ContentLine(false);
        contentLine.value = cArr;
        contentLine.length = i3;
        if (this.rtlAffectingCount > 0) {
            for (int i4 = 0; i4 < contentLine.length; i4++) {
                if (TextBidi.couldAffectRtl(cArr[i4])) {
                    contentLine.rtlAffectingCount++;
                }
            }
        }
        return contentLine;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.github.rosemoe.sora.util.ShareableData
    public ContentLine toMutable() {
        return isMutable() ? this : copy();
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.value, 0, this.length);
    }

    public String toStringWithNewline() {
        int length = this.value.length;
        int i = this.length;
        if (length == i) {
            ensureCapacity(i + 1);
        }
        char[] cArr = this.value;
        int i2 = this.length;
        cArr[i2] = '\n';
        return new String(cArr, 0, i2 + 1);
    }

    public ContentLine(CharSequence charSequence) {
        this(true);
        insert(0, charSequence);
    }

    public ContentLine() {
        this(true);
    }

    public ContentLine(int i) {
        this.length = 0;
        this.value = new char[i];
    }

    private ContentLine(boolean z) {
        if (z) {
            this.length = 0;
            this.value = new char[32];
        }
    }

    public ContentLine insert(int i, CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        return insert(i, charSequence, 0, charSequence.length());
    }

    public ContentLine insert(int i, char c) {
        ensureCapacity(this.length + 1);
        int i2 = this.length;
        if (i < i2) {
            char[] cArr = this.value;
            System.arraycopy(cArr, i, cArr, i + 1, i2 - i);
        }
        if (TextBidi.couldAffectRtl(c)) {
            this.rtlAffectingCount++;
        }
        this.value[i] = c;
        this.length++;
        return this;
    }
}
