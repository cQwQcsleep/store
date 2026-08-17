package com.android.tools.r8.internal;

import defpackage.pv9;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1672hd implements InterfaceC2242oE, InterfaceC0869Ub {
    public static final Map b;
    public final Class a;

    static {
        List listA = AbstractC1929ke.a((Object[]) new Class[]{InterfaceC1270cr.class, InterfaceC1439er.class, InterfaceC2635sr.class, InterfaceC2806ur.class, InterfaceC2892vr.class, InterfaceC2978wr.class, InterfaceC3063xr.class, InterfaceC3147yr.class, InterfaceC3233zr.class, InterfaceC0366Ar.class, InterfaceC1353dr.class, InterfaceC1525fr.class, InterfaceC1610gr.class, InterfaceC1696hr.class, InterfaceC1781ir.class, InterfaceC1866jr.class, InterfaceC1951kr.class, InterfaceC2037lr.class, InterfaceC2122mr.class, InterfaceC2208nr.class, InterfaceC2380pr.class, InterfaceC2465qr.class, InterfaceC2550rr.class});
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) listA));
        int i = 0;
        for (Object obj : listA) {
            int i2 = i + 1;
            if (i < 0) {
                pv9.a("Index overflow has happened.");
                return;
            } else {
                arrayList.add(new C1491fW((Class) obj, Integer.valueOf(i)));
                i = i2;
            }
        }
        b = AbstractC1823jN.a(arrayList);
        HashMap map = new HashMap();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        HashMap map2 = new HashMap();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        HashMap map3 = new HashMap();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        KB.b(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            KB.a((Object) str);
            sb.append(AbstractC1679hg0.a(str, '.'));
            sb.append("CompanionObject");
            map3.put(sb.toString(), str.concat(".Companion"));
        }
        for (Map.Entry entry : b.entrySet()) {
            Class cls = (Class) entry.getKey();
            int iIntValue = ((Number) entry.getValue()).intValue();
            map3.put(cls.getName(), "kotlin.Function" + iIntValue);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(AbstractC1823jN.a(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), AbstractC1679hg0.a((String) entry2.getValue(), '.'));
        }
    }

    public C1672hd(Class cls) {
        KB.c(cls, "jClass");
        this.a = cls;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0869Ub
    public final Class a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1672hd) && AbstractC3095yD.a(this).equals(AbstractC3095yD.a((InterfaceC2242oE) obj));
    }

    public final int hashCode() {
        return AbstractC3095yD.a(this).hashCode();
    }

    public final String toString() {
        return this.a.toString() + " (Kotlin reflection is not available)";
    }
}
