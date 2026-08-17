package io.github.rosemoe.sora.text.breaker;

import io.github.rosemoe.sora.text.ContentLine;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface WordBreaker {

    public static class Factory {
        public static WordBreaker newInstance(ContentLine contentLine) {
            return new WordBreakerProgram(contentLine);
        }
    }

    int getOptimizedBreakPoint(int i, int i2);
}
