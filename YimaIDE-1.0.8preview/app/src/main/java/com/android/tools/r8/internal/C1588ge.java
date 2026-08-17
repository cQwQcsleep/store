package com.android.tools.r8.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ge, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1588ge implements Ai0 {
    public final C0692Ng b;

    public C1588ge(C0692Ng c0692Ng) {
        this.b = c0692Ng;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Type type = fj0.b;
        Class cls = fj0.a;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type typeB = AbstractC1278d.b(type, cls, Collection.class);
        Type type2 = typeB instanceof ParameterizedType ? ((ParameterizedType) typeB).getActualTypeArguments()[0] : Object.class;
        return new C1503fe(c0471Es, type2, c0471Es.a(new Fj0(type2)), this.b.a(fj0));
    }
}
