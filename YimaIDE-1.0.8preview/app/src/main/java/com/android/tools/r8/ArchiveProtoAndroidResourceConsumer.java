package com.android.tools.r8;

import com.android.tools.r8.internal.C1031a3;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveProtoAndroidResourceConsumer implements AndroidResourceConsumer {
    private final C1031a3 a;
    private final Path b;
    private Map c;

    public ArchiveProtoAndroidResourceConsumer(Path path, Path path2) {
        C1031a3 c1031a3 = new C1031a3(path);
        this.a = c1031a3;
        c1031a3.open();
        this.b = path2;
    }

    private synchronized Map a(DiagnosticsHandler diagnosticsHandler) {
        Map map = this.c;
        if (map != null) {
            return map;
        }
        if (this.b != null) {
            this.c = new HashMap();
            try {
                com.android.tools.r8.utils.v.a(this.b, (Consumer<ZipEntry>) new Consumer() { // from class: je0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a((ZipEntry) obj);
                    }
                });
            } catch (IOException e) {
                diagnosticsHandler.error(new ExceptionDiagnostic(e, new PathOrigin(this.b)));
            }
        } else {
            this.c = Collections.EMPTY_MAP;
        }
        return this.c;
    }

    @Override // com.android.tools.r8.AndroidResourceConsumer
    public void accept(AndroidResourceOutput androidResourceOutput, DiagnosticsHandler diagnosticsHandler) {
        this.a.a(androidResourceOutput.getPath().location(), androidResourceOutput.getByteDataView(), ((Boolean) a(diagnosticsHandler).getOrDefault(androidResourceOutput.getPath().location(), Boolean.TRUE)).booleanValue());
    }

    @Override // com.android.tools.r8.AndroidResourceConsumer
    public void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.a(diagnosticsHandler);
    }

    public ArchiveProtoAndroidResourceConsumer(Path path) {
        this(path, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ZipEntry zipEntry) {
        this.c.put(zipEntry.getName(), Boolean.valueOf(zipEntry.getMethod() != 0));
    }
}
