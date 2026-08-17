package org.jetbrains.kotlin.fir.java.enhancement;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/DelegatingDeferredReturnTypeWithSubstitution;", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "deferredCalc", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "calc", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class DelegatingDeferredReturnTypeWithSubstitution extends DeferredCallableCopyReturnType {
    private final DeferredCallableCopyReturnType deferredCalc;
    private final ConeSubstitutor substitutor;

    public DelegatingDeferredReturnTypeWithSubstitution(DeferredCallableCopyReturnType deferredCallableCopyReturnType, ConeSubstitutor coneSubstitutor) {
        deferredCallableCopyReturnType.getClass();
        coneSubstitutor.getClass();
        this.deferredCalc = deferredCallableCopyReturnType;
        this.substitutor = coneSubstitutor;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType
    public ConeKotlinType computeReturnType(CallableCopyTypeCalculator calc) {
        calc.getClass();
        ConeKotlinType coneKotlinTypeComputeReturnType = this.deferredCalc.computeReturnType(calc);
        if (coneKotlinTypeComputeReturnType != null) {
            return this.substitutor.substituteOrSelf(coneKotlinTypeComputeReturnType);
        }
        return null;
    }

    public String toString() {
        return "DelegatingDeferredReturnTypeWithSubstitution(deferredCalc=" + this.deferredCalc + ", substitutor=" + this.substitutor + ')';
    }
}
