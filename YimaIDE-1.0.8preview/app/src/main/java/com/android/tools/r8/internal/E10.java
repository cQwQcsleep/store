package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E10 implements ProgramResourceProvider {
    public final /* synthetic */ ProgramResource a;

    public E10(ProgramResource programResource) {
        this.a = programResource;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        List listSingletonList = Collections.singletonList(this.a);
        KB.b(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }
}
