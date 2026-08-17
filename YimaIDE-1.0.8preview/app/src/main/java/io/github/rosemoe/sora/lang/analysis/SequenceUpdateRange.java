package io.github.rosemoe.sora.lang.analysis;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0010"}, d2 = {"Lio/github/rosemoe/sora/lang/analysis/SequenceUpdateRange;", "Lio/github/rosemoe/sora/lang/analysis/StyleUpdateRange;", "startLine", "", "endLine", "<init>", "(II)V", "getStartLine", "()I", "getEndLine", "isInRange", "", "line", "lineIndexIterator", "Lkotlin/collections/IntIterator;", "maxLineIndex", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SequenceUpdateRange implements StyleUpdateRange {
    private final int endLine;
    private final int startLine;

    public /* synthetic */ SequenceUpdateRange(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? Integer.MAX_VALUE : i2);
    }

    public final int getEndLine() {
        return this.endLine;
    }

    public final int getStartLine() {
        return this.startLine;
    }

    @Override // io.github.rosemoe.sora.lang.analysis.StyleUpdateRange
    public boolean isInRange(int line) {
        return line <= this.endLine && this.startLine <= line;
    }

    @Override // io.github.rosemoe.sora.lang.analysis.StyleUpdateRange
    public IntIterator lineIndexIterator(final int maxLineIndex) {
        return new IntIterator() { // from class: io.github.rosemoe.sora.lang.analysis.SequenceUpdateRange.lineIndexIterator.1
            private int currentLine;

            {
                this.currentLine = SequenceUpdateRange.this.getStartLine();
            }

            public final int getCurrentLine() {
                return this.currentLine;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentLine <= Integer.min(SequenceUpdateRange.this.getEndLine(), maxLineIndex);
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                int i = this.currentLine;
                this.currentLine = i + 1;
                return i;
            }

            public final void setCurrentLine(int i) {
                this.currentLine = i;
            }
        };
    }

    public SequenceUpdateRange(int i, int i2) {
        this.startLine = i;
        this.endLine = i2;
    }
}
