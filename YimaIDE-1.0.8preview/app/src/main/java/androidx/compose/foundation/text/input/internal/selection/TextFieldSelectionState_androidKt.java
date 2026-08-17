package androidx.compose.foundation.text.input.internal.selection;

import android.content.Context;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a@\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0080@¢\u0006\u0002\u0010\u000f\u001a*\u0010\u0010\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"addBasicTextFieldTextContextMenuComponents", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "detectTextFieldTapGestures", "", "pointerInputScope", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "Lkotlin/Function0;", "showKeyboard", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textFieldSelectionGestures", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldSelectionState_androidKt {
    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier modifier, final TextFieldSelectionState textFieldSelectionState, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: x9e
            public final Object invoke(Object obj, Object obj2) {
                return TextFieldSelectionState_androidKt.b(textFieldSelectionState, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2(final TextFieldSelectionState textFieldSelectionState, CoroutineScope coroutineScope, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Cut, textFieldSelectionState.canShowCutMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$1(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Copy, textFieldSelectionState.canShowCopyMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$2(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Paste, textFieldSelectionState.canShowPasteMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, textFieldSelectionState, TextContextMenuItems.SelectAll, textFieldSelectionState.canShowSelectAllMenuItem(), TextToolbarState.Selection, new Function0() { // from class: u9e
            public final Object invoke() {
                return Boolean.valueOf(TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$0(textFieldSelectionState));
            }
        }, new Function0() { // from class: v9e
            public final Object invoke() {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$1(textFieldSelectionState);
            }
        });
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(textContextMenuBuilderScope, context, textFieldSelectionState, TextContextMenuItems.Autofill, textFieldSelectionState.canShowAutofillMenuItem(), null, null, new Function0() { // from class: w9e
            public final Object invoke() {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$2(textFieldSelectionState);
            }
        }, 48, null);
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$0(TextFieldSelectionState textFieldSelectionState) {
        return !textFieldSelectionState.getTextToolbarShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$1(TextFieldSelectionState textFieldSelectionState) {
        textFieldSelectionState.selectAll();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$2(TextFieldSelectionState textFieldSelectionState) {
        textFieldSelectionState.autofill();
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, final TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, final TextToolbarState textToolbarState, final Function0<Boolean> function0, final Function0<Unit> function1) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: z9e
            public final Object invoke(Object obj) {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(function1, function0, textFieldSelectionState, textToolbarState, (TextContextMenuSession) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(Function0 function0, Function0 function1, TextFieldSelectionState textFieldSelectionState, TextToolbarState textToolbarState, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function1 != null ? ((Boolean) function1.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        textFieldSelectionState.updateTextToolbarState(textToolbarState);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, TextToolbarState textToolbarState, Function0 function0, Function0 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            textToolbarState = TextToolbarState.None;
        }
        TextToolbarState textToolbarState2 = textToolbarState;
        if ((i & 32) != 0) {
            function0 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, textFieldSelectionState, textContextMenuItems, z, textToolbarState2, function0, function1);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(TextContextMenuBuilderScope textContextMenuBuilderScope, final CoroutineScope coroutineScope, Context context, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(textContextMenuBuilderScope, context, textFieldSelectionState, textContextMenuItems, z, null, null, new Function0() { // from class: y9e
            public final Object invoke() {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(coroutineScope, function1);
            }
        }, 48, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(CoroutineScope coroutineScope, Function1 function1) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1(function1, null), 1, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit b(final TextFieldSelectionState textFieldSelectionState, final CoroutineScope coroutineScope, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        PlatformSelectionBehaviors_androidKt.m1757addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, textFieldSelectionState.getEditable$foundation(), textFieldSelectionState.getTextFieldState().getVisualText().getText(), TextRange.box-impl(textFieldSelectionState.getTextFieldState().getVisualText().getSelection()), textFieldSelectionState.getPlatformSelectionBehaviors(), new Function1() { // from class: aae
            public final Object invoke(Object obj) {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2(textFieldSelectionState, coroutineScope, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Object detectTextFieldTapGestures(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, MutableInteractionSource mutableInteractionSource, Function0<Unit> function0, Function0<Unit> function1, Continuation<? super Unit> continuation) {
        Object objDefaultDetectTextFieldTapGestures = TextFieldSelectionStateKt.defaultDetectTextFieldTapGestures(textFieldSelectionState, pointerInputScope, mutableInteractionSource, function0, function1, continuation);
        return objDefaultDetectTextFieldTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDefaultDetectTextFieldTapGestures : Unit.INSTANCE;
    }

    public static final Object textFieldSelectionGestures(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objDefaultTextFieldSelectionGestures = TextFieldSelectionStateKt.defaultTextFieldSelectionGestures(pointerInputScope, mouseSelectionObserver, textDragObserver, continuation);
        return objDefaultTextFieldSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDefaultTextFieldSelectionGestures : Unit.INSTANCE;
    }
}
