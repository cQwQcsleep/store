package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2334pK extends F0 {
    public final Object b;
    public Object c;
    public C2334pK d;
    public C2334pK e;
    public C2334pK f;
    public C2334pK g;

    public C2334pK(Object obj, Object obj2) {
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.F0, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.c;
        this.c = obj;
        return obj2;
    }
}
