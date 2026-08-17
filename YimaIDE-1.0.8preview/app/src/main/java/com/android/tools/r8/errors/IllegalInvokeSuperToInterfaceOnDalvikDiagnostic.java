package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IllegalInvokeSuperToInterfaceOnDalvikDiagnostic implements Diagnostic {
    private final MethodReference b;
    private final MethodReference c;
    private final Origin d;

    public IllegalInvokeSuperToInterfaceOnDalvikDiagnostic(MethodReference methodReference, MethodReference methodReference2, Origin origin) {
        this.b = methodReference;
        this.c = methodReference2;
        this.d = origin;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Verification error in `" + MO.b(this.b) + "`: Illegal invoke-super to interface method `" + MO.b(this.c) + "` on Dalvik (Android 4).";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return MethodPosition.create(this.b);
    }
}
