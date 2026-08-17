package com.android.tools.r8.internal;

import defpackage.jo7;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0589Jg implements AU {
    public final /* synthetic */ Type a;

    public C0589Jg(Type type) {
        this.a = type;
    }

    @Override // com.android.tools.r8.internal.AU
    public final Object a() {
        Type type = this.a;
        if (!(type instanceof ParameterizedType)) {
            jo7.a("Invalid EnumSet type: ", this.a);
            return null;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            return EnumSet.noneOf((Class) type2);
        }
        jo7.a("Invalid EnumSet type: ", this.a);
        return null;
    }
}
