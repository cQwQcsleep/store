package com.android.tools.r8;

import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: renamed from: com.android.tools.r8.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3517t implements DataDirectoryResource {
    public static final /* synthetic */ boolean c = true;
    public final ZipFile a;
    public final ZipEntry b;

    public C3517t(ZipFile zipFile, ZipEntry zipEntry) {
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

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        return this.b.getName();
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new ArchiveEntryOrigin(this.b.getName(), new PathOrigin(Paths.get(this.a.getName(), new String[0])));
    }
}
