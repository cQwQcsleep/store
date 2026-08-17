package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u0006H\u0097@b\u0002\b\u0016¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSinkImpl;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "continuation", "Lkotlin/coroutines/Continuation;", Argument.Delimiters.none, "stopOnFirstError", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;Z)V", "getContinuation", "()Lkotlin/coroutines/Continuation;", "setContinuation", "(Lkotlin/coroutines/Continuation;)V", "getStopOnFirstError", "()Z", "reportDiagnostic", "diagnostic", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "yield", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "needYielding", "getNeedYielding", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckerSinkImpl extends CheckerSink {
    private final Candidate candidate;
    private Continuation<? super Unit> continuation;
    private final boolean stopOnFirstError;

    public /* synthetic */ CheckerSinkImpl(Candidate candidate, Continuation continuation, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(candidate, (i & 2) != 0 ? null : continuation, (i & 4) != 0 ? true : z);
    }

    public final Continuation<Unit> getContinuation() {
        return this.continuation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink
    public boolean getNeedYielding() {
        return this.stopOnFirstError && !this.candidate.isSuccessful();
    }

    public final boolean getStopOnFirstError() {
        return this.stopOnFirstError;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink
    public void reportDiagnostic(ResolutionDiagnostic diagnostic) {
        diagnostic.getClass();
        this.candidate.addDiagnostic(diagnostic);
    }

    public final void setContinuation(Continuation<? super Unit> continuation) {
        this.continuation = continuation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink
    @PrivateForInline
    public Object yield(Continuation<? super Unit> continuation) {
        this.continuation = continuation;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }

    public CheckerSinkImpl(Candidate candidate, Continuation<? super Unit> continuation, boolean z) {
        candidate.getClass();
        this.candidate = candidate;
        this.continuation = continuation;
        this.stopOnFirstError = z;
    }
}
