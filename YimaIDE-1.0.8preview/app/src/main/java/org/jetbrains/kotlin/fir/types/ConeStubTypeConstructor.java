package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "variable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "isTypeVariableInSubtyping", Argument.Delimiters.none, "isForFixation", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;ZZ)V", "getVariable", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "()Z", "toString", Argument.Delimiters.none, "component1", "component2", "component3", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConeStubTypeConstructor implements ConeTypeConstructorMarker {
    private final boolean isForFixation;
    private final boolean isTypeVariableInSubtyping;
    private final ConeTypeVariable variable;

    public ConeStubTypeConstructor(ConeTypeVariable coneTypeVariable, boolean z, boolean z2) {
        coneTypeVariable.getClass();
        this.variable = coneTypeVariable;
        this.isTypeVariableInSubtyping = z;
        this.isForFixation = z2;
    }

    public static /* synthetic */ ConeStubTypeConstructor copy$default(ConeStubTypeConstructor coneStubTypeConstructor, ConeTypeVariable coneTypeVariable, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeVariable = coneStubTypeConstructor.variable;
        }
        if ((i & 2) != 0) {
            z = coneStubTypeConstructor.isTypeVariableInSubtyping;
        }
        if ((i & 4) != 0) {
            z2 = coneStubTypeConstructor.isForFixation;
        }
        return coneStubTypeConstructor.copy(coneTypeVariable, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeTypeVariable getVariable() {
        return this.variable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsTypeVariableInSubtyping() {
        return this.isTypeVariableInSubtyping;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsForFixation() {
        return this.isForFixation;
    }

    public final ConeStubTypeConstructor copy(ConeTypeVariable variable, boolean isTypeVariableInSubtyping, boolean isForFixation) {
        variable.getClass();
        return new ConeStubTypeConstructor(variable, isTypeVariableInSubtyping, isForFixation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConeStubTypeConstructor)) {
            return false;
        }
        ConeStubTypeConstructor coneStubTypeConstructor = (ConeStubTypeConstructor) other;
        return Intrinsics.areEqual(this.variable, coneStubTypeConstructor.variable) && this.isTypeVariableInSubtyping == coneStubTypeConstructor.isTypeVariableInSubtyping && this.isForFixation == coneStubTypeConstructor.isForFixation;
    }

    public final ConeTypeVariable getVariable() {
        return this.variable;
    }

    public int hashCode() {
        return (((this.variable.hashCode() * 31) + Boolean.hashCode(this.isTypeVariableInSubtyping)) * 31) + Boolean.hashCode(this.isForFixation);
    }

    public final boolean isForFixation() {
        return this.isForFixation;
    }

    public final boolean isTypeVariableInSubtyping() {
        return this.isTypeVariableInSubtyping;
    }

    public String toString() {
        return "Stub(" + this.variable.getTypeConstructor().getDebugName() + ')';
    }

    public /* synthetic */ ConeStubTypeConstructor(ConeTypeVariable coneTypeVariable, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneTypeVariable, z, (i & 4) != 0 ? false : z2);
    }
}
