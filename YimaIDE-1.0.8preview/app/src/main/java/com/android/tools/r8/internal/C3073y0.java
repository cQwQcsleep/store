package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3073y0 extends AbstractC2902w0 implements List {
    public final /* synthetic */ AbstractC3157z0 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3073y0(AbstractC3157z0 abstractC3157z0, Object obj, List list, AbstractC2902w0 abstractC2902w0) {
        super(abstractC3157z0, obj, list, abstractC2902w0);
        this.g = abstractC3157z0;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        b();
        boolean zIsEmpty = this.c.isEmpty();
        ((List) this.c).add(i, obj);
        this.g.g++;
        if (zIsEmpty) {
            a();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        b();
        int size = this.c.size();
        boolean zAddAll = ((List) this.c).addAll(i, collection);
        if (zAddAll) {
            int size2 = this.c.size();
            this.g.g += size2 - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }

    @Override // java.util.List
    public final Object get(int i) {
        b();
        return ((List) this.c).get(i);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        b();
        return ((List) this.c).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        b();
        return ((List) this.c).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        b();
        return new C2988x0(this);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        b();
        Object objRemove = ((List) this.c).remove(i);
        this.g.g--;
        c();
        return objRemove;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        b();
        return ((List) this.c).set(i, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.tools.r8.internal.w0] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    @Override // java.util.List
    public final List subList(int i, int i2) {
        b();
        AbstractC3157z0 abstractC3157z0 = this.g;
        Object obj = this.b;
        List listSubList = ((List) this.c).subList(i, i2);
        AbstractC2902w0 abstractC2902w0 = this.d;
        ?? r3 = this;
        if (abstractC2902w0 != null) {
            r3 = abstractC2902w0;
        }
        abstractC3157z0.getClass();
        return listSubList instanceof RandomAccess ? new C2731u0(abstractC3157z0, obj, listSubList, r3) : new C3073y0(abstractC3157z0, obj, listSubList, r3);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        b();
        return new C2988x0(this, i);
    }
}
