package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirScriptDeclarationsScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J(\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u0016\u0010\u001c\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030 \u0012\u0004\u0012\u00020\u001a0\u001dH\u0016J5\u0010!\u001a\u00020\u001a\"\u000e\b\u0000\u0010\"\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u001b\u001a\u00020\u000e2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u00020\u001a0\u001dH\u0082\bJ\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0$H\u0016J.\u0010%\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u001c\u0010\u001c\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001a0&H\u0016J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0$H\u0016J\u001c\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020-H\u0017b\u0002\b.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR1\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f0\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R'\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00160\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0017\u0010\u0012¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirScriptDeclarationsScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirScript;)V", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScript", "()Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "callablesIndex", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getCallablesIndex", "()Ljava/util/Map;", "callablesIndex$delegate", "Lkotlin/Lazy;", "classIndex", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getClassIndex", "classIndex$delegate", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processCallables", "D", "getCallableNames", Argument.Delimiters.none, "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getClassifierNames", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScriptDeclarationsScope extends FirContainingNamesAwareScope {

    /* JADX INFO: renamed from: callablesIndex$delegate, reason: from kotlin metadata */
    private final Lazy callablesIndex;

    /* JADX INFO: renamed from: classIndex$delegate, reason: from kotlin metadata */
    private final Lazy classIndex;
    private final FirScript script;
    private final FirSession useSiteSession;

    public FirScriptDeclarationsScope(FirSession firSession, FirScript firScript) {
        firSession.getClass();
        firScript.getClass();
        this.useSiteSession = firSession;
        this.script = firScript;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.callablesIndex = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: tc5
            public final Object invoke() {
                return FirScriptDeclarationsScope.b(this.b);
            }
        });
        this.classIndex = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: uc5
            public final Object invoke() {
                return FirScriptDeclarationsScope.c(this.b);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0045  */
    public static Map b(FirScriptDeclarationsScope firScriptDeclarationsScope) {
        Name name;
        Object arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclaration firDeclaration : firScriptDeclarationsScope.script.getDeclarations()) {
            if (firDeclaration instanceof FirCallableDeclaration) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
                if (firCallableDeclaration instanceof FirVariable) {
                    if (!(firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                        name = ((FirVariable) firDeclaration).getName();
                        arrayList = linkedHashMap.get(name);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            linkedHashMap.put(name, arrayList);
                        }
                        ((Collection) arrayList).add(firCallableDeclaration.getSymbol());
                    }
                } else if (firCallableDeclaration instanceof FirNamedFunction) {
                    name = ((FirNamedFunction) firDeclaration).getName();
                    arrayList = linkedHashMap.get(name);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(name, arrayList);
                    }
                    ((Collection) arrayList).add(firCallableDeclaration.getSymbol());
                }
            }
        }
        return linkedHashMap;
    }

    public static Map c(FirScriptDeclarationsScope firScriptDeclarationsScope) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirDeclaration firDeclaration : firScriptDeclarationsScope.script.getDeclarations()) {
            if (firDeclaration instanceof FirRegularClass) {
                FirRegularClass firRegularClass = (FirRegularClass) firDeclaration;
                linkedHashMap.put(firRegularClass.getName(), firRegularClass.getSymbol());
            }
        }
        return linkedHashMap;
    }

    private final Map<Name, List<FirCallableSymbol<?>>> getCallablesIndex() {
        return (Map) this.callablesIndex.getValue();
    }

    private final Map<Name, FirRegularClassSymbol> getClassIndex() {
        return (Map) this.classIndex.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getCallablesIndex().keySet();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return getClassIndex().keySet();
    }

    public final FirScript getScript() {
        return this.script;
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        FirRegularClassSymbol firRegularClassSymbol = getClassIndex().get(name);
        if (firRegularClassSymbol == null) {
            return;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = firRegularClassSymbol.getTypeParameterSymbols();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10)), 16));
        for (Object obj : typeParameterSymbols) {
            linkedHashMap.put(obj, FirNestedClassifierScopeKt.toConeType((FirTypeParameterSymbol) obj));
        }
        processor.invoke(firRegularClassSymbol, ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, this.useSiteSession, false, 4, null));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (Intrinsics.areEqual(name, SpecialNames.INIT)) {
            return;
        }
        List<FirCallableSymbol<?>> listEmptyList = getCallablesIndex().get(name);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (FirCallableSymbol<?> firCallableSymbol : listEmptyList) {
            if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
                processor.invoke(firCallableSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        List<FirCallableSymbol<?>> listEmptyList = getCallablesIndex().get(name);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (FirCallableSymbol<?> firCallableSymbol : listEmptyList) {
            if (firCallableSymbol instanceof FirVariableSymbol) {
                processor.invoke(firCallableSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirScriptDeclarationsScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirScriptDeclarationsScope(newSession, this.script);
    }
}
