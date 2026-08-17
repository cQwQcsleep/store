package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1105av;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.av, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1105av extends A5 implements Serializable {
    public final transient AbstractC0706Nu f;
    public final transient int g;

    public AbstractC1105av(int i, AbstractC0706Nu abstractC0706Nu) {
        this.f = abstractC0706Nu;
        this.g = i;
    }

    public static /* synthetic */ Spliterator a(Map.Entry entry) {
        final Object key = entry.getKey();
        return AbstractC1165be.a(((Collection) entry.getValue()).spliterator(), new Function() { // from class: feg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC1105av.a(key, obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.O0, com.android.tools.r8.internal.WP
    public final InterfaceC1231cQ c() {
        return (AbstractC1529fv) super.c();
    }

    @Override // com.android.tools.r8.internal.WP
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.WP
    public final boolean containsKey(Object obj) {
        return this.f.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Map d() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.O0
    public final Set e() {
        throw new AssertionError("unreachable");
    }

    @Override // com.android.tools.r8.internal.O0
    public final InterfaceC1231cQ f() {
        return new C1018Zu(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Iterator g() {
        return new C0966Xu(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Spliterator h() {
        return AbstractC1165be.a(b().entrySet().spliterator(), new Function() { // from class: eeg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC1105av.a((Map.Entry) obj);
            }
        }, 64, this.g);
    }

    @Override // com.android.tools.r8.internal.O0, com.android.tools.r8.internal.WP
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public AbstractC0706Nu b() {
        return this.f;
    }

    public final Collection j() {
        return new C0992Yu(this);
    }

    @Override // com.android.tools.r8.internal.O0, com.android.tools.r8.internal.WP
    public final Set keySet() {
        return this.f.keySet();
    }

    @Override // com.android.tools.r8.internal.O0, com.android.tools.r8.internal.WP
    public final boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.WP
    public final int size() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a() {
        Collection collectionJ = this.b;
        if (collectionJ == null) {
            collectionJ = j();
            this.b = collectionJ;
        }
        return (AbstractC3066xu) collectionJ;
    }

    public static Map.Entry a(Object obj, Object obj2) {
        return new C3236zu(obj, obj2);
    }
}
