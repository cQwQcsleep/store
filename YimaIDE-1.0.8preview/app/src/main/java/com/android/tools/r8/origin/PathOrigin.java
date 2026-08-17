package com.android.tools.r8.origin;

import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PathOrigin extends Origin {
    static final /* synthetic */ boolean g = true;
    private final Path f;

    public PathOrigin(Path path) {
        super(Origin.root());
        if (g || path != null) {
            this.f = path;
        } else {
            x1f.a();
            throw null;
        }
    }

    public Path getPath() {
        return this.f;
    }

    @Override // com.android.tools.r8.origin.Origin
    public String part() {
        return this.f.toString();
    }
}
