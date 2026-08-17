package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextToolbarHandler;
import androidx.compose.foundation.text.input.internal.selection.TextToolbarState;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096@¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"androidx/compose/foundation/text/BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarHandler;", "showTextToolbar", "", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "rect", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/geometry/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hideTextToolbar", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 implements TextToolbarHandler {
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ TextToolbar $currentTextToolbar;

    public BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1(TextToolbar textToolbar, CoroutineScope coroutineScope) {
        this.$currentTextToolbar = textToolbar;
        this.$coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.foundation.text.input.internal.selection.TextToolbarHandler
    public void hideTextToolbar() {
        if (this.$currentTextToolbar.getStatus() == TextToolbarStatus.Shown) {
            this.$currentTextToolbar.hide();
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.compose.foundation.text.input.internal.selection.TextToolbarHandler
    public Object showTextToolbar(final TextFieldSelectionState textFieldSelectionState, Rect rect, Continuation<? super Unit> continuation) {
        BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1 basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1;
        final CoroutineScope coroutineScope;
        Rect rect2;
        TextToolbar textToolbar;
        if (continuation instanceof BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1) {
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1 = (BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1) continuation;
            int i = basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.label = i - Integer.MIN_VALUE;
            } else {
                basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1 = new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1(this, continuation);
            }
        } else {
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1 = new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1(this, continuation);
        }
        Object obj = basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            TextToolbar textToolbar2 = this.$currentTextToolbar;
            coroutineScope = this.$coroutineScope;
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$0 = rect;
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$1 = textToolbar2;
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$2 = coroutineScope;
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$3 = textFieldSelectionState;
            basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.label = 1;
            if (textFieldSelectionState.updateClipboardEntry(basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            rect2 = rect;
            textToolbar = textToolbar2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            textFieldSelectionState = (TextFieldSelectionState) basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$3;
            coroutineScope = (CoroutineScope) basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$2;
            TextToolbar textToolbar3 = (TextToolbar) basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$1;
            Rect rect3 = (Rect) basicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$1.L$0;
            ResultKt.throwOnFailure(obj);
            textToolbar = textToolbar3;
            rect2 = rect3;
        }
        boolean zCanShowCopyMenuItem = textFieldSelectionState.canShowCopyMenuItem();
        final TextToolbarState textToolbarState = TextToolbarState.None;
        Function0<Unit> function0 = !zCanShowCopyMenuItem ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$lambda$0$$inlined$menuItem$1
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1301invoke() {
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$2$1$1(textFieldSelectionState, null), 1, (Object) null);
                textFieldSelectionState.updateTextToolbarState(textToolbarState);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m1301invoke();
                return Unit.INSTANCE;
            }
        };
        Function0<Unit> function1 = !textFieldSelectionState.canShowPasteMenuItem() ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$lambda$0$$inlined$menuItem$2
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1302invoke() {
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$2$2$1(textFieldSelectionState, null), 1, (Object) null);
                textFieldSelectionState.updateTextToolbarState(textToolbarState);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m1302invoke();
                return Unit.INSTANCE;
            }
        };
        Function0<Unit> function2 = !textFieldSelectionState.canShowCutMenuItem() ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$lambda$0$$inlined$menuItem$3
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1303invoke() {
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$2$3$1(textFieldSelectionState, null), 1, (Object) null);
                textFieldSelectionState.updateTextToolbarState(textToolbarState);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m1303invoke();
                return Unit.INSTANCE;
            }
        };
        boolean zCanShowSelectAllMenuItem = textFieldSelectionState.canShowSelectAllMenuItem();
        final TextToolbarState textToolbarState2 = TextToolbarState.Selection;
        textToolbar.showMenu(rect2, function0, function1, function2, !zCanShowSelectAllMenuItem ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$lambda$0$$inlined$menuItem$4
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1304invoke() {
                textFieldSelectionState.selectAll();
                textFieldSelectionState.updateTextToolbarState(textToolbarState2);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m1304invoke();
                return Unit.INSTANCE;
            }
        }, textFieldSelectionState.canShowAutofillMenuItem() ? new Function0<Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1$showTextToolbar$lambda$0$$inlined$menuItem$5
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1305invoke() {
                textFieldSelectionState.autofill();
                textFieldSelectionState.updateTextToolbarState(textToolbarState);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m1305invoke();
                return Unit.INSTANCE;
            }
        } : null);
        return Unit.INSTANCE;
    }
}
