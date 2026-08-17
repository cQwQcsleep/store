package com.android.tools.r8.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ck, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1260ck extends AbstractC2027lk {
    public final C0643Li b;
    public final String c;
    public final C1941kk d;
    public final C1515fk[] e;
    public final C1515fk[] f;
    public final int g;
    public HashMap h = null;
    public ReferenceQueue i = null;

    public C1260ck(C0643Li c0643Li, C1941kk c1941kk, C0955Xj c0955Xj) throws C1091ak {
        C1515fk[] c1515fkArr;
        this.b = c0643Li;
        this.c = AbstractC2370pk.a(c1941kk, c0955Xj, c0643Li.k());
        this.d = c1941kk;
        if (c0643Li.g.size() == 0) {
            throw new C1091ak(this, "Enums must contain at least one value.");
        }
        this.e = new C1515fk[c0643Li.g.size()];
        int i = 0;
        int i2 = 0;
        while (true) {
            int size = c0643Li.g.size();
            c1515fkArr = this.e;
            if (i2 >= size) {
                break;
            }
            c1515fkArr[i2] = new C1515fk((C0798Ri) c0643Li.g.get(i2), c1941kk, this);
            i2++;
        }
        C1515fk[] c1515fkArr2 = (C1515fk[]) c1515fkArr.clone();
        this.f = c1515fkArr2;
        Arrays.sort(c1515fkArr2, C1515fk.e);
        for (int i3 = 1; i3 < c0643Li.g.size(); i3++) {
            C1515fk[] c1515fkArr3 = this.f;
            C1515fk c1515fk = c1515fkArr3[i];
            C1515fk c1515fk2 = c1515fkArr3[i3];
            if (c1515fk.b.g != c1515fk2.b.g) {
                i++;
                c1515fkArr3[i] = c1515fk2;
            }
        }
        int i4 = i + 1;
        this.g = i4;
        Arrays.fill(this.f, i4, c0643Li.g.size(), (Object) null);
        c1941kk.h.a(this);
    }

    public final C1515fk b(int i) {
        C1515fk[] c1515fkArr = this.f;
        int i2 = this.g - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            C1515fk c1515fk = c1515fkArr[i4];
            int i5 = c1515fk.b.g;
            if (i < i5) {
                i2 = i4 - 1;
            } else {
                if (i <= i5) {
                    return c1515fk;
                }
                i3 = i4 + 1;
            }
        }
        return null;
    }

    public final C1515fk c(int i) {
        C1515fk c1515fk;
        C1515fk c1515fkB = b(i);
        if (c1515fkB != null) {
            return c1515fkB;
        }
        synchronized (this) {
            try {
                if (this.i == null) {
                    this.i = new ReferenceQueue();
                    this.h = new HashMap();
                } else {
                    while (true) {
                        C1177bk c1177bk = (C1177bk) this.i.poll();
                        if (c1177bk == null) {
                            break;
                        }
                        this.h.remove(Integer.valueOf(c1177bk.a));
                    }
                }
                WeakReference weakReference = (WeakReference) this.h.get(Integer.valueOf(i));
                c1515fk = weakReference == null ? null : (C1515fk) weakReference.get();
                if (c1515fk == null) {
                    c1515fk = new C1515fk(this, Integer.valueOf(i));
                    this.h.put(Integer.valueOf(i), new C1177bk(i, c1515fk));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return c1515fk;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.c;
    }
}
