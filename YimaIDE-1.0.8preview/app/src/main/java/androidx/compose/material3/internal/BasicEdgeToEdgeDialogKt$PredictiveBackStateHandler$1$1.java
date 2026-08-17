package androidx.compose.material3.internal;

import androidx.activity.BackEventCompat;
import androidx.compose.runtime.State;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003H\n"}, d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Landroidx/compose/material3/internal/BackEventCompat;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.internal.BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1", f = "BasicEdgeToEdgeDialog.kt", i = {}, l = {100}, m = "invokeSuspend", n = {}, s = {})
public final class BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1 extends SuspendLambda implements Function2<Flow<? extends BackEventCompat>, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Function0<Unit>> $currentOnBack$delegate;
    final /* synthetic */ PredictiveBackState $state;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1(PredictiveBackState predictiveBackState, State<? extends Function0<Unit>> state, Continuation<? super BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1> continuation) {
        super(2, continuation);
        this.$state = predictiveBackState;
        this.$currentOnBack$delegate = state;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1 basicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1 = new BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1(this.$state, this.$currentOnBack$delegate, continuation);
        basicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1.L$0 = obj;
        return basicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1;
    }

    public final Object invoke(Flow<BackEventCompat> flow, Continuation<? super Unit> continuation) {
        return create(flow, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = (Flow) this.L$0;
                final PredictiveBackState predictiveBackState = this.$state;
                FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialogKt$PredictiveBackStateHandler$1$1.1
                    public final Object emit(BackEventCompat backEventCompat, Continuation<? super Unit> continuation) {
                        SwipeEdge swipeEdge;
                        PredictiveBackStateImpl predictiveBackStateImpl = (PredictiveBackStateImpl) predictiveBackState;
                        float touchX = backEventCompat.getTouchX();
                        float touchY = backEventCompat.getTouchY();
                        float progress = backEventCompat.getProgress();
                        int swipeEdge2 = backEventCompat.getSwipeEdge();
                        if (swipeEdge2 != 0) {
                            swipeEdge = swipeEdge2 != 1 ? SwipeEdge.None : SwipeEdge.Right;
                        } else {
                            swipeEdge = SwipeEdge.Left;
                        }
                        predictiveBackStateImpl.setValue(new BackEventProgress.InProgress(touchX, touchY, progress, swipeEdge));
                        return Unit.INSTANCE;
                    }

                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((BackEventCompat) obj2, (Continuation<? super Unit>) continuation);
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            ((PredictiveBackStateImpl) this.$state).setValue(BackEventProgress.Completed.INSTANCE);
            BasicEdgeToEdgeDialogKt.PredictiveBackStateHandler$lambda$1(this.$currentOnBack$delegate).invoke();
            return Unit.INSTANCE;
        } catch (CancellationException e) {
            ((PredictiveBackStateImpl) this.$state).setValue(BackEventProgress.NotRunning.INSTANCE);
            throw e;
        }
    }
}
