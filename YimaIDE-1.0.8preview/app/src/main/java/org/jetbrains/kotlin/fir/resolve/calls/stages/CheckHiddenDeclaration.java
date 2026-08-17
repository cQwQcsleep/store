package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.CallToPotentiallyHiddenSymbolResult;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.resolve.calls.CallToDeprecatedOverrideOfHidden;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.calls.HiddenCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.SyntheticsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckHiddenDeclaration;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00020\u000e*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J5\u0010\u0013\u001a\u00020\u000e2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckHiddenDeclaration;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isDeprecatedHidden", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;)Z", "isHiddenForThisCallSite", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckHiddenDeclaration extends ResolutionStage {
    public static final CheckHiddenDeclaration INSTANCE = new CheckHiddenDeclaration();

    private CheckHiddenDeclaration() {
    }

    public static ProcessorAction a(boolean z, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        CallToPotentiallyHiddenSymbolResult callToPotentiallyHiddenSymbolResultHiddenStatusOfCall = DeprecationUtilsKt.hiddenStatusOfCall(firNamedFunctionSymbol, z, true);
        if (callToPotentiallyHiddenSymbolResultHiddenStatusOfCall == CallToPotentiallyHiddenSymbolResult.Visible) {
            return ProcessorAction.NEXT;
        }
        if (callToPotentiallyHiddenSymbolResultHiddenStatusOfCall == CallToPotentiallyHiddenSymbolResult.Hidden) {
            booleanRef.element = true;
        } else if (callToPotentiallyHiddenSymbolResultHiddenStatusOfCall == CallToPotentiallyHiddenSymbolResult.VisibleWithDeprecation) {
            booleanRef2.element = true;
        }
        return ProcessorAction.STOP;
    }

    private final boolean isDeprecatedHidden(ResolutionContext resolutionContext, FirBasedSymbol<?> firBasedSymbol, CallInfo callInfo) {
        FirDeprecationInfo deprecation = DeprecationUtilsKt.getDeprecation(firBasedSymbol, resolutionContext.getSession(), callInfo.getCallSite());
        return (deprecation != null ? deprecation.getDeprecationLevel() : null) == DeprecationLevelValue.HIDDEN;
    }

    private final boolean isHiddenForThisCallSite(CheckerSink checkerSink, FirCallableSymbol<?> firCallableSymbol, CallInfo callInfo, Candidate candidate) {
        if ((firCallableSymbol instanceof FirSimpleSyntheticPropertySymbol) && SyntheticsKt.getDeprecatedOverrideOfHidden((FirSimpleSyntheticPropertySymbol) firCallableSymbol)) {
            checkerSink.reportDiagnostic(CallToDeprecatedOverrideOfHidden.INSTANCE);
        }
        if (((FirCallableDeclaration) firCallableSymbol.getFir()).getDispatchReceiverType() == null || !(firCallableSymbol instanceof FirNamedFunctionSymbol)) {
            return false;
        }
        final boolean zIsSuperCall = ResolutionStagesKt.isSuperCall(callInfo.getCallSite());
        if (DeprecationUtilsKt.hiddenStatusOfCall(firCallableSymbol, zIsSuperCall, false) == CallToPotentiallyHiddenSymbolResult.Hidden) {
            return true;
        }
        FirScope originScope = candidate.getOriginScope();
        FirTypeScope firTypeScope = originScope instanceof FirTypeScope ? (FirTypeScope) originScope : null;
        if (firTypeScope == null) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        FirTypeScopeKt.processOverriddenFunctions(firTypeScope, (FirNamedFunctionSymbol) firCallableSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: hj1
            public final Object invoke(Object obj) {
                return CheckHiddenDeclaration.a(zIsSuperCall, booleanRef, booleanRef2, (FirNamedFunctionSymbol) obj);
            }
        });
        if (booleanRef2.element) {
            checkerSink.reportDiagnostic(CallToDeprecatedOverrideOfHidden.INSTANCE);
        }
        return booleanRef.element;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo;
        FirTypeAliasSymbol typeAliasSymbol;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (!(symbol instanceof FirCallableSymbol)) {
            if (isDeprecatedHidden(resolutionContext, symbol, candidate.getCallInfo()) && LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.SkipHiddenObjectsInResolution)) {
                checkerSink.reportDiagnostic(HiddenCandidate.INSTANCE);
            }
            return Unit.INSTANCE;
        }
        if (!isDeprecatedHidden(resolutionContext, symbol, candidate.getCallInfo()) && ((!(symbol instanceof FirConstructorSymbol) || (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirConstructorSymbol) symbol)) == null || (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) == null || !isDeprecatedHidden(resolutionContext, typeAliasSymbol, candidate.getCallInfo())) && !isHiddenForThisCallSite(checkerSink, (FirCallableSymbol) symbol, candidate.getCallInfo(), candidate))) {
            return Unit.INSTANCE;
        }
        checkerSink.reportDiagnostic(HiddenCandidate.INSTANCE);
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
