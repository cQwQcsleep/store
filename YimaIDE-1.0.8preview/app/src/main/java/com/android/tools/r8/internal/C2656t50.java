package com.android.tools.r8.internal;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsLevel;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2656t50 {
    public final DiagnosticsLevel a;
    public final DiagnosticsLevel b;
    public final String c;

    public C2656t50(DiagnosticsLevel diagnosticsLevel, DiagnosticsLevel diagnosticsLevel2, String str) {
        this.a = diagnosticsLevel;
        this.b = diagnosticsLevel2;
        this.c = str;
    }

    public final DiagnosticsLevel a(DiagnosticsLevel diagnosticsLevel, Diagnostic diagnostic) {
        if (diagnosticsLevel == this.a) {
            if (this.c.length() == 0 || this.c.equals(diagnostic.getClass().getSimpleName()) || this.c.equals(diagnostic.getClass().getTypeName())) {
                return this.b;
            }
            for (Class<?> cls : diagnostic.getClass().getInterfaces()) {
                if (this.c.equals(cls.getSimpleName()) || this.c.equals(cls.getTypeName())) {
                    return this.b;
                }
            }
        }
        return diagnosticsLevel;
    }
}
