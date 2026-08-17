package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0988Yq extends G {
    public int g;
    public ArrayList h;
    public ArrayList i;

    public C0988Yq(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        super(-1);
        this.g = i;
        int i4 = 0;
        if (i == -1 || i == 0) {
            ArrayList arrayList = new ArrayList(i2);
            for (int i5 = 0; i5 < i2; i5++) {
                arrayList.add(objArr[i5]);
            }
            this.h = arrayList;
            ArrayList arrayList2 = new ArrayList(i3);
            while (i4 < i3) {
                arrayList2.add(objArr2[i4]);
                i4++;
            }
            this.i = arrayList2;
            return;
        }
        if (i == 1) {
            ArrayList arrayList3 = new ArrayList(i2);
            while (i4 < i2) {
                arrayList3.add(objArr[i4]);
                i4++;
            }
            this.h = arrayList3;
            return;
        }
        if (i == 2) {
            ArrayList arrayList4 = new ArrayList(i2);
            while (i4 < i2) {
                arrayList4.add(null);
                i4++;
            }
            this.h = arrayList4;
            return;
        }
        if (i != 3) {
            if (i != 4) {
                j2d.a();
                throw null;
            }
            ArrayList arrayList5 = new ArrayList(1);
            arrayList5.add(objArr2[0]);
            this.i = arrayList5;
        }
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        int i = this.g;
        if (i == -1 || i == 0) {
            xo.a(i, this.h.size(), a(this.h), this.i.size(), a(this.i));
            return;
        }
        if (i == 1) {
            xo.a(i, this.h.size(), a(this.h), 0, (Object[]) null);
            return;
        }
        if (i == 2) {
            xo.a(i, this.h.size(), (Object[]) null, 0, (Object[]) null);
            return;
        }
        if (i == 3) {
            xo.a(i, 0, (Object[]) null, 0, (Object[]) null);
        } else if (i == 4) {
            xo.a(i, 0, (Object[]) null, 1, a(this.i));
        } else {
            j2d.a();
        }
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 14;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        C0988Yq c0988Yq = new C0988Yq();
        c0988Yq.g = this.g;
        if (this.h != null) {
            c0988Yq.h = new ArrayList();
            int size = this.h.size();
            for (int i = 0; i < size; i++) {
                Object objA = this.h.get(i);
                if (objA instanceof XI) {
                    objA = rc.a((XI) objA);
                }
                c0988Yq.h.add(objA);
            }
        }
        if (this.i != null) {
            c0988Yq.i = new ArrayList();
            int size2 = this.i.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Object objA2 = this.i.get(i2);
                if (objA2 instanceof XI) {
                    objA2 = rc.a((XI) objA2);
                }
                c0988Yq.i.add(objA2);
            }
        }
        return c0988Yq;
    }

    public C0988Yq() {
        super(-1);
    }

    public static Object[] a(List list) {
        int size = list.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            Object objB = list.get(i);
            if (objB instanceof XI) {
                objB = ((XI) objB).b();
            }
            objArr[i] = objB;
        }
        return objArr;
    }
}
