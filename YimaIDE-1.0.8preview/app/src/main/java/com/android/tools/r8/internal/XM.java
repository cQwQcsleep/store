package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XM extends F0 {
    public final /* synthetic */ Map.Entry b;

    public XM(Map.Entry entry) {
        this.b = entry;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.b.getValue();
    }
}
