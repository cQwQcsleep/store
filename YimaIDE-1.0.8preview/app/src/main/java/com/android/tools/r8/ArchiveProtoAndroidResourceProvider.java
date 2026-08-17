package com.android.tools.r8;

import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveProtoAndroidResourceProvider implements AndroidResourceProvider {
    private final Path a;
    private final Origin b;

    public ArchiveProtoAndroidResourceProvider(Path path, Origin origin) {
        this.a = path;
        this.b = origin;
    }

    @Override // com.android.tools.r8.AndroidResourceProvider
    public /* bridge */ /* synthetic */ void finished(DiagnosticsHandler diagnosticsHandler) {
        super.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.AndroidResourceProvider
    public Collection<AndroidResourceInput> getAndroidResources() throws ResourceException {
        AndroidResourceInput.Kind kind;
        try {
            ZipFile zipFileA = C0831Sp.a(this.a.toFile(), StandardCharsets.UTF_8);
            try {
                ArrayList arrayList = new ArrayList();
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileA.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (name.equals("AndroidManifest.xml")) {
                        kind = AndroidResourceInput.Kind.MANIFEST;
                    } else if (name.equals("resources.pb")) {
                        kind = AndroidResourceInput.Kind.RESOURCE_TABLE;
                    } else if (name.startsWith("res/")) {
                        kind = name.endsWith(".xml") ? AndroidResourceInput.Kind.XML_FILE : AndroidResourceInput.Kind.RES_FOLDER_FILE;
                    } else {
                        kind = AndroidResourceInput.Kind.UNKNOWN;
                    }
                    arrayList.add(new C0006b(name, kind, K7.a(zipFileA.getInputStream(zipEntryNextElement)), new ArchiveEntryOrigin(name, this.b)));
                }
                zipFileA.close();
                return arrayList;
            } catch (Throwable th) {
                try {
                    zipFileA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            throw new ResourceException(this.b, e);
        }
    }
}
