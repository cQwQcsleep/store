package androidx.compose.foundation.text.selection;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/selection/DownResolution;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1", f = "SelectionGestures.kt", i = {0}, l = {195}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull"}, s = {"L$0"}, v = 1)
public final class SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super DownResolution>, Object> {
    final /* synthetic */ Ref.LongRef $overSlop;
    final /* synthetic */ long $pointerId;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(long j, Ref.LongRef longRef, Continuation<? super SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1> continuation) {
        super(2, continuation);
        this.$pointerId = j;
        this.$overSlop = longRef;
    }

    public static Unit b(Ref.LongRef longRef, PointerInputChange pointerInputChange, Offset offset) {
        pointerInputChange.consume();
        longRef.element = offset.unbox-impl();
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(this.$pointerId, this.$overSlop, continuation);
        selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1.L$0 = obj;
        return selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1;
    }

    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super DownResolution> continuation) {
        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        AwaitPointerEventScope awaitPointerEventScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
            long j = this.$pointerId;
            final Ref.LongRef longRef = this.$overSlop;
            Function2 function2 = new Function2() { // from class: androidx.compose.foundation.text.selection.b
                public final Object invoke(Object obj2, Object obj3) {
                    return SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1.b(longRef, (PointerInputChange) obj2, (Offset) obj3);
                }
            };
            this.L$0 = awaitPointerEventScope2;
            this.label = 1;
            Object objM565awaitTouchSlopOrCancellationjO51t88 = DragGestureDetectorKt.m565awaitTouchSlopOrCancellationjO51t88(awaitPointerEventScope2, j, function2, this);
            if (objM565awaitTouchSlopOrCancellationjO51t88 == coroutine_suspended) {
                return coroutine_suspended;
            }
            awaitPointerEventScope = awaitPointerEventScope2;
            obj = objM565awaitTouchSlopOrCancellationjO51t88;
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((PointerInputChange) obj) != null && (this.$overSlop.element & SieveCacheKt.InvalidMapping) != 9205357640488583168L) {
            return DownResolution.Drag;
        }
        PointerInputChange pointerInputChange = (PointerInputChange) CollectionsKt.first(awaitPointerEventScope.getCurrentEvent().getChanges());
        if (!PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            return DownResolution.Cancel;
        }
        pointerInputChange.consume();
        return DownResolution.Up;
    }
}
