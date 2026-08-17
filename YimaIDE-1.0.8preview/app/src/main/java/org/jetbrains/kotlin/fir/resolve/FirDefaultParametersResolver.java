package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.FirDefaultParametersResolver;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirActualizingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirDefaultParametersResolver;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "declaresDefaultValue", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "originScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "index", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultParametersResolver implements FirSessionComponent {
    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(int i, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.itOrExpectHasDefaultParameterValue((FirFunction) firNamedFunctionSymbol.getFir(), i)) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    public final boolean declaresDefaultValue(FirSession session, ScopeSession scopeSession, FirFunction function, FirScope originScope, final int index) {
        FirRegularClass containingClass;
        FirTypeScope firTypeScopeScopeForClass;
        session.getClass();
        scopeSession.getClass();
        function.getClass();
        if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.itOrExpectHasDefaultParameterValue(function, index)) {
            return true;
        }
        if (!(function instanceof FirNamedFunction)) {
            return false;
        }
        FirNamedFunctionSymbol symbol = ((FirNamedFunction) function).getSymbol();
        if (originScope instanceof FirTypeScope) {
            firTypeScopeScopeForClass = (FirTypeScope) originScope;
        } else {
            if ((!(originScope instanceof FirActualizingScope) && !(originScope instanceof FirAbstractImportingScope)) || (containingClass = ResolveUtilsKt.getContainingClass(function)) == null) {
                return false;
            }
            FirRegularClassSymbol symbol2 = containingClass.getSymbol();
            firTypeScopeScopeForClass = FirKotlinScopeProviderKt.scopeForClass(containingClass, ConeSubstitutor.Empty.INSTANCE, session, scopeSession, symbol2, symbol2.getLookupTag(), null);
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirTypeScopeKt.processOverriddenFunctions(firTypeScopeScopeForClass, symbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: g25
            public final Object invoke(Object obj) {
                return FirDefaultParametersResolver.a(index, booleanRef, (FirNamedFunctionSymbol) obj);
            }
        });
        return booleanRef.element;
    }
}
