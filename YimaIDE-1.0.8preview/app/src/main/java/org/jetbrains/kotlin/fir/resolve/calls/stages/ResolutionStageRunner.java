package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSinkImpl;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLogger;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLoggerKt;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", Argument.Delimiters.none, "<init>", "()V", "processCandidate", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "stopOnFirstError", Argument.Delimiters.none, "runAdditionalStages", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolutionStageRunner {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner$processCandidate$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Candidate $candidate;
        final /* synthetic */ ResolutionContext $context;
        final /* synthetic */ FirInferenceLogger $inferenceLogger;
        final /* synthetic */ boolean $runAdditionalStages;
        final /* synthetic */ CheckerSinkImpl $sink;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Candidate candidate, ResolutionContext resolutionContext, CheckerSinkImpl checkerSinkImpl, boolean z, FirInferenceLogger firInferenceLogger, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$candidate = candidate;
            this.$context = resolutionContext;
            this.$sink = checkerSinkImpl;
            this.$runAdditionalStages = z;
            this.$inferenceLogger = firInferenceLogger;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$candidate, this.$context, this.$sink, this.$runAdditionalStages, this.$inferenceLogger, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            ResolutionStage[] resolutionSequenceWithAdditionalStages;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CallKind callKind = this.$candidate.getCallInfo().getCallKind();
                resolutionSequenceWithAdditionalStages = this.$runAdditionalStages ? callKind.getResolutionSequenceWithAdditionalStages() : callKind.getResolutionSequence();
            } else {
                if (i != 1) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                resolutionSequenceWithAdditionalStages = (ResolutionStage[]) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (this.$candidate.getPassedStages() < resolutionSequenceWithAdditionalStages.length) {
                ResolutionContext resolutionContext = this.$context;
                CheckerSinkImpl checkerSinkImpl = this.$sink;
                Candidate candidate = this.$candidate;
                FirInferenceLogger firInferenceLogger = this.$inferenceLogger;
                int passedStages = candidate.getPassedStages();
                candidate.setPassedStages(passedStages + 1);
                ResolutionStage resolutionStage = resolutionSequenceWithAdditionalStages[passedStages];
                if (firInferenceLogger != null) {
                    firInferenceLogger.logStage("Resolution Stages > " + Reflection.getOrCreateKotlinClass(resolutionStage.getClass()).getSimpleName(), candidate.getSystem());
                }
                this.L$0 = resolutionSequenceWithAdditionalStages;
                this.L$1 = SpillingKt.nullOutSpilledVariable(resolutionStage);
                this.label = 1;
                if (resolutionStage.check(checkerSinkImpl, resolutionContext, candidate, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ CandidateApplicability processCandidate$default(ResolutionStageRunner resolutionStageRunner, Candidate candidate, ResolutionContext resolutionContext, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return resolutionStageRunner.processCandidate(candidate, resolutionContext, z, z2);
    }

    public final CandidateApplicability processCandidate(Candidate candidate, ResolutionContext context, boolean stopOnFirstError, boolean runAdditionalStages) {
        candidate.getClass();
        context.getClass();
        CheckerSinkImpl checkerSinkImpl = new CheckerSinkImpl(candidate, null, stopOnFirstError, 2, null);
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(candidate.getCallInfo().getSession());
        if (inferenceLogger != null) {
            inferenceLogger.logCandidate(candidate);
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        checkerSinkImpl.setContinuation(IntrinsicsKt.createCoroutineUnintercepted(new AnonymousClass1(candidate, context, checkerSinkImpl, runAdditionalStages, inferenceLogger, null), new Continuation<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner.processCandidate.2
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            public void resumeWith(Object result) throws Throwable {
                Throwable th = Result.exceptionOrNull-impl(result);
                if (th != null) {
                    throw th;
                }
                booleanRef.element = true;
            }
        }));
        while (!booleanRef.element) {
            Continuation<Unit> continuation = checkerSinkImpl.getContinuation();
            continuation.getClass();
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
            if (!candidate.isSuccessful()) {
                break;
            }
        }
        return candidate.getLowestApplicability();
    }
}
