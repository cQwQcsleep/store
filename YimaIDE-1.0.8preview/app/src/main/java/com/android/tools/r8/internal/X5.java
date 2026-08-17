package com.android.tools.r8.internal;

import com.android.tools.r8.internal.X5;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class X5 implements InterfaceC1911kQ {
    public static final /* synthetic */ boolean d = true;
    public final Map b;
    public final Map c;

    public X5(AbstractMap abstractMap, AbstractMap abstractMap2) {
        this.b = abstractMap;
        this.c = abstractMap2;
    }

    public static /* synthetic */ Set e(Object obj) {
        return new LinkedHashSet();
    }

    @Override // com.android.tools.r8.internal.Y5
    public final void a(final BiConsumer biConsumer) {
        this.c.forEach(new BiConsumer() { // from class: ixf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept((Set) obj2, obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.InterfaceC1911kQ
    public Set b(Object obj) {
        Set set = (Set) this.c.remove(obj);
        if (set == null) {
            return Collections.EMPTY_SET;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Object objRemove = this.b.remove(it.next());
            if (!d && objRemove != obj) {
                x1f.a();
                return null;
            }
        }
        return set;
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsValue(Object obj) {
        return this.c.containsKey(obj);
    }

    public Object f(Object obj) {
        Object objRemove = this.b.remove(obj);
        if (objRemove != null) {
            Set set = (Set) this.c.get(objRemove);
            set.remove(obj);
            if (set.isEmpty()) {
                this.c.remove(objRemove);
            }
        }
        return objRemove;
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
        return this.b.getOrDefault(obj, obj2);
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Set keySet() {
        return this.b.keySet();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1911kQ
    /* JADX INFO: renamed from: put, reason: merged with bridge method [inline-methods] */
    public final Object a(Object obj, Object obj2) {
        Object objF = f(obj);
        this.b.put(obj, obj2);
        ((Set) this.c.computeIfAbsent(obj2, new Function() { // from class: fxf
            @Override // java.util.function.Function
            public final Object apply(Object obj3) {
                return X5.e(obj3);
            }
        })).add(obj);
        return objF;
    }

    @Override // com.android.tools.r8.internal.Y5
    /* JADX INFO: renamed from: values */
    public final Set mo15values() {
        return this.c.keySet();
    }

    @Override // com.android.tools.r8.internal.Y5
    public final Map e() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.V5
    public final Set a(Object obj) {
        return (Set) this.c.getOrDefault(obj, Collections.EMPTY_SET);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1911kQ
    public final void a(Iterable iterable, final Object obj) {
        iterable.forEach(new Consumer() { // from class: gxf
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                this.b.a(obj, obj2);
            }
        });
    }

    public final void b(Set set) {
        set.forEach(new Consumer() { // from class: hxf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.f(obj);
            }
        });
    }
}
