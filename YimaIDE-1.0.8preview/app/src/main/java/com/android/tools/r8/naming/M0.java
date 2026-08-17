package com.android.tools.r8.naming;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.MapIdProvider;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M0 {
    public static final /* synthetic */ boolean f = true;
    public final C3313b a;
    public final C2752uB b;
    public final Q c;
    public final C2742u50 d;
    public final com.android.tools.r8.dex.W.b e;

    public M0(C3313b c3313b, com.android.tools.r8.dex.W.b bVar, C2752uB c2752uB) {
        if (!f && c3313b == null) {
            x1f.a();
            throw null;
        }
        this.a = c3313b.g();
        this.c = c2752uB.F1;
        this.b = c2752uB;
        this.d = c2752uB.i;
        this.e = bVar;
    }

    public final J0 a() {
        List list;
        L0 l0 = new L0();
        this.a.a(l0);
        MapIdProvider mapIdProvider = this.b.R1;
        String string = l0.a.a().toString();
        J0 j0 = new J0(L0.a(mapIdProvider).get(new K0(string)), string);
        A0 a0 = new A0(this.e.name(), this.b.Z(), this.b.F(), this.b.u1.Y0 ? MapVersion.MAP_VERSION_EXPERIMENTAL : MapVersion.STABLE, j0);
        C3313b c3313b = this.a;
        ArrayList arrayListA = a0.a();
        List list2 = this.a.f;
        boolean z = C2847vL.a;
        if (arrayListA.isEmpty()) {
            list = list2;
        } else if (!list2.isEmpty()) {
            list = arrayListA;
            ArrayList arrayList = new ArrayList(list2.size() + arrayListA.size());
            arrayList.addAll(arrayListA);
            arrayList.addAll(list2);
            list = arrayList;
        }
        list = arrayListA;
        c3313b.f = list;
        this.c.a(this.d, this.a);
        C2742u50 c2742u50 = this.d;
        final Q q = this.c;
        Objects.requireNonNull(q);
        AbstractC2632so.a(c2742u50, new Consumer() { // from class: tq9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                q.finished((DiagnosticsHandler) obj);
            }
        });
        return j0;
    }
}
