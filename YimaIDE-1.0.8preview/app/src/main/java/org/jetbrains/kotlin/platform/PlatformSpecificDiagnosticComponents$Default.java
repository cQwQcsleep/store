package org.jetbrains.kotlin.platform;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/platform/PlatformSpecificDiagnosticComponents$Default;", "Lorg/jetbrains/kotlin/platform/PlatformSpecificDiagnosticComponents;", "<init>", "()V", "isNullabilityAnnotation", "", "annotationDescriptor", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlatformSpecificDiagnosticComponents$Default implements PlatformSpecificDiagnosticComponents {
    public static final PlatformSpecificDiagnosticComponents$Default INSTANCE = new PlatformSpecificDiagnosticComponents$Default();

    private PlatformSpecificDiagnosticComponents$Default() {
    }

    public boolean isNullabilityAnnotation(AnnotationDescriptor annotationDescriptor, DeclarationDescriptor containingDeclaration) {
        annotationDescriptor.getClass();
        containingDeclaration.getClass();
        return false;
    }
}
