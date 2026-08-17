package com.android.tools.r8.utils;

import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.internal.AbstractC0551Hu;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class h implements ProgramResourceProvider {
    public final /* synthetic */ List a;
    public final /* synthetic */ List b;

    public h(AbstractC0551Hu abstractC0551Hu, AbstractC0551Hu abstractC0551Hu2) {
        this.a = abstractC0551Hu;
        this.b = abstractC0551Hu2;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final DataResourceProvider getDataResourceProvider() {
        if (this.b.isEmpty()) {
            return null;
        }
        return new g(this);
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        return this.a;
    }
}
