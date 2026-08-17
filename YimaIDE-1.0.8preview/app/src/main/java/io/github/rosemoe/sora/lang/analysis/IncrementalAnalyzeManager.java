package io.github.rosemoe.sora.lang.analysis;

import io.github.rosemoe.sora.lang.styling.Span;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IncrementalAnalyzeManager<S, T> extends AnalyzeManager {
    List<Span> generateSpansForLine(LineTokenizeResult<S, T> lineTokenizeResult);

    S getInitialState();

    LineTokenizeResult<S, T> getState(int i);

    void onAbandonState(S s);

    void onAddState(S s);

    boolean stateEquals(S s, S s2);

    LineTokenizeResult<S, T> tokenizeLine(CharSequence charSequence, S s, int i);

    public static class LineTokenizeResult<S_, T_> {
        public List<Span> spans;
        public S_ state;
        public List<T_> tokens;

        public LineTokenizeResult(S_ s_, List<T_> list, List<Span> list2) {
            this.state = s_;
            this.tokens = list;
            this.spans = list2;
        }

        public LineTokenizeResult<S_, T_> clearSpans() {
            this.spans = null;
            return this;
        }

        public LineTokenizeResult(S_ s_, List<T_> list) {
            this.state = s_;
            this.tokens = list;
        }
    }
}
