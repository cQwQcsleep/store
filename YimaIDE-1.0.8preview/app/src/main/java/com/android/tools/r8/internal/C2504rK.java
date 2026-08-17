package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2504rK implements ListIterator {
    public final Object b;
    public int c;
    public C2334pK d;
    public C2334pK e;
    public C2334pK f;
    public final /* synthetic */ C2590sK g;

    public C2504rK(C2590sK c2590sK, Object obj, int i) {
        this.g = c2590sK;
        C2248oK c2248oK = (C2248oK) c2590sK.h.get(obj);
        int i2 = c2248oK == null ? 0 : c2248oK.c;
        DX.b(i, i2);
        if (i < i2 / 2) {
            this.d = c2248oK == null ? null : c2248oK.a;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                next();
                i = i3;
            }
        } else {
            this.f = c2248oK == null ? null : c2248oK.b;
            this.c = i2;
            while (true) {
                int i4 = i + 1;
                if (i >= i2) {
                    break;
                }
                previous();
                i = i4;
            }
        }
        this.b = obj;
        this.e = null;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        this.f = this.g.a(this.b, obj, this.d);
        this.c++;
        this.e = null;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.d != null;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f != null;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C2334pK c2334pK = this.d;
        if (c2334pK == null) {
            z0e.a();
            return null;
        }
        this.e = c2334pK;
        this.f = c2334pK;
        this.d = c2334pK.f;
        this.c++;
        return c2334pK.c;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.c;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        C2334pK c2334pK = this.f;
        if (c2334pK == null) {
            z0e.a();
            return null;
        }
        this.e = c2334pK;
        this.d = c2334pK;
        this.f = c2334pK.g;
        this.c--;
        return c2334pK.c;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.c - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        C2334pK c2334pK = this.e;
        if (!(c2334pK != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        if (c2334pK != this.d) {
            this.f = c2334pK.g;
            this.c--;
        } else {
            this.d = c2334pK.f;
        }
        C2590sK.a(this.g, c2334pK);
        this.e = null;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        C2334pK c2334pK = this.e;
        if (c2334pK != null) {
            c2334pK.c = obj;
        } else {
            g33.a();
        }
    }

    public C2504rK(C2590sK c2590sK, Object obj) {
        this.g = c2590sK;
        this.b = obj;
        C2248oK c2248oK = (C2248oK) c2590sK.h.get(obj);
        this.d = c2248oK == null ? null : c2248oK.a;
    }
}
