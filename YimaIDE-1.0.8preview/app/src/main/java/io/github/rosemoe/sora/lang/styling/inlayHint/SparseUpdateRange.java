package io.github.rosemoe.sora.lang.styling.inlayHint;

import android.util.SparseBooleanArray;
import io.github.rosemoe.sora.lang.analysis.StyleUpdateRange;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/inlayHint/SparseUpdateRange;", "Lio/github/rosemoe/sora/lang/analysis/StyleUpdateRange;", "<init>", "()V", "array", "Landroid/util/SparseBooleanArray;", "getArray", "()Landroid/util/SparseBooleanArray;", "addLine", "", "line", "", "isInRange", "", "lineIndexIterator", "Lkotlin/collections/IntIterator;", "maxLineIndex", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SparseUpdateRange implements StyleUpdateRange {
    private final SparseBooleanArray array = new SparseBooleanArray();

    public final void addLine(int line) {
        this.array.put(line, true);
    }

    public final SparseBooleanArray getArray() {
        return this.array;
    }

    @Override // io.github.rosemoe.sora.lang.analysis.StyleUpdateRange
    public boolean isInRange(int line) {
        return this.array.get(line);
    }

    @Override // io.github.rosemoe.sora.lang.analysis.StyleUpdateRange
    public IntIterator lineIndexIterator(final int maxLineIndex) {
        return new IntIterator() { // from class: io.github.rosemoe.sora.lang.styling.inlayHint.SparseUpdateRange.lineIndexIterator.1
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < SparseUpdateRange.this.getArray().size();
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                int i = maxLineIndex;
                SparseBooleanArray array = SparseUpdateRange.this.getArray();
                int i2 = this.index;
                this.index = i2 + 1;
                return Integer.min(i, array.keyAt(i2));
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }
}
