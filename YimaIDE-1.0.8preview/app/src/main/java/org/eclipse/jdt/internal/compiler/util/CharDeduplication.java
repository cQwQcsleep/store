package org.eclipse.jdt.internal.compiler.util;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.function.Supplier;
import org.eclipse.jdt.internal.compiler.util.CharDeduplication;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class CharDeduplication {
    public static final int SEARCH_SIZE = 8;
    public static final int TABLE_SIZE = 8192;
    private static final ThreadLocal<SoftReference<CharDeduplication>> mutableCache;
    private static final char[] CHAR_ARRAY0 = new char[0];
    static final char[][] ASCII_CHARS = new char[128][];
    private final char[][] hashTable = new char[8192][];
    private final int[] circularBufferPointer = new int[8192];

    static {
        int i = 0;
        while (true) {
            char[][] cArr = ASCII_CHARS;
            if (i >= cArr.length) {
                mutableCache = ThreadLocal.withInitial(new Supplier() { // from class: vf1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return CharDeduplication.a();
                    }
                });
                return;
            } else {
                cArr[i] = new char[]{(char) i};
                i++;
            }
        }
    }

    private CharDeduplication() {
    }

    public static /* synthetic */ SoftReference a() {
        return new SoftReference(new CharDeduplication());
    }

    private boolean equals(char[] cArr, int i, int i2, char[] cArr2) {
        if (cArr2.length != i2 - i) {
            return false;
        }
        for (int i3 = i; i3 < i2; i3++) {
            if (cArr[i3] != cArr2[i3 - i]) {
                return false;
            }
        }
        return true;
    }

    public static CharDeduplication getThreadLocalInstance() {
        ThreadLocal<SoftReference<CharDeduplication>> threadLocal = mutableCache;
        CharDeduplication charDeduplication = threadLocal.get().get();
        if (charDeduplication != null) {
            return charDeduplication;
        }
        CharDeduplication charDeduplication2 = new CharDeduplication();
        threadLocal.set(new SoftReference<>(charDeduplication2));
        return charDeduplication2;
    }

    private int hashCode(char[] cArr, int i, int i2) {
        int i3 = cArr[i];
        while (true) {
            i++;
            if (i >= i2) {
                return i3;
            }
            i3 = (i3 * 31) + cArr[i];
        }
    }

    @Deprecated
    public void reset() {
        Arrays.fill(this.hashTable, (Object) null);
        Arrays.fill(this.circularBufferPointer, 0);
    }

    public char[] sharedCopyOfRange(char[] cArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 == 0) {
            return CHAR_ARRAY0;
        }
        if (i3 == 1) {
            char c = cArr[i];
            char[][] cArr2 = ASCII_CHARS;
            if (c < cArr2.length) {
                return cArr2[c];
            }
        }
        int iHashCode = hashCode(cArr, i, i2) & 8191;
        int i4 = -1;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = (iHashCode + i5) & 8191;
            char[] cArr3 = this.hashTable[i6];
            if (cArr3 == null) {
                i4 = i6;
            } else if (equals(cArr, i, i2, cArr3)) {
                return cArr3;
            }
        }
        char[] cArrCopyOfRange = Arrays.copyOfRange(cArr, i, i2);
        if (i4 == -1) {
            int[] iArr = this.circularBufferPointer;
            int i7 = iArr[iHashCode];
            iArr[iHashCode] = i7 + 1;
            i4 = (iHashCode + (i7 & 7)) & 8191;
        }
        this.hashTable[i4] = cArrCopyOfRange;
        return cArrCopyOfRange;
    }
}
