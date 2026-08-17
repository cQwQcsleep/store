package com.android.tools.r8;

import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0006b implements AndroidResourceInput {
    public final String a;
    public final AndroidResourceInput.Kind b;
    public final byte[] c;
    public final Origin d;

    public C0006b(String str, AndroidResourceInput.Kind kind, byte[] bArr, ArchiveEntryOrigin archiveEntryOrigin) {
        this.a = str;
        this.b = kind;
        this.c = bArr;
        this.d = archiveEntryOrigin;
    }

    public final /* synthetic */ String b() {
        return this.a;
    }

    @Override // com.android.tools.r8.AndroidResourceInput
    public final InputStream getByteStream() {
        return new ByteArrayInputStream(this.c);
    }

    @Override // com.android.tools.r8.AndroidResourceInput
    public final AndroidResourceInput.Kind getKind() {
        return this.b;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.AndroidResourceInput
    public final ResourcePath getPath() {
        return new ResourcePath() { // from class: veg
            @Override // com.android.tools.r8.ResourcePath
            public final String location() {
                return this.a.b();
            }
        };
    }
}
