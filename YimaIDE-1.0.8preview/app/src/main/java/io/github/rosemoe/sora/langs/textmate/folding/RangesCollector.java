package io.github.rosemoe.sora.langs.textmate.folding;

import android.util.SparseIntArray;
import io.github.rosemoe.sora.text.Content;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RangesCollector {
    private final SparseIntArray _startIndexes = new SparseIntArray();
    private final SparseIntArray _endIndexes = new SparseIntArray();
    private int _length = 0;

    public void insertFirst(int i, int i2, int i3) {
        if (i > 16777215 || i2 > 16777215) {
            return;
        }
        int i4 = this._length;
        this._startIndexes.put(i4, i);
        this._endIndexes.put(i4, i2);
        this._length++;
    }

    public FoldingRegions toIndentRanges(Content content) throws Exception {
        return new FoldingRegions(this._startIndexes, this._endIndexes);
    }
}
