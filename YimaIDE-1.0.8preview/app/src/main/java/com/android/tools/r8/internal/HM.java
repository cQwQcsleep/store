package com.android.tools.r8.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HM implements Ai0 {
    public final C0692Ng b;
    public final boolean c = false;

    public HM(C0692Ng c0692Ng) {
        this.b = c0692Ng;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Type[] actualTypeArguments;
        Type type = fj0.b;
        Class cls = fj0.a;
        if (!Map.class.isAssignableFrom(cls)) {
            return null;
        }
        if (type == Properties.class) {
            actualTypeArguments = new Type[]{String.class, String.class};
        } else {
            Type typeB = AbstractC1278d.b(type, cls, Map.class);
            actualTypeArguments = typeB instanceof ParameterizedType ? ((ParameterizedType) typeB).getActualTypeArguments() : new Type[]{Object.class, Object.class};
        }
        Type type2 = actualTypeArguments[0];
        return new GM(this, c0471Es, actualTypeArguments[0], (type2 == Boolean.TYPE || type2 == Boolean.class) ? AbstractC2197nj0.c : c0471Es.a(new Fj0(type2)), actualTypeArguments[1], c0471Es.a(new Fj0(actualTypeArguments[1])), this.b.a(fj0));
    }
}
