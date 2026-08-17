package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2896vv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2896vv extends AbstractC2982wv implements NavigableMap {
    public static final C2767uQ i;
    public static final C2896vv j;
    public final transient X40 f;
    public final transient AbstractC0551Hu g;
    public final transient C2896vv h;

    static {
        C2767uQ c2767uQ = C2767uQ.b;
        i = c2767uQ;
        X40 x40A = AbstractC3067xv.a(c2767uQ);
        int i2 = AbstractC0551Hu.c;
        j = new C2896vv(x40A, P40.e);
    }

    public C2896vv(X40 x40, AbstractC0551Hu abstractC0551Hu) {
        this.f = x40;
        this.g = abstractC0551Hu;
        this.h = null;
    }

    public static C2896vv a(final Comparator comparator, boolean z, Map.Entry[] entryArr, int i2) {
        if (i2 == 0) {
            return C2767uQ.b.equals(comparator) ? j : new C2896vv(AbstractC3067xv.a(comparator), P40.e);
        }
        if (i2 == 1) {
            Map.Entry entry = entryArr[0];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            Object key = entry2.getKey();
            Object value = entry2.getValue();
            Bc0 bc0 = new Bc0(key);
            comparator.getClass();
            return new C2896vv(new X40(bc0, comparator), new Bc0(value));
        }
        Object[] objArr = new Object[i2];
        Object[] objArr2 = new Object[i2];
        if (z) {
            for (int i3 = 0; i3 < i2; i3++) {
                Map.Entry entry3 = entryArr[i3];
                Objects.requireNonNull(entry3);
                Map.Entry entry4 = entry3;
                Object key2 = entry4.getKey();
                Object value2 = entry4.getValue();
                AbstractC0871Ud.a(key2, value2);
                objArr[i3] = key2;
                objArr2[i3] = value2;
            }
        } else {
            Arrays.sort(entryArr, 0, i2, new Comparator() { // from class: tmi
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return C2896vv.a(comparator, (Map.Entry) obj, (Map.Entry) obj2);
                }
            });
            Map.Entry entry5 = entryArr[0];
            Objects.requireNonNull(entry5);
            Map.Entry entry6 = entry5;
            Object key3 = entry6.getKey();
            objArr[0] = key3;
            Object value3 = entry6.getValue();
            objArr2[0] = value3;
            AbstractC0871Ud.a(objArr[0], value3);
            int i4 = 1;
            while (i4 < i2) {
                Map.Entry entry7 = entryArr[i4 - 1];
                Objects.requireNonNull(entry7);
                Map.Entry entry8 = entry7;
                Map.Entry entry9 = entryArr[i4];
                Objects.requireNonNull(entry9);
                Map.Entry entry10 = entry9;
                Object key4 = entry10.getKey();
                Object value4 = entry10.getValue();
                AbstractC0871Ud.a(key4, value4);
                objArr[i4] = key4;
                objArr2[i4] = value4;
                if (!(comparator.compare(key3, key4) != 0)) {
                    throw AbstractC0706Nu.a("key", entry8, entry10);
                }
                i4++;
                key3 = key4;
            }
        }
        return new C2896vv(new X40(new P40(objArr), comparator), new P40(objArr2));
    }

    public final C2896vv b(int i2, int i3) {
        if (i2 == 0 && i3 == this.g.size()) {
            return this;
        }
        if (i2 != i3) {
            return new C2896vv(this.f.e(i2, i3), this.g.subList(i2, i3));
        }
        Comparator comparator = comparator();
        return C2767uQ.b.equals(comparator) ? j : new C2896vv(AbstractC3067xv.a(comparator), P40.e);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object ceilingKey(Object obj) {
        return AbstractC1739iN.a(tailMap(obj, true).firstEntry());
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return this.f.e;
    }

    @Override // java.util.NavigableMap
    public final NavigableSet descendingKeySet() {
        return this.f.descendingSet();
    }

    @Override // java.util.NavigableMap
    public final NavigableMap descendingMap() {
        C2896vv c2896vv = this.h;
        if (c2896vv != null) {
            return c2896vv;
        }
        if (!isEmpty()) {
            return new C2896vv((X40) this.f.descendingSet(), this.g.j(), this);
        }
        Comparator comparator = comparator();
        AbstractC2943wV abstractC2943wVA = (comparator instanceof AbstractC2943wV ? (AbstractC2943wV) comparator : new C2870ve(comparator)).a();
        return C2767uQ.b.equals(abstractC2943wVA) ? j : new C2896vv(AbstractC3067xv.a(abstractC2943wVA), P40.e);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Set entrySet() {
        return super.entrySet();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) super.entrySet().a().get(0);
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return this.f.first();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object floorKey(Object obj) {
        return AbstractC1739iN.a(headMap(obj, true).lastEntry());
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        DX.a(biConsumer);
        AbstractC0551Hu abstractC0551HuA = this.f.a();
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            biConsumer.accept(abstractC0551HuA.get(i2), this.g.get(i2));
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        int iIndexOf = this.f.indexOf(obj);
        if (iIndexOf == -1) {
            return null;
        }
        return this.g.get(iIndexOf);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object higherKey(Object obj) {
        return AbstractC1739iN.a(tailMap(obj, false).firstEntry());
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        if (!isEmpty()) {
            return new C2810uv(this);
        }
        int i2 = AbstractC2554rv.c;
        return W40.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC3066xu k() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Set keySet() {
        return this.f;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) super.entrySet().a().get(this.g.size() - 1);
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return this.f.last();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object lowerKey(Object obj) {
        return AbstractC1739iN.a(headMap(obj, false).lastEntry());
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return this.f.h.e() || this.g.e();
    }

    @Override // java.util.NavigableMap
    public final NavigableSet navigableKeySet() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    /* JADX INFO: renamed from: o */
    public final AbstractC2554rv keySet() {
        return this.f;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    /* JADX INFO: renamed from: r */
    public final AbstractC3066xu values() {
        return this.g;
    }

    @Override // java.util.Map
    public final int size() {
        return this.g.size();
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
        if (comparator().compare(obj, obj2) <= 0) {
            return b(0, this.f.a(obj2, false)).tailMap(obj, true);
        }
        w01.a(Xf0.a("expected fromKey <= toKey but %s > %s", new Object[]{obj, obj2}));
        return null;
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Collection values() {
        return this.g;
    }

    public C2896vv(X40 x40, AbstractC0551Hu abstractC0551Hu, C2896vv c2896vv) {
        this.f = x40;
        this.g = abstractC0551Hu;
        this.h = c2896vv;
    }

    @Override // java.util.NavigableMap
    public final NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        obj.getClass();
        obj2.getClass();
        if (comparator().compare(obj, obj2) <= 0) {
            return b(0, this.f.a(obj2, z2)).tailMap(obj, z);
        }
        w01.a(Xf0.a("expected fromKey <= toKey but %s > %s", new Object[]{obj, obj2}));
        return null;
    }

    @Override // java.util.NavigableMap
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C2896vv tailMap(Object obj, boolean z) {
        X40 x40 = this.f;
        obj.getClass();
        return b(x40.b(obj, z), this.g.size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static C2896vv a(IdentityHashMap identityHashMap) {
        boolean zEquals;
        C2767uQ c2767uQ = i;
        if (identityHashMap instanceof SortedMap) {
            Comparator comparator = ((SortedMap) identityHashMap).comparator();
            zEquals = comparator == null ? true : c2767uQ.equals(comparator);
        } else {
            zEquals = false;
        }
        Collection collectionEntrySet = identityHashMap.entrySet();
        Map.Entry[] entryArr = AbstractC0706Nu.e;
        if (!(collectionEntrySet instanceof Collection)) {
            Iterator it = collectionEntrySet.iterator();
            ArrayList arrayList = new ArrayList();
            NC.a(arrayList, it);
            collectionEntrySet = arrayList;
        }
        Map.Entry[] entryArr2 = (Map.Entry[]) collectionEntrySet.toArray(entryArr);
        return a(c2767uQ, zEquals, entryArr2, entryArr2.length);
    }

    public static /* synthetic */ int a(Comparator comparator, Map.Entry entry, Map.Entry entry2) {
        Objects.requireNonNull(entry);
        Objects.requireNonNull(entry2);
        return comparator.compare(entry.getKey(), entry2.getKey());
    }

    @Override // java.util.NavigableMap
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C2896vv headMap(Object obj, boolean z) {
        X40 x40 = this.f;
        obj.getClass();
        return b(0, x40.a(obj, z));
    }
}
