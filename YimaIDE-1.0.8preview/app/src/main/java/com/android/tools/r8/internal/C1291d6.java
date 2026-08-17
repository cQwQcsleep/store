package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1291d6 implements InterfaceC1997lQ, Map {
    public final R5 b = C0860Ts.j();

    @Override // com.android.tools.r8.internal.V5
    public final Set a(Object obj) {
        return this.b.containsValue(obj) ? Collections.singleton(d(obj)) : Collections.EMPTY_SET;
    }

    @Override // com.android.tools.r8.internal.W5
    public final Object c(Object obj) {
        return this.b.get(obj);
    }

    @Override // java.util.Map
    public final void clear() {
        this.b.clear();
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsValue(Object obj) {
        return this.b.containsValue(obj);
    }

    @Override // com.android.tools.r8.internal.W5
    public final Object d(Object obj) {
        return this.b.f().get(obj);
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Map e() {
        return this.b;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.b.entrySet();
    }

    @Override // com.android.tools.r8.internal.V5
    public final void forEach(BiConsumer biConsumer) {
        this.b.forEach(biConsumer);
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Object get(Object obj) {
        return this.b.get(obj);
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = this.b.get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Set keySet() {
        return this.b.keySet();
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        return this.b.a(obj, obj2);
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        this.b.putAll(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Map
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.b.values();
    }

    @Override // com.android.tools.r8.internal.Y5
    /* JADX INFO: renamed from: values, reason: collision with other method in class */
    public final Set mo15values() {
        return this.b.values();
    }

    @Override // com.android.tools.r8.internal.Y5
    public final void a(final BiConsumer biConsumer) {
        this.b.forEach(new BiConsumer() { // from class: xlg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept(Collections.singleton(obj), obj2);
            }
        });
    }

    @Override // com.android.tools.r8.internal.InterfaceC1037a6
    public final void a(final InterfaceC1853ji0 interfaceC1853ji0) {
        this.b.forEach(new BiConsumer() { // from class: wlg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                interfaceC1853ji0.a(Collections.singleton(obj), obj2, obj);
            }
        });
    }

    public final void a(V5 v5) {
        v5.forEach(new BiConsumer() { // from class: vlg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.put(obj, obj2);
            }
        });
    }
}
