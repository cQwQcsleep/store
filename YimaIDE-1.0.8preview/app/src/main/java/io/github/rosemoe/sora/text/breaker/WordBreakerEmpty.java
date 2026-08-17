package io.github.rosemoe.sora.text.breaker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WordBreakerEmpty implements WordBreaker {
    public static WordBreaker INSTANCE = new WordBreakerEmpty();

    private WordBreakerEmpty() {
    }

    @Override // io.github.rosemoe.sora.text.breaker.WordBreaker
    public int getOptimizedBreakPoint(int i, int i2) {
        return i2;
    }
}
