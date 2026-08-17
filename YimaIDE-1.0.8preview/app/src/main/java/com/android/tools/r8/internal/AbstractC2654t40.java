package com.android.tools.r8.internal;

import kotlin.reflect.jvm.internal.ReflectionFactoryImpl;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2654t40 {
    public static final C2997x40 a;

    static {
        C2997x40 c2997x40;
        try {
            c2997x40 = (C2997x40) ReflectionFactoryImpl.class.newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            c2997x40 = null;
        }
        if (c2997x40 == null) {
            c2997x40 = new C2997x40();
        }
        a = c2997x40;
    }

    public static C1237cW a() {
        a.getClass();
        return new C1237cW(AbstractC1289d5.class, "kotlinx-metadata");
    }

    public static C1672hd a(Class cls) {
        a.getClass();
        return new C1672hd(cls);
    }
}
