package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.mpp.ConstructorSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqNamesUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/mpp/ConstructorSymbolMarker;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "isPrimary", Argument.Delimiters.none, "()Z", "resolvedDelegatedConstructor", "getResolvedDelegatedConstructor", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "resolvedDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "getResolvedDelegatedConstructorCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "delegatedConstructorCallIsThis", "getDelegatedConstructorCallIsThis", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstructorSymbol extends FirFunctionSymbol<FirConstructor> implements ConstructorSymbolMarker {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirConstructorSymbol(ClassId classId) {
        this(FqNamesUtilKt.callableIdForConstructor(classId));
        classId.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getDelegatedConstructorCallIsThis() {
        FirDelegatedConstructorCall delegatedConstructor = ((FirConstructor) getFir()).getDelegatedConstructor();
        return delegatedConstructor != null && delegatedConstructor.isThis();
    }

    public final FirConstructorSymbol getResolvedDelegatedConstructor() {
        FirReference calleeReference;
        FirDelegatedConstructorCall resolvedDelegatedConstructorCall = getResolvedDelegatedConstructorCall();
        if (resolvedDelegatedConstructorCall == null || (calleeReference = resolvedDelegatedConstructorCall.getCalleeReference()) == null) {
            return null;
        }
        return FirReferenceUtilsKt.toResolvedConstructorSymbol$default(calleeReference, false, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirDelegatedConstructorCall getResolvedDelegatedConstructorCall() {
        if (((FirConstructor) getFir()).getDelegatedConstructor() == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.BODY_RESOLVE);
        return ((FirConstructor) getFir()).getDelegatedConstructor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isPrimary() {
        return ((FirConstructor) getFir()).isPrimary();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirConstructorSymbol(CallableId callableId) {
        super(callableId, null);
        callableId.getClass();
    }
}
