package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3518u implements DataEntryResource {
    public final byte[] a;
    public final String b;
    public final Origin c;

    public C3518u(byte[] bArr, String str, Origin origin) {
        this.a = bArr;
        this.b = str;
        this.c = origin;
    }

    @Override // com.android.tools.r8.DataEntryResource
    public final InputStream getByteStream() {
        return new ByteArrayInputStream(this.a);
    }

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        return this.b;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.c;
    }
}
