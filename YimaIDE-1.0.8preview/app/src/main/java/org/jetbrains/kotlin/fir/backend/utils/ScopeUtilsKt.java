package org.jetbrains.kotlin.fir.backend.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0080\u0010R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006\u001a9\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\rH\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000f\u001a.\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\r\u001a.\u0010\u0014\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\r\u001a9\u0010\u0017\u001a\u00020\b*\u00020\u00182\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\rH\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0019\u001a!\u0010\u001a\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u001bH\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001a\u001a\u00020\u0012*\u00020\u000bH\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001d\u001a!\u0010\u001e\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\u001bH\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"unwrapCallRepresentative", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "owner", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processOverriddenFunctionSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function1;)V", "processOverriddenFunctionsFromSuperClasses", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "functionSymbol", "processOverriddenPropertiesFromSuperClasses", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processOverriddenPropertySymbols", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function1;)V", "unsubstitutedScope", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "declaredScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopeUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(Fir2IrComponents fir2IrComponents, FirNamedFunction firNamedFunction, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!FirVisibilityCheckerKt.getVisibilityChecker(fir2IrComponents.getSession()).isVisibleForOverriding((FirCallableDeclaration) firNamedFunction.getSymbol().getFir(), (FirCallableDeclaration) firNamedFunctionSymbol.getFir())) {
            return ProcessorAction.NEXT;
        }
        function1.invoke(firNamedFunctionSymbol);
        return ProcessorAction.NEXT;
    }

    public static Unit b(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction d(Fir2IrComponents fir2IrComponents, FirProperty firProperty, Function1 function1, FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        if (!FirVisibilityCheckerKt.getVisibilityChecker(fir2IrComponents.getSession()).isVisibleForOverriding((FirCallableDeclaration) firProperty.getSymbol().getFir(), (FirCallableDeclaration) firPropertySymbol.getFir())) {
            return ProcessorAction.NEXT;
        }
        function1.invoke(firPropertySymbol);
        return ProcessorAction.NEXT;
    }

    public static final FirContainingNamesAwareScope declaredScope(Fir2IrComponents fir2IrComponents, FirClassSymbol<?> firClassSymbol) {
        fir2IrComponents.getClass();
        firClassSymbol.getClass();
        return FirDeclaredMemberScopeProviderKt.declaredMemberScope(firClassSymbol, fir2IrComponents.getSession(), FirResolvePhase.STATUS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction e(ConeClassLikeLookupTag coneClassLikeLookupTag, Function1 function1, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        if (ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) firPropertySymbol.getFir()) && CallableIdUtilsKt.isRealOwnerOf(coneClassLikeLookupTag, firPropertySymbol)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firPropertySymbol.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            firPropertySymbol = (FirPropertySymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
            firPropertySymbol.getClass();
        }
        return (ProcessorAction) function1.invoke(firPropertySymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction f(ConeClassLikeLookupTag coneClassLikeLookupTag, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        if (ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) firNamedFunctionSymbol.getFir()) && CallableIdUtilsKt.isRealOwnerOf(coneClassLikeLookupTag, firNamedFunctionSymbol)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            firNamedFunctionSymbol = (FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
            firNamedFunctionSymbol.getClass();
        }
        return (ProcessorAction) function1.invoke(firNamedFunctionSymbol);
    }

    public static final void processOverriddenFunctionSymbols(final Fir2IrComponents fir2IrComponents, final FirNamedFunction firNamedFunction, FirClass firClass, final Function1<? super FirNamedFunctionSymbol, Unit> function1) {
        fir2IrComponents.getClass();
        firNamedFunction.getClass();
        firClass.getClass();
        function1.getClass();
        FirTypeScope firTypeScopeUnsubstitutedScope = unsubstitutedScope(fir2IrComponents, firClass);
        firTypeScopeUnsubstitutedScope.processFunctionsByName(firNamedFunction.getName(), new Function1() { // from class: hxc
            public final Object invoke(Object obj) {
                return ScopeUtilsKt.b((FirNamedFunctionSymbol) obj);
            }
        });
        processOverriddenFunctionsFromSuperClasses(firTypeScopeUnsubstitutedScope, firNamedFunction.getSymbol(), firClass, new Function1() { // from class: lxc
            public final Object invoke(Object obj) {
                return ScopeUtilsKt.a(fir2IrComponents, firNamedFunction, function1, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    public static final ProcessorAction processOverriddenFunctionsFromSuperClasses(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, FirClass firClass, final Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        firClass.getClass();
        function1.getClass();
        final ConeClassLikeLookupTag lookupTag = firClass.getSymbol().getLookupTag();
        return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, new Function2() { // from class: qxc
            public final Object invoke(Object obj, Object obj2) {
                return ScopeUtilsKt.f(lookupTag, function1, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
            }
        });
    }

    public static final ProcessorAction processOverriddenPropertiesFromSuperClasses(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, FirClass firClass, final Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firPropertySymbol.getClass();
        firClass.getClass();
        function1.getClass();
        final ConeClassLikeLookupTag lookupTag = firClass.getSymbol().getLookupTag();
        return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, new Function2() { // from class: pxc
            public final Object invoke(Object obj, Object obj2) {
                return ScopeUtilsKt.e(lookupTag, function1, (FirPropertySymbol) obj, (FirTypeScope) obj2);
            }
        });
    }

    public static final void processOverriddenPropertySymbols(final Fir2IrComponents fir2IrComponents, final FirProperty firProperty, FirClass firClass, final Function1<? super FirPropertySymbol, Unit> function1) {
        fir2IrComponents.getClass();
        firProperty.getClass();
        firClass.getClass();
        function1.getClass();
        FirTypeScope firTypeScopeUnsubstitutedScope = unsubstitutedScope(fir2IrComponents, firClass);
        firTypeScopeUnsubstitutedScope.processPropertiesByName(firProperty.getName(), new Function1() { // from class: nxc
            public final Object invoke(Object obj) {
                return ScopeUtilsKt.c((FirVariableSymbol) obj);
            }
        });
        processOverriddenPropertiesFromSuperClasses(firTypeScopeUnsubstitutedScope, firProperty.getSymbol(), firClass, new Function1() { // from class: oxc
            public final Object invoke(Object obj) {
                return ScopeUtilsKt.d(fir2IrComponents, firProperty, function1, (FirPropertySymbol) obj);
            }
        });
    }

    public static final FirTypeScope unsubstitutedScope(Fir2IrComponents fir2IrComponents, FirClass firClass) {
        fir2IrComponents.getClass();
        firClass.getClass();
        return unsubstitutedScope(fir2IrComponents, firClass.getSymbol());
    }

    public static final FirCallableSymbol<?> unwrapCallRepresentative(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        FirCallableDeclaration originalForIntersectionOverrideAttr;
        fir2IrComponents.getClass();
        firCallableSymbol.getClass();
        while (true) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
            if (firCallableDeclaration instanceof FirConstructor) {
                TypeAliasConstructorInfo typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirFunction) firCallableDeclaration);
                FirConstructor firConstructor = typeAliasConstructorInfo != null ? (FirConstructor) typeAliasConstructorInfo.getOriginalConstructor() : null;
                if (firConstructor != null) {
                    firCallableSymbol = firConstructor.getSymbol();
                }
            }
            if (!ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
                originalForIntersectionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForIntersectionOverrideAttr == null || !Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(originalForIntersectionOverrideAttr), coneClassLikeLookupTag)) {
                    break;
                }
                firCallableSymbol = originalForIntersectionOverrideAttr.getSymbol();
            } else {
                if (!(firCallableDeclaration.getDispatchReceiverType() instanceof ConeIntersectionType)) {
                    break;
                }
                originalForIntersectionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                originalForIntersectionOverrideAttr.getClass();
                firCallableSymbol = originalForIntersectionOverrideAttr.getSymbol();
            }
        }
        return firCallableSymbol;
    }

    public static /* synthetic */ FirCallableSymbol unwrapCallRepresentative$default(Fir2IrComponents fir2IrComponents, FirCallableSymbol firCallableSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol);
        }
        return unwrapCallRepresentative(fir2IrComponents, firCallableSymbol, coneClassLikeLookupTag);
    }

    public static final FirTypeScope unsubstitutedScope(Fir2IrComponents fir2IrComponents, FirClassSymbol<?> firClassSymbol) {
        fir2IrComponents.getClass();
        firClassSymbol.getClass();
        return FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) fir2IrComponents, firClassSymbol, true, FirResolvePhase.STATUS);
    }
}
