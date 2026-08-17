package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.Calendar;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ui0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            c2754uD.i();
            return;
        }
        c2754uD.e();
        c2754uD.b("year");
        c2754uD.a(calendar.get(1));
        c2754uD.b("month");
        c2754uD.a(calendar.get(2));
        c2754uD.b("dayOfMonth");
        c2754uD.a(calendar.get(5));
        c2754uD.b("hourOfDay");
        c2754uD.a(calendar.get(11));
        c2754uD.b("minute");
        c2754uD.a(calendar.get(12));
        c2754uD.b("second");
        c2754uD.a(calendar.get(13));
        c2754uD.g();
    }
}
