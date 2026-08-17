package com.android.tools.r8.internal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2401q50 implements I0 {
    public I0 a;
    public List b;
    public boolean c;
    public ArrayList d;
    public boolean e;
    public C2315p50 f;

    public C2401q50(List list, boolean z, C0885Ur c0885Ur, boolean z2) {
        this.b = list;
        this.c = z;
        this.a = c0885Ur;
        this.e = z2;
    }

    public final void a(Iterable iterable) {
        int size;
        I0 i0;
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            J0 j0 = (J0) it.next();
            Charset charset = AbstractC1556gB.a;
            j0.getClass();
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.isEmpty()) {
                return;
            } else {
                size = collection.size();
            }
        } else {
            size = -1;
        }
        if (!this.c) {
            this.b = new ArrayList(this.b);
            this.c = true;
        }
        if (size >= 0) {
            List list = this.b;
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(list.size() + size);
            }
        }
        Iterator it2 = iterable.iterator();
        while (it2.hasNext()) {
            a((J0) it2.next());
        }
        if (this.e && (i0 = this.a) != null) {
            i0.a();
            this.e = false;
        }
        C2315p50 c2315p50 = this.f;
        if (c2315p50 != null) {
            c2315p50.a();
        }
    }

    public final List b() {
        J0 j0B;
        this.e = true;
        boolean z = this.c;
        if (!z && this.d == null) {
            return this.b;
        }
        if (!z) {
            int i = 0;
            while (true) {
                int size = this.b.size();
                List list = this.b;
                if (i >= size) {
                    return list;
                }
                J0 j0 = (J0) list.get(i);
                C2183nc0 c2183nc0 = (C2183nc0) this.d.get(i);
                if (c2183nc0 != null) {
                    c2183nc0.d = true;
                    if (c2183nc0.b() != j0) {
                        break;
                    }
                }
                i++;
            }
        }
        if (!this.c) {
            this.b = new ArrayList(this.b);
            this.c = true;
        }
        int i2 = 0;
        while (true) {
            int size2 = this.b.size();
            List list2 = this.b;
            if (i2 >= size2) {
                List listUnmodifiableList = Collections.unmodifiableList(list2);
                this.b = listUnmodifiableList;
                this.c = false;
                return listUnmodifiableList;
            }
            ArrayList arrayList = this.d;
            if (arrayList == null) {
                j0B = (J0) list2.get(i2);
            } else {
                C2183nc0 c2183nc1 = (C2183nc0) arrayList.get(i2);
                if (c2183nc1 == null) {
                    j0B = (J0) this.b.get(i2);
                } else {
                    c2183nc1.d = true;
                    j0B = c2183nc1.b();
                }
            }
            list2.set(i2, j0B);
            i2++;
        }
    }

    public final void a(J0 j0) {
        I0 i0;
        Charset charset = AbstractC1556gB.a;
        j0.getClass();
        if (!this.c) {
            this.b = new ArrayList(this.b);
            this.c = true;
        }
        this.b.add(j0);
        ArrayList arrayList = this.d;
        if (arrayList != null) {
            arrayList.add(null);
        }
        if (this.e && (i0 = this.a) != null) {
            i0.a();
            this.e = false;
        }
        C2315p50 c2315p50 = this.f;
        if (c2315p50 != null) {
            c2315p50.a();
        }
    }

    @Override // com.android.tools.r8.internal.I0
    public final void a() {
        I0 i0;
        if (!this.e || (i0 = this.a) == null) {
            return;
        }
        i0.a();
        this.e = false;
    }
}
