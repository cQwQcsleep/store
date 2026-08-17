package com.android.tools.r8.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: renamed from: com.android.tools.r8.internal.od0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2271od0 extends AbstractC3220zi0 {
    public static final C2185nd0 b = new C2185nd0();
    public final SimpleDateFormat a = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        String str;
        Time time = (Time) obj;
        if (time == null) {
            c2754uD.i();
            return;
        }
        synchronized (this) {
            str = this.a.format((Date) time);
        }
        c2754uD.d(str);
    }
}
