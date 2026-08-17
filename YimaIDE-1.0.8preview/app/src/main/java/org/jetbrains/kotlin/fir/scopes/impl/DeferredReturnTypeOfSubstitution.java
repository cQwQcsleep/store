package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfSubstitution;", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "baseSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "calc", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeferredReturnTypeOfSubstitution extends DeferredCallableCopyReturnType {
    private final FirBasedSymbol<?> baseSymbol;
    private final ConeSubstitutor substitutor;

    public DeferredReturnTypeOfSubstitution(ConeSubstitutor coneSubstitutor, FirBasedSymbol<?> firBasedSymbol) {
        coneSubstitutor.getClass();
        firBasedSymbol.getClass();
        this.substitutor = coneSubstitutor;
        this.baseSymbol = firBasedSymbol;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType
    public ConeKotlinType computeReturnType(CallableCopyTypeCalculator calc) throws KotlinIllegalArgumentExceptionWithAttachments {
        calc.getClass();
        FirDeclaration fir = this.baseSymbol.getFir();
        fir.getClass();
        ConeKotlinType coneKotlinTypeComputeReturnTypeOrNull = calc.computeReturnTypeOrNull((FirCallableDeclaration) fir);
        if (coneKotlinTypeComputeReturnTypeOrNull == null) {
            return null;
        }
        return this.substitutor.substituteOrSelf(coneKotlinTypeComputeReturnTypeOrNull);
    }

    public String toString() {
        return "DeferredReturnTypeOfSubstitution(substitutor=" + this.substitutor + ", baseSymbol=" + this.baseSymbol + ')';
    }
}
