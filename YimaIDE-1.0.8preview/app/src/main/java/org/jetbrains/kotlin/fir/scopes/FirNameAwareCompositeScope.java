package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u000fH\u0016J$\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J(\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J\u001c\u0010\u0017\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000b0\u0013H\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u001fH\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u001fH\u0016J\u001e\u0010%\u001a\u0004\u0018\u00010\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0017b\u0002\b*R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010!\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirNameAwareCompositeScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "scopes", Argument.Delimiters.none, "<init>", "(Ljava/lang/Iterable;)V", "getScopes", "()Ljava/lang/Iterable;", "delegate", "Lorg/jetbrains/kotlin/fir/scopes/FirCompositeScope;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processFunctionsByName", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "hasDefinitelyNoStaticMembers", Argument.Delimiters.none, "getHasDefinitelyNoStaticMembers", "()Z", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNameAwareCompositeScope extends FirContainingNamesAwareScope {
    private final FirCompositeScope delegate;
    private final Iterable<FirContainingNamesAwareScope> scopes;

    /* JADX WARN: Multi-variable type inference failed */
    public FirNameAwareCompositeScope(Iterable<? extends FirContainingNamesAwareScope> iterable) {
        iterable.getClass();
        this.scopes = iterable;
        this.delegate = new FirCompositeScope(iterable);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        Iterable<FirContainingNamesAwareScope> iterable = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirContainingNamesAwareScope> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, it.next().getCallableNames());
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        Iterable<FirContainingNamesAwareScope> iterable = this.scopes;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<FirContainingNamesAwareScope> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, it.next().getClassifierNames());
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public boolean getHasDefinitelyNoStaticMembers() {
        Iterable<FirContainingNamesAwareScope> iterable = this.scopes;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator<FirContainingNamesAwareScope> it = iterable.iterator();
        while (it.hasNext()) {
            if (!it.next().getHasDefinitelyNoStaticMembers()) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.delegate.getScopeOwnerLookupNames();
    }

    public final Iterable<FirContainingNamesAwareScope> getScopes() {
        return this.scopes;
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
        this.delegate.processFunctionsByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.delegate.processPropertiesByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirNameAwareCompositeScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        Iterable<FirContainingNamesAwareScope> iterable = this.scopes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<FirContainingNamesAwareScope> it = iterable.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            FirContainingNamesAwareScope next = it.next();
            FirScope firScopeWithReplacedSessionOrNull = next.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                z = true;
                firScope = firScopeWithReplacedSessionOrNull;
            }
            FirContainingNamesAwareScope firContainingNamesAwareScope = (FirContainingNamesAwareScope) firScope;
            if (firContainingNamesAwareScope != null) {
                next = firContainingNamesAwareScope;
            }
            arrayList.add(next);
        }
        if (!z) {
            arrayList = null;
        }
        if (arrayList == null) {
            return null;
        }
        return new FirNameAwareCompositeScope(arrayList);
    }
}
