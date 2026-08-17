package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class DexFileOverflowDiagnostic implements Diagnostic {
    private final boolean b;
    private final long c;
    private final long d;

    public DexFileOverflowDiagnostic(boolean z, long j, long j2) {
        this.b = z;
        this.c = j;
        this.d = j2;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder("Cannot fit requested classes in ");
        sb.append(hasMainDexSpecification() ? "the main-" : "a single ");
        sb.append("dex file (");
        if (getNumberOfMethods() > getMaximumNumberOfMethods()) {
            sb.append("# methods: ");
            sb.append(getNumberOfMethods());
            sb.append(" > ");
            sb.append(getMaximumNumberOfMethods());
            if (getNumberOfFields() > getMaximumNumberOfFields()) {
                sb.append(" ; ");
            }
        }
        if (getNumberOfFields() > getMaximumNumberOfFields()) {
            sb.append("# fields: ");
            sb.append(getNumberOfFields());
            sb.append(" > ");
            sb.append(getMaximumNumberOfFields());
        }
        sb.append(")");
        return sb.toString();
    }

    public long getMaximumNumberOfFields() {
        return 65536L;
    }

    public long getMaximumNumberOfMethods() {
        return 65536L;
    }

    public long getNumberOfFields() {
        return this.d;
    }

    public long getNumberOfMethods() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return null;
    }

    public boolean hasMainDexSpecification() {
        return this.b;
    }
}
