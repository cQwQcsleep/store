package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class d0 implements AndroidResourceOutput {
    public final AndroidResourceInput a;
    public final C2742u50 b;

    public d0(AndroidResourceInput androidResourceInput, C2742u50 c2742u50) {
        this.a = androidResourceInput;
        this.b = c2742u50;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return this.a.getOrigin();
    }

    @Override // com.android.tools.r8.AndroidResourceOutput
    public final ResourcePath getPath() {
        return this.a.getPath();
    }
}
