package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FixedBlockContainer extends BlockContainer<Block> {
    private static final Block[] EMPTY = new Block[0];
    private final Block[] mChildes;

    public FixedBlockContainer(int i) {
        this.mChildes = i == 0 ? EMPTY : new Block[i];
    }

    public void addChild(int i, Block block) {
        this.mChildes[i] = block;
        if (block != null) {
            block.setIndex(i);
            block.setParent(this);
        }
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public Block[] getChildes() {
        return this.mChildes;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public int getChildesCount() {
        return this.mChildes.length;
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onRefreshed() {
    }
}
