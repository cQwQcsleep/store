package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lf0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2018lf0 implements InterfaceC2446qf0 {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.InterfaceC2446qf0
    public final boolean a(AbstractC1932kf0 abstractC1932kf0, AbstractC1932kf0 abstractC1932kf1, C2361pf0 c2361pf0) {
        InterfaceC1168bf0 interfaceC1168bf0J;
        String strA;
        String strA2;
        Xe0 xe0G = abstractC1932kf1.g();
        if (xe0G != null && xe0G.o() && (interfaceC1168bf0J = abstractC1932kf1.m().j()) != 0) {
            AbstractC1932kf0 abstractC1932kf2 = (AbstractC1932kf0) interfaceC1168bf0J;
            if (abstractC1932kf2.p()) {
                if ((abstractC1932kf2.q() && c2361pf0.d.contains(abstractC1932kf0)) || (strA = AbstractC2531rf0.a(xe0G, c2361pf0)) == null || (strA2 = AbstractC2531rf0.a(interfaceC1168bf0J, c2361pf0)) == null) {
                    return false;
                }
                String strConcat = strA2.concat(strA);
                interfaceC1168bf0J.a(strConcat);
                c2361pf0.a.put(interfaceC1168bf0J.a(), new Fe0(strConcat));
                c2361pf0.a.put(xe0G.a(), Ge0.a);
                abstractC1932kf1.t();
                return true;
            }
        }
        return false;
    }
}
