package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3521w implements DataEntryResource {
    public final String a;
    public final DataEntryResource b;

    public C3521w(String str, DataEntryResource dataEntryResource) {
        this.a = str;
        this.b = dataEntryResource;
    }

    @Override // com.android.tools.r8.DataEntryResource
    public final InputStream getByteStream() {
        return this.b.getByteStream();
    }

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        String str = this.a;
        return str != null ? str : this.b.getName();
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.b.getOrigin();
    }
}
