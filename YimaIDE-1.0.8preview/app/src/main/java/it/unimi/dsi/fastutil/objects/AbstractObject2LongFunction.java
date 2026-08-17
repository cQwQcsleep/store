package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class AbstractObject2LongFunction<K> implements Object2LongFunction<K>, Serializable {
    private static final long serialVersionUID = -4940583368468432370L;
    protected long defRetValue;

    @Override // it.unimi.dsi.fastutil.objects.Object2LongFunction
    public long defaultReturnValue() {
        return this.defRetValue;
    }
}
