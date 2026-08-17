package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractInt2ObjectFunction<V> implements Int2ObjectFunction<V>, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected V defRetValue;

    public void defaultReturnValue(V v) {
        this.defRetValue = v;
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
    public V defaultReturnValue() {
        return this.defRetValue;
    }
}
