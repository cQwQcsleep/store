package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0168a1;
import com.android.tools.r8.graph.C0322w2;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ul, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0879Ul extends C0168a1 {
    public static final /* synthetic */ boolean l = true;
    public final /* synthetic */ C1131bA g;
    public final /* synthetic */ C1405eW h;
    public final /* synthetic */ InterfaceC2347pX i;
    public final /* synthetic */ List j;
    public final /* synthetic */ C0905Vl k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0879Ul(C0905Vl c0905Vl, int i, C0322w2 c0322w2, boolean z, C1131bA c1131bA, C1405eW c1405eW, C2175nX c2175nX, ArrayList arrayList) {
        super(i, c0322w2, z);
        this.k = c0905Vl;
        this.g = c1131bA;
        this.h = c1405eW;
        this.i = c2175nX;
        this.j = arrayList;
    }

    @Override // com.android.tools.r8.graph.C0168a1, com.android.tools.r8.graph.V0
    public final void a(com.android.tools.r8.graph.O0.b bVar) {
        super.a(bVar);
        if (!l && a() < 0) {
            x1f.a();
            return;
        }
        if (this.g.a() < 0) {
            this.g.d(b());
        }
        AbstractC2004lX abstractC2004lXC = c();
        if (this.h.b() != null) {
            InterfaceC2774uX interfaceC2774uX = this.k.b;
            int iB = b();
            AbstractC2004lX abstractC2004lX = (AbstractC2004lX) this.h.b();
            InterfaceC2347pX interfaceC2347pX = this.i;
            ArrayList arrayList = (ArrayList) this.j;
            AbstractC2004lX abstractC2004lX2 = (AbstractC2004lX) ((C2175nX) interfaceC2347pX).a(abstractC2004lX).a();
            for (int iIntValue = ((Integer) this.h.a()).intValue(); iIntValue < iB; iIntValue++) {
                arrayList.add(new JM(interfaceC2774uX.a(iIntValue), abstractC2004lX2));
            }
        }
        this.h.a = Integer.valueOf(b());
        this.h.b = abstractC2004lXC;
    }
}
