package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.TextOutputStream;
import com.android.tools.r8.profile.art.ArtProfileConsumer;
import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1798j4 implements ArtProfileConsumer {
    public final /* synthetic */ Path a;

    public C1798j4(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileConsumer
    public final TextOutputStream getHumanReadableArtProfileConsumer() {
        try {
            return new Vj0(this.a);
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }
}
