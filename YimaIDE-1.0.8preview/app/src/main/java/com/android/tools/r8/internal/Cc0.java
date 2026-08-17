package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Cc0 extends AbstractC2554rv {
    public final transient Object d;

    public Cc0(Object obj) {
        obj.getClass();
        this.d = obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final AbstractC0551Hu a() {
        return new Bc0(this.d);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.d.equals(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.d.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return new IC(this.d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.d.toString() + ']';
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        objArr[i] = this.d;
        return i + 1;
    }
}
