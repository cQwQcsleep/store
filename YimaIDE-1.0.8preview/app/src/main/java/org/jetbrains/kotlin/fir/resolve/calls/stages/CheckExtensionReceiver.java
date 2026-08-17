package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.NoReceiverAllowed;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverIsNotAClass;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ5\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010J>\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0082@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u0004\u0018\u00010\u000f*\u00020\u000bH\u0002¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckExtensionReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkCompanionExtensionReceiver", "expectedReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "resolveExtensionReceiver", "receiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitArgumentDescription;", "expectedType", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitArgumentDescription;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpectedReceiverType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckExtensionReceiver extends ResolutionStage {
    public static final CheckExtensionReceiver INSTANCE = new CheckExtensionReceiver();

    private CheckExtensionReceiver() {
    }

    private final void checkCompanionExtensionReceiver(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, ConeKotlinType coneKotlinType) {
        ConeResolutionAtom givenExtensionReceiver = candidate.getGivenExtensionReceiver();
        if (givenExtensionReceiver == null) {
            k2d.a("Candidate for companion extension without extension receiver.");
            return;
        }
        FirExpression expression = givenExtensionReceiver.getExpression();
        FirResolvedQualifier firResolvedQualifier = expression instanceof FirResolvedQualifier ? (FirResolvedQualifier) expression : null;
        if (firResolvedQualifier == null) {
            k2d.a("Candidate for companion extension has non-qualifier extension receiver");
            return;
        }
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = symbol != null ? DeclarationUtilsKt.fullyExpandedClass(resolutionContext, symbol) : null;
        if (firRegularClassSymbolFullyExpandedClass == null) {
            checkerSink.reportDiagnostic(ReceiverIsNotAClass.INSTANCE);
        } else {
            if (Intrinsics.areEqual(firRegularClassSymbolFullyExpandedClass, ToSymbolUtilsKt.toClassSymbol(resolutionContext, coneKotlinType))) {
                return;
            }
            checkerSink.reportDiagnostic(new InapplicableWrongReceiver(coneKotlinType, TypeConstructionUtilsKt.constructClassType$default(firRegularClassSymbolFullyExpandedClass.getLookupTag(), null, false, null, 7, null)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeKotlinType getExpectedReceiverType(Candidate candidate) {
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol == null || (receiverParameter = ((FirCallableDeclaration) firCallableSymbol.getFir()).getReceiverParameter()) == null || (typeRef = receiverParameter.getTypeRef()) == null) {
            return null;
        }
        return FirTypeUtilsKt.getConeType(typeRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveExtensionReceiver(CheckerSink checkerSink, ResolutionContext resolutionContext, ImplicitArgumentDescription implicitArgumentDescription, Candidate candidate, ConeKotlinType coneKotlinType, Continuation<? super Unit> continuation) {
        ConeResolutionAtom atom = implicitArgumentDescription.getAtom();
        ArgumentCheckingProcessor.INSTANCE.resolvePlainArgumentType(candidate, atom, implicitArgumentDescription.getType(), coneKotlinType, checkerSink, resolutionContext, true, false, candidate.getCallInfo().getCallSite().getSource());
        candidate.setChosenExtensionReceiver(atom);
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        FirElement callSite = candidate.getCallInfo().getCallSite();
        if (callSite instanceof FirImplicitInvokeCall) {
            boolean zIsInvokeFromExtensionFunctionType = ResolutionStagesKt.isInvokeFromExtensionFunctionType(candidate);
            boolean isCallWithExplicitReceiver = ((FirImplicitInvokeCall) callSite).getIsCallWithExplicitReceiver();
            if (!zIsInvokeFromExtensionFunctionType && isCallWithExplicitReceiver) {
                checkerSink.reportDiagnostic(NoReceiverAllowed.INSTANCE);
            }
        }
        ConeKotlinType expectedReceiverType = getExpectedReceiverType(candidate);
        if (expectedReceiverType == null) {
            return Unit.INSTANCE;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol == null || !FirSymbolStatusUtilsKt.isCompanionExtension(firCallableSymbol)) {
            ConeKotlinType coneKotlinTypeSubstituteOrSelf = candidate.getSubstitutor().substituteOrSelf(expectedReceiverType);
            return candidate.getGivenExtensionReceiver() == null ? Unit.INSTANCE : resolveExtensionReceiver(checkerSink, resolutionContext, ResolutionStagesKt.prepareImplicitArgument(candidate.getGivenExtensionReceiver(), coneKotlinTypeSubstituteOrSelf, resolutionContext.getSession()), candidate, coneKotlinTypeSubstituteOrSelf, continuation);
        }
        checkCompanionExtensionReceiver(checkerSink, resolutionContext, candidate, expectedReceiverType);
        return Unit.INSTANCE;
    }
}
