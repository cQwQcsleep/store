package com.android.tools.r8;

import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.references.ClassReference;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M implements GlobalSyntheticsConsumer {
    public boolean a = false;
    public final /* synthetic */ Path b;

    public M(Path path) {
        this.b = path;
    }

    @Override // com.android.tools.r8.GlobalSyntheticsConsumer
    public final synchronized void accept(ByteDataView byteDataView, ClassReference classReference, DiagnosticsHandler diagnosticsHandler) {
        if (this.a) {
            throw new Kk0("Unexpected attempt to repeatedly write global synthetics");
        }
        this.a = true;
        try {
            Files.write(this.b, byteDataView.copyByteData(), new OpenOption[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
