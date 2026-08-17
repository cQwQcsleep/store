package com.android.tools.r8.shaking;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J3 extends Exception implements Diagnostic {
    public final String b;
    public final String c;
    public final Origin d;
    public final Position e;

    public J3(String str, String str2, Origin origin, Position position) {
        this.b = str;
        this.c = str2;
        this.d = origin;
        this.e = position;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        return this.b + " at " + this.c;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.b + " at " + this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return this.e;
    }
}
