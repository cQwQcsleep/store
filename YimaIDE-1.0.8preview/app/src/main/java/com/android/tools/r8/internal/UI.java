package com.android.tools.r8.internal;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UI extends LinkedHashMap {
    public final int b;

    public UI(int i) {
        super(i, 0.75f);
        this.b = i;
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.b;
    }
}
