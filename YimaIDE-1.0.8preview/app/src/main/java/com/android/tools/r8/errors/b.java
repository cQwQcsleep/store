package com.android.tools.r8.errors;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class b {
    public I2 a;
    public H2 b;
    public Origin c;
    public Position d;

    public final AssumeValuesMissingStaticFieldDiagnostic a() {
        return new AssumeValuesMissingStaticFieldDiagnostic(this.a, this.b, this.c, this.d);
    }
}
