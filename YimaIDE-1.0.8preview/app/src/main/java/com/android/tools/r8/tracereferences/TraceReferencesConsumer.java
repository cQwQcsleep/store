package com.android.tools.r8.tracereferences;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.PackageReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface TraceReferencesConsumer {

    public interface AccessFlags {
        boolean isPrivate();

        boolean isProtected();

        boolean isPublic();

        boolean isStatic();
    }

    public interface ClassAccessFlags extends AccessFlags {
        boolean isEnum();

        boolean isInterface();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPrivate();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isProtected();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPublic();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isStatic();
    }

    public interface FieldAccessFlags extends AccessFlags {
        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPrivate();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isProtected();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPublic();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isStatic();
    }

    public static class ForwardingConsumer implements TraceReferencesConsumer {
        private static final ForwardingConsumer b = new ForwardingConsumer(null);
        private final TraceReferencesConsumer a;

        public ForwardingConsumer(TraceReferencesConsumer traceReferencesConsumer) {
            this.a = traceReferencesConsumer;
        }

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
        public void acceptField(TracedField tracedField, DiagnosticsHandler diagnosticsHandler) {
            TraceReferencesConsumer traceReferencesConsumer = this.a;
            if (traceReferencesConsumer != null) {
                traceReferencesConsumer.acceptField(tracedField, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
        public void acceptMethod(TracedMethod tracedMethod, DiagnosticsHandler diagnosticsHandler) {
            TraceReferencesConsumer traceReferencesConsumer = this.a;
            if (traceReferencesConsumer != null) {
                traceReferencesConsumer.acceptMethod(tracedMethod, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
        public void acceptPackage(PackageReference packageReference, DiagnosticsHandler diagnosticsHandler) {
            TraceReferencesConsumer traceReferencesConsumer = this.a;
            if (traceReferencesConsumer != null) {
                traceReferencesConsumer.acceptPackage(packageReference, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
        public void acceptType(TracedClass tracedClass, DiagnosticsHandler diagnosticsHandler) {
            TraceReferencesConsumer traceReferencesConsumer = this.a;
            if (traceReferencesConsumer != null) {
                traceReferencesConsumer.acceptType(tracedClass, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            TraceReferencesConsumer traceReferencesConsumer = this.a;
            if (traceReferencesConsumer != null) {
                traceReferencesConsumer.finished(diagnosticsHandler);
            }
        }
    }

    public interface MethodAccessFlags extends AccessFlags {
        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPrivate();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isProtected();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isPublic();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
        /* synthetic */ boolean isStatic();
    }

    public interface TracedClass extends TracedReference<ClassReference, ClassAccessFlags> {
        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ ClassAccessFlags getAccessFlags();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ DefinitionContext getReferencedFromContext();
    }

    public interface TracedField extends TracedReference<FieldReference, FieldAccessFlags> {
        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ FieldAccessFlags getAccessFlags();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ DefinitionContext getReferencedFromContext();
    }

    public interface TracedMethod extends TracedReference<MethodReference, MethodAccessFlags> {
        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ MethodAccessFlags getAccessFlags();

        @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
        /* synthetic */ DefinitionContext getReferencedFromContext();
    }

    public interface TracedReference<T, F> {
        F getAccessFlags();

        T getReference();

        DefinitionContext getReferencedFromContext();

        boolean isMissingDefinition();
    }

    static TraceReferencesConsumer emptyConsumer() {
        return ForwardingConsumer.b;
    }

    void acceptField(TracedField tracedField, DiagnosticsHandler diagnosticsHandler);

    void acceptMethod(TracedMethod tracedMethod, DiagnosticsHandler diagnosticsHandler);

    default void acceptPackage(PackageReference packageReference, DiagnosticsHandler diagnosticsHandler) {
    }

    void acceptType(TracedClass tracedClass, DiagnosticsHandler diagnosticsHandler);

    default void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
