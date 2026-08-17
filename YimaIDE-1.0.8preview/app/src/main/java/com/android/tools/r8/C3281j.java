package com.android.tools.r8;

import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3281j extends DexFilePerClassFileConsumer.ArchiveConsumer {
    public C3281j(Path path, boolean z) {
        super(path, z);
    }

    @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.DexFilePerClassFileConsumer
    public final boolean combineSyntheticClassesWithPrimaryClass() {
        return false;
    }
}
