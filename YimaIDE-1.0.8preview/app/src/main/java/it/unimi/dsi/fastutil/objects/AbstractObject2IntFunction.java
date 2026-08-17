package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractObject2IntFunction<K> implements Object2IntFunction<K>, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected int defRetValue;

    public void defaultReturnValue(int i) {
        this.defRetValue = i;
    }

    @Override // it.unimi.dsi.fastutil.objects.Object2IntFunction
    public int defaultReturnValue() {
        return this.defRetValue;
    }
}
