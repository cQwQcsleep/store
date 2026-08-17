package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC3157z0;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3157z0 extends O0 implements Serializable {
    public final transient Map f;
    public transient int g;

    public AbstractC3157z0(HashMap map) {
        if (map.isEmpty()) {
            this.f = map;
        } else {
            j2d.a();
            throw null;
        }
    }

    public static /* synthetic */ Spliterator a(Map.Entry entry) {
        final Object key = entry.getKey();
        return AbstractC1165be.a(((Collection) entry.getValue()).spliterator(), new Function() { // from class: mzi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3157z0.a(key, obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.WP
    public final void clear() {
        Iterator it = this.f.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        this.f.clear();
        this.g = 0;
    }

    @Override // com.android.tools.r8.internal.WP
    public final boolean containsKey(Object obj) {
        return this.f.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Map d() {
        return new C2390q0(this, this.f);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Set e() {
        return new C2645t0(this, this.f);
    }

    @Override // com.android.tools.r8.internal.O0
    public final InterfaceC1231cQ f() {
        return new C1147bQ(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Iterator g() {
        return new C2132n0(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Spliterator h() {
        return AbstractC1165be.a(this.f.entrySet().spliterator(), new Function() { // from class: nzi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3157z0.a((Map.Entry) obj);
            }
        }, 64, this.g);
    }

    public final Collection i() {
        return new N0(this);
    }

    @Override // com.android.tools.r8.internal.WP
    public final int size() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a() {
        Collection collection = this.b;
        if (collection != null) {
            return collection;
        }
        Collection collectionI = i();
        this.b = collectionI;
        return collectionI;
    }

    public static Map.Entry a(Object obj, Object obj2) {
        return new C3236zu(obj, obj2);
    }
}
