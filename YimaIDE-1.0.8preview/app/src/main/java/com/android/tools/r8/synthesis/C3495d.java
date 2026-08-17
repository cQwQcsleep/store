package com.android.tools.r8.synthesis;

import com.android.tools.r8.GlobalSyntheticsResourceProvider;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3495d implements GlobalSyntheticsResourceProvider {
    public final Origin a;
    public final byte[] b;

    public C3495d(ArchiveEntryOrigin archiveEntryOrigin, byte[] bArr) {
        this.a = archiveEntryOrigin;
        this.b = bArr;
    }

    @Override // com.android.tools.r8.GlobalSyntheticsResourceProvider
    public final InputStream getByteStream() {
        return new ByteArrayInputStream(this.b);
    }

    @Override // com.android.tools.r8.GlobalSyntheticsResourceProvider
    public final Origin getOrigin() {
        return this.a;
    }
}
