package com.android.tools.r8.tracereferences;

import com.android.tools.r8.internal.Wf0;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class b extends a {
    public final boolean c;

    public b(boolean z) {
        this.c = z;
    }

    @Override // com.android.tools.r8.tracereferences.a
    public final void a(List list) {
        if (list.isEmpty()) {
            return;
        }
        a("-keeppackagenames " + Wf0.a(",", (Iterable) list) + System.lineSeparator());
    }

    @Override // com.android.tools.r8.tracereferences.a
    public final void b(TraceReferencesConsumer.TracedClass tracedClass) {
        if (tracedClass.isMissingDefinition()) {
            b("# Missing class: " + tracedClass.getReference().getTypeName());
            return;
        }
        a(this.c ? "-keep,allowobfuscation" : "-keep");
        if (((TraceReferencesConsumer.ClassAccessFlags) tracedClass.getAccessFlags()).isInterface()) {
            b(" interface " + tracedClass.getReference().getTypeName() + " {");
            return;
        }
        if (((TraceReferencesConsumer.ClassAccessFlags) tracedClass.getAccessFlags()).isEnum()) {
            b(" enum " + tracedClass.getReference().getTypeName() + " {");
            return;
        }
        b(" class " + tracedClass.getReference().getTypeName() + " {");
    }

    @Override // com.android.tools.r8.tracereferences.a
    public final void b(TraceReferencesConsumer.TracedField tracedField) {
        a("  " + tracedField.getReference().getFieldType().getTypeName() + " " + tracedField.getReference().getFieldName() + ";" + System.lineSeparator());
    }
}
