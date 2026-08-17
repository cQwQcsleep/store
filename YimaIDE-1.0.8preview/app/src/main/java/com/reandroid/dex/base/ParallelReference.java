package com.reandroid.dex.base;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParallelReference implements IntegerReference, BlockRefresh {
    private final IntegerReference reference1;
    private IntegerReference reference2;

    public ParallelReference(IntegerReference integerReference, IntegerReference integerReference2) {
        this.reference1 = integerReference;
        this.reference2 = integerReference2;
    }

    public int get() {
        return this.reference1.get();
    }

    public int get2() {
        IntegerReference integerReference = this.reference2;
        return integerReference != null ? integerReference.get() : this.reference1.get();
    }

    public IntegerReference getReference2() {
        return this.reference2;
    }

    public void refresh() {
        set(get());
    }

    public void set(int i) {
        this.reference1.set(i);
        IntegerReference integerReference = this.reference2;
        if (integerReference != null) {
            integerReference.set(i);
        }
    }

    public void setReference2(IntegerReference integerReference) {
        if (integerReference != this) {
            this.reference2 = integerReference;
        }
    }

    public String toString() {
        int i = this.reference1.get();
        IntegerReference integerReference = this.reference2;
        if (integerReference != null && i != integerReference.get()) {
            return "reference1=" + this.reference1 + ", reference2=" + this.reference2;
        }
        return Integer.toString(i);
    }

    public ParallelReference(IntegerReference integerReference) {
        this(integerReference, null);
    }
}
