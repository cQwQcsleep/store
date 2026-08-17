package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockList$2 extends BlockList<Block> {
    public boolean add(Block block) {
        throw new IllegalArgumentException("Empty BlockList");
    }

    public void ensureCapacity(int i) {
        if (i == 0) {
            return;
        }
        w01.a("Empty BlockList");
    }

    public void setSize(int i, boolean z) {
        if (i == 0) {
            return;
        }
        w01.a("Empty BlockList");
    }

    public int size() {
        return 0;
    }

    public void add(int i, Block block) {
        throw new IllegalArgumentException("Empty BlockList");
    }
}
