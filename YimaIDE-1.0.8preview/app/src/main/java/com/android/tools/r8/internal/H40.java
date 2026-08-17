package com.android.tools.r8.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H40 extends E40 {
    public static final HashMap e;
    public final Constructor b;
    public final Object[] c;
    public final HashMap d;

    static {
        HashMap map = new HashMap();
        map.put(Byte.TYPE, (byte) 0);
        map.put(Short.TYPE, (short) 0);
        map.put(Integer.TYPE, 0);
        map.put(Long.TYPE, 0L);
        map.put(Float.TYPE, Float.valueOf(0.0f));
        map.put(Double.TYPE, Double.valueOf(0.0d));
        map.put(Character.TYPE, (char) 0);
        map.put(Boolean.TYPE, Boolean.FALSE);
        e = map;
    }

    public H40(Class cls, LinkedHashMap linkedHashMap) {
        super(linkedHashMap);
        this.d = new HashMap();
        AbstractC3082y40 abstractC3082y40 = B40.a;
        Constructor constructorA = abstractC3082y40.a(cls);
        this.b = constructorA;
        B40.a((AccessibleObject) constructorA);
        String[] strArrB = abstractC3082y40.b(cls);
        for (int i = 0; i < strArrB.length; i++) {
            this.d.put(strArrB[i], Integer.valueOf(i));
        }
        Class<?>[] parameterTypes = this.b.getParameterTypes();
        this.c = new Object[parameterTypes.length];
        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
            this.c[i2] = e.get(parameterTypes[i2]);
        }
    }
}
