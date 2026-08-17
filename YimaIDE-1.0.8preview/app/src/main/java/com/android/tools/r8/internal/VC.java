package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VC extends AbstractC0551Hu {
    public final /* synthetic */ WC d;

    public VC(WC wc) {
        this.d = wc;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Map.Entry entry = (Map.Entry) this.d.f.get(i);
        return new C3236zu(entry.getValue(), entry.getKey());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d.f.size();
    }
}
