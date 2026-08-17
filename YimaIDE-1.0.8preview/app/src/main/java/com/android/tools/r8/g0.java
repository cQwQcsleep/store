package com.android.tools.r8;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class g0 implements ProgramResourceProvider {
    public final /* synthetic */ ProgramResourceProvider a;

    public g0(ProgramResourceProvider programResourceProvider) {
        this.a = programResourceProvider;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final DataResourceProvider getDataResourceProvider() {
        return null;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        return this.a.getProgramResources();
    }
}
