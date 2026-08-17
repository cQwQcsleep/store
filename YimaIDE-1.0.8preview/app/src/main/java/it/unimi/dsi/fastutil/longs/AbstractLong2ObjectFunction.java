package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractLong2ObjectFunction<V> implements Long2ObjectFunction<V>, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected V defRetValue;

    @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction
    public V defaultReturnValue() {
        return this.defRetValue;
    }
}
