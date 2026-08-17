package com.android.tools.r8.tracereferences;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class f implements ProgramResourceProvider {
    public final ProgramResource a;

    public f(Path path) {
        this.a = ProgramResource.fromFile(ProgramResource.Kind.DEX, path);
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        return Collections.singletonList(this.a);
    }
}
