package com.android.tools.r8.synthesis;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.internal.YV;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3498g implements GlobalSyntheticsConsumer {
    public final /* synthetic */ YV a;

    public C3498g(YV yv) {
        this.a = yv;
    }

    @Override // com.android.tools.r8.GlobalSyntheticsConsumer
    public final void accept(ByteDataView byteDataView, ClassReference classReference, DiagnosticsHandler diagnosticsHandler) {
        this.a.a(byteDataView, classReference.getBinaryName() + ".globals", diagnosticsHandler);
    }

    @Override // com.android.tools.r8.GlobalSyntheticsConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.a(diagnosticsHandler);
    }
}
