package com.reandroid.dex.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AddingIntegerReference implements IntegerReference {
    private final IntegerReference baseReference;
    private final Block byteSizeBlock;

    public AddingIntegerReference(IntegerReference integerReference, Block block) {
        this.baseReference = integerReference;
        this.byteSizeBlock = block;
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return this.baseReference.get() + this.byteSizeBlock.countBytes();
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        this.baseReference.set(i - this.byteSizeBlock.countBytes());
    }

    public String toString() {
        return this.baseReference + " + " + this.byteSizeBlock.countBytes() + " = " + get();
    }
}
