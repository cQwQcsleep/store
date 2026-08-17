package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2724tu extends AbstractC0551Hu {
    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return k().contains(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public boolean e() {
        return k().e();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return k().isEmpty();
    }

    public abstract AbstractC3066xu k();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return k().size();
    }
}
