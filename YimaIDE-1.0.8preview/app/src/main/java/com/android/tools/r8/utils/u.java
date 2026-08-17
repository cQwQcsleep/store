package com.android.tools.r8.utils;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.retrace.FinishedPartitionMappingCallback;
import com.android.tools.r8.retrace.MappingPartitionFromKeySupplier;
import com.android.tools.r8.retrace.PartitionMappingSupplier;
import com.android.tools.r8.retrace.RetracePartitionException;
import com.android.tools.r8.utils.u;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class u {
    public static final byte[] a = new byte[0];

    /* JADX WARN: Multi-variable type inference failed */
    public static PartitionMappingSupplier a(Path path) throws Exception {
        final ZipFile zipFile = new ZipFile(path.toFile());
        return ((PartitionMappingSupplier.Builder) PartitionMappingSupplier.builder().setMetadata(K7.a(zipFile.getInputStream(zipFile.getEntry("METADATA")))).setMappingPartitionFromKeySupplier(new MappingPartitionFromKeySupplier() { // from class: vdi
            @Override // com.android.tools.r8.retrace.MappingPartitionFromKeySupplier
            public final byte[] get(String str) {
                return u.a(zipFile, str);
            }
        }).setFinishedPartitionMappingCallback(new FinishedPartitionMappingCallback() { // from class: xdi
            @Override // com.android.tools.r8.retrace.FinishedPartitionMappingCallback
            public final void finished(DiagnosticsHandler diagnosticsHandler) {
                u.a(zipFile, diagnosticsHandler);
            }
        })).build();
    }

    public static /* synthetic */ byte[] a(ZipFile zipFile, String str) {
        try {
            ZipEntry entry = zipFile.getEntry(str);
            if (entry == null) {
                return a;
            }
            return K7.a(zipFile.getInputStream(entry));
        } catch (IOException e) {
            throw new RetracePartitionException(e);
        }
    }

    public static /* synthetic */ void a(ZipFile zipFile, DiagnosticsHandler diagnosticsHandler) {
        try {
            zipFile.close();
        } catch (IOException e) {
            throw new RetracePartitionException(e);
        }
    }
}
