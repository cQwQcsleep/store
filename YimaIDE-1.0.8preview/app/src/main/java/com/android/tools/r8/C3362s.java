package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: renamed from: com.android.tools.r8.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3362s implements DataDirectoryResource {
    public static final /* synthetic */ boolean c = true;
    public final String a;
    public final Origin b;

    public C3362s(String str, Origin origin) {
        boolean z = c;
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        this.a = str;
        this.b = origin;
    }

    @Override // com.android.tools.r8.DataResource
    public final String getName() {
        return this.a;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.b;
    }
}
