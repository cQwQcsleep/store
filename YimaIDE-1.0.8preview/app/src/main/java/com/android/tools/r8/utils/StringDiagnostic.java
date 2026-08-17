package com.android.tools.r8.utils;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StringDiagnostic implements Diagnostic {
    static final /* synthetic */ boolean e = true;
    private final Origin b;
    private final Position c;
    private final String d;

    public StringDiagnostic(String str, Origin origin, Position position) {
        boolean z = e;
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && position == null) {
            x1f.a();
            throw null;
        }
        this.b = origin;
        this.c = position;
        this.d = str;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.c;
    }

    public StringDiagnostic(String str, Origin origin) {
        this(str, origin, Position.UNKNOWN);
    }

    public StringDiagnostic(String str) {
        this(str, Origin.unknown());
    }
}
