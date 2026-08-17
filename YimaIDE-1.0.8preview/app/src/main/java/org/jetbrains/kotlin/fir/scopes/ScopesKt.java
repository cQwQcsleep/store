package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a.\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\r\u001a0\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f*\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0014"}, d2 = {"getNestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "debugCollectOverrides", Argument.Delimiters.none, Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "overriddenFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopesKt {
    public static ProcessorAction a(List list, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        list.add(debugCollectOverrides$process(firTypeScope, firNamedFunctionSymbol));
        return ProcessorAction.NEXT;
    }

    public static ProcessorAction b(Set set, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        set.add(firNamedFunctionSymbol);
        return ProcessorAction.NEXT;
    }

    public static ProcessorAction c(List list, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        list.add(debugCollectOverrides$process(firTypeScope, firPropertySymbol));
        return ProcessorAction.NEXT;
    }

    public static Unit d(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static final Map<Object, Object> debugCollectOverrides(FirCallableSymbol<?> firCallableSymbol, FirSession firSession, ScopeSession scopeSession) {
        FirTypeScope firTypeScopeScope;
        firCallableSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
        return (dispatchReceiverType == null || (firTypeScopeScope = ScopeUtilsKt.scope(dispatchReceiverType, firSession, scopeSession, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS)) == null) ? MapsKt.emptyMap() : debugCollectOverrides(firCallableSymbol, firTypeScopeScope);
    }

    private static final Map<Object, Object> debugCollectOverrides$process(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        final ArrayList arrayList = new ArrayList();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            firTypeScope.processDirectOverriddenFunctionsWithBaseScope((FirNamedFunctionSymbol) firCallableSymbol, new Function2() { // from class: txc
                public final Object invoke(Object obj, Object obj2) {
                    return ScopesKt.a(arrayList, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
                }
            });
        } else if (firCallableSymbol instanceof FirPropertySymbol) {
            firTypeScope.processDirectOverriddenPropertiesWithBaseScope((FirPropertySymbol) firCallableSymbol, new Function2() { // from class: uxc
                public final Object invoke(Object obj, Object obj2) {
                    return ScopesKt.c(arrayList, (FirPropertySymbol) obj, (FirTypeScope) obj2);
                }
            });
        }
        linkedHashMap.put(firCallableSymbol, arrayList);
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope getNestedClassifierScope(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, ScopeSession scopeSession) {
        FirClass firClass;
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        scopeSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTag, firSession);
        if (regularClassSymbol == null || (firClass = (FirRegularClass) regularClassSymbol.getFir()) == null) {
            return null;
        }
        return firClass.getScopeProvider().getNestedClassifierScope(firClass, firSession, scopeSession);
    }

    public static final Collection<FirFunctionSymbol<?>> overriddenFunctions(FirNamedFunctionSymbol firNamedFunctionSymbol, FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession) {
        firNamedFunctionSymbol.getClass();
        firClassSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope(firClassSymbol, firSession, scopeSession, true, FirResolvePhase.STATUS);
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        firTypeScopeUnsubstitutedScope.processFunctionsByName(firNamedFunctionSymbol.getCallableId().getCallableName(), new Function1() { // from class: vxc
            public final Object invoke(Object obj) {
                return ScopesKt.d((FirNamedFunctionSymbol) obj);
            }
        });
        FirTypeScopeKt.processOverriddenFunctions(firTypeScopeUnsubstitutedScope, firNamedFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: wxc
            public final Object invoke(Object obj) {
                return ScopesKt.b(linkedHashSet, (FirNamedFunctionSymbol) obj);
            }
        });
        linkedHashSet.remove(firNamedFunctionSymbol);
        return linkedHashSet;
    }

    public static final Map<Object, Object> debugCollectOverrides(FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        return debugCollectOverrides$process(firTypeScope, firCallableSymbol);
    }
}
