package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$maybeSuggestSelection$1", f = "TextFieldSelectionManager.kt", i = {}, l = {567}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TextFieldSelectionManager$maybeSuggestSelection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ PlatformSelectionBehaviors $platformSelectionBehaviors;
    final /* synthetic */ TextRange $selection;
    final /* synthetic */ String $text;
    final /* synthetic */ long $transformedSelection;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionManager$maybeSuggestSelection$1(PlatformSelectionBehaviors platformSelectionBehaviors, String str, long j, TextRange textRange, TextFieldSelectionManager textFieldSelectionManager, OffsetMapping offsetMapping, Continuation<? super TextFieldSelectionManager$maybeSuggestSelection$1> continuation) {
        super(2, continuation);
        this.$platformSelectionBehaviors = platformSelectionBehaviors;
        this.$text = str;
        this.$transformedSelection = j;
        this.$selection = textRange;
        this.this$0 = textFieldSelectionManager;
        this.$offsetMapping = offsetMapping;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldSelectionManager$maybeSuggestSelection$1(this.$platformSelectionBehaviors, this.$text, this.$transformedSelection, this.$selection, this.this$0, this.$offsetMapping, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlatformSelectionBehaviors platformSelectionBehaviors = this.$platformSelectionBehaviors;
            String str = this.$text;
            long j = this.$transformedSelection;
            this.label = 1;
            obj = platformSelectionBehaviors.mo1749suggestSelectionForLongPressOrDoubleClickpYaCww(str, j, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        TextRange textRange = (TextRange) obj;
        if (textRange == null) {
            return Unit.INSTANCE;
        }
        OffsetMapping offsetMapping = this.$offsetMapping;
        long j2 = textRange.unbox-impl();
        long jTextRange = TextRangeKt.TextRange(offsetMapping.transformedToOriginal(TextRange.getStart-impl(j2)), offsetMapping.transformedToOriginal(TextRange.getEnd-impl(j2)));
        if (!TextRange.equals-impl(jTextRange, this.$selection) && Intrinsics.areEqual(this.this$0.getValue$foundation().getText(), this.$text) && this.$offsetMapping == this.this$0.getOffsetMapping()) {
            Function1<TextFieldValue, Unit> onValueChange$foundation = this.this$0.getOnValueChange$foundation();
            TextFieldSelectionManager textFieldSelectionManager = this.this$0;
            onValueChange$foundation.invoke(textFieldSelectionManager.m1811createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation().getAnnotatedString(), jTextRange));
            this.this$0.m1823setLatestSelectionOEnZFl4$foundation(TextRange.box-impl(jTextRange));
        }
        return Unit.INSTANCE;
    }
}
