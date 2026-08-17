package com.android.tools.r8.synthesis;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.internal.YV;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.utils.StringDiagnostic;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3497f implements GlobalSyntheticsConsumer {
    public static final /* synthetic */ boolean c = true;
    public boolean a = false;
    public final /* synthetic */ YV b;

    public C3497f(YV yv) {
        this.b = yv;
    }

    @Override // com.android.tools.r8.GlobalSyntheticsConsumer
    public final synchronized void accept(ByteDataView byteDataView, ClassReference classReference, DiagnosticsHandler diagnosticsHandler) {
        try {
            if (!c && classReference != null) {
                throw new AssertionError();
            }
            if (this.a) {
                diagnosticsHandler.error(new StringDiagnostic("Attempt to write multiple global-synthetics files in dex-indexed mode."));
                throw new RuntimeException("Attempt to write multiple global-synthetics files in dex-indexed mode.");
            }
            this.b.a(byteDataView, "classes.globals", diagnosticsHandler);
            this.b.a(diagnosticsHandler);
            this.a = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.android.tools.r8.GlobalSyntheticsConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        if (this.a) {
            return;
        }
        this.b.a(diagnosticsHandler);
        this.a = true;
    }
}
