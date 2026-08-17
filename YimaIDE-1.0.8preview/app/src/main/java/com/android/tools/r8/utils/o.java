package com.android.tools.r8.utils;

import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.internal.K7;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class o implements DataResourceConsumer {
    public final /* synthetic */ DataResourceConsumer a;
    public final /* synthetic */ p b;

    public o(p pVar, DataResourceConsumer dataResourceConsumer) {
        this.b = pVar;
        this.a = dataResourceConsumer;
    }

    @Override // com.android.tools.r8.DataResourceConsumer
    public final void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
        try {
            DataEntryResource dataEntryResourceFromBytes = DataEntryResource.fromBytes(K7.a(dataEntryResource.getByteStream()), dataEntryResource.getName(), dataEntryResource.getOrigin());
            this.b.e.a.a(dataEntryResourceFromBytes);
            DataResourceConsumer dataResourceConsumer = this.a;
            if (dataResourceConsumer != null) {
                dataResourceConsumer.accept(dataEntryResourceFromBytes, diagnosticsHandler);
            }
        } catch (ResourceException | IOException e) {
            rc6.a(e);
        }
    }

    @Override // com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        DataResourceConsumer dataResourceConsumer = this.a;
        if (dataResourceConsumer != null) {
            dataResourceConsumer.finished(diagnosticsHandler);
        }
    }

    @Override // com.android.tools.r8.DataResourceConsumer
    public final void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler) {
        this.b.e.a.a(DataDirectoryResource.fromName(dataDirectoryResource.getName(), dataDirectoryResource.getOrigin()));
        DataResourceConsumer dataResourceConsumer = this.a;
        if (dataResourceConsumer != null) {
            dataResourceConsumer.accept(dataDirectoryResource, diagnosticsHandler);
        }
    }
}
