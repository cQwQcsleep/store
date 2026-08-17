package org.jetbrains.kotlin.analysis.decompiler.stub;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithTarget;", "", "annotationWithArgs", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithArgs;", "target", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithArgs;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "getAnnotationWithArgs", "()Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithArgs;", "getTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class AnnotationWithTarget {
    private final AnnotationWithArgs annotationWithArgs;
    private final AnnotationUseSiteTarget target;

    public AnnotationWithTarget(AnnotationWithArgs annotationWithArgs, AnnotationUseSiteTarget annotationUseSiteTarget) {
        annotationWithArgs.getClass();
        this.annotationWithArgs = annotationWithArgs;
        this.target = annotationUseSiteTarget;
    }

    public static /* synthetic */ AnnotationWithTarget copy$default(AnnotationWithTarget annotationWithTarget, AnnotationWithArgs annotationWithArgs, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 1) != 0) {
            annotationWithArgs = annotationWithTarget.annotationWithArgs;
        }
        if ((i & 2) != 0) {
            annotationUseSiteTarget = annotationWithTarget.target;
        }
        return annotationWithTarget.copy(annotationWithArgs, annotationUseSiteTarget);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AnnotationWithArgs getAnnotationWithArgs() {
        return this.annotationWithArgs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }

    public final AnnotationWithTarget copy(AnnotationWithArgs annotationWithArgs, AnnotationUseSiteTarget target) {
        annotationWithArgs.getClass();
        return new AnnotationWithTarget(annotationWithArgs, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationWithTarget)) {
            return false;
        }
        AnnotationWithTarget annotationWithTarget = (AnnotationWithTarget) other;
        return Intrinsics.areEqual(this.annotationWithArgs, annotationWithTarget.annotationWithArgs) && this.target == annotationWithTarget.target;
    }

    public final AnnotationWithArgs getAnnotationWithArgs() {
        return this.annotationWithArgs;
    }

    public final AnnotationUseSiteTarget getTarget() {
        return this.target;
    }

    public int hashCode() {
        int iHashCode = this.annotationWithArgs.hashCode() * 31;
        AnnotationUseSiteTarget annotationUseSiteTarget = this.target;
        return iHashCode + (annotationUseSiteTarget == null ? 0 : annotationUseSiteTarget.hashCode());
    }

    public String toString() {
        return "AnnotationWithTarget(annotationWithArgs=" + this.annotationWithArgs + ", target=" + this.target + Util.C_PARAM_END;
    }
}
