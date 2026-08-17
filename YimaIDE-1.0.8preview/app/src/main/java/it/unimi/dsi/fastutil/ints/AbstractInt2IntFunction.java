package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractInt2IntFunction implements Int2IntFunction, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected int defRetValue;

    @Override // it.unimi.dsi.fastutil.ints.Int2IntFunction
    public int defaultReturnValue() {
        return this.defRetValue;
    }
}
