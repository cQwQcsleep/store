package com.android.tools.r8.internal;

import com.android.tools.r8.internal.InterfaceC2045lz;
import defpackage.xz0;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0391Bq extends U5 implements InterfaceC0599Jq {
    public static final /* synthetic */ boolean c = true;
    public final Map a;
    public final Map b;

    public C0391Bq(Collection collection) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        IdentityHashMap identityHashMap2 = new IdentityHashMap();
        this.a = identityHashMap;
        this.b = identityHashMap2;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            AbstractC0469Eq abstractC0469Eq = (AbstractC0469Eq) it.next();
            abstractC0469Eq.getClass();
            if (abstractC0469Eq instanceof C0443Dq) {
                C0443Dq c0443DqA = abstractC0469Eq.a();
                this.a.put(c0443DqA.e.getReference(), c0443DqA);
            } else {
                C0495Fq c0495FqB = abstractC0469Eq.b();
                ((InterfaceC2045lz) this.b.computeIfAbsent(c0495FqB.e.getReference(), IM.a(new xz0()))).a(c0495FqB.g, c0495FqB);
            }
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC0599Jq
    public final Cl0 a(B5 b5, Supplier supplier) {
        if (b5.e()) {
            return ((C0443Dq) this.a.get(b5.j().a)).f;
        }
        if (!c && !b5.h()) {
            x1f.a();
            return null;
        }
        FO foI = b5.i();
        C0495Fq c0495Fq = (C0495Fq) ((InterfaceC2045lz) this.b.getOrDefault(foI.a, AbstractC2216nz.a)).get(foI.b);
        return c0495Fq != null ? c0495Fq.c() : (Cl0) supplier.get();
    }

    public final void b(Consumer consumer) {
        this.a.values().forEach(consumer);
    }

    @Override // com.android.tools.r8.internal.U5
    public final void a(final Consumer consumer) {
        this.a.values().forEach(consumer);
        this.b.values().forEach(new Consumer() { // from class: wz0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((InterfaceC2045lz) obj).values().forEach(consumer);
            }
        });
    }

    @Override // com.android.tools.r8.internal.U5
    public final void a(Consumer consumer, Object obj) {
        AbstractC0469Eq abstractC0469Eq = (AbstractC0469Eq) obj;
        abstractC0469Eq.a.forEach(consumer);
        abstractC0469Eq.b.keySet().forEach(consumer);
    }

    public C0391Bq(IdentityHashMap identityHashMap, IdentityHashMap identityHashMap2) {
        this.a = identityHashMap;
        this.b = identityHashMap2;
    }
}
