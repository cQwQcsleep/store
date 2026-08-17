package io.github.rosemoe.sora.lang.completion;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import io.github.rosemoe.sora.text.TextUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MatchHelper {
    public int highlightColor = -12627531;
    public boolean ignoreCase = false;
    public boolean matchFirstCase = false;

    public Spanned commonSub(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence.length() < charSequence2.length()) {
            return null;
        }
        int length = charSequence2.length();
        int i = 0;
        SpannableString spannableString = null;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = charSequence2.charAt(i2);
            boolean z2 = false;
            while (i < charSequence.length() && !z2) {
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt2 == i || (z && Character.toLowerCase(cCharAt2) == Character.toLowerCase(cCharAt))) {
                    if (spannableString == null) {
                        spannableString = new SpannableString(charSequence);
                    }
                    spannableString.setSpan(new ForegroundColorSpan(this.highlightColor), i, i + 1, 33);
                    z2 = true;
                }
                i++;
            }
            if (!z2) {
                return null;
            }
        }
        return spannableString;
    }

    public Spanned contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        int iIndexOf = TextUtils.indexOf(charSequence, charSequence2, z, 0);
        if (iIndexOf == -1) {
            return null;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(this.highlightColor), iIndexOf, charSequence2.length() + iIndexOf, 33);
        return spannableString;
    }

    public Spanned startsWith(CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        if (charSequence.length() < charSequence2.length()) {
            return null;
        }
        int length = charSequence2.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            char cCharAt2 = charSequence2.charAt(i);
            if (cCharAt != cCharAt2) {
                if (!z2) {
                    return null;
                }
                if ((i == 0 && z) || Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2)) {
                    return null;
                }
            }
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(this.highlightColor), 0, length, 33);
        return spannableString;
    }

    public Spanned contains(CharSequence charSequence, CharSequence charSequence2) {
        return contains(charSequence, charSequence2, this.ignoreCase);
    }

    public Spanned startsWith(CharSequence charSequence, CharSequence charSequence2) {
        return startsWith(charSequence, charSequence2, this.matchFirstCase, this.ignoreCase);
    }

    public Spanned commonSub(CharSequence charSequence, CharSequence charSequence2) {
        return commonSub(charSequence, charSequence2, this.ignoreCase);
    }
}
