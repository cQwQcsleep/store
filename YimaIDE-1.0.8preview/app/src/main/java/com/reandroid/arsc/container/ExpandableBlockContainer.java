package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ExpandableBlockContainer extends BlockContainer<Block> {
    private Block[] mChildes;
    private int mCursor;

    public ExpandableBlockContainer(int i) {
        this.mChildes = new Block[i];
    }

    private void ensureCount(int i) {
        if (i <= getChildesCount()) {
            return;
        }
        Block[] blockArr = this.mChildes;
        this.mChildes = new Block[i];
        for (int i2 = 0; i2 < blockArr.length; i2++) {
            this.mChildes[i2] = blockArr[i2];
        }
    }

    public final void addChild(Block block) {
        if (block == null) {
            return;
        }
        int i = this.mCursor;
        ensureCount(i + 1);
        this.mChildes[i] = block;
        block.setIndex(i);
        block.setParent(this);
        this.mCursor++;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public final Block[] getChildes() {
        return this.mChildes;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public final int getChildesCount() {
        return this.mChildes.length;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onRefreshed() {
    }
}
