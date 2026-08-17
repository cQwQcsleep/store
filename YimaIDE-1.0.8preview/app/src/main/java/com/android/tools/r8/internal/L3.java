package com.android.tools.r8.internal;

import defpackage.wk8;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L3 extends K2 {
    public final /* synthetic */ AbstractC3114yW c;
    public final /* synthetic */ Consumer d;
    public final /* synthetic */ String e;
    public final /* synthetic */ M3 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L3(M3 m3, AbstractC3114yW abstractC3114yW, AbstractC3114yW abstractC3114yW2, Consumer consumer, String str) {
        super(abstractC3114yW);
        this.f = m3;
        this.c = abstractC3114yW2;
        this.d = consumer;
        this.e = str;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        InterfaceC1665hZ interfaceC1665hZB = b();
        String str2 = this.e;
        ArrayList arrayList = this.f.h;
        Objects.requireNonNull(arrayList);
        if (interfaceC1665hZB.a(str2, new wk8(arrayList), obj)) {
            return;
        }
        super.a(obj, this.e);
        throw null;
    }

    public final InterfaceC1665hZ b() {
        final InterfaceC1665hZ interfaceC1665hZ = (InterfaceC1665hZ) this.f.g.apply(this.c);
        HashMap map = this.f.b;
        Objects.requireNonNull(interfaceC1665hZ);
        map.forEach(new BiConsumer() { // from class: zk8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                interfaceC1665hZ.a((String) obj, obj2);
            }
        });
        return interfaceC1665hZ;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        this.d.accept(this.f.h);
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        InterfaceC1665hZ interfaceC1665hZB = b();
        String str3 = this.e;
        ArrayList arrayList = this.f.h;
        Objects.requireNonNull(arrayList);
        J2 j2A = interfaceC1665hZB.a(str3, (Consumer) new wk8(arrayList), str2);
        if (j2A != null) {
            return j2A;
        }
        super.a(this.e, str2);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        InterfaceC1665hZ interfaceC1665hZB = b();
        String str4 = this.e;
        ArrayList arrayList = this.f.h;
        Objects.requireNonNull(arrayList);
        if (interfaceC1665hZB.a(str4, str2, str3, new wk8(arrayList))) {
            return;
        }
        super.a(this.e, str2, str3);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        InterfaceC1665hZ interfaceC1665hZB = b();
        String str2 = this.e;
        ArrayList arrayList = this.f.h;
        Objects.requireNonNull(arrayList);
        J2 j2A = interfaceC1665hZB.a(new wk8(arrayList), str2);
        if (j2A != null) {
            return j2A;
        }
        super.a(this.e);
        throw null;
    }
}
