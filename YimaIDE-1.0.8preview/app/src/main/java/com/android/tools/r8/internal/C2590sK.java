package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2590sK extends O0 implements InterfaceC2676tL, Serializable {
    public transient C2334pK f;
    public transient C2334pK g;
    public final transient HashMap h;
    public transient int i;
    public transient int j;

    public C2590sK() {
        int i = WW.a;
        this.h = new HashMap(AbstractC1739iN.a(12));
    }

    public final C2334pK a(Object obj, Object obj2, C2334pK c2334pK) {
        C2334pK c2334pK2 = new C2334pK(obj, obj2);
        if (this.f == null) {
            this.g = c2334pK2;
            this.f = c2334pK2;
            this.h.put(obj, new C2248oK(c2334pK2));
            this.j++;
        } else if (c2334pK == null) {
            C2334pK c2334pK3 = this.g;
            Objects.requireNonNull(c2334pK3);
            c2334pK3.d = c2334pK2;
            c2334pK2.e = this.g;
            this.g = c2334pK2;
            C2248oK c2248oK = (C2248oK) this.h.get(obj);
            if (c2248oK == null) {
                this.h.put(obj, new C2248oK(c2334pK2));
                this.j++;
            } else {
                c2248oK.c++;
                C2334pK c2334pK4 = c2248oK.b;
                c2334pK4.f = c2334pK2;
                c2334pK2.g = c2334pK4;
                c2248oK.b = c2334pK2;
            }
        } else {
            C2248oK c2248oK2 = (C2248oK) this.h.get(obj);
            Objects.requireNonNull(c2248oK2);
            c2248oK2.c++;
            c2334pK2.e = c2334pK.e;
            c2334pK2.g = c2334pK.g;
            c2334pK2.d = c2334pK;
            c2334pK2.f = c2334pK;
            C2334pK c2334pK5 = c2334pK.g;
            if (c2334pK5 == null) {
                c2248oK2.a = c2334pK2;
            } else {
                c2334pK5.f = c2334pK2;
            }
            C2334pK c2334pK6 = c2334pK.e;
            if (c2334pK6 == null) {
                this.f = c2334pK2;
            } else {
                c2334pK6.d = c2334pK2;
            }
            c2334pK.e = c2334pK2;
            c2334pK.g = c2334pK2;
        }
        this.i++;
        return c2334pK2;
    }

    @Override // com.android.tools.r8.internal.WP
    public final void clear() {
        this.f = null;
        this.g = null;
        this.h.clear();
        this.i = 0;
        this.j++;
    }

    @Override // com.android.tools.r8.internal.WP
    public final boolean containsKey(Object obj) {
        return this.h.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Map d() {
        return new YP(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Set e() {
        return new C2077mK(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final InterfaceC1231cQ f() {
        return new C1147bQ(this);
    }

    @Override // com.android.tools.r8.internal.O0
    public final Iterator g() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection get(Object obj) {
        return new C1905kK(this, obj);
    }

    public final Collection i() {
        return new C1991lK(this);
    }

    @Override // com.android.tools.r8.internal.O0, com.android.tools.r8.internal.WP
    public final boolean isEmpty() {
        return this.f == null;
    }

    public final List j() {
        Collection collectionI = this.b;
        if (collectionI == null) {
            collectionI = i();
            this.b = collectionI;
        }
        return (List) collectionI;
    }

    @Override // com.android.tools.r8.internal.WP
    public final int size() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.WP
    public final List get(Object obj) {
        return new C1905kK(this, obj);
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a() {
        Collection collectionI = this.b;
        if (collectionI == null) {
            collectionI = i();
            this.b = collectionI;
        }
        return (List) collectionI;
    }

    public static void a(C2590sK c2590sK, C2334pK c2334pK) {
        c2590sK.getClass();
        C2334pK c2334pK2 = c2334pK.e;
        C2334pK c2334pK3 = c2334pK.d;
        if (c2334pK2 != null) {
            c2334pK2.d = c2334pK3;
        } else {
            c2590sK.f = c2334pK3;
        }
        C2334pK c2334pK4 = c2334pK.d;
        if (c2334pK4 != null) {
            c2334pK4.e = c2334pK2;
        } else {
            c2590sK.g = c2334pK2;
        }
        if (c2334pK.g == null && c2334pK.f == null) {
            C2248oK c2248oK = (C2248oK) c2590sK.h.remove(c2334pK.b);
            Objects.requireNonNull(c2248oK);
            c2248oK.c = 0;
            c2590sK.j++;
        } else {
            C2248oK c2248oK2 = (C2248oK) c2590sK.h.get(c2334pK.b);
            Objects.requireNonNull(c2248oK2);
            c2248oK2.c--;
            C2334pK c2334pK5 = c2334pK.g;
            C2334pK c2334pK6 = c2334pK.f;
            if (c2334pK5 == null) {
                Objects.requireNonNull(c2334pK6);
                c2248oK2.a = c2334pK6;
            } else {
                c2334pK5.f = c2334pK6;
            }
            C2334pK c2334pK7 = c2334pK.f;
            C2334pK c2334pK8 = c2334pK.g;
            if (c2334pK7 == null) {
                Objects.requireNonNull(c2334pK8);
                c2248oK2.b = c2334pK8;
            } else {
                c2334pK7.g = c2334pK8;
            }
        }
        c2590sK.i--;
    }

    @Override // com.android.tools.r8.internal.WP
    public final Collection a(Object obj) {
        C2504rK c2504rK = new C2504rK(this, obj);
        ArrayList arrayList = new ArrayList();
        NC.a(arrayList, c2504rK);
        List listUnmodifiableList = Collections.unmodifiableList(arrayList);
        C2248oK c2248oK = (C2248oK) this.h.get(obj);
        C2334pK c2334pK = c2248oK == null ? null : c2248oK.a;
        while (c2334pK != null) {
            if (c2334pK != null) {
                C2334pK c2334pK2 = c2334pK.f;
                a(this, c2334pK);
                c2334pK = c2334pK2;
            } else {
                z0e.a();
                return null;
            }
        }
        return listUnmodifiableList;
    }
}
