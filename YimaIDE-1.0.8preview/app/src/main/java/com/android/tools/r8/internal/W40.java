package com.android.tools.r8.internal;

import java.util.Spliterator;
import java.util.Spliterators;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W40 extends AbstractC1955kv {
    public static final Object[] i;
    public static final W40 j;
    public final transient Object[] e;
    public final transient int f;
    public final transient Object[] g;
    public final transient int h;

    static {
        Object[] objArr = new Object[0];
        i = objArr;
        j = new W40(0, 0, objArr, objArr);
    }

    public W40(int i2, int i3, Object[] objArr, Object[] objArr2) {
        this.e = objArr;
        this.f = i2;
        this.g = objArr2;
        this.h = i3;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i2, Object[] objArr) {
        Object[] objArr2 = this.e;
        System.arraycopy(objArr2, 0, objArr, i2, objArr2.length);
        return i2 + this.e.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final Object[] b() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int c() {
        return this.e.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Object[] objArr = this.g;
        if (obj == null || objArr.length == 0) {
            return false;
        }
        int iA = AbstractC1189bt.a(obj);
        while (true) {
            int i2 = iA & this.h;
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iA = i2 + 1;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int d() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        Object[] objArr = this.e;
        return NC.a(objArr.length, 0, objArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return this.g.length == 0 ? P40.e : new K40(this, this.e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.e.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this.e, 1297);
    }
}
