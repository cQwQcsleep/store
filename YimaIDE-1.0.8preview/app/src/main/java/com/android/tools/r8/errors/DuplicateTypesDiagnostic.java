package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.ClassReference;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class DuplicateTypesDiagnostic implements Diagnostic {
    static final /* synthetic */ boolean d = true;
    private final ClassReference b;
    private final Collection c;

    public DuplicateTypesDiagnostic(ClassReference classReference, Collection<Origin> collection) {
        boolean z = d;
        if (!z && classReference == null) {
            x1f.a();
            throw null;
        }
        if (!z && collection.size() <= 1) {
            x1f.a();
            throw null;
        }
        this.b = classReference;
        this.c = collection;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Type " + C0929Wj.b(this.b.getDescriptor()) + " is defined multiple times: " + Wf0.a(", ", this.c);
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return (Origin) this.c.iterator().next();
    }

    public Collection<Origin> getOrigins() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }

    public ClassReference getType() {
        return this.b;
    }
}
