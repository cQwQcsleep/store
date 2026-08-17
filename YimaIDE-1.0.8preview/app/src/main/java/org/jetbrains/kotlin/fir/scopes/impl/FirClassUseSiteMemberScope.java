package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirIntersectionScopeOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00072\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J.\u0010\u0016\u001a \u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u00172\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\f\u0010\u0019\u001a\u00020\u001a*\u00020\u001bH\u0014J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\u001c\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0017b\u0002\b\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassUseSiteMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirUseSiteMemberScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "superTypeScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;)V", "collectProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "computeDirectOverriddenForDeclaredProperty", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "declaredPropertySymbol", "getPropertiesAndFieldsFromSupertypesByName", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "isVisibleInCurrentClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassUseSiteMemberScope extends AbstractFirUseSiteMemberScope {
    private final FirClass klass;

    /* JADX WARN: Illegal instructions before constructor call */
    public FirClassUseSiteMemberScope(FirClass firClass, FirSession firSession, List<? extends FirTypeScope> list, FirContainingNamesAwareScope firContainingNamesAwareScope) {
        firClass.getClass();
        firSession.getClass();
        list.getClass();
        firContainingNamesAwareScope.getClass();
        ConeClassLikeLookupTag lookupTag = firClass.getSymbol().getLookupTag();
        FirOverrideChecker firOverrideChecker = FirOverrideCheckerKt.getFirOverrideChecker(firSession);
        FirOverrideChecker firOverrideChecker2 = FirOverrideCheckerKt.getFirOverrideChecker(firSession);
        firOverrideChecker2 = firOverrideChecker2 instanceof FirStandardOverrideChecker ? null : firOverrideChecker2;
        super(lookupTag, firSession, firOverrideChecker, firOverrideChecker2 == null ? new FirIntersectionScopeOverrideChecker(firSession) : firOverrideChecker2, list, ScopeUtilsKt.defaultType(firClass), firContainingNamesAwareScope);
        this.klass = firClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit collectProperties$lambda$0$0(FirClassUseSiteMemberScope firClassUseSiteMemberScope, Set set, List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol.getRawStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        if (firVariableSymbol instanceof FirPropertySymbol) {
            firClassUseSiteMemberScope.getDirectOverriddenProperties().put(firVariableSymbol, firClassUseSiteMemberScope.computeDirectOverriddenForDeclaredProperty((FirPropertySymbol) firVariableSymbol));
        }
        set.add(firVariableSymbol);
        list.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    private final List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>> computeDirectOverriddenForDeclaredProperty(FirPropertySymbol declaredPropertySymbol) {
        ArrayList arrayList = new ArrayList();
        for (FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection : (List) getPropertiesAndFieldsFromSupertypesByName(declaredPropertySymbol.getName()).getFirst()) {
            if (resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) {
                FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember singleMember = (FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) resultOfIntersection;
                FirCallableSymbol chosenSymbol = singleMember.getChosenSymbol();
                singleMember.getScopeOfChosenSymbol();
                if (FirOverrideCheckerKt.isOverriddenProperty(getOverrideChecker(), declaredPropertySymbol, (FirPropertySymbol) chosenSymbol)) {
                    arrayList.add(resultOfIntersection);
                }
            } else {
                if (!(resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial)) {
                    bu8.a();
                    return null;
                }
                Iterable overriddenMembers = ((FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection).getOverriddenMembers();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : overriddenMembers) {
                    MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) obj;
                    FirCallableSymbol member = memberWithBaseScope.getMember();
                    memberWithBaseScope.getBaseScope();
                    if (FirOverrideCheckerKt.isOverriddenProperty(getOverrideChecker(), declaredPropertySymbol, (FirPropertySymbol) member)) {
                        arrayList2.add(obj);
                    } else {
                        arrayList3.add(obj);
                    }
                }
                Pair pair = new Pair(arrayList2, arrayList3);
                List list = (List) pair.component1();
                if (((List) pair.component2()).isEmpty()) {
                    arrayList.add(resultOfIntersection);
                } else if (!list.isEmpty()) {
                    FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = ((AbstractFirUseSiteMemberScope) this).supertypeScopeContext;
                    List<MemberWithBaseScope> list2 = list;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (MemberWithBaseScope memberWithBaseScope2 : list2) {
                        arrayList4.add(TuplesKt.to(memberWithBaseScope2.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope2.getMember())));
                    }
                    CollectionsKt.addAll(arrayList, firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList4));
                }
            }
        }
        return arrayList;
    }

    private final Pair<List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>, List<FirFieldSymbol>> getPropertiesAndFieldsFromSupertypesByName(Name name) {
        List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>> list = getPropertiesFromSupertypes().get(name);
        if (list != null) {
            return TuplesKt.to(list, MapsKt.getValue(getFieldsFromSupertypes(), name));
        }
        final ArrayList arrayList = new ArrayList();
        FirTypeIntersectionScopeContext supertypeScopeContext = getSupertypeScopeContext();
        List<FirTypeScope> scopes = supertypeScopeContext.getScopes();
        ArrayList arrayList2 = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            ArrayList arrayList3 = new ArrayList();
            final FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1 firTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1 = new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList3);
            firTypeScope.processPropertiesByName(name, new Function1() { // from class: nz4
                public final Object invoke(Object obj) {
                    return FirClassUseSiteMemberScope.getPropertiesAndFieldsFromSupertypesByName$lambda$1$0(firTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1, arrayList, (FirVariableSymbol) obj);
                }
            });
            if (arrayList3.isEmpty()) {
                arrayList3 = null;
            }
            Pair pair = arrayList3 != null ? TuplesKt.to(firTypeScope, arrayList3) : null;
            if (pair != null) {
                arrayList2.add(pair);
            }
        }
        List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>> listConvertGroupedCallablesToIntersectionResults = supertypeScopeContext.convertGroupedCallablesToIntersectionResults(arrayList2);
        getPropertiesFromSupertypes().put(name, listConvertGroupedCallablesToIntersectionResults);
        getFieldsFromSupertypes().put(name, arrayList);
        return TuplesKt.to(listConvertGroupedCallablesToIntersectionResults, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getPropertiesAndFieldsFromSupertypesByName$lambda$1$0(Function1 function1, List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirPropertySymbol) {
            function1.invoke(firVariableSymbol);
        } else if (firVariableSymbol instanceof FirFieldSymbol) {
            list.add(firVariableSymbol);
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public Collection<FirVariableSymbol<?>> collectProperties(Name name) {
        name.getClass();
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        getDeclaredMemberScope().processPropertiesByName(name, new Function1() { // from class: oz4
            public final Object invoke(Object obj) {
                return FirClassUseSiteMemberScope.collectProperties$lambda$0$0(this.b, linkedHashSet, listCreateListBuilder, (FirVariableSymbol) obj);
            }
        });
        Pair<List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>, List<FirFieldSymbol>> propertiesAndFieldsFromSupertypesByName = getPropertiesAndFieldsFromSupertypesByName(name);
        List list = (List) propertiesAndFieldsFromSupertypesByName.component1();
        List list2 = (List) propertiesAndFieldsFromSupertypesByName.component2();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            collectNonOverriddenDeclarations((FirTypeIntersectionScopeContext.ResultOfIntersection) it.next(), linkedHashSet, listCreateListBuilder);
        }
        listCreateListBuilder.addAll(list2);
        return CollectionsKt.build(listCreateListBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public boolean isVisibleInCurrentClass(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return true;
    }

    public String toString() {
        return "Use site scope of " + getOwnerClassLookupTag().getClassId();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirClassUseSiteMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirClass firClass = this.klass;
        List<FirTypeScope> superTypeScopes = getSuperTypeScopes();
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypeScopes, 10));
        Iterator<T> it = superTypeScopes.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            FirScope firScope2 = (FirScope) it.next();
            FirScope firScopeWithReplacedSessionOrNull = firScope2.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                z = true;
                firScope = firScopeWithReplacedSessionOrNull;
            }
            FirTypeScope firTypeScope = (FirTypeScope) firScope;
            if (firTypeScope != null) {
                firScope2 = firTypeScope;
            }
            arrayList.add(firScope2);
        }
        if (!z) {
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = getSuperTypeScopes();
        }
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = getDeclaredMemberScope().withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = getDeclaredMemberScope();
        }
        return new FirClassUseSiteMemberScope(firClass, newSession, arrayList, firContainingNamesAwareScopeWithReplacedSessionOrNull);
    }
}
