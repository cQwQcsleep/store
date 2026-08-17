package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FromNativeContext {
    private Class<?> type;

    public FromNativeContext(Class<?> cls) {
        this.type = cls;
    }

    public Class<?> getTargetType() {
        return this.type;
    }
}
