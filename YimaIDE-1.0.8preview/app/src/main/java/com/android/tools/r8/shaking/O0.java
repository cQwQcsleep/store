package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC2205no;
import com.android.tools.r8.internal.AbstractC3179zC;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O0 extends AbstractC2205no {
    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        C3412j3 c3412j3 = (C3412j3) obj;
        C3412j3 c3412j4 = (C3412j3) obj2;
        return c3412j3.b().equals(c3412j4.b()) && c3412j3.a().equals(c3412j4.a()) && c3412j3.h().equals(c3412j4.h()) && c3412j3.d() == c3412j4.d() && c3412j3.g == c3412j4.g && c3412j3.f() == c3412j4.f() && c3412j3.j.equals(c3412j4.j) && Objects.equals(c3412j3.e(), c3412j4.e()) && c3412j3.c().equals(c3412j4.c());
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        C3412j3 c3412j3 = (C3412j3) obj;
        return AbstractC3179zC.c(c3412j3.u.u()) + ((((((c3412j3.j.hashCode() + ((c3412j3.c().hashCode() + (((((((((c3412j3.b().hashCode() * 3) + c3412j3.a().a) * 3) + c3412j3.h().a) * 3) + (c3412j3.g ? 1 : 0)) * 3) + (c3412j3.d() != null ? c3412j3.d().hashCode() : 0)) * 3)) * 3)) * 3) + (c3412j3.e() != null ? c3412j3.e().hashCode() : 0)) * 3) + (!c3412j3.l ? 1 : 0)) * 3);
    }
}
