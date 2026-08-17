package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitDispatchReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeWithSubstitutionKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u000e\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\t\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u0014\u0010\r\u001a\u00020\b*\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a\u0014\u0010\r\u001a\u00020\b*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\n\u001a\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u0002\u001a \u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018\u001a\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0002\u001a\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018*\u0016\u0010\u0019\"\b\u0012\u0004\u0012\u00020\u001b0\u001a2\b\u0012\u0004\u0012\u00020\u001b0\u001a*\u0016\u0010\u001c\"\b\u0012\u0004\u0012\u00020\u001e0\u001d2\b\u0012\u0004\u0012\u00020\u001e0\u001d¨\u0006\u001f"}, d2 = {"collectTowerDataElementsForClass", "Lorg/jetbrains/kotlin/fir/declarations/TowerElementsForClass;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "defaultType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "asTowerDataElement", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "isLocal", Argument.Delimiters.none, "asTowerDataElementForStaticScope", "staticScopeOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "scope", "staticScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "sessionHolder", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "ContextParameterGroup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "FirLocalScopes", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitReceiverUtilsKt {
    public static final FirTowerDataElement asTowerDataElement(ImplicitReceiverValue<?> implicitReceiverValue) {
        implicitReceiverValue.getClass();
        return new FirTowerDataElement(null, implicitReceiverValue, null, false, null, 20, null);
    }

    public static final FirTowerDataElement asTowerDataElementForStaticScope(FirScope firScope, FirRegularClassSymbol firRegularClassSymbol) {
        firScope.getClass();
        return new FirTowerDataElement(firScope, null, null, false, firRegularClassSymbol, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final TowerElementsForClass collectTowerDataElementsForClass(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirClass firClass, ConeKotlinType coneKotlinType) {
        FirRegularClass firRegularClass;
        FirContainingNamesAwareScope firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType;
        FirTowerDataElement firTowerDataElementAsTowerDataElementForStaticScope;
        FirRegularClassSymbol companionObjectSymbol;
        sessionAndScopeSessionHolder.getClass();
        firClass.getClass();
        coneKotlinType.getClass();
        ArrayList arrayList = new ArrayList();
        FirRegularClass firRegularClass2 = firClass instanceof FirRegularClass ? (FirRegularClass) firClass : null;
        FirRegularClass firRegularClass3 = (firRegularClass2 == null || (companionObjectSymbol = firRegularClass2.getCompanionObjectSymbol()) == null) ? null : (FirRegularClass) companionObjectSymbol.getFir();
        ImplicitDispatchReceiverValue implicitDispatchReceiverValue = firRegularClass3 != null ? new ImplicitDispatchReceiverValue(firRegularClass3.getSymbol(), (ConeKotlinType) null, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), 2, (DefaultConstructorMarker) null) : null;
        CollectionsKt.addIfNotNull(arrayList, implicitDispatchReceiverValue);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = SupertypeUtilsKt.lookupSuperTypes$default(firClass, false, true, sessionAndScopeSessionHolder.getSession(), true, null, 32, null).iterator();
        while (it.hasNext()) {
            ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) sessionAndScopeSessionHolder, (ConeClassLikeType) it.next());
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) sessionAndScopeSessionHolder, coneClassLikeTypeFullyExpandedType.getLookupTag());
            if (regularClassSymbol != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null) {
                FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = staticScope(firRegularClass, sessionAndScopeSessionHolder);
                if (firContainingNamesAwareScopeStaticScope != null && (firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType = FirNestedClassifierScopeWithSubstitutionKt.wrapNestedClassifierScopeWithSubstitutionForSuperType(firContainingNamesAwareScopeStaticScope, coneClassLikeTypeFullyExpandedType, sessionAndScopeSessionHolder.getSession())) != null && (firTowerDataElementAsTowerDataElementForStaticScope = asTowerDataElementForStaticScope(firContainingNamesAwareScopeWrapNestedClassifierScopeWithSubstitutionForSuperType, firRegularClass.getSymbol())) != null) {
                    arrayList2.add(firTowerDataElementAsTowerDataElementForStaticScope);
                }
                FirRegularClassSymbol companionObjectSymbol2 = firRegularClass.getCompanionObjectSymbol();
                if (companionObjectSymbol2 != null) {
                    ImplicitDispatchReceiverValue implicitDispatchReceiverValue2 = new ImplicitDispatchReceiverValue(companionObjectSymbol2, (ConeKotlinType) null, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), 2, (DefaultConstructorMarker) null);
                    arrayList2.add(asTowerDataElement(implicitDispatchReceiverValue2));
                    arrayList.add(implicitDispatchReceiverValue2);
                }
            }
        }
        ImplicitDispatchReceiverValue implicitDispatchReceiverValue3 = new ImplicitDispatchReceiverValue(firClass.getSymbol(), coneKotlinType, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession());
        ImplicitDispatchReceiverValue implicitDispatchReceiverValue4 = implicitDispatchReceiverValue;
        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope2 = staticScope(firClass, sessionAndScopeSessionHolder);
        FirClassSymbol<FirClass> symbol = firClass.getSymbol();
        return new TowerElementsForClass(implicitDispatchReceiverValue3, firContainingNamesAwareScopeStaticScope2, symbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) symbol : null, implicitDispatchReceiverValue4, firRegularClass3 != null ? staticScope(firRegularClass3, sessionAndScopeSessionHolder) : null, kotlin.collections.CollectionsKt.asReversedMutable(arrayList2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope staticScope(FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return staticScope((FirClass) firClassSymbol.getFir(), firSession, scopeSession);
    }

    public static final FirTowerDataElement asTowerDataElementForStaticScope(FirRegularClassSymbol firRegularClassSymbol, FirScope firScope) {
        firRegularClassSymbol.getClass();
        return new FirTowerDataElement(firScope, null, null, false, firRegularClassSymbol, 4, null);
    }

    public static final FirTowerDataElement asTowerDataElement(FirScope firScope, boolean z) {
        firScope.getClass();
        return new FirTowerDataElement(firScope, null, null, z, null, 20, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope staticScope(FirClassSymbol<?> firClassSymbol, SessionAndScopeSessionHolder sessionAndScopeSessionHolder) {
        firClassSymbol.getClass();
        sessionAndScopeSessionHolder.getClass();
        return staticScope((FirClass) firClassSymbol.getFir(), sessionAndScopeSessionHolder);
    }

    public static final FirContainingNamesAwareScope staticScope(FirClass firClass, SessionAndScopeSessionHolder sessionAndScopeSessionHolder) {
        firClass.getClass();
        sessionAndScopeSessionHolder.getClass();
        return staticScope(firClass, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession());
    }

    public static final FirContainingNamesAwareScope staticScope(FirClass firClass, FirSession firSession, ScopeSession scopeSession) {
        firClass.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return firClass.getScopeProvider().getStaticScope(firClass, firSession, scopeSession);
    }
}
