package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1", f = "CommonContextMenuArea.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<MenuItemsAvailability> $menuItemsAvailability;
    final /* synthetic */ TextFieldSelectionState $selectionState;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1(MutableState<MenuItemsAvailability> mutableState, TextFieldSelectionState textFieldSelectionState, Continuation<? super CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1> continuation) {
        super(2, continuation);
        this.$menuItemsAvailability = mutableState;
        this.$selectionState = textFieldSelectionState;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonContextMenuAreaKt$CommonContextMenuArea$5$1$1(this.$menuItemsAvailability, this.$selectionState, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MutableState<MenuItemsAvailability> mutableState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableState<MenuItemsAvailability> mutableState2 = this.$menuItemsAvailability;
            TextFieldSelectionState textFieldSelectionState = this.$selectionState;
            this.L$0 = mutableState2;
            this.label = 1;
            Object contextMenuItemsAvailability = CommonContextMenuAreaKt.getContextMenuItemsAvailability(textFieldSelectionState, (Continuation<? super MenuItemsAvailability>) this);
            if (contextMenuItemsAvailability == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = contextMenuItemsAvailability;
            mutableState = mutableState2;
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            mutableState = (MutableState) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        mutableState.setValue(obj);
        return Unit.INSTANCE;
    }
}
