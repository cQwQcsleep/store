package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0018\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfIntersection;", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "mostSpecific", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/FirSession;)V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "calc", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class DeferredReturnTypeOfIntersection extends DeferredCallableCopyReturnType {
    private final Collection<FirCallableSymbol<?>> mostSpecific;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public DeferredReturnTypeOfIntersection(Collection<? extends FirCallableSymbol<?>> collection, FirSession firSession) {
        collection.getClass();
        firSession.getClass();
        this.mostSpecific = collection;
        this.session = firSession;
    }

    public static ConeKotlinType a(CallableCopyTypeCalculator callableCopyTypeCalculator, FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return callableCopyTypeCalculator.computeReturnTypeOrNull(firCallableDeclaration);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType
    public ConeKotlinType computeReturnType(final CallableCopyTypeCalculator calc) {
        calc.getClass();
        return FirTypeIntersectionScopeContextKt.intersectReturnTypes(this.mostSpecific, this.session, new Function1() { // from class: org.jetbrains.kotlin.fir.scopes.impl.a
            public final Object invoke(Object obj) {
                return DeferredReturnTypeOfIntersection.a(calc, (FirCallableDeclaration) obj);
            }
        });
    }

    public String toString() {
        return "CallableCopyIntersection(mostSpecific=" + this.mostSpecific + ')';
    }
}
