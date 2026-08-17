package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirScopeWithCallableCopyReturnTypeUpdater;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\t0\rH\u0016J(\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\t0\rH\u0016J*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0014H\u0016J*\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004J\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0017b\u0002\b\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirScopeWithCallableCopyReturnTypeUpdater;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingTypeScope;", "delegate", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "callableCopyTypeCalculator", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;)V", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "updateReturnType", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScopeWithCallableCopyReturnTypeUpdater extends FirDelegatingTypeScope {
    private final CallableCopyTypeCalculator callableCopyTypeCalculator;
    private final FirTypeScope delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirScopeWithCallableCopyReturnTypeUpdater(FirTypeScope firTypeScope, CallableCopyTypeCalculator callableCopyTypeCalculator) {
        super(firTypeScope);
        firTypeScope.getClass();
        callableCopyTypeCalculator.getClass();
        this.delegate = firTypeScope;
        this.callableCopyTypeCalculator = callableCopyTypeCalculator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction b(FirScopeWithCallableCopyReturnTypeUpdater firScopeWithCallableCopyReturnTypeUpdater, Function2 function2, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        firScopeWithCallableCopyReturnTypeUpdater.updateReturnType((FirCallableDeclaration) firPropertySymbol.getFir());
        return (ProcessorAction) function2.invoke(firPropertySymbol, firTypeScope);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(FirScopeWithCallableCopyReturnTypeUpdater firScopeWithCallableCopyReturnTypeUpdater, Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        firScopeWithCallableCopyReturnTypeUpdater.updateReturnType((FirCallableDeclaration) firVariableSymbol.getFir());
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction d(FirScopeWithCallableCopyReturnTypeUpdater firScopeWithCallableCopyReturnTypeUpdater, Function2 function2, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        firScopeWithCallableCopyReturnTypeUpdater.updateReturnType((FirCallableDeclaration) firNamedFunctionSymbol.getFir());
        return (ProcessorAction) function2.invoke(firNamedFunctionSymbol, firTypeScope);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit e(FirScopeWithCallableCopyReturnTypeUpdater firScopeWithCallableCopyReturnTypeUpdater, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        firScopeWithCallableCopyReturnTypeUpdater.updateReturnType((FirCallableDeclaration) firNamedFunctionSymbol.getFir());
        function1.invoke(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    private final void updateReturnType(FirCallableDeclaration declaration) {
        if (ClassMembersKt.getCanHaveDeferredReturnTypeCalculation(declaration)) {
            this.callableCopyTypeCalculator.mo617computeReturnType(declaration);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, final Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return this.delegate.processDirectOverriddenFunctionsWithBaseScope(functionSymbol, new Function2() { // from class: pc5
            public final Object invoke(Object obj, Object obj2) {
                return FirScopeWithCallableCopyReturnTypeUpdater.d(this.b, processor, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, final Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return this.delegate.processDirectOverriddenPropertiesWithBaseScope(propertySymbol, new Function2() { // from class: sc5
            public final Object invoke(Object obj, Object obj2) {
                return FirScopeWithCallableCopyReturnTypeUpdater.b(this.b, processor, (FirPropertySymbol) obj, (FirTypeScope) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.delegate.processFunctionsByName(name, new Function1() { // from class: rc5
            public final Object invoke(Object obj) {
                return FirScopeWithCallableCopyReturnTypeUpdater.e(this.b, processor, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.delegate.processPropertiesByName(name, new Function1() { // from class: qc5
            public final Object invoke(Object obj) {
                return FirScopeWithCallableCopyReturnTypeUpdater.c(this.b, processor, (FirVariableSymbol) obj);
            }
        });
    }

    public String toString() {
        return this.delegate.toString();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirScopeWithCallableCopyReturnTypeUpdater withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        if (this.delegate.withReplacedSessionOrNull(newSession, newScopeSession) != null) {
            return new FirScopeWithCallableCopyReturnTypeUpdater(this.delegate, this.callableCopyTypeCalculator);
        }
        return null;
    }
}
