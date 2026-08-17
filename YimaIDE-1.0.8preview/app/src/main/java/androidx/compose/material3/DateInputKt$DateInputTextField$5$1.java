package androidx.compose.material3;

import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.DateInputKt$DateInputTextField$5$1", f = "DateInput.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class DateInputKt$DateInputTextField$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DateInputFormat $dateInputFormat;
    final /* synthetic */ Long $initialDateMillis;
    final /* synthetic */ Locale $locale;
    final /* synthetic */ MutableState<TextFieldValue> $text$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateInputKt$DateInputTextField$5$1(Long l, CalendarModel calendarModel, DateInputFormat dateInputFormat, Locale locale, MutableState<TextFieldValue> mutableState, Continuation<? super DateInputKt$DateInputTextField$5$1> continuation) {
        super(2, continuation);
        this.$initialDateMillis = l;
        this.$calendarModel = calendarModel;
        this.$dateInputFormat = dateInputFormat;
        this.$locale = locale;
        this.$text$delegate = mutableState;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DateInputKt$DateInputTextField$5$1(this.$initialDateMillis, this.$calendarModel, this.$dateInputFormat, this.$locale, this.$text$delegate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        Long l = this.$initialDateMillis;
        if (l != null) {
            CalendarModel calendarModel = this.$calendarModel;
            DateInputFormat dateInputFormat = this.$dateInputFormat;
            Locale locale = this.$locale;
            MutableState<TextFieldValue> mutableState = this.$text$delegate;
            String withPattern = calendarModel.formatWithPattern(l.longValue(), dateInputFormat.getPatternWithoutDelimiters(), locale);
            mutableState.setValue(new TextFieldValue(withPattern, withPattern.length() == 0 ? TextRange.INSTANCE.m5484getZerod9O1mEE() : TextRangeKt.TextRange(withPattern.length(), withPattern.length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
        }
        return Unit.INSTANCE;
    }
}
