package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B2;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.ir.optimize.info.C3263d;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0749Pl implements Map {
    public final Map b;

    public C0749Pl(Map map) {
        this.b = map;
    }

    public static C0749Pl e() {
        return new C0749Pl(new HashMap());
    }

    public final void a(C0231j1 c0231j1, com.android.tools.r8.graph.I2 i2, BiFunction biFunction) {
        C0322w2 reference = c0231j1.getReference();
        this.b.merge(AbstractC0507Gc.a(reference, reference), i2, biFunction);
    }

    @Override // java.util.Map
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Map
    public final Object compute(Object obj, BiFunction biFunction) {
        return this.b.compute((com.android.tools.r8.graph.B2) obj, biFunction);
    }

    @Override // java.util.Map
    public final Object computeIfAbsent(Object obj, Function function) {
        return this.b.computeIfAbsent((com.android.tools.r8.graph.B2) obj, function);
    }

    @Override // java.util.Map
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        return this.b.computeIfPresent((com.android.tools.r8.graph.B2) obj, biFunction);
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.b.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.b.entrySet();
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        this.b.forEach(biConsumer);
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return this.b.get(obj);
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return this.b.getOrDefault(obj, obj2);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.b.keySet();
    }

    @Override // java.util.Map
    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return this.b.merge((com.android.tools.r8.graph.B2) obj, obj2, biFunction);
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        return this.b.put((com.android.tools.r8.graph.B2) obj, obj2);
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        map.forEach(new BiConsumer() { // from class: a2b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((B2) obj, obj2);
            }
        });
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        return this.b.putIfAbsent((com.android.tools.r8.graph.B2) obj, obj2);
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        return this.b.remove(obj, obj2);
    }

    @Override // java.util.Map
    public final Object replace(Object obj, Object obj2) {
        return this.b.replace((com.android.tools.r8.graph.B2) obj, obj2);
    }

    @Override // java.util.Map
    public final void replaceAll(BiFunction biFunction) {
        this.b.replaceAll(biFunction);
    }

    @Override // java.util.Map
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.b.values();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        return this.b.replace((com.android.tools.r8.graph.B2) obj, obj2, obj3);
    }

    public final void a(C0231j1 c0231j1, C0231j1 c0231j2) {
        this.b.put(c0231j1.g1(), c0231j2);
    }

    public final Object a(C0231j1 c0231j1) {
        C3263d c3263d = C3263d.b;
        return this.b.getOrDefault(c0231j1.g1(), c3263d);
    }

    public final Object a(com.android.tools.r8.graph.B2 b2, Function function) {
        return this.b.computeIfAbsent(b2, function);
    }

    public final void a(com.android.tools.r8.graph.B2 b2, BiFunction biFunction) {
        this.b.compute(b2, biFunction);
    }

    public final Object a(com.android.tools.r8.graph.B2 b2, Object obj) {
        return this.b.put(b2, obj);
    }
}
