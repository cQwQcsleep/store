package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "<init>", "()V", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "resolvedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getResolvedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "receiverTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "calculateResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReceiverParameterSymbol extends FirThisOwnerSymbol<FirReceiverParameter> {
    public FirReceiverParameterSymbol() {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirTypeRef receiverTypeRef() {
        return ((FirReceiverParameter) getFir()).getTypeRef();
    }

    public final FirResolvedTypeRef calculateResolvedTypeRef() {
        FirResolvedTypeRef firResolvedTypeRefReceiverTypeRef = receiverTypeRef();
        if (firResolvedTypeRefReceiverTypeRef instanceof FirResolvedTypeRef) {
            return firResolvedTypeRefReceiverTypeRef;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.TYPES);
        FirResolvedTypeRef firResolvedTypeRefReceiverTypeRef2 = receiverTypeRef();
        if (firResolvedTypeRefReceiverTypeRef2 instanceof FirResolvedTypeRef) {
            return firResolvedTypeRefReceiverTypeRef2;
        }
        UtilsKt.errorInLazyResolve(this, "receiverTypeRef", Reflection.getOrCreateKotlinClass(firResolvedTypeRefReceiverTypeRef.getClass()), Reflection.getOrCreateKotlinClass(FirResolvedTypeRef.class));
        wq6.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return ((FirReceiverParameter) getFir()).getContainingDeclarationSymbol();
    }

    public final ConeKotlinType getResolvedType() {
        return calculateResolvedTypeRef().getConeType();
    }

    public String toString() {
        return "FirReceiverParameterSymbol";
    }
}
