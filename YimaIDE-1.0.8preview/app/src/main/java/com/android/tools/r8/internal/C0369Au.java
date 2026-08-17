package com.android.tools.r8.internal;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Au, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0369Au extends AbstractC0680Mu {
    public final transient EnumMap f;

    public C0369Au(EnumMap enumMap) {
        this.f = enumMap;
        if (enumMap.isEmpty()) {
            j2d.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0369Au) {
            obj = ((C0369Au) obj).f;
        }
        return this.f.equals(obj);
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        this.f.forEach(biConsumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        return this.f.get(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final Ck0 n() {
        Iterator it = this.f.keySet().iterator();
        it.getClass();
        return it instanceof Ck0 ? (Ck0) it : new CC(it);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final Spliterator p() {
        return this.f.keySet().spliterator();
    }

    @Override // java.util.Map
    public final int size() {
        return this.f.size();
    }
}
