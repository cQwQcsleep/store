package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2077mK extends AbstractC2608sb0 {
    public final /* synthetic */ C2590sK b;

    public C2077mK(C2590sK c2590sK) {
        this.b = c2590sK;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.h.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2162nK(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C2590sK c2590sK = this.b;
        c2590sK.getClass();
        C2504rK c2504rK = new C2504rK(c2590sK, obj);
        ArrayList arrayList = new ArrayList();
        NC.a(arrayList, c2504rK);
        List listUnmodifiableList = Collections.unmodifiableList(arrayList);
        C2248oK c2248oK = (C2248oK) c2590sK.h.get(obj);
        C2334pK c2334pK = c2248oK == null ? null : c2248oK.a;
        while (c2334pK != null) {
            if (c2334pK == null) {
                z0e.a();
                return false;
            }
            C2334pK c2334pK2 = c2334pK.f;
            C2590sK.a(c2590sK, c2334pK);
            c2334pK = c2334pK2;
        }
        return !listUnmodifiableList.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.h.size();
    }
}
