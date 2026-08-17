package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.types.EmptyIntersectionTypeKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B;\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/InferredEmptyIntersectionDiagnostic;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "incompatibleTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "causingTypes", "typeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "kind", "Lorg/jetbrains/kotlin/types/EmptyIntersectionTypeKind;", "isError", Argument.Delimiters.none, "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;Lorg/jetbrains/kotlin/types/EmptyIntersectionTypeKind;Z)V", "getIncompatibleTypes", "()Ljava/util/Collection;", "getCausingTypes", "getTypeVariable", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "getKind", "()Lorg/jetbrains/kotlin/types/EmptyIntersectionTypeKind;", "()Z", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InferredEmptyIntersectionDiagnostic extends ResolutionDiagnostic {
    private final Collection<ConeKotlinType> causingTypes;
    private final Collection<ConeKotlinType> incompatibleTypes;
    private final boolean isError;
    private final EmptyIntersectionTypeKind kind;
    private final ConeTypeVariable typeVariable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferredEmptyIntersectionDiagnostic(Collection<? extends ConeKotlinType> collection, Collection<? extends ConeKotlinType> collection2, ConeTypeVariable coneTypeVariable, EmptyIntersectionTypeKind emptyIntersectionTypeKind, boolean z) {
        super(CandidateApplicability.INAPPLICABLE);
        collection.getClass();
        collection2.getClass();
        coneTypeVariable.getClass();
        emptyIntersectionTypeKind.getClass();
        this.incompatibleTypes = collection;
        this.causingTypes = collection2;
        this.typeVariable = coneTypeVariable;
        this.kind = emptyIntersectionTypeKind;
        this.isError = z;
    }

    public final Collection<ConeKotlinType> getCausingTypes() {
        return this.causingTypes;
    }

    public final Collection<ConeKotlinType> getIncompatibleTypes() {
        return this.incompatibleTypes;
    }

    public final EmptyIntersectionTypeKind getKind() {
        return this.kind;
    }

    public final ConeTypeVariable getTypeVariable() {
        return this.typeVariable;
    }

    /* JADX INFO: renamed from: isError, reason: from getter */
    public final boolean getIsError() {
        return this.isError;
    }
}
