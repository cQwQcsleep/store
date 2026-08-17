package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirUnstableSmartcastTypeScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\u0010H\u0016Jf\u0010\u0013\u001a\u00020\f\"\f\b\u0000\u0010\u0014*\u0006\u0012\u0002\b\u00030\n2/\u0010\u0015\u001a+\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\f0\u0017\u0012\u0004\u0012\u00020\f0\u0016¢\u0006\u0002\b\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0014\b\b\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\f0\u0017H\u0082\bJ$\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\f0\u0017H\u0016J(\u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\u0004\u0012\u00020\f0\u0017H\u0016Jw\u0010\u001d\u001a\u00020\u001e\"\f\b\u0000\u0010\u001f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010 \u001a\u0002H\u001f25\u0010!\u001a1\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u001f\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u0010\u0012\u0004\u0012\u00020\u001e0\u0016¢\u0006\u0002\b\u00182\u001a\b\b\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u0010H\u0082\b¢\u0006\u0002\u0010\"J\u0012\u0010#\u001a\u00020$2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030&J\u0012\u0010'\u001a\u00020\f2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\nJ*\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001a2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u0010H\u0016J*\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u0010H\u0016J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0.H\u0016J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0.H\u0016J\u001e\u00106\u001a\u0004\u0018\u00010\u00002\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0017b\u0002\b;R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b2\u00103¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirUnstableSmartcastTypeScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "smartcastScope", "originalScope", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "scopes", Argument.Delimiters.none, "symbolsFromUnstableSmartcast", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processComposite", "T", "process", "Lkotlin/Function3;", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDirectOverriddenWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "S", "originalSymbol", "processDirectOverriddenSymbolsWithBaseScope", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "isSymbolFromUnstableSmartcast", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "markSymbolFromUnstableSmartcast", "processDirectOverriddenFunctionsWithBaseScope", "functionSymbol", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "scopeOwnerLookupNames", Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "scopeOwnerLookupNames$delegate", "Lkotlin/Lazy;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnstableSmartcastTypeScope extends FirTypeScope {
    private final FirTypeScope originalScope;

    /* JADX INFO: renamed from: scopeOwnerLookupNames$delegate, reason: from kotlin metadata */
    private final Lazy scopeOwnerLookupNames;
    private final List<FirTypeScope> scopes;
    private final FirTypeScope smartcastScope;
    private final Set<FirCallableSymbol<?>> symbolsFromUnstableSmartcast;

    public FirUnstableSmartcastTypeScope(FirTypeScope firTypeScope, FirTypeScope firTypeScope2) {
        firTypeScope.getClass();
        firTypeScope2.getClass();
        this.smartcastScope = firTypeScope;
        this.originalScope = firTypeScope2;
        this.scopes = CollectionsKt.listOf(new FirTypeScope[]{firTypeScope2, firTypeScope});
        this.symbolsFromUnstableSmartcast = new LinkedHashSet();
        this.scopeOwnerLookupNames = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: hg5
            public final Object invoke() {
                return FirUnstableSmartcastTypeScope.b(this.b);
            }
        });
    }

    public static List b(FirUnstableSmartcastTypeScope firUnstableSmartcastTypeScope) {
        List<FirTypeScope> list = firUnstableSmartcastTypeScope.scopes;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((FirTypeScope) it.next()).getScopeOwnerLookupNames());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        List<FirTypeScope> list = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((FirTypeScope) it.next()).getCallableNames());
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        List<FirTypeScope> list = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((FirTypeScope) it.next()).getClassifierNames());
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return (List) this.scopeOwnerLookupNames.getValue();
    }

    public final boolean isSymbolFromUnstableSmartcast(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        return CollectionsKt.contains(this.symbolsFromUnstableSmartcast, symbol);
    }

    public final void markSymbolFromUnstableSmartcast(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        this.symbolsFromUnstableSmartcast.add(symbol);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        Iterator<FirTypeScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processClassifiersByNameWithSubstitution(name, processor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ProcessorAction processorActionProcessDirectOverriddenFunctionsWithBaseScope = this.originalScope.processDirectOverriddenFunctionsWithBaseScope(functionSymbol, new FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$1(linkedHashSet, processor));
        ProcessorAction processorAction = ProcessorAction.STOP;
        return (processorActionProcessDirectOverriddenFunctionsWithBaseScope == processorAction || this.smartcastScope.processDirectOverriddenFunctionsWithBaseScope(functionSymbol, new FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$3(functionSymbol, linkedHashSet, this, processor)) == processorAction) ? processorAction : ProcessorAction.NEXT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ProcessorAction processorActionProcessDirectOverriddenPropertiesWithBaseScope = this.originalScope.processDirectOverriddenPropertiesWithBaseScope(propertySymbol, new FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$1(linkedHashSet, processor));
        ProcessorAction processorAction = ProcessorAction.STOP;
        return (processorActionProcessDirectOverriddenPropertiesWithBaseScope == processorAction || this.smartcastScope.processDirectOverriddenPropertiesWithBaseScope(propertySymbol, new FirUnstableSmartcastTypeScope$processDirectOverriddenWithBaseScope$3(propertySymbol, linkedHashSet, this, processor)) == processorAction) ? processorAction : ProcessorAction.NEXT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.originalScope.processFunctionsByName(name, new FirUnstableSmartcastTypeScope$processComposite$1(linkedHashSet, processor));
        this.smartcastScope.processFunctionsByName(name, new FirUnstableSmartcastTypeScope$processComposite$2(linkedHashSet, this, processor));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.originalScope.processPropertiesByName(name, new FirUnstableSmartcastTypeScope$processComposite$1(linkedHashSet, processor));
        this.smartcastScope.processPropertiesByName(name, new FirUnstableSmartcastTypeScope$processComposite$2(linkedHashSet, this, processor));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirUnstableSmartcastTypeScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirTypeScope firTypeScopeWithReplacedSessionOrNull = this.smartcastScope.withReplacedSessionOrNull(newSession, newScopeSession);
        FirTypeScope firTypeScopeWithReplacedSessionOrNull2 = this.originalScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firTypeScopeWithReplacedSessionOrNull == null && firTypeScopeWithReplacedSessionOrNull2 == null) {
            return null;
        }
        if (firTypeScopeWithReplacedSessionOrNull == null) {
            firTypeScopeWithReplacedSessionOrNull = this.smartcastScope;
        }
        if (firTypeScopeWithReplacedSessionOrNull2 == null) {
            firTypeScopeWithReplacedSessionOrNull2 = this.originalScope;
        }
        return new FirUnstableSmartcastTypeScope(firTypeScopeWithReplacedSessionOrNull, firTypeScopeWithReplacedSessionOrNull2);
    }
}
