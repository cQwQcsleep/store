package org.jetbrains.kotlin.descriptors.annotations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationWithTarget;", "", "annotation", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "target", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "getAnnotation", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "getTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class AnnotationWithTarget {
    private final AnnotationDescriptor annotation;
    private final AnnotationUseSiteTarget target;

    public AnnotationWithTarget(AnnotationDescriptor annotationDescriptor, AnnotationUseSiteTarget annotationUseSiteTarget) {
        annotationDescriptor.getClass();
        annotationUseSiteTarget.getClass();
        this.annotation = annotationDescriptor;
        this.target = annotationUseSiteTarget;
    }

    public static /* synthetic */ AnnotationWithTarget copy$default(AnnotationWithTarget annotationWithTarget, AnnotationDescriptor annotationDescriptor, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 1) != 0) {
            annotationDescriptor = annotationWithTarget.annotation;
        }
        if ((i & 2) != 0) {
            annotationUseSiteTarget = annotationWithTarget.target;
        }
        return annotationWithTarget.copy(annotationDescriptor, annotationUseSiteTarget);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AnnotationDescriptor getAnnotation() {
        return this.annotation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }

    public final AnnotationWithTarget copy(AnnotationDescriptor annotation, AnnotationUseSiteTarget target) {
        annotation.getClass();
        target.getClass();
        return new AnnotationWithTarget(annotation, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationWithTarget)) {
            return false;
        }
        AnnotationWithTarget annotationWithTarget = (AnnotationWithTarget) other;
        return Intrinsics.areEqual(this.annotation, annotationWithTarget.annotation) && this.target == annotationWithTarget.target;
    }

    public final AnnotationDescriptor getAnnotation() {
        return this.annotation;
    }

    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }

    public int hashCode() {
        return (this.annotation.hashCode() * 31) + this.target.hashCode();
    }

    public String toString() {
        return "AnnotationWithTarget(annotation=" + this.annotation + ", target=" + this.target + ')';
    }
}
