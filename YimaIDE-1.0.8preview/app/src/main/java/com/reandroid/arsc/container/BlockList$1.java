package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.utils.collection.ArrayCollection;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockList$1<T> implements ArrayCollection.Monitor<T> {
    final /* synthetic */ BlockList this$0;

    public BlockList$1(BlockList blockList) {
        this.this$0 = blockList;
    }

    /* JADX WARN: Incorrect types in method signature: (ITT;)V */
    public void onAdd(int i, Block block) {
    }

    /* JADX WARN: Incorrect types in method signature: (ITT;)V */
    public void onRemoved(int i, Block block) {
        this.this$0.notifyPreRemove(block);
    }
}
