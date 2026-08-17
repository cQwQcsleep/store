package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2836vA extends AbstractC1111b0 implements Serializable {
    public transient int[] b;
    public transient int c;
    public transient boolean d;
    public transient int e;
    public transient int f;
    public int g;
    public final float h;

    public C2836vA(int i) {
        if (i < 0) {
            w01.a("The expected number of elements must be nonnegative");
            throw null;
        }
        this.h = 0.75f;
        int iA = AbstractC0938Ws.a(i, 0.75f);
        this.e = iA;
        this.c = iA - 1;
        this.f = AbstractC0938Ws.b(iA, 0.75f);
        this.b = new int[this.e + 1];
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean a(InterfaceC1215cA interfaceC1215cA) {
        if (this.h <= 0.5d) {
            int iA = AbstractC0938Ws.a(interfaceC1215cA.size(), this.h);
            if (iA > this.e) {
                l(iA);
            }
        } else {
            int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((interfaceC1215cA.size() + this.g) / this.h))));
            if (iMin > this.e) {
                l(iMin);
            }
        }
        return super.a(interfaceC1215cA);
    }

    @Override // com.android.tools.r8.internal.V, com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean add(int i) {
        int i2;
        if (i != 0) {
            int[] iArr = this.b;
            int iA = AbstractC0938Ws.a(i) & this.c;
            int i3 = iArr[iA];
            if (i3 != 0) {
                if (i3 == i) {
                    return false;
                }
                do {
                    iA = (iA + 1) & this.c;
                    i2 = iArr[iA];
                    if (i2 != 0) {
                    }
                } while (i2 != i);
                return false;
            }
            iArr[iA] = i;
        } else {
            if (this.d) {
                return false;
            }
            this.d = true;
        }
        int i4 = this.g;
        this.g = i4 + 1;
        if (i4 >= this.f) {
            l(AbstractC0938Ws.a(i4 + 2, this.h));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        if (this.h <= 0.5d) {
            int iA = AbstractC0938Ws.a(collection.size(), this.h);
            if (iA > this.e) {
                l(iA);
            }
        } else {
            int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((collection.size() + this.g) / this.h))));
            if (iMin > this.e) {
                l(iMin);
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
        Arrays.fill(this.b, 0);
    }

    public final Object clone() {
        try {
            C2836vA c2836vA = (C2836vA) super.clone();
            c2836vA.b = (int[]) this.b.clone();
            c2836vA.d = this.d;
            return c2836vA;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        int i2;
        if (i == 0) {
            return this.d;
        }
        int[] iArr = this.b;
        int iA = AbstractC0938Ws.a(i) & this.c;
        int i3 = iArr[iA];
        if (i3 == 0) {
            return false;
        }
        if (i == i3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.c;
            i2 = iArr[iA];
            if (i2 == 0) {
                return false;
            }
        } while (i != i2);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i;
        boolean z = this.d;
        int i2 = this.g;
        if (z) {
            i2--;
        }
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        while (true) {
            int i6 = i4 - 1;
            if (i4 == 0) {
                return i3;
            }
            while (true) {
                i = this.b[i5];
                if (i == 0) {
                    i5++;
                }
            }
            i3 += i;
            i5++;
            i4 = i6;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.g == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C2751uA(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0
    public final boolean k(int i) {
        int i2;
        if (i == 0) {
            if (!this.d) {
                return false;
            }
            this.d = false;
            int[] iArr = this.b;
            int i3 = this.e;
            iArr[i3] = 0;
            int i4 = this.g - 1;
            this.g = i4;
            if (i4 < this.f / 4 && i3 > 16) {
                l(i3 / 2);
            }
            return true;
        }
        int[] iArr2 = this.b;
        int iA = AbstractC0938Ws.a(i) & this.c;
        int i5 = iArr2[iA];
        if (i5 == 0) {
            return false;
        }
        if (i == i5) {
            m(iA);
            return true;
        }
        do {
            iA = (iA + 1) & this.c;
            i2 = iArr2[iA];
            if (i2 == 0) {
                return false;
            }
        } while (i != i2);
        m(iA);
        return true;
    }

    public final void l(int i) {
        int i2;
        int[] iArr = this.b;
        int i3 = i - 1;
        int[] iArr2 = new int[i + 1];
        int i4 = this.e;
        boolean z = this.d;
        int i5 = this.g;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                this.e = i;
                this.c = i3;
                this.f = AbstractC0938Ws.b(i, this.h);
                this.b = iArr2;
                return;
            }
            do {
                i4--;
                i2 = iArr[i4];
            } while (i2 == 0);
            int iA = AbstractC0938Ws.a(i2) & i3;
            if (iArr2[iA] != 0) {
                do {
                    iA = (iA + 1) & i3;
                } while (iArr2[iA] != 0);
            }
            iArr2[iA] = iArr[i4];
            i5 = i6;
        }
    }

    public final void m(int i) {
        int i2;
        int i3;
        this.g--;
        int[] iArr = this.b;
        loop0: while (true) {
            int i4 = (i + 1) & this.c;
            while (true) {
                i2 = iArr[i4];
                if (i2 != 0) {
                    int iA = AbstractC0938Ws.a(i2);
                    int i5 = this.c;
                    int i6 = iA & i5;
                    if (i > i4) {
                        if (i >= i6 && i6 > i4) {
                            break;
                        } else {
                            i4 = (i4 + 1) & i5;
                        }
                    } else if (i >= i6 || i6 > i4) {
                        break;
                    } else {
                        i4 = (i4 + 1) & i5;
                    }
                } else {
                    break loop0;
                }
            }
            iArr[i] = i2;
            i = i4;
        }
        iArr[i] = 0;
        if (this.g >= this.f / 4 || (i3 = this.e) <= 16) {
            return;
        }
        l(i3 / 2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.g;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2751uA(this);
    }
}
