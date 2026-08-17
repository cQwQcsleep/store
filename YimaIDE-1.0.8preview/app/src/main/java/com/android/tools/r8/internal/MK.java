package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.internal.AbstractC2956we;
import com.android.tools.r8.internal.MK;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.rr9;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MK implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC0706Nu b;
    public final C2986wz c;

    public MK(HashMap map, C2986wz c2986wz) {
        C2986wz c2986wz2 = null;
        if (!d && map.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.b = AbstractC0706Nu.a(map);
        if (!c2986wz.isEmpty()) {
            c2986wz2 = new C2986wz(c2986wz.i);
            c2986wz2.putAll(c2986wz);
        }
        this.c = c2986wz2;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.a.a(this.b.size());
        final ArrayList<Integer> arrayList = new ArrayList(this.b.size());
        this.b.forEach(new BiConsumer() { // from class: qr9
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MK.a(arrayList, obj, (C0230j0) obj2);
            }
        });
        arrayList.sort(new rr9());
        for (Integer num : arrayList) {
            qVar.a.a(num.intValue());
            ((C0230j0) this.b.get(num)).a(oVar);
        }
        if (this.c != null) {
            final C1131bA c1131bA = new C1131bA();
            final C1131bA c1131bA2 = new C1131bA();
            this.c.forEach(new BiConsumer() { // from class: sr9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    MK.a(c1131bA, c1131bA2, (Integer) obj, (int[]) obj2);
                }
            });
            qVar.a.a(c1131bA.a());
            qVar.a.a(c1131bA2.a());
        }
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    public final void a(BiConsumer biConsumer) {
        this.b.forEach(biConsumer);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(MK mk, final AbstractC3519a abstractC3519a) {
        int size = this.b.size();
        int iCompare = Integer.compare(size, mk.b.size());
        if (iCompare != 0) {
            return iCompare;
        }
        C2986wz c2986wz = this.c;
        boolean z = c2986wz == null;
        C2986wz c2986wz2 = mk.c;
        if (z != (c2986wz2 == null)) {
            return c2986wz == null ? -1 : 1;
        }
        if (c2986wz != null) {
            if (!d && c2986wz2 == null) {
                x1f.a();
                return 0;
            }
            int iA = AbstractC2956we.a(c2986wz, c2986wz2, new Comparator() { // from class: mr9
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return AbstractC2956we.a((int[]) obj, (int[]) obj2);
                }
            });
            if (iA != 0) {
                return iA;
            }
        }
        if (!d && (this.b instanceof InterfaceC2045lz)) {
            x1f.a();
            return 0;
        }
        final C2986wz c2986wz3 = new C2986wz(size);
        final C2986wz c2986wz4 = new C2986wz(size);
        this.b.forEach(new BiConsumer() { // from class: nr9
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2986wz3.a(((Integer) obj).intValue(), (C0230j0) obj2);
            }
        });
        mk.b.forEach(new BiConsumer() { // from class: or9
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2986wz4.a(((Integer) obj).intValue(), (C0230j0) obj2);
            }
        });
        return AbstractC2956we.a(c2986wz3, c2986wz4, new Comparator() { // from class: pr9
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((C0230j0) obj).a((C0230j0) obj2, abstractC3519a);
            }
        });
    }

    public static /* synthetic */ void a(ArrayList arrayList, Object obj, C0230j0 c0230j0) {
        Integer num = (Integer) obj;
        num.getClass();
        arrayList.add(num);
    }

    public static /* synthetic */ void a(C1131bA c1131bA, C1131bA c1131bA2, Integer num, int[] iArr) {
        c1131bA.c(num.intValue());
        c1131bA2.c(Arrays.hashCode(iArr));
    }
}
