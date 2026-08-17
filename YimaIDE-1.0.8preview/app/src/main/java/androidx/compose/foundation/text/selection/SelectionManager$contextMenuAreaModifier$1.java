package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$contextMenuAreaModifier$1", f = "SelectionManager.kt", i = {}, l = {187}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class SelectionManager$contextMenuAreaModifier$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ SelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionManager$contextMenuAreaModifier$1(SelectionManager selectionManager, Continuation<? super SelectionManager$contextMenuAreaModifier$1> continuation) {
        super(2, continuation);
        this.this$0 = selectionManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionManager$contextMenuAreaModifier$1 selectionManager$contextMenuAreaModifier$1 = new SelectionManager$contextMenuAreaModifier$1(this.this$0, continuation);
        selectionManager$contextMenuAreaModifier$1.J$0 = ((Offset) obj).unbox-impl();
        return selectionManager$contextMenuAreaModifier$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1788invoke3MmeM6k(((Offset) obj).unbox-impl(), (Continuation) obj2);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m1788invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return create(Offset.box-impl(j), continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j = this.J$0;
            Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = this.this$0.getContextTextAndSelection$foundation();
            if (contextTextAndSelection$foundation != null) {
                SelectionManager selectionManager = this.this$0;
                AnnotatedString annotatedString = (AnnotatedString) contextTextAndSelection$foundation.component1();
                long j2 = ((TextRange) contextTextAndSelection$foundation.component2()).unbox-impl();
                PlatformSelectionBehaviors platformSelectionBehaviors$foundation = selectionManager.getPlatformSelectionBehaviors();
                if (platformSelectionBehaviors$foundation != null) {
                    Offset offset = Offset.box-impl(j);
                    this.label = 1;
                    if (platformSelectionBehaviors$foundation.mo1747onShowContextMenu_2OEclM(annotatedString, j2, offset, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
