package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1", f = "TextFieldSelectionState.kt", i = {}, l = {1808, 1816}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PressGestureScope $$this$detectTapAndPress;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $offset;
    final /* synthetic */ TextFieldSelectionState $this_defaultDetectTextFieldTapGestures;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1$1", f = "TextFieldSelectionState.kt", i = {1}, l = {1800, 1805}, m = "invokeSuspend", n = {"press"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ long $offset;
        final /* synthetic */ TextFieldSelectionState $this_defaultDetectTextFieldTapGestures;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_defaultDetectTextFieldTapGestures = textFieldSelectionState;
            this.$offset = j;
            this.$interactionSource = mutableInteractionSource;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_defaultDetectTextFieldTapGestures, this.$offset, this.$interactionSource, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x005c  */
        public final Object invokeSuspend(Object obj) {
            TextFieldSelectionState textFieldSelectionState;
            PressInteraction.Press press;
            MutableInteractionSource mutableInteractionSource;
            PressInteraction.Press press2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    textFieldSelectionState = (TextFieldSelectionState) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    press2 = (PressInteraction.Press) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                this.$this_defaultDetectTextFieldTapGestures.setPressInteraction(press2);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            PressInteraction.Press pressInteraction = this.$this_defaultDetectTextFieldTapGestures.getPressInteraction();
            if (pressInteraction != null) {
                MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
                TextFieldSelectionState textFieldSelectionState2 = this.$this_defaultDetectTextFieldTapGestures;
                PressInteraction.Cancel cancel = new PressInteraction.Cancel(pressInteraction);
                this.L$0 = textFieldSelectionState2;
                this.label = 1;
                if (mutableInteractionSource2.emit(cancel, this) != coroutine_suspended) {
                    textFieldSelectionState = textFieldSelectionState2;
                }
            } else {
                press = new PressInteraction.Press(this.$offset, null);
                mutableInteractionSource = this.$interactionSource;
                this.L$0 = press;
                this.label = 2;
                if (mutableInteractionSource.emit(press, this) != coroutine_suspended) {
                    press2 = press;
                    this.$this_defaultDetectTextFieldTapGestures.setPressInteraction(press2);
                    return Unit.INSTANCE;
                }
            }
            return coroutine_suspended;
            textFieldSelectionState.setPressInteraction(null);
            press = new PressInteraction.Press(this.$offset, null);
            mutableInteractionSource = this.$interactionSource;
            this.L$0 = press;
            this.label = 2;
            if (mutableInteractionSource.emit(press, this) != coroutine_suspended) {
                press2 = press;
                this.$this_defaultDetectTextFieldTapGestures.setPressInteraction(press2);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1(PressGestureScope pressGestureScope, TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1> continuation) {
        super(2, continuation);
        this.$$this$detectTapAndPress = pressGestureScope;
        this.$this_defaultDetectTextFieldTapGestures = textFieldSelectionState;
        this.$offset = j;
        this.$interactionSource = mutableInteractionSource;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1 textFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1 = new TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1(this.$$this$detectTapAndPress, this.$this_defaultDetectTextFieldTapGestures, this.$offset, this.$interactionSource, continuation);
        textFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1.L$0 = obj;
        return textFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
    
        if (r4.emit(r13, r12) == r0) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default((CoroutineScope) this.L$0, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.$this_defaultDetectTextFieldTapGestures, this.$offset, this.$interactionSource, null), 3, (Object) null);
            PressGestureScope pressGestureScope = this.$$this$detectTapAndPress;
            this.label = 1;
            obj = pressGestureScope.tryAwaitRelease(this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$this_defaultDetectTextFieldTapGestures.setPressInteraction(null);
        return Unit.INSTANCE;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        PressInteraction.Press pressInteraction = this.$this_defaultDetectTextFieldTapGestures.getPressInteraction();
        if (pressInteraction != null) {
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            Interaction release = zBooleanValue ? new PressInteraction.Release(pressInteraction) : new PressInteraction.Cancel(pressInteraction);
            this.label = 2;
        }
        this.$this_defaultDetectTextFieldTapGestures.setPressInteraction(null);
        return Unit.INSTANCE;
    }
}
