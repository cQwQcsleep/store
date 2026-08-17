package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W implements ClassFileConsumer, ProgramResourceProvider {
    public final ArrayList a = new ArrayList();

    @Override // com.android.tools.r8.ClassFileConsumer
    public final synchronized void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
        this.a.add(ProgramResource.fromBytes(Origin.unknown(), ProgramResource.Kind.CF, byteDataView.copyByteData(), Collections.singleton(str)));
    }

    @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        return this.a;
    }
}
