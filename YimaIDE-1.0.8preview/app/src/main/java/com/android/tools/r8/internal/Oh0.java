package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.Oh0;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.PackageReference;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Oh0 implements TraceReferencesConsumer {
    public final HashSet a = new HashSet();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final HashSet d = new HashSet();

    public static /* synthetic */ Set a(ClassReference classReference) {
        return new HashSet();
    }

    public static /* synthetic */ Set b(ClassReference classReference) {
        return new HashSet();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public final void acceptField(TraceReferencesConsumer.TracedField tracedField, DiagnosticsHandler diagnosticsHandler) {
        ((Set) this.b.computeIfAbsent(tracedField.getReference().getHolderClass(), new Function() { // from class: woa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Oh0.a((ClassReference) obj);
            }
        })).add(tracedField);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public final void acceptMethod(TraceReferencesConsumer.TracedMethod tracedMethod, DiagnosticsHandler diagnosticsHandler) {
        ((Set) this.c.computeIfAbsent(tracedMethod.getReference().getHolderClass(), new Function() { // from class: xoa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Oh0.b((ClassReference) obj);
            }
        })).add(tracedMethod);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public final void acceptPackage(PackageReference packageReference, DiagnosticsHandler diagnosticsHandler) {
        this.d.add(packageReference);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public final void acceptType(TraceReferencesConsumer.TracedClass tracedClass, DiagnosticsHandler diagnosticsHandler) {
        this.a.add(tracedClass);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
