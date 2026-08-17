package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class InterfaceDesugarMissingTypeDiagnostic implements DesugarDiagnostic {
    static final /* synthetic */ boolean g = true;
    private final Origin b;
    private final Position c;
    private final ClassReference d;
    private final ClassReference e;
    private final ClassReference f;

    public InterfaceDesugarMissingTypeDiagnostic(Origin origin, Position position, ClassReference classReference, ClassReference classReference2, ClassReference classReference3) {
        boolean z = g;
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && position == null) {
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
        this.b = origin;
        this.c = position;
        this.d = classReference;
        this.e = classReference2;
        this.f = classReference3;
    }

    public ClassReference getContextType() {
        return this.e;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder("Type `");
        sb.append(this.d.getTypeName());
        sb.append("` was not found, it is required for default or static interface methods desugaring of `");
        Position position = this.c;
        if (position != Position.UNKNOWN) {
            sb.append(position.getDescription());
        } else {
            sb.append(this.e.getTypeName());
        }
        sb.append("`");
        if (this.f != null) {
            sb.append(" This missing interface is declared in the direct hierarchy of `");
            sb.append(this.f);
            sb.append("`");
        }
        return sb.toString();
    }

    public ClassReference getMissingType() {
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
}
