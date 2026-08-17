package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2031lm implements E5 {
    public static final /* synthetic */ boolean f = true;
    public final H5[] a;
    public final H5[] b;
    public final int d;
    public final H5 c = new H5();
    public boolean e = false;

    public C2031lm(C0705Nt c0705Nt, int i) {
        H5[] h5Arr;
        boolean z = f;
        if (!z && i == 0) {
            x1f.a();
            throw null;
        }
        if (!z && i != 2 && !c0705Nt.p().isEmpty()) {
            x1f.a();
            throw null;
        }
        AbstractC0551Hu abstractC0551HuD = c0705Nt.D();
        Ck0 it = abstractC0551HuD.iterator();
        while (it.hasNext()) {
            H5 h5 = (H5) it.next();
            if (h5.h().v2()) {
                this.c.l().add(h5);
            }
        }
        int size = c0705Nt.d.size();
        if (i == 2) {
            this.a = new H5[size + 1];
            int iA = c0705Nt.A();
            Ck0 it2 = abstractC0551HuD.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                H5 h6 = (H5) it2.next();
                this.a[i2] = h6;
                h6.b(iA);
                i2++;
            }
            this.a[i2] = this.c;
            int i3 = i2 + 1;
            this.d = i3;
            for (H5 h7 : c0705Nt.d) {
                if (!h7.a(iA)) {
                    this.a[i3] = h7;
                    i3++;
                }
            }
            c0705Nt.a(iA);
        } else {
            int i4 = size + 1;
            H5[] h5Arr2 = (H5[]) abstractC0551HuD.toArray(new H5[i4]);
            this.a = h5Arr2;
            h5Arr2[size] = this.c;
            this.d = i4;
        }
        int i5 = 0;
        while (true) {
            h5Arr = this.a;
            if (i5 >= h5Arr.length) {
                break;
            }
            h5Arr[i5].c(i5);
            i5++;
        }
        H5[] h5Arr3 = new H5[h5Arr.length];
        this.b = h5Arr3;
        h5Arr3[0] = h5Arr[0];
        boolean z2 = true;
        while (z2) {
            z2 = false;
            int i6 = 1;
            while (true) {
                H5[] h5Arr4 = this.a;
                if (i6 < h5Arr4.length) {
                    H5 h8 = h5Arr4[i6];
                    int i7 = -1;
                    H5 h9 = null;
                    for (int i8 = 0; h9 == null && i8 < h8.s().size(); i8++) {
                        H5 h10 = h8.s().get(i8);
                        if (this.b[h10.o()] != null) {
                            i7 = i8;
                            h9 = h10;
                        }
                    }
                    for (int i9 = 0; i9 < h8.s().size(); i9++) {
                        H5 h11 = h8.s().get(i9);
                        if (i9 != i7 && this.b[h11.o()] != null) {
                            while (h11 != h9) {
                                while (h11.o() > h9.o()) {
                                    h11 = this.b[h11.o()];
                                }
                                while (h9.o() > h11.o()) {
                                    h9 = this.b[h9.o()];
                                }
                            }
                            h9 = h11;
                        }
                    }
                    if (this.b[h8.o()] != h9) {
                        this.b[h8.o()] = h9;
                        z2 = true;
                    }
                    i6++;
                }
            }
        }
        if (f) {
            return;
        }
        Iterator<H5> it3 = c0705Nt.d.iterator();
        while (it3.hasNext()) {
            it3.next().a(this);
        }
    }

    public final boolean a(H5 h5, H5 h6) {
        boolean z = f;
        if (!z && this.e) {
            x1f.a();
            return false;
        }
        if (h5 == h6) {
            return true;
        }
        if (!z && this.e) {
            x1f.a();
            return false;
        }
        if (h5.o() != 0 && h5 != this.c) {
            do {
                if (!f && this.e) {
                    x1f.a();
                    return false;
                }
                h5 = this.b[h5.o()];
                if (h5.o() < h6.o()) {
                }
            } while (h5 != h6);
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Dominators\n");
        for (H5 h5 : this.a) {
            sb.append(h5.o());
            sb.append(": ");
            sb.append(this.b[h5.o()].o());
            sb.append("\n");
        }
        return sb.toString();
    }

    public final Collection a(H5 h5, Collection collection) {
        if (!f && this.e) {
            x1f.a();
            return null;
        }
        for (int iO = h5.o(); iO < this.d; iO++) {
            H5 h6 = this.a[iO];
            if (a(h6, h5)) {
                collection.add(h6);
            }
        }
        return collection;
    }

    public final Iterable a(final H5 h5) {
        final EnumC1945km enumC1945km = EnumC1945km.b;
        if (f || !this.e) {
            return new Iterable() { // from class: ekh
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return this.b.a(h5, enumC1945km);
                }
            };
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ Iterator a(H5 h5, EnumC1945km enumC1945km) {
        C1860jm c1860jm = new C1860jm(this, h5);
        if (enumC1945km == EnumC1945km.b) {
            H5 h6 = (H5) c1860jm.next();
            if (!f && h6 != h5) {
                x1f.a();
                return null;
            }
        }
        return c1860jm;
    }
}
