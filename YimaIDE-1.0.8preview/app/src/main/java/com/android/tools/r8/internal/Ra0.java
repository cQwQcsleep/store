package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Ra0;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ra0<V> {
    public static final /* synthetic */ boolean d = true;
    public final boolean b;
    public final TreeMap a = new TreeMap();
    public int c = 0;

    public Ra0(boolean z) {
        this.b = z;
    }

    public Ra0<V> a(final int i, final int i2, V v) {
        Map.Entry<Integer, V> entryA = a(Integer.valueOf(i2));
        final C1975l7 c1975l7 = new C1975l7(0);
        boolean zRemoveIf = this.a.navigableKeySet().removeIf(new Predicate() { // from class: w5c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a(i, i2, c1975l7, (Integer) obj);
            }
        });
        if (entryA != null) {
            if (!d && !this.b) {
                x1f.a();
                return null;
            }
            if (zRemoveIf) {
                c1975l7.a(Integer.valueOf(((Integer) c1975l7.a()).intValue() - 1));
            }
        }
        this.a.put(Integer.valueOf(i), v);
        int i3 = i2 + 1;
        if (!this.a.containsKey(Integer.valueOf(i3))) {
            this.a.put(Integer.valueOf(i3), entryA != null ? entryA.getValue() : null);
        }
        this.c = (this.c - ((Integer) c1975l7.a()).intValue()) + 1;
        return this;
    }

    public Map.Entry<Integer, V> a(Integer num) {
        Map.Entry<Integer, V> entryFloorEntry = this.a.floorEntry(num);
        if (entryFloorEntry == null || entryFloorEntry.getValue() == null) {
            return null;
        }
        return entryFloorEntry;
    }

    public V a(int i) {
        Map.Entry<Integer, V> entryA = a(Integer.valueOf(i));
        if (entryA != null) {
            return entryA.getValue();
        }
        return null;
    }

    public final /* synthetic */ boolean a(int i, int i2, C1975l7 c1975l7, Integer num) {
        if (i >= num.intValue() || num.intValue() > i2) {
            return false;
        }
        if (!d && !this.b) {
            x1f.a();
            return false;
        }
        if (this.a.get(num) != null) {
            c1975l7.a(Integer.valueOf(((Integer) c1975l7.a()).intValue() + 1));
        }
        return true;
    }

    public int a() {
        return this.c;
    }

    public final void a(final Consumer consumer) {
        this.a.values().forEach(new Consumer() { // from class: x5c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Ra0.a(consumer, obj);
            }
        });
    }

    public static /* synthetic */ void a(Consumer consumer, Object obj) {
        if (obj != null) {
            consumer.accept(obj);
        }
    }
}
