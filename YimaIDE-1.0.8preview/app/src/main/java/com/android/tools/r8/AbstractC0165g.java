package com.android.tools.r8;

import com.android.tools.r8.origin.PathOrigin;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0165g extends PathOrigin {
    public final String h;

    public AbstractC0165g(String str, Path path) {
        super(path);
        this.h = str;
    }

    @Override // com.android.tools.r8.origin.PathOrigin, com.android.tools.r8.origin.Origin
    public final String part() {
        return this.h + " '" + super.part() + "'";
    }
}
