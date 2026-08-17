package com.android.tools.r8.internal;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.DiagnosticsLevel;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.hkh;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2742u50 implements DiagnosticsHandler {
    public final DiagnosticsHandler a;
    public final ArrayList b;
    public C1534g c;

    public C2742u50(DiagnosticsHandler diagnosticsHandler) {
        this.b = new ArrayList();
        this.c = null;
        this.a = diagnosticsHandler;
    }

    public final void a(DiagnosticsLevel diagnosticsLevel, Diagnostic diagnostic) {
        if (diagnosticsLevel != null) {
            DiagnosticsLevel diagnosticsLevelModifyDiagnosticsLevel = this.a.modifyDiagnosticsLevel(diagnosticsLevel, diagnostic);
            if (diagnosticsLevelModifyDiagnosticsLevel != null) {
                diagnosticsLevel = diagnosticsLevelModifyDiagnosticsLevel;
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                diagnosticsLevel = ((C2656t50) it.next()).a(diagnosticsLevel, diagnostic);
            }
        } else {
            diagnosticsLevel = DiagnosticsLevel.ERROR;
        }
        int i = AbstractC2571s50.a[diagnosticsLevel.ordinal()];
        if (i == 1) {
            this.a.info(diagnostic);
            return;
        }
        if (i == 2) {
            this.a.warning(diagnostic);
            return;
        }
        if (i == 3) {
            this.c = new C1534g(diagnostic);
            this.a.error(diagnostic);
        } else {
            if (i == 4) {
                return;
            }
            hkh.a();
        }
    }

    public RuntimeException b(String str) {
        a(null, new StringDiagnostic(str));
        throw this.c;
    }

    public void c(String str) {
        warning(new StringDiagnostic(str));
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public synchronized void error(Diagnostic diagnostic) {
        a(DiagnosticsLevel.ERROR, diagnostic);
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public final synchronized void info(Diagnostic diagnostic) {
        a(DiagnosticsLevel.INFO, diagnostic);
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public synchronized void warning(Diagnostic diagnostic) {
        a(DiagnosticsLevel.WARNING, diagnostic);
    }

    public C2742u50() {
        this(new C2485r50());
    }

    public void a(String str) {
        error(new StringDiagnostic(str));
    }

    public final void a(Diagnostic diagnostic) {
        a(null, diagnostic);
        throw this.c;
    }

    public synchronized void a() {
        C1534g c1534g = this.c;
        if (c1534g != null) {
            throw new RuntimeException(c1534g);
        }
    }

    public final void a(DiagnosticsLevel diagnosticsLevel, DiagnosticsLevel diagnosticsLevel2, String str) {
        this.b.add(new C2656t50(diagnosticsLevel, diagnosticsLevel2, str));
    }
}
