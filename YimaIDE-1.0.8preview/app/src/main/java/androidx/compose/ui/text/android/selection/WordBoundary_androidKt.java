package androidx.compose.ui.text.android.selection;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"getWordStart", "", "Landroidx/compose/ui/text/android/selection/WordIterator;", "offset", "getWordEnd", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WordBoundary_androidKt {
    public static final int getWordEnd(WordIterator wordIterator, int i) {
        int punctuationEnd = wordIterator.isAfterPunctuation(wordIterator.nextBoundary(i)) ? wordIterator.getPunctuationEnd(i) : wordIterator.getNextWordEndOnTwoWordBoundary(i);
        return punctuationEnd == -1 ? i : punctuationEnd;
    }

    public static final int getWordStart(WordIterator wordIterator, int i) {
        int punctuationBeginning = wordIterator.isOnPunctuation(wordIterator.prevBoundary(i)) ? wordIterator.getPunctuationBeginning(i) : wordIterator.getPrevWordBeginningOnTwoWordsBoundary(i);
        return punctuationBeginning == -1 ? i : punctuationBeginning;
    }
}
