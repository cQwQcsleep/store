package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 K2\u00020\u0001:\u0001KB/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ$\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\"0%H\u0016J(\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u0016\u0010$\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020\"0%H\u0016Jf\u0010)\u001a\u00020\"\"\f\b\u0000\u0010**\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010#\u001a\u00020\u00162\u0014\b\b\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u00020\"0%2/\u0010+\u001a+\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u00020\"0%\u0012\u0004\u0012\u00020\"0,¢\u0006\u0002\b.H\u0082\bJ.\u0010/\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u001c\u0010$\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u000301\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\"00H\u0016J-\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H40\u00130\u0012\"\f\b\u0000\u00104*\u0006\u0012\u0002\b\u00030\u00112\u0006\u00105\u001a\u0002H4¢\u0006\u0002\u00106J*\u00107\u001a\u0002082\u0006\u00109\u001a\u00020&2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020800H\u0016J*\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020<2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020800H\u0016Jt\u0010=\u001a\u000208\"\f\b\u0000\u0010**\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010>\u001a\u0002H*2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002080025\u0010?\u001a1\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H*\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020800\u0012\u0004\u0012\u0002080,¢\u0006\u0002\b.H\u0002¢\u0006\u0002\u0010@J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00160BH\u0016J\u000e\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00160BH\u0016J\n\u0010D\u001a\u00020EH\u0096\u0080\u0004J\u001c\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u00032\u0006\u0010H\u001a\u00020IH\u0017b\u0002\bJR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000f\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00130\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R+\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u001cj\b\u0012\u0004\u0012\u00020\u0016`\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001a\u001a\u0004\b\u001e\u0010\u001f¨\u0006L"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirOverrideScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "scopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", "intersectionContext", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", "overriddenSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "callableNamesCached", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getCallableNamesCached", "()Ljava/util/Set;", "callableNamesCached$delegate", "Lkotlin/Lazy;", "classifiersNamesCached", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getClassifiersNamesCached", "()Ljava/util/HashSet;", "classifiersNamesCached$delegate", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processCallablesByName", "D", "processCallables", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lkotlin/ExtensionFunctionType;", "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getDirectOverriddenSymbols", "S", "symbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/util/Collection;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processDirectOverriddenCallablesWithBaseScope", "callableSymbol", "processDirectOverriddenInBaseScope", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeIntersectionScope extends AbstractFirOverrideScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: callableNamesCached$delegate, reason: from kotlin metadata */
    private final Lazy callableNamesCached;

    /* JADX INFO: renamed from: classifiersNamesCached$delegate, reason: from kotlin metadata */
    private final Lazy classifiersNamesCached;
    private final ConeSimpleKotlinType dispatchReceiverType;
    private final FirTypeIntersectionScopeContext intersectionContext;
    private final Map<FirCallableSymbol<?>, Collection<MemberWithBaseScope<FirCallableSymbol<?>>>> overriddenSymbols;
    private final List<FirTypeScope> scopes;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScope$processDirectOverriddenFunctionsWithBaseScope$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, FirTypeScope.class, "processDirectOverriddenFunctionsWithBaseScope", "processDirectOverriddenFunctionsWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firNamedFunctionSymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScope$processDirectOverriddenPropertiesWithBaseScope$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00651 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00651 INSTANCE = new C00651();

        public C00651() {
            super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firPropertySymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FirTypeIntersectionScope(FirSession firSession, FirOverrideChecker firOverrideChecker, List<? extends FirTypeScope> list, ConeSimpleKotlinType coneSimpleKotlinType) {
        super(firSession, firOverrideChecker);
        this.scopes = list;
        this.dispatchReceiverType = coneSimpleKotlinType;
        this.intersectionContext = new FirTypeIntersectionScopeContext(firSession, firOverrideChecker, list, coneSimpleKotlinType, false);
        this.overriddenSymbols = new HashMap();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.callableNamesCached = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: kf5
            public final Object invoke() {
                return FirTypeIntersectionScope.b(this.b);
            }
        });
        this.classifiersNamesCached = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: lf5
            public final Object invoke() {
                return FirTypeIntersectionScope.c(this.b);
            }
        });
    }

    public static Set b(FirTypeIntersectionScope firTypeIntersectionScope) {
        List<FirTypeScope> list = firTypeIntersectionScope.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((FirTypeScope) it.next()).getCallableNames());
        }
        return linkedHashSet;
    }

    public static HashSet c(FirTypeIntersectionScope firTypeIntersectionScope) {
        List<FirTypeScope> list = firTypeIntersectionScope.scopes;
        HashSet hashSet = new HashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(hashSet, ((FirTypeScope) it.next()).getClassifierNames());
        }
        return hashSet;
    }

    private final Set<Name> getCallableNamesCached() {
        return (Set) this.callableNamesCached.getValue();
    }

    private final HashSet<Name> getClassifiersNamesCached() {
        return (HashSet) this.classifiersNamesCached.getValue();
    }

    private final <D extends FirCallableSymbol<?>> ProcessorAction processDirectOverriddenCallablesWithBaseScope(D callableSymbol, Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction> processor, Function3<? super FirTypeScope, ? super D, ? super Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> processDirectOverriddenInBaseScope) {
        for (MemberWithBaseScope memberWithBaseScope : getDirectOverriddenSymbols(callableSymbol)) {
            FirCallableSymbol firCallableSymbolComponent1 = memberWithBaseScope.component1();
            FirTypeScope baseScope = memberWithBaseScope.getBaseScope();
            if (firCallableSymbolComponent1 == callableSymbol) {
                if (((ProcessorAction) processDirectOverriddenInBaseScope.invoke(baseScope, callableSymbol, processor)).not()) {
                    return ProcessorAction.STOP;
                }
            } else if (((ProcessorAction) processor.invoke(firCallableSymbolComponent1, baseScope)).not()) {
                return ProcessorAction.STOP;
            }
        }
        return ProcessorAction.NEXT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getCallableNamesCached();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return getClassifiersNamesCached();
    }

    public final <S extends FirCallableSymbol<?>> Collection<MemberWithBaseScope<S>> getDirectOverriddenSymbols(S symbol) {
        symbol.getClass();
        MemberWithBaseScope<FirCallableSymbol<?>> valueIfComputed = this.intersectionContext.getIntersectionOverrides().getValueIfComputed(symbol);
        List listEmptyList = this.overriddenSymbols.get(symbol);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List listEmptyList2 = valueIfComputed != null ? this.overriddenSymbols.get(valueIfComputed.getMember()) : null;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        List listPlus = CollectionsKt.plus(listEmptyList, listEmptyList2);
        listPlus.getClass();
        return listPlus;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getClassifierNames().contains(name)) {
            this.intersectionContext.processClassifiersByNameWithSubstitution(name, processor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return processDirectOverriddenCallablesWithBaseScope(functionSymbol, processor, AnonymousClass1.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return processDirectOverriddenCallablesWithBaseScope(propertySymbol, processor, C00651.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getCallableNames().contains(name)) {
            FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.intersectionContext;
            List<FirTypeScope> scopes = firTypeIntersectionScopeContext.getScopes();
            ArrayList arrayList = new ArrayList();
            for (FirTypeScope firTypeScope : scopes) {
                ArrayList arrayList2 = new ArrayList();
                firTypeScope.processFunctionsByName(name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
                if (arrayList2.isEmpty()) {
                    arrayList2 = null;
                }
                Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            for (FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection : firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList)) {
                FirCallableSymbol<?> chosenSymbol = resultOfIntersection.getChosenSymbol();
                this.overriddenSymbols.put(chosenSymbol, resultOfIntersection.getOverriddenMembers());
                processor.invoke(chosenSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getCallableNames().contains(name)) {
            FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.intersectionContext;
            List<FirTypeScope> scopes = firTypeIntersectionScopeContext.getScopes();
            ArrayList arrayList = new ArrayList();
            for (FirTypeScope firTypeScope : scopes) {
                ArrayList arrayList2 = new ArrayList();
                firTypeScope.processPropertiesByName(name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
                if (arrayList2.isEmpty()) {
                    arrayList2 = null;
                }
                Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            for (FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection : firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList)) {
                FirCallableSymbol<?> chosenSymbol = resultOfIntersection.getChosenSymbol();
                this.overriddenSymbols.put(chosenSymbol, resultOfIntersection.getOverriddenMembers());
                processor.invoke(chosenSymbol);
            }
        }
    }

    public String toString() {
        return "Intersection of [" + CollectionsKt.joinToString$default(this.scopes, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + ']';
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirTypeIntersectionScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirOverrideChecker overrideChecker = getOverrideChecker();
        List<FirTypeScope> list = this.scopes;
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
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
            arrayList = this.scopes;
        }
        return new FirTypeIntersectionScope(newSession, overrideChecker, arrayList, this.dispatchReceiverType);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScope$Companion;", Argument.Delimiters.none, "<init>", "()V", "prepareIntersectionScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "scopes", Argument.Delimiters.none, "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirTypeScope prepareIntersectionScope(FirSession session, FirOverrideChecker overrideChecker, List<? extends FirTypeScope> scopes, ConeSimpleKotlinType dispatchReceiverType) {
            session.getClass();
            overrideChecker.getClass();
            scopes.getClass();
            dispatchReceiverType.getClass();
            FirTypeScope firTypeScope = (FirTypeScope) CollectionsKt.singleOrNull(scopes);
            if (firTypeScope != null) {
                return firTypeScope;
            }
            return scopes.isEmpty() ? FirTypeScope.Empty.INSTANCE : new FirTypeIntersectionScope(session, overrideChecker, scopes, dispatchReceiverType, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirTypeIntersectionScope(FirSession firSession, FirOverrideChecker firOverrideChecker, List list, ConeSimpleKotlinType coneSimpleKotlinType, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firOverrideChecker, list, coneSimpleKotlinType);
    }
}
