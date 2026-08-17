package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.inspector.FieldInspector;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.Reference;
import java.util.Optional;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1864jp implements FieldInspector {
    public final C2439qc a;
    public final C0210g1 b;
    public FieldReference c = null;

    public C1864jp(C2439qc c2439qc, C0210g1 c0210g1) {
        this.a = c2439qc;
        this.b = c0210g1;
    }

    @Override // com.android.tools.r8.inspector.FieldInspector
    public final FieldReference getFieldReference() {
        if (this.c == null) {
            this.c = Reference.field(this.a.getClassReference(), this.b.getReference().g.toString(), Reference.typeFromDescriptor(this.b.getReference().i.Z0()));
        }
        return this.c;
    }

    @Override // com.android.tools.r8.inspector.FieldInspector
    public final Optional getInitialValue() {
        return (!this.b.z0() || this.b.R0() == null) ? Optional.empty() : Optional.of(new C2971wl0(this.b.R0(), this.b.getReference().i));
    }

    @Override // com.android.tools.r8.inspector.FieldInspector
    public final boolean isFinal() {
        return this.b.g.f();
    }

    @Override // com.android.tools.r8.inspector.FieldInspector
    public final boolean isStatic() {
        return this.b.g.n();
    }
}
