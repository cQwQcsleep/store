package com.shadow.kotlin.text;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.ranges.IntRange;
import java.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsJVMKt {
    /* JADX WARN: Type inference failed for: r0v3, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    public static final boolean isBlank(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return true;
        }
        Iterable intRange = new IntRange(0, str.length() - 1, 1);
        if ((intRange instanceof Collection) && ((Collection) intRange).isEmpty()) {
            return true;
        }
        ?? Iterator2 = intRange.iterator2();
        while (Iterator2.hasNext()) {
            char cCharAt = str.charAt(Iterator2.nextInt());
            if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean regionMatches(String str, int i, String str2, int i2, int i3, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        CloseableKt.checkNotNullParameter(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }
}
