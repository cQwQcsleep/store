package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirDynamicScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0002\b\t¢\u0006\u0004\b\u0007\u0010\bJ*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u0013H\u0016J*\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u0013H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00192\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020!0#H\u0016J(\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00192\u0016\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030%\u0012\u0004\u0012\u00020!0#H\u0016J\u001c\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0006H\u0017b\u0002\b)R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u001b\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDynamicScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDynamicScopeConstructor;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processor", "Lkotlin/Function2;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getCallableNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getClassifierNames", "anyTypeScope", "getAnyTypeScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "anyTypeScope$delegate", "Lkotlin/Lazy;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lkotlin/Function1;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDynamicScope extends FirTypeScope implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: anyTypeScope$delegate, reason: from kotlin metadata */
    private final Lazy anyTypeScope;
    private final ScopeSession scopeSession;
    private final FirSession session;

    @FirDynamicScopeConstructor
    public FirDynamicScope(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.anyTypeScope = LazyKt.lazy(new Function0() { // from class: w45
            public final Object invoke() {
                return FirDynamicScope.d(this.b);
            }
        });
    }

    public static Unit b(Ref.BooleanRef booleanRef, Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        booleanRef.element = true;
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    public static Unit c(Ref.BooleanRef booleanRef, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        booleanRef.element = true;
        function1.invoke(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    public static FirTypeScope d(FirDynamicScope firDynamicScope) {
        return ScopeUtilsKt.scope(firDynamicScope, firDynamicScope.getSession().getBuiltinTypes().getAnyType().getConeType(), CallableCopyTypeCalculator.DoNothing.INSTANCE, (FirResolvePhase) null);
    }

    private final FirTypeScope getAnyTypeScope() {
        return (FirTypeScope) this.anyTypeScope.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return ProcessorAction.NEXT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return ProcessorAction.NEXT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirTypeScope anyTypeScope = getAnyTypeScope();
        if (anyTypeScope != null) {
            anyTypeScope.processFunctionsByName(name, new Function1() { // from class: x45
                public final Object invoke(Object obj) {
                    return FirDynamicScope.c(booleanRef, processor, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        if (booleanRef.element) {
            return;
        }
        processor.invoke(((FirNamedFunction) FirDynamicScopeKt.getDynamicMembersStorage(getSession()).getFunctionsCacheByName().getValue(name, null)).getSymbol());
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirTypeScope anyTypeScope = getAnyTypeScope();
        if (anyTypeScope != null) {
            anyTypeScope.processPropertiesByName(name, new Function1() { // from class: y45
                public final Object invoke(Object obj) {
                    return FirDynamicScope.b(booleanRef, processor, (FirVariableSymbol) obj);
                }
            });
        }
        if (booleanRef.element) {
            return;
        }
        processor.invoke(((FirProperty) FirDynamicScopeKt.getDynamicMembersStorage(getSession()).getPropertiesCacheByName().getValue(name, null)).getSymbol());
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirDynamicScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirDynamicScope(newSession, newScopeSession);
    }
}
