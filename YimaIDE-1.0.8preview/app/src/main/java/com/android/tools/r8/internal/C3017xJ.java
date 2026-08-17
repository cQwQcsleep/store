package com.android.tools.r8.internal;

import defpackage.g3c;
import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xJ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3017xJ extends AbstractList implements InterfaceC3186zJ, RandomAccess {
    public static final Hk0 c = new Hk0(new C3017xJ());
    public final ArrayList b;

    public C3017xJ(InterfaceC3186zJ interfaceC3186zJ) {
        this.b = new ArrayList(interfaceC3186zJ.size());
        addAll(interfaceC3186zJ);
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final void a(CL cl) {
        this.b.add(cl);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        this.b.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        if (collection instanceof InterfaceC3186zJ) {
            collection = ((InterfaceC3186zJ) collection).h();
        }
        boolean zAddAll = this.b.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.b.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final Hk0 f() {
        return new Hk0(this);
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final T7 g(int i) {
        T7 cl;
        Object obj = this.b.get(i);
        if (obj instanceof T7) {
            cl = (T7) obj;
        } else if (obj instanceof String) {
            cl = T7.a((String) obj);
        } else {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            cl = new CL(bArr2);
        }
        if (cl != obj) {
            this.b.set(i, cl);
        }
        return cl;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        Object obj = this.b.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof T7) {
            T7 t7 = (T7) obj;
            String strG = t7.g();
            if (t7.c()) {
                this.b.set(i, strG);
            }
            return strG;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = AbstractC1470fB.a;
        try {
            String str = new String(bArr, "UTF-8");
            if (AbstractC2115ml0.b(bArr, 0, bArr.length) == 0) {
                this.b.set(i, str);
            }
            return str;
        } catch (UnsupportedEncodingException e) {
            g3c.a("UTF-8 not supported?", e);
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC3186zJ
    public final List h() {
        return Collections.unmodifiableList(this.b);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        Object objRemove = this.b.remove(i);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (objRemove instanceof T7) {
            return ((T7) objRemove).g();
        }
        byte[] bArr = (byte[]) objRemove;
        byte[] bArr2 = AbstractC1470fB.a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            g3c.a("UTF-8 not supported?", e);
            return null;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        Object obj2 = this.b.set(i, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 instanceof T7) {
            return ((T7) obj2).g();
        }
        byte[] bArr = (byte[]) obj2;
        byte[] bArr2 = AbstractC1470fB.a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            g3c.a("UTF-8 not supported?", e);
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.size();
    }

    public C3017xJ() {
        this.b = new ArrayList();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.b.size(), collection);
    }
}
