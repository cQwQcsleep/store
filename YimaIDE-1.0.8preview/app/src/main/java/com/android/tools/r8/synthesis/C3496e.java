package com.android.tools.r8.synthesis;

import com.android.tools.r8.GlobalSyntheticsResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3496e implements GlobalSyntheticsResourceProvider {
    public final Path a;
    public final PathOrigin b;

    public C3496e(Path path) {
        this.a = path;
        this.b = new PathOrigin(path);
    }

    @Override // com.android.tools.r8.GlobalSyntheticsResourceProvider
    public final InputStream getByteStream() throws ResourceException {
        try {
            return Files.newInputStream(this.a, new OpenOption[0]);
        } catch (IOException e) {
            throw new ResourceException(this.b, e);
        }
    }

    @Override // com.android.tools.r8.GlobalSyntheticsResourceProvider
    public final Origin getOrigin() {
        return this.b;
    }
}
