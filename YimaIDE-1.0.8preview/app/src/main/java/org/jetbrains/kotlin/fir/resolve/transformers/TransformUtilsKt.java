package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\"\u0010\u000b\u001a\u00020\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a1\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0014H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a;\u0010\u0016\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u00192\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0014H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\"\u0019\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\t8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, d2 = {"transformVarargTypeToArrayType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "transformTypeToArrayType", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "isArrayConstructorWithLambda", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "transformInlineStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "parameter", "functionIsInline", "withScopeCleanup", "T", "scopes", Argument.Delimiters.none, "l", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withClassDeclarationCleanup", "classDeclarations", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "topClassDeclaration", "(Lkotlin/collections/ArrayDeque;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TransformUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isArrayConstructorWithLambda(FirBasedSymbol<?> firBasedSymbol) {
        FirConstructor firConstructor;
        firBasedSymbol.getClass();
        FirConstructorSymbol firConstructorSymbol = firBasedSymbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) firBasedSymbol : null;
        if (firConstructorSymbol == null || (firConstructor = (FirConstructor) firConstructorSymbol.getFir()) == null || firConstructor.getValueParameters().size() != 2) {
            return false;
        }
        return ArrayUtilsKt.isArrayOrPrimitiveArray(FirTypeUtilsKt.getConeType(firConstructor.getReturnTypeRef()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void transformInlineStatus(FirAnonymousFunction firAnonymousFunction, FirValueParameter firValueParameter, boolean z, FirSession firSession) {
        InlineStatus inlineStatus;
        Object next;
        FirValueParameter firValueParameter2;
        firAnonymousFunction.getClass();
        firValueParameter.getClass();
        firSession.getClass();
        FirBasedSymbol<?> containingDeclarationSymbol = firValueParameter.getContainingDeclarationSymbol();
        FirFunctionSymbol firFunctionSymbol = containingDeclarationSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) containingDeclarationSymbol : null;
        if (firFunctionSymbol != null && ClassMembersKt.isSubstitutionOrIntersectionOverride(firFunctionSymbol)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firFunctionSymbol.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol<*>");
                return;
            }
            Iterator<T> it = ((FirFunctionSymbol) symbol).getValueParameterSymbols().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((FirValueParameterSymbol) next).getName(), firValueParameter.getName()));
            FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) next;
            if (firValueParameterSymbol != null && (firValueParameter2 = (FirValueParameter) firValueParameterSymbol.getFir()) != null) {
                firValueParameter = firValueParameter2;
            }
        }
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), firSession, false, 2, (Object) null);
        if (functionTypeKindFunctionTypeKind$default == null || !functionTypeKindFunctionTypeKind$default.isInlineable() || firValueParameter.getIsNoinline()) {
            inlineStatus = InlineStatus.NoInline;
        } else if (firValueParameter.getIsCrossinline() && z) {
            inlineStatus = InlineStatus.CrossInline;
        } else {
            inlineStatus = z ? InlineStatus.Inline : InlineStatus.NoInline;
        }
        firAnonymousFunction.replaceInlineStatus(inlineStatus);
    }

    public static final void transformTypeToArrayType(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        KtSourceElement source;
        firCallableDeclaration.getClass();
        firSession.getClass();
        FirResolvedTypeRef returnTypeRef = firCallableDeclaration.getReturnTypeRef();
        if (!(returnTypeRef instanceof FirResolvedTypeRef)) {
            w01.a("Failed requirement.");
            return;
        }
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef;
        if (firResolvedTypeRef.getDelegatedTypeRef() instanceof FirResolvedTypeRef) {
            FirTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
            if (Intrinsics.areEqual((delegatedTypeRef == null || (source = delegatedTypeRef.getSource()) == null) ? null : source.getKind(), KtFakeSourceElementKind.ArrayTypeFromVarargParameter.INSTANCE)) {
                return;
            }
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(firResolvedTypeRef.getConeType(), firSession, (Function1) null, 2, (Object) null);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(firResolvedTypeRef.getSource());
        firResolvedTypeRefBuilder.setConeType(ArrayUtilsKt.createArrayType$default(new ConeKotlinTypeProjectionOut(coneKotlinTypeFullyExpandedType$default), false, false, 3, null));
        CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), firResolvedTypeRef.getAnnotations());
        firResolvedTypeRefBuilder.setDelegatedTypeRef(UtilsKt.copyWithNewSourceKind(returnTypeRef, KtFakeSourceElementKind.ArrayTypeFromVarargParameter.INSTANCE));
        firCallableDeclaration.replaceReturnTypeRef(firResolvedTypeRefBuilder.build());
    }

    public static final void transformVarargTypeToArrayType(FirValueParameter firValueParameter, FirSession firSession) {
        firValueParameter.getClass();
        firSession.getClass();
        if (firValueParameter.getIsVararg()) {
            transformTypeToArrayType(firValueParameter, firSession);
        }
    }

    public static final <T> T withClassDeclarationCleanup(ArrayDeque<FirClass> arrayDeque, FirClass firClass, Function0<? extends T> function0) {
        arrayDeque.getClass();
        firClass.getClass();
        function0.getClass();
        arrayDeque.addLast(firClass);
        try {
            return (T) function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            arrayDeque.removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withScopeCleanup(List<?> list, Function0<? extends T> function0) {
        list.getClass();
        function0.getClass();
        int size = list.size();
        int i = 0;
        try {
            return (T) function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            int size2 = list.size() - size;
            while (i < size2) {
                list.remove(list.size() - 1);
                i++;
            }
            InlineMarker.finallyEnd(1);
        }
    }
}
