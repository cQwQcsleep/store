package com.android.tools.r8.internal;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0421Cu extends C0789Qz {
    public final InterfaceC0763Pz c;

    public C0421Cu(C0944Wy c0944Wy) {
        this.c = c0944Wy;
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object a(int i, Object obj) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz b(int i) {
        return this.c.b(i);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2045lz
    public final JU c() {
        return this.c.c();
    }

    @Override // java.util.Map
    public final Object compute(Object obj, BiFunction biFunction) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // java.util.Map
    public final Object computeIfAbsent(Object obj, Function function) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // java.util.Map
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    public final int d() {
        return this.c.d();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz
    /* JADX INFO: renamed from: e */
    public final JU entrySet() {
        return this.c.entrySet();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final NU entrySet() {
        return this.c.entrySet();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final Integer firstKey() {
        return this.c.firstKey();
    }

    @Override // com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC1109az
    public final Object get(int i) {
        return this.c.get(i);
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return this.c.getOrDefault(obj, obj2);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return this.c.headMap((Integer) obj);
    }

    @Override // com.android.tools.r8.internal.C2130mz, java.util.Map
    public final boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final InterfaceC3177zA keySet() {
        return this.c.keySet();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final Integer lastKey() {
        return this.c.lastKey();
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C2130mz, java.util.Map
    public final void putAll(Map map) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC1109az
    public final Object remove(int i) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return this.c.size();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return this.c.subMap((Integer) obj, (Integer) obj2);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return this.c.tailMap((Integer) obj);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: b */
    public final InterfaceC0763Pz headMap(Integer num) {
        return this.c.headMap(num);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2045lz
    public final NU c() {
        return this.c.c();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final Set entrySet() {
        return this.c.entrySet();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, java.util.SortedMap
    public final Object firstKey() {
        return this.c.firstKey();
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final Object get(Object obj) {
        return this.c.get(obj);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final CA keySet() {
        return this.c.keySet();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, java.util.SortedMap
    public final Object lastKey() {
        return this.c.lastKey();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    public final int a() {
        return this.c.a();
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz c(int i) {
        return this.c.c(i);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.C2130mz, java.util.Map, java.util.SortedMap
    public final Set keySet() {
        return this.c.keySet();
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: a */
    public final InterfaceC0763Pz tailMap(Integer num) {
        return this.c.tailMap(num);
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final Object remove(Object obj) {
        throw new Kk0("Should not modify an immutable structure");
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    /* JADX INFO: renamed from: a */
    public final InterfaceC0763Pz subMap(Integer num, Integer num2) {
        return this.c.subMap(num, num2);
    }

    @Override // com.android.tools.r8.internal.C0789Qz, com.android.tools.r8.internal.InterfaceC0763Pz
    public final InterfaceC0763Pz a(int i, int i2) {
        return this.c.a(i, i2);
    }

    @Override // com.android.tools.r8.internal.P, com.android.tools.r8.internal.InterfaceC2045lz
    /* JADX INFO: renamed from: a */
    public final Object put(Integer num, Object obj) {
        throw new Kk0("Should not modify an immutable structure");
    }
}
