package com.android.tools.r8.utils;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnverifiableCfCodeDiagnostic implements Diagnostic {
    private final MethodReference b;
    private final int c;
    private final String d;
    private final Origin e;

    public UnverifiableCfCodeDiagnostic(MethodReference methodReference, int i, String str, Origin origin) {
        this.b = methodReference;
        this.c = i;
        this.d = str;
        this.e = origin;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder("Unverifiable code in `");
        sb.append(MO.b(this.b));
        sb.append("`");
        if (this.c >= 0) {
            sb.append(" at instruction ");
            sb.append(this.c);
        }
        sb.append(": ");
        sb.append(this.d);
        sb.append(".");
        return sb.toString();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.e;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
