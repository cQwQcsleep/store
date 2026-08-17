package com.android.tools.r8.internal;

import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1176bj0 implements Ai0 {
    public final /* synthetic */ Class b = Calendar.class;
    public final /* synthetic */ Class c = GregorianCalendar.class;
    public final /* synthetic */ AbstractC3220zi0 d;

    public C1176bj0(Ui0 ui0) {
        this.d = ui0;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Class cls = fj0.a;
        if (cls == this.b || cls == this.c) {
            return this.d;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.b.getName() + "+" + this.c.getName() + ",adapter=" + this.d + "]";
    }
}
