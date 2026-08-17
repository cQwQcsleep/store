package androidx.compose.foundation.text;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.emoji2.text.EmojiCompat;
import java.text.BreakIterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¨\u0006\u000b"}, d2 = {"findPrecedingBreak", "", "", "index", "findFollowingBreak", "findCodePointBefore", "", "ifNotFound", "findCodePointOrEmojiStartBefore", "getEmojiCompatIfLoaded", "Landroidx/emoji2/text/EmojiCompat;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class StringHelpers_androidKt {
    private static final int findCodePointBefore(CharSequence charSequence, int i, int i2) {
        return i <= 0 ? i2 : Character.offsetByCodePoints(charSequence, i, -1);
    }

    public static final int findCodePointOrEmojiStartBefore(String str, int i, int i2) {
        int emojiStart;
        if (i <= 0) {
            return i2;
        }
        EmojiCompat emojiCompatIfLoaded = getEmojiCompatIfLoaded();
        return (emojiCompatIfLoaded != null && (emojiStart = emojiCompatIfLoaded.getEmojiStart(str, i + (-1))) >= 0) ? emojiStart : findCodePointBefore(str, i, i2);
    }

    public static final int findFollowingBreak(String str, int i) {
        EmojiCompat emojiCompatIfLoaded = getEmojiCompatIfLoaded();
        Integer num = null;
        if (emojiCompatIfLoaded != null) {
            Integer numValueOf = Integer.valueOf(emojiCompatIfLoaded.getEmojiEnd(str, i));
            if (numValueOf.intValue() != -1) {
                num = numValueOf;
            }
        }
        if (num != null) {
            return num.intValue();
        }
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(str);
        return characterInstance.following(i);
    }

    public static final int findPrecedingBreak(String str, int i) {
        EmojiCompat emojiCompatIfLoaded = getEmojiCompatIfLoaded();
        Integer num = null;
        if (emojiCompatIfLoaded != null) {
            Integer numValueOf = Integer.valueOf(emojiCompatIfLoaded.getEmojiStart(str, Math.max(0, i - 1)));
            if (numValueOf.intValue() != -1) {
                num = numValueOf;
            }
        }
        if (num != null) {
            return num.intValue();
        }
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(str);
        return characterInstance.preceding(i);
    }

    private static final EmojiCompat getEmojiCompatIfLoaded() {
        if (EmojiCompat.isConfigured()) {
            EmojiCompat emojiCompat = EmojiCompat.get();
            if (emojiCompat.getLoadState() == 1) {
                return emojiCompat;
            }
        }
        return null;
    }
}
