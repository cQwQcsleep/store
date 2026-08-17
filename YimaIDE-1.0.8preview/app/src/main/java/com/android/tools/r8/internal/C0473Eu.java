package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Eu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0473Eu extends AbstractC2981wu {
    public Object[] a;
    public int b;
    public boolean c;

    public C0473Eu(int i) {
        this.a = new Object[i];
        this.b = 0;
    }

    public final void a(int i) {
        Object[] objArr = this.a;
        if (objArr.length < i) {
            this.a = Arrays.copyOf(objArr, AbstractC2981wu.a(objArr.length, i));
            this.c = false;
        } else if (this.c) {
            this.a = Arrays.copyOf(objArr, objArr.length);
            this.c = false;
        }
    }

    public final C0473Eu b(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            a(collection.size() + this.b);
            if (collection instanceof AbstractC3066xu) {
                this.b = ((AbstractC3066xu) collection).a(this.b, this.a);
                return this;
            }
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        return this;
    }

    public C0473Eu() {
        this(4);
    }

    public final void a(int i, Object[] objArr) {
        a(this.b + i);
        System.arraycopy(objArr, 0, this.a, this.b, i);
        this.b += i;
    }

    public final AbstractC0551Hu a() {
        this.c = true;
        return AbstractC0551Hu.b(this.b, this.a);
    }

    public final C0473Eu a(C0473Eu c0473Eu) {
        c0473Eu.getClass();
        a(c0473Eu.b, c0473Eu.a);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2981wu
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C0473Eu a(Object obj) {
        obj.getClass();
        a(this.b + 1);
        Object[] objArr = this.a;
        int i = this.b;
        this.b = i + 1;
        objArr[i] = obj;
        return this;
    }
}
