package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yJ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3101yJ extends AbstractC2220o1 implements AJ {
    public static final C3101yJ d;
    public final ArrayList c;

    static {
        C3101yJ c3101yJ = new C3101yJ();
        c3101yJ.b = false;
        d = c3101yJ;
    }

    public C3101yJ(AJ aj) {
        this.c = new ArrayList(aj.size());
        addAll(aj);
    }

    @Override // com.android.tools.r8.internal.AJ
    public final void a(U7 u7) {
        a();
        this.c.add(u7);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        a();
        this.c.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        a();
        if (collection instanceof AJ) {
            collection = ((AJ) collection).h();
        }
        boolean zAddAll = this.c.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.c.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.android.tools.r8.internal.AJ
    public final Object e(int i) {
        return this.c.get(i);
    }

    @Override // com.android.tools.r8.internal.AJ
    public final AJ f() {
        return this.b ? new Ik0(this) : this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        Object obj = this.c.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof U7) {
            U7 u7 = (U7) obj;
            String strC = u7.c();
            if (u7.a()) {
                this.c.set(i, strC);
            }
            return strC;
        }
        byte[] bArr = (byte[]) obj;
        String strA = AbstractC1556gB.a(bArr);
        if (AbstractC2201nl0.a.b(bArr, 0, bArr.length) == 0) {
            this.c.set(i, strA);
        }
        return strA;
    }

    @Override // com.android.tools.r8.internal.AJ
    public final List h() {
        return Collections.unmodifiableList(this.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        Object objRemove = this.c.remove(i);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        return objRemove instanceof U7 ? ((U7) objRemove).c() : AbstractC1556gB.a((byte[]) objRemove);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        a();
        Object obj2 = this.c.set(i, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        return obj2 instanceof U7 ? ((U7) obj2).c() : AbstractC1556gB.a((byte[]) obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c.size();
    }

    public C3101yJ() {
        this.c = new ArrayList(10);
    }

    @Override // com.android.tools.r8.internal.AbstractC2220o1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.c.size(), collection);
    }
}
