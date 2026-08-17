package com.android.tools.r8.origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveEntryOrigin extends Origin {
    final String f;

    public ArchiveEntryOrigin(String str, Origin origin) {
        super(origin);
        this.f = str;
    }

    public String getEntryName() {
        return this.f;
    }

    @Override // com.android.tools.r8.origin.Origin
    public String part() {
        return this.f;
    }
}
