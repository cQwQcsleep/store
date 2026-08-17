package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ReturnArgumentsAnalysisResult;", Argument.Delimiters.none, "returnArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "additionalConstraints", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "<init>", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;)V", "getReturnArguments", "()Ljava/util/Collection;", "getAdditionalConstraints", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ReturnArgumentsAnalysisResult {
    private final ConstraintStorage additionalConstraints;
    private final Collection<ConeResolutionAtom> returnArguments;

    /* JADX WARN: Multi-variable type inference failed */
    public ReturnArgumentsAnalysisResult(Collection<? extends ConeResolutionAtom> collection, ConstraintStorage constraintStorage) {
        collection.getClass();
        this.returnArguments = collection;
        this.additionalConstraints = constraintStorage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReturnArgumentsAnalysisResult copy$default(ReturnArgumentsAnalysisResult returnArgumentsAnalysisResult, Collection collection, ConstraintStorage constraintStorage, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = returnArgumentsAnalysisResult.returnArguments;
        }
        if ((i & 2) != 0) {
            constraintStorage = returnArgumentsAnalysisResult.additionalConstraints;
        }
        return returnArgumentsAnalysisResult.copy(collection, constraintStorage);
    }

    public final Collection<ConeResolutionAtom> component1() {
        return this.returnArguments;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConstraintStorage getAdditionalConstraints() {
        return this.additionalConstraints;
    }

    public final ReturnArgumentsAnalysisResult copy(Collection<? extends ConeResolutionAtom> returnArguments, ConstraintStorage additionalConstraints) {
        returnArguments.getClass();
        return new ReturnArgumentsAnalysisResult(returnArguments, additionalConstraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReturnArgumentsAnalysisResult)) {
            return false;
        }
        ReturnArgumentsAnalysisResult returnArgumentsAnalysisResult = (ReturnArgumentsAnalysisResult) other;
        return Intrinsics.areEqual(this.returnArguments, returnArgumentsAnalysisResult.returnArguments) && Intrinsics.areEqual(this.additionalConstraints, returnArgumentsAnalysisResult.additionalConstraints);
    }

    public final ConstraintStorage getAdditionalConstraints() {
        return this.additionalConstraints;
    }

    public final Collection<ConeResolutionAtom> getReturnArguments() {
        return this.returnArguments;
    }

    public int hashCode() {
        int iHashCode = this.returnArguments.hashCode() * 31;
        ConstraintStorage constraintStorage = this.additionalConstraints;
        return iHashCode + (constraintStorage == null ? 0 : constraintStorage.hashCode());
    }

    public String toString() {
        return "ReturnArgumentsAnalysisResult(returnArguments=" + this.returnArguments + ", additionalConstraints=" + this.additionalConstraints + ')';
    }
}
