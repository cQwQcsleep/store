package com.android.tools.r8.internal;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1264cm implements YV {
    public final Path a;
    public final PathOrigin b;

    public C1264cm(Path path) {
        this.a = path;
        this.b = new PathOrigin(path);
    }

    @Override // com.android.tools.r8.internal.YV
    public final void a(String str, DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
        try {
            InputStream byteStream = dataEntryResource.getByteStream();
            try {
                a(ByteDataView.of(K7.a(byteStream)), str, diagnosticsHandler);
                byteStream.close();
            } catch (Throwable th) {
                if (byteStream != null) {
                    try {
                        byteStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (ResourceException e) {
            diagnosticsHandler.error(new StringDiagnostic("Failed to open input: " + e.getMessage(), dataEntryResource.getOrigin()));
        } catch (IOException e2) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e2, dataEntryResource.getOrigin()));
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final PathOrigin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.YV
    public final Path getPath() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.YV
    public final void open() {
    }

    @Override // com.android.tools.r8.internal.YV
    public final void a(String str, DiagnosticsHandler diagnosticsHandler) {
        Path pathResolve = this.a.resolve(str.replace(DataResource.SEPARATOR, File.separatorChar));
        try {
            Files.createDirectories(pathResolve, new FileAttribute[0]);
        } catch (IOException e) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e, new PathOrigin(pathResolve)));
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final void a(DiagnosticsHandler diagnosticsHandler) {
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void a(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
        Path pathResolve = this.a.resolve(str.replace(DataResource.SEPARATOR, File.separatorChar));
        try {
            Files.createDirectories(pathResolve.getParent(), new FileAttribute[0]);
            C0831Sp.a(pathResolve, (OutputStream) null, byteDataView);
        } catch (IOException e) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e, new PathOrigin(pathResolve)));
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final void a(int i, String str, ByteDataView byteDataView, DiagnosticsHandler diagnosticsHandler) {
        a(byteDataView, str, diagnosticsHandler);
    }
}
