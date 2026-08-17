package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a0\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\"\u0010\u000f\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t¨\u0006\u0012"}, d2 = {"createErrorReferenceWithErrorCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirErrorReferenceWithCandidate;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "createErrorReferenceWithExistingCandidate", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "createErrorCandidate", "fullyProcessCandidate", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ErrorCandidateUtilsKt {
    public static final Candidate createErrorCandidate(ResolutionStageRunner resolutionStageRunner, CallInfo callInfo, ResolutionContext resolutionContext, ConeDiagnostic coneDiagnostic) {
        resolutionStageRunner.getClass();
        callInfo.getClass();
        resolutionContext.getClass();
        coneDiagnostic.getClass();
        Candidate candidateCreateErrorCandidate = new CandidateFactory(resolutionContext, callInfo).createErrorCandidate(callInfo, coneDiagnostic);
        ResolutionStageRunner.processCandidate$default(resolutionStageRunner, candidateCreateErrorCandidate, resolutionContext, false, false, 8, null);
        return candidateCreateErrorCandidate;
    }

    public static final FirErrorReferenceWithCandidate createErrorReferenceWithErrorCandidate(CallInfo callInfo, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, ResolutionContext resolutionContext, ResolutionStageRunner resolutionStageRunner) {
        callInfo.getClass();
        coneDiagnostic.getClass();
        resolutionContext.getClass();
        resolutionStageRunner.getClass();
        return new FirErrorReferenceWithCandidate(ktSourceElement, callInfo.getName(), createErrorCandidate(resolutionStageRunner, callInfo, resolutionContext, coneDiagnostic), coneDiagnostic);
    }

    public static final FirErrorReferenceWithCandidate createErrorReferenceWithExistingCandidate(Candidate candidate, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, ResolutionContext resolutionContext, ResolutionStageRunner resolutionStageRunner) {
        candidate.getClass();
        coneDiagnostic.getClass();
        resolutionContext.getClass();
        resolutionStageRunner.getClass();
        fullyProcessCandidate(resolutionStageRunner, candidate, resolutionContext);
        return new FirErrorReferenceWithCandidate(ktSourceElement, candidate.getCallInfo().getName(), candidate, coneDiagnostic);
    }

    public static final void fullyProcessCandidate(ResolutionStageRunner resolutionStageRunner, Candidate candidate, ResolutionContext resolutionContext) {
        resolutionStageRunner.getClass();
        candidate.getClass();
        resolutionContext.getClass();
        if (candidate.getPassedStages() < candidate.getCallInfo().getCallKind().getResolutionSequence().length) {
            ResolutionStageRunner.processCandidate$default(resolutionStageRunner, candidate, resolutionContext, false, false, 8, null);
        }
    }
}
