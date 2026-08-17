package com.android.tools.r8.internal;

import defpackage.lka;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0706Nu implements Map, Serializable {
    public static final Map.Entry[] e = new Map.Entry[0];
    public transient AbstractC2554rv b;
    public transient AbstractC2554rv c;
    public transient AbstractC3066xu d;

    public static AbstractC0706Nu a(Map map) {
        if ((map instanceof AbstractC0706Nu) && !(map instanceof SortedMap)) {
            AbstractC0706Nu abstractC0706Nu = (AbstractC0706Nu) map;
            if (!abstractC0706Nu.m()) {
                return abstractC0706Nu;
            }
        } else if (map instanceof EnumMap) {
            EnumMap enumMap = new EnumMap((EnumMap) map);
            for (Map.Entry entry : enumMap.entrySet()) {
                AbstractC0871Ud.a(entry.getKey(), entry.getValue());
            }
            int size = enumMap.size();
            if (size == 0) {
                return T40.i;
            }
            if (size != 1) {
                return new C0369Au(enumMap);
            }
            Map.Entry entry2 = (Map.Entry) AbstractC3179zC.a(enumMap.entrySet());
            return new Ac0((Enum) entry2.getKey(), entry2.getValue());
        }
        Collection collectionEntrySet = map.entrySet();
        Map.Entry[] entryArr = e;
        if (!(collectionEntrySet instanceof Collection)) {
            Iterator it = collectionEntrySet.iterator();
            ArrayList arrayList = new ArrayList();
            NC.a(arrayList, it);
            collectionEntrySet = arrayList;
        }
        Map.Entry[] entryArr2 = (Map.Entry[]) collectionEntrySet.toArray(entryArr);
        int length = entryArr2.length;
        if (length == 0) {
            return T40.i;
        }
        if (length == 1) {
            Map.Entry entry3 = entryArr2[0];
            Objects.requireNonNull(entry3);
            Map.Entry entry4 = entry3;
            return new Ac0(entry4.getKey(), entry4.getValue());
        }
        int length2 = entryArr2.length;
        T40 t40 = T40.i;
        DX.b(length2, entryArr2.length);
        if (length2 == 0) {
            return T40.i;
        }
        try {
            return T40.a(length2, entryArr2);
        } catch (Q40 unused) {
            return XC.a(length2, entryArr2);
        }
    }

    public static C0629Ku e() {
        return new C0629Ku(4);
    }

    public static AbstractC0706Nu q() {
        return T40.i;
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object compute(Object obj, BiFunction biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object computeIfAbsent(Object obj, Function function) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public int hashCode() {
        return AbstractC2780ub0.a((Set) entrySet());
    }

    public abstract AbstractC2554rv i();

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    public abstract AbstractC2554rv j();

    public abstract AbstractC3066xu k();

    @Override // java.util.Map
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public AbstractC2554rv entrySet() {
        AbstractC2554rv abstractC2554rv = this.b;
        if (abstractC2554rv != null) {
            return abstractC2554rv;
        }
        AbstractC2554rv abstractC2554rvI = i();
        this.b = abstractC2554rvI;
        return abstractC2554rvI;
    }

    public abstract boolean m();

    @Override // java.util.Map
    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        throw new UnsupportedOperationException();
    }

    public Ck0 n() {
        return new C0603Ju(entrySet().iterator());
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public AbstractC2554rv keySet() {
        AbstractC2554rv abstractC2554rv = this.c;
        if (abstractC2554rv != null) {
            return abstractC2554rv;
        }
        AbstractC2554rv abstractC2554rvJ = j();
        this.c = abstractC2554rvJ;
        return abstractC2554rvJ;
    }

    public Spliterator p() {
        return AbstractC1165be.a(entrySet().spliterator(), new lka());
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public AbstractC3066xu values() {
        AbstractC3066xu abstractC3066xu = this.d;
        if (abstractC3066xu != null) {
            return abstractC3066xu;
        }
        AbstractC3066xu abstractC3066xuK = k();
        this.d = abstractC3066xuK;
        return abstractC3066xuK;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void replaceAll(BiFunction biFunction) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        AbstractC0871Ud.a(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object replace(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public static IllegalArgumentException a(String str, Object obj, Object obj2) {
        return new IllegalArgumentException("Multiple entries with same " + str + ": " + obj + " and " + obj2);
    }
}
