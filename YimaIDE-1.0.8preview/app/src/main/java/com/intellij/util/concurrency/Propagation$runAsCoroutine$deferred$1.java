package com.intellij.util.concurrency;

import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Ref;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
public final class Propagation$runAsCoroutine$deferred$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function0<T> $action;
    final /* synthetic */ Ref<ProcessCanceledException> $originalPCE;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Propagation$runAsCoroutine$deferred$1(Function0<? extends T> function0, Ref<ProcessCanceledException> ref, Continuation<? super Propagation$runAsCoroutine$deferred$1> continuation) {
        super(2, continuation);
        this.$action = function0;
        this.$originalPCE = ref;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Propagation$runAsCoroutine$deferred$1(this.$action, this.$originalPCE, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        try {
            return this.$action.invoke();
        } catch (ProcessCanceledException e) {
            this.$originalPCE.set(e);
            CancellationException cancellationException = new CancellationException("Masking ProcessCanceledException: " + e.getMessage());
            cancellationException.initCause(e);
            throw cancellationException;
        }
    }
}
