package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.startup.StartupProfileBuilder;
import com.android.tools.r8.startup.StartupProfileProvider;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.be0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1166be0 implements StartupProfileProvider {
    public final /* synthetic */ Path a;
    public final /* synthetic */ Consumer b;

    public C1166be0(Path path, Consumer consumer) {
        this.a = path;
        this.b = consumer;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new PathOrigin(this.a);
    }

    @Override // com.android.tools.r8.startup.StartupProfileProvider
    public final void getStartupProfile(StartupProfileBuilder startupProfileBuilder) {
        try {
            startupProfileBuilder.addHumanReadableArtProfile(new Uj0(this.a), this.b);
        } catch (IOException e) {
            u8i.a(e);
        }
    }
}
