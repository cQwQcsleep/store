package com.android.tools.r8;

import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: renamed from: com.android.tools.r8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3522x implements DataEntryResource {
    public static final /* synthetic */ boolean c = true;
    public final ZipFile a;
    public final ZipEntry b;

    public C3522x(ZipFile zipFile, ZipEntry zipEntry) {
        boolean z = c;
        if (!z && zipFile == null) {
            x1f.a();
            throw null;
        }
        if (!z && zipEntry == null) {
            x1f.a();
            throw null;
        }
        this.a = zipFile;
        this.b = zipEntry;
    }

    @Override // com.android.tools.r8.DataEntryResource
    public final InputStream getByteStream() throws ResourceException {
        try {
            return this.a.getInputStream(this.b);
        } catch (IOException e) {
            throw new ResourceException(getOrigin(), e);
        }
    }

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        return this.b.getName();
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new ArchiveEntryOrigin(this.b.getName(), new PathOrigin(Paths.get(this.a.getName(), new String[0])));
    }
}
