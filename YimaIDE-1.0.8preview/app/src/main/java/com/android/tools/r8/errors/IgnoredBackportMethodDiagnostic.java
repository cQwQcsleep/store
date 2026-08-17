package com.android.tools.r8.errors;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IgnoredBackportMethodDiagnostic implements DesugarDiagnostic {
    private final C0322w2 b;
    private final Origin c;
    private final Position d;
    private final int e;

    public IgnoredBackportMethodDiagnostic(C0322w2 c0322w2, Origin origin, Position position, int i) {
        this.b = c0322w2;
        this.c = origin;
        this.d = position;
        this.e = i;
    }

    public int getConfiguredMinApiLevel() {
        return this.e;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Ignored reference to backport " + this.b.m0() + ". The compiler is compiling for min-api " + this.e + " which includes runtimes that do not support " + this.b.m0() + " but this method will be retained as is (i.e., it is not backported).";
    }

    public MethodReference getIgnoredBackportMethod() {
        return this.b.z0();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.d;
    }
}
