package io.github.rosemoe.sora.lang.util;

import io.github.rosemoe.sora.lang.styling.EmptyReader;
import io.github.rosemoe.sora.lang.styling.Spans;
import io.github.rosemoe.sora.text.CharPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class PlainTextSpans implements Spans {
    private int lineCount;

    public PlainTextSpans(int i) {
        this.lineCount = i;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public void adjustOnDelete(CharPosition charPosition, CharPosition charPosition2) {
        this.lineCount -= charPosition2.line - charPosition.line;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public void adjustOnInsert(CharPosition charPosition, CharPosition charPosition2) {
        this.lineCount += charPosition2.line - charPosition.line;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public int getLineCount() {
        return this.lineCount;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public Spans.Modifier modify() {
        throw new UnsupportedOperationException();
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public Spans.Reader read() {
        return EmptyReader.getInstance();
    }

    public void setLineCount(int i) {
        this.lineCount = i;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public boolean supportsModify() {
        return false;
    }
}
