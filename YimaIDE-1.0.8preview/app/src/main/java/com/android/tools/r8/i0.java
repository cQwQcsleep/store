package com.android.tools.r8;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class i0 implements ProgramResourceProvider {
    public final ProgramResourceProvider a;

    public i0(ProgramResourceProvider programResourceProvider) {
        this.a = programResourceProvider;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final DataResourceProvider getDataResourceProvider() {
        return this.a.getDataResourceProvider();
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() throws ResourceException {
        Collection<ProgramResource> programResources = this.a.getProgramResources();
        for (ProgramResource programResource : programResources) {
            if (programResource.getKind() == ProgramResource.Kind.DEX) {
                throw new ResourceException(programResource.getOrigin(), "R8 does not support compiling DEX inputs");
            }
        }
        return programResources;
    }
}
