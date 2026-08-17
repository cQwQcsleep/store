package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
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
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$1", f = "TextFieldSelectionManager.kt", i = {0}, l = {228, 230}, m = "invokeSuspend", n = {"clickLocation"}, s = {"J$0"}, v = 1)
public final class TextFieldSelectionManager$contextMenuAreaModifier$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionManager$contextMenuAreaModifier$1(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$contextMenuAreaModifier$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionManager$contextMenuAreaModifier$1 textFieldSelectionManager$contextMenuAreaModifier$1 = new TextFieldSelectionManager$contextMenuAreaModifier$1(this.this$0, continuation);
        textFieldSelectionManager$contextMenuAreaModifier$1.J$0 = ((Offset) obj).unbox-impl();
        return textFieldSelectionManager$contextMenuAreaModifier$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1825invoke3MmeM6k(((Offset) obj).unbox-impl(), (Continuation) obj2);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m1825invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return create(Offset.box-impl(j), continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
    
        if (r6.mo1747onShowContextMenu_2OEclM(r7, r8, r10, r12) == r0) goto L20;
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
        TextFieldSelectionManager textFieldSelectionManager = this.this$0;
        this.J$0 = j2;
        this.label = 1;
        if (textFieldSelectionManager.updateClipboardEntry$foundation(this) != coroutine_suspended) {
            j = j2;
        }
        return coroutine_suspended;
        Pair contextTextAndSelection = this.this$0.getContextTextAndSelection();
        if (contextTextAndSelection != null) {
            TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
            String str = (String) contextTextAndSelection.component1();
            long j3 = ((TextRange) contextTextAndSelection.component2()).unbox-impl();
            PlatformSelectionBehaviors platformSelectionBehaviors = textFieldSelectionManager2.getPlatformSelectionBehaviors();
            if (platformSelectionBehaviors != null) {
                Offset offset = Offset.box-impl(j);
                this.label = 2;
            }
        }
        return Unit.INSTANCE;
    }
}
