package com.android.tools.r8.internal;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.utils.ExceptionDiagnostic;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1534g extends RuntimeException {
    public static final /* synthetic */ boolean c = true;
    public final Diagnostic b;

    public C1534g(Diagnostic diagnostic) {
        if (c || diagnostic != null) {
            this.b = diagnostic;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable getCause() {
        Diagnostic diagnostic;
        diagnostic = this.b;
        return diagnostic instanceof ExceptionDiagnostic ? ((ExceptionDiagnostic) diagnostic).getCause() : null;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.b.getDiagnosticMessage();
    }
}
