package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirActualizingScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J.\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u001c\u0010\u0012\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00110\u0013H\u0016J\u001c\u0010\u0016\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00110\u0017H\u0016J$\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00110\u0017H\u0016J(\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\u0004\u0012\u00020\u00110\u0017H\u0016Jc\u0010\u001d\u001a\u00020\u0011\"\f\b\u0000\u0010\u001e*\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010\t\u001a\u00020\n2/\u0010 \u001a+\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u00110\u0017\u0012\u0004\u0012\u00020\u00110!¢\u0006\u0002\b\"2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u00020\u00110\u0017H\u0002J\"\u0010#\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u001f2\u0010\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0%H\u0002J\u0010\u0010&\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u001fH\u0002J+\u0010'\u001a\u00020\b\"\f\b\u0000\u0010\u001e*\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010(\u001a\u0002H\u001e2\u0006\u0010)\u001a\u0002H\u001eH\u0002¢\u0006\u0002\u0010*J\u001e\u0010+\u001a\u0004\u0018\u00010\u00002\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0017b\u0002\b/R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirActualizingScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "delegate", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/FirSession;)V", "mayContainName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processDeclaredConstructors", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processCallableSymbolsByName", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processingFactory", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "hasEquivalentNotActualFromLibrary", "notExpectSymbols", Argument.Delimiters.none, "isFromLibrary", "areEquivalent", "symbol", "s", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirActualizingScope extends FirScope {
    private final FirScope delegate;
    private final List<String> scopeOwnerLookupNames;
    private final FirSession session;

    public FirActualizingScope(FirScope firScope, FirSession firSession) {
        firScope.getClass();
        firSession.getClass();
        this.delegate = firScope;
        this.session = firSession;
        if (firScope instanceof FirTypeScope) {
            w01.a("Failed requirement.");
            throw null;
        }
        this.scopeOwnerLookupNames = firScope.getScopeOwnerLookupNames();
    }

    private final <S extends FirCallableSymbol<?>> boolean areEquivalent(S symbol, S s) {
        return ConeEquivalentCallConflictResolver.INSTANCE.areEquivalentTopLevelCallables((FirCallableDeclaration) symbol.getFir(), (FirCallableDeclaration) s.getFir(), this.session, null);
    }

    public static Unit b(Set set, Set set2, Set set3, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firCallableSymbol.getRawStatus().isActual()) {
            FirBasedSymbol<?> singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull(firCallableSymbol);
            if (singleMatchedExpectForActualOrNull != null) {
                TypeIntrinsics.asMutableCollection(set).remove(singleMatchedExpectForActualOrNull);
                set2.add(singleMatchedExpectForActualOrNull);
            }
        } else if (firCallableSymbol.getRawStatus().isExpect() && set2.contains(firCallableSymbol)) {
            return Unit.INSTANCE;
        }
        if (!firCallableSymbol.getRawStatus().isExpect()) {
            set = set3;
        }
        set.add(firCallableSymbol);
        return Unit.INSTANCE;
    }

    public static boolean c(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static boolean d(FirActualizingScope firActualizingScope, Set set, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        return firActualizingScope.isFromLibrary(firCallableSymbol) && firActualizingScope.hasEquivalentNotActualFromLibrary(firCallableSymbol, set);
    }

    private final boolean hasEquivalentNotActualFromLibrary(FirCallableSymbol<?> firCallableSymbol, Set<? extends FirCallableSymbol<?>> set) {
        Set<? extends FirCallableSymbol<?>> set2 = set;
        if ((set2 instanceof Collection) && set2.isEmpty()) {
            return false;
        }
        for (FirCallableSymbol<?> firCallableSymbol2 : set2) {
            if (isFromLibrary(firCallableSymbol2) && areEquivalent(firCallableSymbol, firCallableSymbol2)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isFromLibrary(FirCallableSymbol<?> firCallableSymbol) {
        return firCallableSymbol.getModuleData().getSession().getKind() == FirSession.Kind.Library;
    }

    private final <S extends FirCallableSymbol<?>> void processCallableSymbolsByName(Name name, Function3<? super FirScope, ? super Name, ? super Function1<? super S, Unit>, Unit> processingFactory, Function1<? super S, Unit> processor) {
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        final LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        final LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        processingFactory.invoke(this.delegate, name, new Function1() { // from class: by4
            public final Object invoke(Object obj) {
                return FirActualizingScope.b(linkedHashSet, linkedHashSet3, linkedHashSet2, (FirCallableSymbol) obj);
            }
        });
        final Function1 function1 = new Function1() { // from class: cy4
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirActualizingScope.d(this.b, linkedHashSet2, (FirCallableSymbol) obj));
            }
        };
        linkedHashSet.removeIf(new Predicate() { // from class: dy4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return FirActualizingScope.c(function1, obj);
            }
        });
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
        Iterator it2 = linkedHashSet2.iterator();
        while (it2.hasNext()) {
            processor.invoke(it2.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.scopeOwnerLookupNames;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public boolean mayContainName(Name name) {
        name.getClass();
        return this.delegate.mayContainName(name);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.delegate.processClassifiersByNameWithSubstitution(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.delegate.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        processCallableSymbolsByName(name, AnonymousClass1.INSTANCE, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        processCallableSymbolsByName(name, C00611.INSTANCE, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirActualizingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirScope firScopeWithReplacedSessionOrNull = this.delegate.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firScopeWithReplacedSessionOrNull != null) {
            return new FirActualizingScope(firScopeWithReplacedSessionOrNull, newSession);
        }
        return null;
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirActualizingScope$processFunctionsByName$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<FirScope, Name, Function1<? super FirNamedFunctionSymbol, ? extends Unit>, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, FirScope.class, "processFunctionsByName", "processFunctionsByName(Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        public final void invoke(FirScope firScope, Name name, Function1<? super FirNamedFunctionSymbol, Unit> function1) {
            firScope.getClass();
            name.getClass();
            function1.getClass();
            firScope.processFunctionsByName(name, function1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirScope) obj, (Name) obj2, (Function1<? super FirNamedFunctionSymbol, Unit>) obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirActualizingScope$processPropertiesByName$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00611 extends FunctionReferenceImpl implements Function3<FirScope, Name, Function1<? super FirVariableSymbol<?>, ? extends Unit>, Unit> {
        public static final C00611 INSTANCE = new C00611();

        public C00611() {
            super(3, FirScope.class, "processPropertiesByName", "processPropertiesByName(Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        public final void invoke(FirScope firScope, Name name, Function1<? super FirVariableSymbol<?>, Unit> function1) {
            firScope.getClass();
            name.getClass();
            function1.getClass();
            firScope.processPropertiesByName(name, function1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirScope) obj, (Name) obj2, (Function1<? super FirVariableSymbol<?>, Unit>) obj3);
            return Unit.INSTANCE;
        }
    }
}
