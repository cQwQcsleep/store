package io.github.rosemoe.sora.text.breaker;

import io.github.rosemoe.sora.text.ContentLine;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WordBreakerProgram extends WordBreakerIcu {
    public WordBreakerProgram(ContentLine contentLine) {
        super(contentLine);
    }

    @Override // io.github.rosemoe.sora.text.breaker.WordBreakerIcu, io.github.rosemoe.sora.text.breaker.WordBreaker
    public int getOptimizedBreakPoint(int i, int i2) {
        int i3;
        int optimizedBreakPoint = super.getOptimizedBreakPoint(i, i2);
        if (optimizedBreakPoint == i2 && i2 > i) {
            int i4 = i2 - 1;
            if (!Character.isWhitespace(this.chars[i4])) {
                while (i4 > i) {
                    char[] cArr = this.chars;
                    if (cArr[i4] == '.' && (i3 = i4 - 1) >= i && !Character.isDigit(cArr[i3])) {
                        return i4 + 1;
                    }
                    i4--;
                }
                return i2;
            }
        }
        return optimizedBreakPoint;
    }
}
