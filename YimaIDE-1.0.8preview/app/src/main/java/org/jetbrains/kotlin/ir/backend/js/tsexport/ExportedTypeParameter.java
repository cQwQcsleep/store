package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedTypeParameter;", "", "name", "", "variance", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVariance;", "constraint", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVariance;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "getName", "()Ljava/lang/String;", "getVariance", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVariance;", "getConstraint", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "setConstraint", "(Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ExportedTypeParameter {
    private ExportedType constraint;
    private final String name;
    private final ExportedVariance variance;

    public ExportedTypeParameter(String str, ExportedVariance exportedVariance, ExportedType exportedType) {
        str.getClass();
        exportedVariance.getClass();
        this.name = str;
        this.variance = exportedVariance;
        this.constraint = exportedType;
    }

    public static /* synthetic */ ExportedTypeParameter copy$default(ExportedTypeParameter exportedTypeParameter, String str, ExportedVariance exportedVariance, ExportedType exportedType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = exportedTypeParameter.name;
        }
        if ((i & 2) != 0) {
            exportedVariance = exportedTypeParameter.variance;
        }
        if ((i & 4) != 0) {
            exportedType = exportedTypeParameter.constraint;
        }
        return exportedTypeParameter.copy(str, exportedVariance, exportedType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ExportedVariance getVariance() {
        return this.variance;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ExportedType getConstraint() {
        return this.constraint;
    }

    public final ExportedTypeParameter copy(String name, ExportedVariance variance, ExportedType constraint) {
        name.getClass();
        variance.getClass();
        return new ExportedTypeParameter(name, variance, constraint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExportedTypeParameter)) {
            return false;
        }
        ExportedTypeParameter exportedTypeParameter = (ExportedTypeParameter) other;
        return Intrinsics.areEqual(this.name, exportedTypeParameter.name) && this.variance == exportedTypeParameter.variance && Intrinsics.areEqual(this.constraint, exportedTypeParameter.constraint);
    }

    public final ExportedType getConstraint() {
        return this.constraint;
    }

    public final String getName() {
        return this.name;
    }

    public final ExportedVariance getVariance() {
        return this.variance;
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.variance.hashCode()) * 31;
        ExportedType exportedType = this.constraint;
        return iHashCode + (exportedType == null ? 0 : exportedType.hashCode());
    }

    public final void setConstraint(ExportedType exportedType) {
        this.constraint = exportedType;
    }

    public String toString() {
        return "ExportedTypeParameter(name=" + this.name + ", variance=" + this.variance + ", constraint=" + this.constraint + ')';
    }

    public /* synthetic */ ExportedTypeParameter(String str, ExportedVariance exportedVariance, ExportedType exportedType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, exportedVariance, (i & 4) != 0 ? null : exportedType);
    }
}
