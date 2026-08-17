package com.android.tools.r8.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/* JADX INFO: renamed from: com.android.tools.r8.internal.md0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2099md0 extends AbstractC3220zi0 {
    public static final C2014ld0 b = new C2014ld0();
    public final SimpleDateFormat a = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        String str;
        Date date = (Date) obj;
        if (date == null) {
            c2754uD.i();
            return;
        }
        synchronized (this) {
            str = this.a.format((java.util.Date) date);
        }
        c2754uD.d(str);
    }
}
