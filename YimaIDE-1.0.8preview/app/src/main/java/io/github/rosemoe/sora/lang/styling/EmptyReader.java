package io.github.rosemoe.sora.lang.styling;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class EmptyReader implements Spans.Reader {
    private static final EmptyReader INSTANCE = new EmptyReader();
    private final List<Span> spans;

    public EmptyReader() {
        ArrayList arrayList = new ArrayList(1);
        this.spans = arrayList;
        arrayList.add(SpanFactory.obtainNoExt(0, 5L));
    }

    public static EmptyReader getInstance() {
        return INSTANCE;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
    public Span getSpanAt(int i) {
        return this.spans.get(i);
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
    public int getSpanCount() {
        return 1;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
    public List<Span> getSpansOnLine(int i) {
        return new ArrayList(this.spans);
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
    public void moveToLine(int i) {
    }
}
