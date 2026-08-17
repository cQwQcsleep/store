package io.github.rosemoe.sora.text;

import android.icu.text.BreakIterator;
import io.github.rosemoe.sora.util.IntPair;
import io.github.rosemoe.sora.util.MyCharacter;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ICUUtils {
    public static long getWordRange(CharSequence charSequence, int i, boolean z) {
        if (!z) {
            return getWordRangeFallback(charSequence, i);
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(new CharSequenceIterator(charSequence));
        int iFollowing = wordInstance.following(i);
        int iPrevious = wordInstance.previous();
        return (i < iPrevious || i > iFollowing) ? getWordRangeFallback(charSequence, i) : IntPair.pack(iPrevious, iFollowing);
    }

    public static long getWordRangeFallback(CharSequence charSequence, int i) {
        int i2 = i;
        while (i2 < charSequence.length() && MyCharacter.isJavaIdentifierPart(charSequence.charAt(i2))) {
            i2++;
        }
        if (i2 > i) {
            while (i > 0 && MyCharacter.isJavaIdentifierPart(charSequence.charAt(i - 1))) {
                i--;
            }
        }
        return IntPair.pack(i, i2);
    }
}
