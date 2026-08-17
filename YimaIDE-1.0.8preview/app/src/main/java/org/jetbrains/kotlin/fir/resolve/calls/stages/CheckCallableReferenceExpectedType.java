package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.calls.AdaptedCallableReferenceIsUsedWithReflection;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceAdaptation;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.LowerPriorityToPreserveCompatibilityDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckCallableReferenceExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckCallableReferenceExpectedType extends ResolutionStage {
    public static final CheckCallableReferenceExpectedType INSTANCE = new CheckCallableReferenceExpectedType();

    private CheckCallableReferenceExpectedType() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        CallInfo callInfo = candidate.getCallInfo();
        callInfo.getClass();
        ConeKotlinType expectedType = ((CallableReferenceInfo) candidate.getCallInfo()).getExpectedType();
        if (!(candidate.getSymbol() instanceof FirCallableSymbol)) {
            return Unit.INSTANCE;
        }
        ConeKotlinType coneKotlinType = null;
        if (((CallableReferenceInfo) candidate.getCallInfo()).getLhs() instanceof DoubleColonLHS.Type) {
            ConeKotlinType type = ((DoubleColonLHS.Type) ((CallableReferenceInfo) candidate.getCallInfo()).getLhs()).getType();
            FirExpression explicitReceiver = candidate.getCallInfo().getExplicitReceiver();
            if (!((explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null) instanceof FirResolvedQualifier)) {
                coneKotlinType = type;
            }
        }
        FirDeclaration fir = candidate.getSymbol().getFir();
        fir.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
        ConeKotlinType expectedType2 = ((CallableReferenceInfo) candidate.getCallInfo()).getExpectedType();
        boolean z = false;
        if (expectedType2 != null && FunctionalTypeUtilsKt.isReflectFunctionType(expectedType2, candidate.getCallInfo().getSession())) {
            z = true;
        }
        Pair pairBuildResultingTypeAndAdaptation = CheckCallableReferenceExpectedTypeKt.buildResultingTypeAndAdaptation(resolutionContext, firCallableDeclaration, coneKotlinType, candidate, z);
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) pairBuildResultingTypeAndAdaptation.component1();
        CallableReferenceAdaptation callableReferenceAdaptation = (CallableReferenceAdaptation) pairBuildResultingTypeAndAdaptation.component2();
        if (callableReferenceAdaptation != null) {
            if (LanguageVersionUtilsKt.enableCompatibilityModeForNewInference(resolutionContext)) {
                checkerSink.reportDiagnostic(LowerPriorityToPreserveCompatibilityDiagnostic.INSTANCE);
            }
            if (z) {
                checkerSink.reportDiagnostic(AdaptedCallableReferenceIsUsedWithReflection.INSTANCE);
            }
        }
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = candidate.getSubstitutor().substituteOrSelf(coneKotlinType2);
        candidate.initializeCallableReferenceAdaptation$org_jetbrains_kotlin_resolve(callableReferenceAdaptation, coneKotlinTypeSubstituteOrSelf);
        if (expectedType != null && !(candidate.getSymbol() instanceof FirErrorCallableSymbol)) {
            candidate.getSystem().addSubtypeConstraint(coneKotlinTypeSubstituteOrSelf, expectedType, new ConeArgumentConstraintPosition(candidate.getCallInfo().getCallSite()));
        }
        if (!candidate.getSystem().getHasContradiction()) {
            return Unit.INSTANCE;
        }
        checkerSink.reportDiagnostic(InapplicableCandidate.INSTANCE);
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
