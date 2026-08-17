package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ConstantPoolOverflowDiagnostic extends ClassFileOverflowDiagnostic {
    private final int c;
    private final ClassReference d;

    public ConstantPoolOverflowDiagnostic(Origin origin, ClassReference classReference, int i) {
        super(origin);
        this.d = classReference;
        this.c = i;
    }

    public int getConstantPoolSize() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Class " + this.d + " too large for class file. Constant pool size was " + getConstantPoolSize() + ".";
    }
}
