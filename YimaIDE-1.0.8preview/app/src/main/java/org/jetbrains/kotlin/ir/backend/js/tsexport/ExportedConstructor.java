package org.jetbrains.kotlin.ir.backend.js.tsexport;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedConstructor;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedDeclaration;", "parameters", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedParameter;", "visibility", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVisibility;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVisibility;)V", "getParameters", "()Ljava/util/List;", "getVisibility", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVisibility;", "isProtected", "", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ExportedConstructor extends ExportedDeclaration {
    private final List<ExportedParameter> parameters;
    private final ExportedVisibility visibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExportedConstructor(List<ExportedParameter> list, ExportedVisibility exportedVisibility) {
        super(null);
        list.getClass();
        exportedVisibility.getClass();
        this.parameters = list;
        this.visibility = exportedVisibility;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExportedConstructor copy$default(ExportedConstructor exportedConstructor, List list, ExportedVisibility exportedVisibility, int i, Object obj) {
        if ((i & 1) != 0) {
            list = exportedConstructor.parameters;
        }
        if ((i & 2) != 0) {
            exportedVisibility = exportedConstructor.visibility;
        }
        return exportedConstructor.copy(list, exportedVisibility);
    }

    public final List<ExportedParameter> component1() {
        return this.parameters;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ExportedVisibility getVisibility() {
        return this.visibility;
    }

    public final ExportedConstructor copy(List<ExportedParameter> parameters, ExportedVisibility visibility) {
        parameters.getClass();
        visibility.getClass();
        return new ExportedConstructor(parameters, visibility);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExportedConstructor)) {
            return false;
        }
        ExportedConstructor exportedConstructor = (ExportedConstructor) other;
        return Intrinsics.areEqual(this.parameters, exportedConstructor.parameters) && this.visibility == exportedConstructor.visibility;
    }

    public final List<ExportedParameter> getParameters() {
        return this.parameters;
    }

    public final ExportedVisibility getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        return (this.parameters.hashCode() * 31) + this.visibility.hashCode();
    }

    @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedDeclaration
    public boolean isProtected() {
        return this.visibility == ExportedVisibility.PROTECTED;
    }

    public String toString() {
        return "ExportedConstructor(parameters=" + this.parameters + ", visibility=" + this.visibility + ')';
    }
}
