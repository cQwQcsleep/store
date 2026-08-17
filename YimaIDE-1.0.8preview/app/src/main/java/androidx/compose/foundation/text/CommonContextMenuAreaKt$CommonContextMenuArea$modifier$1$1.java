package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1", f = "CommonContextMenuArea.kt", i = {0}, l = {82, 83}, m = "invokeSuspend", n = {"clickLocation"}, s = {"J$0"}, v = 1)
public final class CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextFieldSelectionState $selectionState;
    /* synthetic */ long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(TextFieldSelectionState textFieldSelectionState, Continuation<? super CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1> continuation) {
        super(2, continuation);
        this.$selectionState = textFieldSelectionState;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 = new CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(this.$selectionState, continuation);
        commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1.J$0 = ((Offset) obj).unbox-impl();
        return commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1320invoke3MmeM6k(((Offset) obj).unbox-impl(), (Continuation) obj2);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m1320invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return create(Offset.box-impl(j), continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
    
        if (r5.mo1747onShowContextMenu_2OEclM(r6, r7, r9, r11) == r0) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                j = this.J$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        long j2 = this.J$0;
        TextFieldSelectionState textFieldSelectionState = this.$selectionState;
        this.J$0 = j2;
        this.label = 1;
        if (textFieldSelectionState.updateClipboardEntry(this) != coroutine_suspended) {
            j = j2;
        }
        return coroutine_suspended;
        PlatformSelectionBehaviors platformSelectionBehaviors = this.$selectionState.getPlatformSelectionBehaviors();
        if (platformSelectionBehaviors != null) {
            CharSequence text = this.$selectionState.getTextFieldState().getVisualText().getText();
            long selection = this.$selectionState.getTextFieldState().getVisualText().getSelection();
            Offset offset = Offset.box-impl(j);
            this.label = 2;
        }
        return Unit.INSTANCE;
    }
}
