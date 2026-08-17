package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U30 extends AbstractC3075y1 implements Serializable {
    public transient Object[] b;
    public transient int c;
    public transient boolean d;
    public transient int e;
    public transient int f;
    public int g;
    public final float h = 0.75f;

    public U30() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.e = iA;
        this.c = iA - 1;
        this.f = AbstractC0938Ws.b(iA, 0.75f);
        this.b = new Object[this.e + 1];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        Object obj2;
        if (obj != null) {
            Object[] objArr = this.b;
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.c;
            Object obj3 = objArr[iA];
            if (obj3 != null) {
                if (obj3 == obj) {
                    return false;
                }
                do {
                    iA = (iA + 1) & this.c;
                    obj2 = objArr[iA];
                    if (obj2 != null) {
                    }
                } while (obj2 != obj);
                return false;
            }
            objArr[iA] = obj;
        } else {
            if (this.d) {
                return false;
            }
            this.d = true;
        }
        int i = this.g;
        this.g = i + 1;
        if (i >= this.f) {
            j(AbstractC0938Ws.a(i + 2, this.h));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        if (this.h <= 0.5d) {
            int iA = AbstractC0938Ws.a(collection.size(), this.h);
            if (iA > this.e) {
                j(iA);
            }
        } else {
            int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((collection.size() + this.g) / this.h))));
            if (iMin > this.e) {
                j(iMin);
            }
        }
        return super.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        if (this.g == 0) {
            return;
        }
        this.g = 0;
        this.d = false;
        Arrays.fill(this.b, (Object) null);
    }

    public final Object clone() {
        try {
            U30 u30 = (U30) super.clone();
            u30.b = (Object[]) this.b.clone();
            u30.d = this.d;
            return u30;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.d;
        }
        Object[] objArr = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.c;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.c;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3075y1, java.util.Collection, java.util.Set
    public final int hashCode() {
        Object obj;
        boolean z = this.d;
        int i = this.g;
        if (z) {
            i--;
        }
        int iIdentityHashCode = 0;
        int i2 = i;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 == 0) {
                return iIdentityHashCode;
            }
            while (true) {
                obj = this.b[i3];
                if (obj != null) {
                    break;
                }
                i3++;
            }
            if (this != obj) {
                iIdentityHashCode = System.identityHashCode(obj) + iIdentityHashCode;
            }
            i3++;
            i2 = i4;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.g == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.P30
    public final BU iterator() {
        return new T30(this);
    }

    public final void j(int i) {
        Object obj;
        Object[] objArr = this.b;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i + 1];
        int i3 = this.e;
        boolean z = this.d;
        int i4 = this.g;
        if (z) {
            i4--;
        }
        while (true) {
            int i5 = i4 - 1;
            if (i4 == 0) {
                this.e = i;
                this.c = i2;
                this.f = AbstractC0938Ws.b(i, this.h);
                this.b = objArr2;
                return;
            }
            do {
                i3--;
                obj = objArr[i3];
            } while (obj == null);
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & i2;
            if (objArr2[iA] != null) {
                do {
                    iA = (iA + 1) & i2;
                } while (objArr2[iA] != null);
            }
            objArr2[iA] = objArr[i3];
            i4 = i5;
        }
    }

    public final void k(int i) {
        Object obj;
        int i2;
        this.g--;
        Object[] objArr = this.b;
        loop0: while (true) {
            int i3 = (i + 1) & this.c;
            while (true) {
                obj = objArr[i3];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                    int i4 = this.c;
                    int i5 = iA & i4;
                    if (i > i3) {
                        if (i >= i5 && i5 > i3) {
                            break;
                        } else {
                            i3 = (i3 + 1) & i4;
                        }
                    } else if (i >= i5 || i5 > i3) {
                        break;
                    } else {
                        i3 = (i3 + 1) & i4;
                    }
                } else {
                    break loop0;
                }
            }
            objArr[i] = obj;
            i = i3;
        }
        objArr[i] = null;
        if (this.g >= this.f / 4 || (i2 = this.e) <= 16) {
            return;
        }
        j(i2 / 2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        Object obj2;
        if (obj == null) {
            if (!this.d) {
                return false;
            }
            this.d = false;
            Object[] objArr = this.b;
            int i = this.e;
            objArr[i] = null;
            int i2 = this.g - 1;
            this.g = i2;
            if (i2 < this.f / 4 && i > 16) {
                j(i / 2);
            }
            return true;
        }
        Object[] objArr2 = this.b;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.c;
        Object obj3 = objArr2[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            k(iA);
            return true;
        }
        do {
            iA = (iA + 1) & this.c;
            obj2 = objArr2[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        k(iA);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.g;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new T30(this);
    }
}
