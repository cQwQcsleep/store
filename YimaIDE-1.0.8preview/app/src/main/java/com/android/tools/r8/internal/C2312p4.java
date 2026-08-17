package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.profile.art.ArtProfileBuilder;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import java.io.IOException;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2312p4 implements ArtProfileProvider {
    public final /* synthetic */ Path a;

    public C2312p4(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileProvider
    public final void getArtProfile(ArtProfileBuilder artProfileBuilder) {
        try {
            artProfileBuilder.addHumanReadableArtProfile(new Uj0(this.a), C0822Sg.b());
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new PathOrigin(this.a);
    }
}
