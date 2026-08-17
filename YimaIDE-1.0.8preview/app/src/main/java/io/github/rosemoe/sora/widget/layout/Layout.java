package io.github.rosemoe.sora.widget.layout;

import android.util.SparseArray;
import io.github.rosemoe.sora.lang.analysis.StyleUpdateRange;
import io.github.rosemoe.sora.text.ContentLine;
import io.github.rosemoe.sora.text.ContentListener;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Layout extends ContentListener {
    void destroyLayout();

    default float[] getCharLayoutOffset(int i, int i2) {
        return getCharLayoutOffset(i, i2, new float[2]);
    }

    float[] getCharLayoutOffset(int i, int i2, float[] fArr);

    long getCharPositionForLayoutOffset(float f, float f2);

    long getDownPosition(int i, int i2);

    int getLayoutHeight();

    int getLayoutWidth();

    int getLineNumberForRow(int i);

    Row getRowAt(int i);

    int getRowCount();

    int getRowCountForLine(int i);

    int getRowIndexForPosition(int i);

    long getUpPosition(int i, int i2);

    void invalidateLines(StyleUpdateRange styleUpdateRange);

    default RowIterator obtainRowIterator(int i) {
        return obtainRowIterator(i, null);
    }

    RowIterator obtainRowIterator(int i, SparseArray<ContentLine> sparseArray);
}
