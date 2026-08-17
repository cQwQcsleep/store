package io.github.rosemoe.sora.text.breaker;

import io.github.rosemoe.sora.text.CharSequenceIterator;
import io.github.rosemoe.sora.text.ContentLine;
import java.text.BreakIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WordBreakerIcu implements WordBreaker {
    protected final char[] chars;
    protected final BreakIterator wrappingIterator;

    public WordBreakerIcu(ContentLine contentLine) {
        this.chars = contentLine.getBackingCharArray();
        CharSequenceIterator charSequenceIterator = new CharSequenceIterator(contentLine);
        BreakIterator lineInstance = BreakIterator.getLineInstance();
        this.wrappingIterator = lineInstance;
        lineInstance.setText(charSequenceIterator);
    }

    @Override // io.github.rosemoe.sora.text.breaker.WordBreaker
    public int getOptimizedBreakPoint(int i, int i2) {
        int iPreceding;
        int iMax;
        return (i2 <= 0 || Character.isWhitespace(this.chars[i2 + (-1)]) || this.wrappingIterator.isBoundary(i2) || (iPreceding = this.wrappingIterator.preceding(i2)) == -1 || (iMax = Math.max(i, Math.min(i2, iPreceding))) <= i) ? i2 : iMax;
    }
}
