package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractObject2ObjectFunction<K, V> implements Object2ObjectFunction<K, V>, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected V defRetValue;

    public V defaultReturnValue() {
        return this.defRetValue;
    }
}
