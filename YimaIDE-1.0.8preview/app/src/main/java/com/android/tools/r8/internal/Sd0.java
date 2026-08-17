package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Sd0 {
    public final Set a = Collections.EMPTY_SET;
    public boolean b = Kg0.a("com.android.tools.r8.startup.instrumentation.instrument", Kg0.a("com.android.tools.r8.startup.instrumentation.instrument"), false);
    public final String c = Kg0.a("com.android.tools.r8.startup.instrumentation.instrumentationserversyntheticcontext");
    public String d = Kg0.a("com.android.tools.r8.startup.instrumentation.instrumentationtag");

    public Sd0 a() {
        this.b = true;
        return this;
    }

    public Sd0 a(String str) {
        this.d = str;
        return this;
    }
}
