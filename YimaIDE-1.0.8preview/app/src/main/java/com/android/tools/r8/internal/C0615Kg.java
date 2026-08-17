package com.android.tools.r8.internal;

import defpackage.jo7;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0615Kg implements AU {
    public final /* synthetic */ Type a;

    public C0615Kg(Type type) {
        this.a = type;
    }

    @Override // com.android.tools.r8.internal.AU
    public final Object a() {
        Type type = this.a;
        if (!(type instanceof ParameterizedType)) {
            jo7.a("Invalid EnumMap type: ", this.a);
            return null;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            return new EnumMap((Class) type2);
        }
        jo7.a("Invalid EnumMap type: ", this.a);
        return null;
    }
}
