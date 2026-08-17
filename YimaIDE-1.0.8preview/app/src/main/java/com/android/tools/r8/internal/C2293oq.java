package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2293oq extends Origin {
    public final Path f;

    public C2293oq(Path path) {
        super(Origin.root());
        this.f = path;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "flag file argument: '@" + this.f + "'";
    }
}
