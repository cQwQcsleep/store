package com.android.tools.r8.errors;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class DuplicateTypeInProgramAndLibraryDiagnostic extends DuplicateTypesDiagnostic {
    public DuplicateTypeInProgramAndLibraryDiagnostic(ClassReference classReference, Origin origin, Origin origin2) {
        super(classReference, AbstractC0551Hu.a(origin, origin2));
    }

    @Override // com.android.tools.r8.errors.DuplicateTypesDiagnostic, com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Type " + getType().getTypeName() + " is defined by both the program: " + getProgramOrigin() + " and the library: " + getLibraryOrigin();
    }

    public Origin getLibraryOrigin() {
        return (Origin) ((List) getOrigins()).get(1);
    }

    public Origin getProgramOrigin() {
        return (Origin) ((List) getOrigins()).get(0);
    }
}
