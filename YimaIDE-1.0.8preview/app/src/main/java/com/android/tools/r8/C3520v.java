package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3520v implements DataEntryResource {
    public static final /* synthetic */ boolean c = true;
    public final File a;
    public final String b;

    public C3520v(String str, File file) {
        boolean z = c;
        if (!z && file == null) {
            x1f.a();
            throw null;
        }
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        this.a = file;
        this.b = str;
    }

    @Override // com.android.tools.r8.DataEntryResource
    public final InputStream getByteStream() throws ResourceException {
        try {
            return new FileInputStream(this.a);
        } catch (IOException e) {
            throw new ResourceException(new PathOrigin(this.a.toPath()), e);
        }
    }

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        return this.b;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new PathOrigin(this.a.toPath());
    }
}
