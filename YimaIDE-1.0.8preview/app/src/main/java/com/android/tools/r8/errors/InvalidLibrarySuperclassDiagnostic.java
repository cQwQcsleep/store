package com.android.tools.r8.errors;

import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class InvalidLibrarySuperclassDiagnostic implements DesugarDiagnostic {
    static final /* synthetic */ boolean g = true;
    private final Origin b;
    private final List c;
    private final ClassReference d;
    private final ClassReference e;
    private final String f;

    public InvalidLibrarySuperclassDiagnostic(Origin origin, ClassReference classReference, ClassReference classReference2, String str, List<MethodReference> list) {
        boolean z = g;
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && classReference == null) {
            x1f.a();
            throw null;
        }
        if (!z && classReference2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        this.b = origin;
        this.d = classReference;
        this.e = classReference2;
        this.f = str;
        this.c = list;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Superclass `");
        sb.append(this.e.getTypeName());
        sb.append("` of library class `");
        sb.append(this.d.getTypeName());
        sb.append("` is ");
        sb.append(this.f);
        sb.append(". A superclass of a library class should be a library class. This is required for the desugaring of ");
        Wf0.a(sb, this.c, ", ", Wf0.a.e);
        return sb.toString();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
