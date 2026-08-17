package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B3;
import defpackage.rmc;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S3 implements V3 {
    public static final /* synthetic */ boolean c = true;
    public final InterfaceC0332x5 a;
    public final BiConsumer b;

    public S3(InterfaceC0332x5 interfaceC0332x5, BiConsumer biConsumer) {
        this.a = interfaceC0332x5;
        this.b = biConsumer;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.i a(B3.i iVar) {
        iVar.getClass();
        if (iVar instanceof C3) {
            return iVar;
        }
        if (c || iVar.e()) {
            return b(iVar.d());
        }
        x1f.a();
        return null;
    }

    public final B3.e b(B3.e eVar) {
        if (eVar.a() || eVar.n() || eVar.o()) {
            return eVar;
        }
        if (eVar.l()) {
            eVar.g().a(this);
            return eVar;
        }
        if (c || eVar.m()) {
            return eVar.h().a(this);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List c(List list) {
        list.forEach(new Consumer() { // from class: umc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((B3.f) obj);
            }
        });
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List d(List list) {
        list.forEach(new rmc(this));
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List e(List list) {
        list.forEach(new Consumer() { // from class: qmc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((B3.c) obj);
            }
        });
        return list;
    }

    public final B3.e c(B3.e eVar) {
        return b(eVar);
    }

    @Override // com.android.tools.r8.graph.V3
    public final List a(List list) {
        list.forEach(new Consumer() { // from class: tmc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((B3.e) obj);
            }
        });
        return list;
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.c a(B3.c cVar) {
        return cVar == null ? cVar : cVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.e a(B3.e eVar) {
        return eVar.a() ? eVar : b(eVar);
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.c a(B3.c cVar, B3.c cVar2) {
        return cVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final B3.h a(B3.h hVar) {
        if (hVar.a()) {
            return hVar;
        }
        a(hVar.a);
        return hVar;
    }

    @Override // com.android.tools.r8.graph.V3
    public final List a(I2 i2, I2 i3, List list) {
        list.forEach(new Consumer() { // from class: smc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((B3.e) obj);
            }
        });
        return list;
    }

    public final B3.f a(B3.f fVar) {
        return fVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final I2 a(I2 i2) {
        this.b.accept(i2, this.a);
        return i2;
    }

    public final B3.c b(B3.c cVar) {
        return cVar.a(this);
    }

    @Override // com.android.tools.r8.graph.V3
    public final List b(List list) {
        list.forEach(new rmc(this));
        return list;
    }
}
