package io.github.rosemoe.sora.langs.textmate.folding;

import android.util.SparseIntArray;
import java.util.Stack;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FoldingRegions {
    private final SparseIntArray _endIndexes;
    private boolean _parentsComputed;
    private final SparseIntArray _startIndexes;

    public FoldingRegions(SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2) throws Exception {
        if (sparseIntArray.size() != sparseIntArray2.size() || sparseIntArray.size() > 65535) {
            throw new Exception("invalid startIndexes or endIndexes size");
        }
        this._startIndexes = sparseIntArray;
        this._endIndexes = sparseIntArray2;
        this._parentsComputed = false;
    }

    private void ensureParentIndices() throws Exception {
        if (this._parentsComputed) {
            return;
        }
        this._parentsComputed = true;
        Stack<Integer> stack = new Stack<>();
        int size = this._startIndexes.size();
        for (int i = 0; i < size; i++) {
            int i2 = this._startIndexes.get(i);
            int i3 = this._endIndexes.get(i);
            if (i2 > 16777215 || i3 > 16777215) {
                throw new Exception("startLineNumber or endLineNumber must not exceed 16777215");
            }
            while (stack.size() > 0 && !isInsideLast(stack, i2, i3)) {
                stack.pop();
            }
            int iIntValue = stack.size() > 0 ? stack.get(stack.size() - 1).intValue() : -1;
            stack.push(Integer.valueOf(i));
            this._startIndexes.put(i, i2 + ((iIntValue & 255) << 24));
            this._endIndexes.put(i, i3 + ((iIntValue & 65280) << 16));
        }
    }

    private boolean isInsideLast(Stack<Integer> stack, int i, int i2) {
        int iIntValue = stack.get(stack.size() - 1).intValue();
        return getStartLineNumber(iIntValue) <= i && getEndLineNumber(iIntValue) >= i2;
    }

    public int getEndLineNumber(int i) {
        return this._endIndexes.get(i) & IndentRange.MAX_LINE_NUMBER;
    }

    public int getParentIndex(int i) throws Exception {
        ensureParentIndices();
        int i2 = ((this._startIndexes.get(i) & IndentRange.MASK_INDENT) >>> 24) + ((this._endIndexes.get(i) & IndentRange.MASK_INDENT) >>> 16);
        if (i2 == 65535) {
            return -1;
        }
        return i2;
    }

    public int getStartLineNumber(int i) {
        return this._startIndexes.get(i) & IndentRange.MAX_LINE_NUMBER;
    }

    public int length() {
        return this._startIndexes.size();
    }

    public FoldingRegion toRegion(int i) {
        return new FoldingRegion(this, i);
    }
}
