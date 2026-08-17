package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CodeSizeOverflowDiagnostic extends ClassFileOverflowDiagnostic {
    private final MethodReference c;
    private final int d;
    private final MethodPosition e;

    public CodeSizeOverflowDiagnostic(Origin origin, MethodReference methodReference, int i) {
        super(origin);
        this.c = methodReference;
        this.d = i;
        this.e = new MethodPosition(methodReference);
    }

    public int getCodeSize() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Method " + this.c + " too large for class file. Code size was " + getCodeSize() + ".";
    }

    @Override // com.android.tools.r8.errors.ClassFileOverflowDiagnostic, com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.e;
    }
}
