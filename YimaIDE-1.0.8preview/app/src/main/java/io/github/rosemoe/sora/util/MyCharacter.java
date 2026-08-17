package io.github.rosemoe.sora.util;

import java.util.Arrays;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MyCharacter {
    private static int[] bitsIsPart;
    private static int[] bitsIsStart;

    static {
        initMapInternal();
    }

    public static boolean couldBeEmoji(int i) {
        return i >= 126976 && i <= 129791;
    }

    private static boolean get(int[] iArr, int i) {
        return (iArr[i / 32] & (1 << (i % 32))) != 0;
    }

    @Deprecated
    public static void initMap() {
    }

    private static void initMapInternal() {
        if (bitsIsStart != null) {
            return;
        }
        int[] iArr = new int[NewHope.SENDB_BYTES];
        bitsIsPart = iArr;
        bitsIsStart = new int[NewHope.SENDB_BYTES];
        Arrays.fill(iArr, 0);
        Arrays.fill(bitsIsStart, 0);
        for (int i = 0; i <= 65535; i++) {
            char c = (char) i;
            if (Character.isJavaIdentifierPart(c)) {
                set(bitsIsPart, i);
            }
            if (Character.isJavaIdentifierStart(c)) {
                set(bitsIsStart, i);
            }
        }
    }

    public static boolean isAlpha(char c) {
        if (c < 'a' || c > 'z') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    public static boolean isFitzpatrick(int i) {
        return i >= 127995 && i <= 127999;
    }

    public static boolean isJavaIdentifierPart(char c) {
        return get(bitsIsPart, c);
    }

    public static boolean isJavaIdentifierStart(char c) {
        return get(bitsIsStart, c);
    }

    public static boolean isVariationSelector(int i) {
        return i == 65038 || i == 65039;
    }

    public static boolean isZWJ(int i) {
        return i == 8205;
    }

    public static boolean isZWNJ(int i) {
        return i == 8204;
    }

    private static void set(int[] iArr, int i) {
        int i2 = i / 32;
        iArr[i2] = (1 << (i % 32)) | iArr[i2];
    }
}
