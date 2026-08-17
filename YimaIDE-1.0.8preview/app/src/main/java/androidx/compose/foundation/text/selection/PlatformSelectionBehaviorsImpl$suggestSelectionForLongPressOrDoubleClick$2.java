package androidx.compose.foundation.text.selection;

import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextRange;", "Landroid/view/textclassifier/TextClassifier;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2", f = "PlatformSelectionBehaviors.android.kt", i = {0, 0, 0, 1}, l = {369, 159}, m = "invokeSuspend", n = {"suggestedSelection", "$this$withLock_u24default$iv", "newSelection", "newSelection"}, s = {"L$0", "L$1", "J$0", "J$0"}, v = 1)
public final class PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 extends SuspendLambda implements Function2<TextClassifier, Continuation<? super TextRange>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-text-TextRange$-selection$0, reason: not valid java name */
    final /* synthetic */ long f62$$v$c$androidxcomposeuitextTextRange$selection$0;
    final /* synthetic */ CharSequence $text;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(CharSequence charSequence, long j, PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, Continuation<? super PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2> continuation) {
        super(2, continuation);
        this.$text = charSequence;
        this.f62$$v$c$androidxcomposeuitextTextRange$selection$0 = j;
        this.this$0 = platformSelectionBehaviorsImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2 = new PlatformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2(this.$text, this.f62$$v$c$androidxcomposeuitextTextRange$selection$0, this.this$0, continuation);
        platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2.L$0 = obj;
        return platformSelectionBehaviorsImpl$suggestSelectionForLongPressOrDoubleClick$2;
    }

    public final Object invoke(TextClassifier textClassifier, Continuation<? super TextRange> continuation) {
        return create(textClassifier, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        long jTextRange;
        long j;
        Mutex mutex;
        CharSequence charSequence;
        TextSelection textSelection;
        PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextClassifier textClassifier = (TextClassifier) this.L$0;
            TextSelection.Request.Builder defaultLocales = new TextSelection.Request.Builder(this.$text, TextRange.getMin-impl(this.f62$$v$c$androidxcomposeuitextTextRange$selection$0), TextRange.getMax-impl(this.f62$$v$c$androidxcomposeuitextTextRange$selection$0)).setDefaultLocales(this.this$0.getAndroidLocalList());
            defaultLocales.setIncludeTextClassification(true);
            TextSelection textSelectionSuggestSelection = textClassifier.suggestSelection(defaultLocales.build());
            jTextRange = TextRangeKt.TextRange(textSelectionSuggestSelection.getSelectionStartIndex(), textSelectionSuggestSelection.getSelectionEndIndex());
            TextClassification textClassification = textSelectionSuggestSelection.getTextClassification();
            PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl2 = this.this$0;
            if (textClassification != null) {
                mutex = platformSelectionBehaviorsImpl2.mutex;
                PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl3 = this.this$0;
                charSequence = this.$text;
                this.L$0 = textSelectionSuggestSelection;
                this.L$1 = mutex;
                this.L$2 = platformSelectionBehaviorsImpl3;
                this.L$3 = charSequence;
                this.J$0 = jTextRange;
                this.label = 1;
                if (mutex.lock((Object) null, this) != coroutine_suspended) {
                    textSelection = textSelectionSuggestSelection;
                    platformSelectionBehaviorsImpl = platformSelectionBehaviorsImpl3;
                    CharSequence charSequence2 = charSequence;
                    Mutex mutex2 = mutex;
                    TextClassification textClassification2 = textSelection.getTextClassification();
                    textClassification2.getClass();
                    platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence2, jTextRange, textClassification2, null));
                    Unit unit = Unit.INSTANCE;
                }
            } else {
                CharSequence charSequence3 = this.$text;
                this.J$0 = jTextRange;
                this.label = 2;
                if (platformSelectionBehaviorsImpl2.m1752classifyTextM8tDOmk(charSequence3, jTextRange, textClassifier, this) != coroutine_suspended) {
                    j = jTextRange;
                    jTextRange = j;
                }
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            long j2 = this.J$0;
            charSequence = (CharSequence) this.L$3;
            platformSelectionBehaviorsImpl = (PlatformSelectionBehaviorsImpl) this.L$2;
            mutex = (Mutex) this.L$1;
            textSelection = (TextSelection) this.L$0;
            ResultKt.throwOnFailure(obj);
            jTextRange = j2;
            CharSequence charSequence4 = charSequence;
            Mutex mutex3 = mutex;
            try {
                TextClassification textClassification3 = textSelection.getTextClassification();
                textClassification3.getClass();
                platformSelectionBehaviorsImpl.setTextClassificationResult(new TextClassificationResult(charSequence4, jTextRange, textClassification3, null));
                Unit unit2 = Unit.INSTANCE;
            } finally {
                mutex3.unlock((Object) null);
            }
        } else {
            if (i != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j = this.J$0;
            ResultKt.throwOnFailure(obj);
            jTextRange = j;
        }
        return TextRange.box-impl(jTextRange);
    }
}
