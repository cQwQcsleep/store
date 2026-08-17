package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¨\u0006\u0003"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none, "org/jetbrains/kotlin/fir/resolve/calls/tower/FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$1", "org/jetbrains/kotlin/fir/resolve/calls/tower/FirInvokeResolveTowerExtension$enqueueResolveForExplicitReceiver$$inlined$enqueueInvokeReceiverTask$1"}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInvokeResolveTowerExtension$enqueueResolveTasksForQualifier$$inlined$enqueueResolveForExplicitReceiver$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ InvokeReceiverResolveTask $invokeReceiverProcessor;
    final /* synthetic */ CallInfo $invokeReceiverVariableInfo$inlined;
    final /* synthetic */ FirResolvedQualifier $receiver$inlined;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirInvokeResolveTowerExtension$enqueueResolveTasksForQualifier$$inlined$enqueueResolveForExplicitReceiver$1(InvokeReceiverResolveTask invokeReceiverResolveTask, Continuation continuation, CallInfo callInfo, FirResolvedQualifier firResolvedQualifier) {
        super(1, continuation);
        this.$invokeReceiverProcessor = invokeReceiverResolveTask;
        this.$invokeReceiverVariableInfo$inlined = callInfo;
        this.$receiver$inlined = firResolvedQualifier;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new FirInvokeResolveTowerExtension$enqueueResolveTasksForQualifier$$inlined$enqueueResolveForExplicitReceiver$1(this.$invokeReceiverProcessor, continuation, this.$invokeReceiverVariableInfo$inlined, this.$receiver$inlined);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return create(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            InvokeReceiverResolveTask invokeReceiverResolveTask = this.$invokeReceiverProcessor;
            CallInfo callInfo = this.$invokeReceiverVariableInfo$inlined;
            FirResolvedQualifier firResolvedQualifier = this.$receiver$inlined;
            this.L$0 = SpillingKt.nullOutSpilledVariable(this);
            this.L$1 = SpillingKt.nullOutSpilledVariable(invokeReceiverResolveTask);
            this.L$2 = SpillingKt.nullOutSpilledVariable(this);
            this.L$3 = SpillingKt.nullOutSpilledVariable(callInfo);
            this.L$4 = SpillingKt.nullOutSpilledVariable(invokeReceiverResolveTask);
            this.label = 1;
            if (invokeReceiverResolveTask.runResolverForQualifierReceiver(callInfo, firResolvedQualifier, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
