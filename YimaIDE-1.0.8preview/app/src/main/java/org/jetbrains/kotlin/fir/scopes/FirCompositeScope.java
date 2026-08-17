package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
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
import org.jetbrains.kotlin.fir.scopes.FirCompositeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\rH\u0016J^\u0010\u0010\u001a\u00020\t\"\u0004\b\u0000\u0010\u00112/\u0010\u0012\u001a+\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0014\b\b\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\t0\u0014H\u0082\bJP\u0010\u0010\u001a\u00020\t\"\u0004\b\u0000\u0010\u00112)\u0010\u0012\u001a%\u0012\u0004\u0012\u00020\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u00152\u0014\b\b\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\t0\u0014H\u0082\bJ$\u0010\u0016\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\t0\u0014H\u0016J(\u0010\u0018\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u0004\u0012\u00020\t0\u0014H\u0016J\u001c\u0010\u001a\u001a\u00020\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t0\u0014H\u0016J\u001e\u0010#\u001a\u0004\u0018\u00010\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0017b\u0002\b(R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 ¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirCompositeScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "scopes", Argument.Delimiters.none, "<init>", "(Ljava/lang/Iterable;)V", "getScopes", "()Ljava/lang/Iterable;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processComposite", "T", "process", "Lkotlin/Function3;", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "scopeOwnerLookupNames$delegate", "Lkotlin/Lazy;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompositeScope extends FirScope {

    /* JADX INFO: renamed from: scopeOwnerLookupNames$delegate, reason: from kotlin metadata */
    private final Lazy scopeOwnerLookupNames;
    private final Iterable<FirScope> scopes;

    /* JADX WARN: Multi-variable type inference failed */
    public FirCompositeScope(Iterable<? extends FirScope> iterable) {
        iterable.getClass();
        this.scopes = iterable;
        this.scopeOwnerLookupNames = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: sz4
            public final Object invoke() {
                return FirCompositeScope.b(this.b);
            }
        });
    }

    public static List b(FirCompositeScope firCompositeScope) {
        Iterable<FirScope> iterable = firCompositeScope.scopes;
        ArrayList arrayList = new ArrayList();
        Iterator<FirScope> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, it.next().getScopeOwnerLookupNames());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return (List) this.scopeOwnerLookupNames.getValue();
    }

    public final Iterable<FirScope> getScopes() {
        return this.scopes;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        Iterator<FirScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processClassifiersByNameWithSubstitution(name, processor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processDeclaredConstructors(new Function1() { // from class: org.jetbrains.kotlin.fir.scopes.FirCompositeScope$processComposite$2
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m619invoke(Object obj) {
                    if (linkedHashSet.add(obj)) {
                        processor.invoke(obj);
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m619invoke(obj);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processFunctionsByName(name, new FirCompositeScope$processComposite$1(linkedHashSet, processor));
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processPropertiesByName(name, new FirCompositeScope$processComposite$1(linkedHashSet, processor));
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirCompositeScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        Iterable<FirScope> iterable = this.scopes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<FirScope> it = iterable.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            FirScope next = it.next();
            FirScope firScopeWithReplacedSessionOrNull = next.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                z = true;
                firScope = firScopeWithReplacedSessionOrNull;
            }
            if (firScope != null) {
                next = firScope;
            }
            arrayList.add(next);
        }
        if (!z) {
            arrayList = null;
        }
        if (arrayList == null) {
            return null;
        }
        return new FirCompositeScope(arrayList);
    }
}
