package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.TimePickerKt", f = "TimePicker.kt", i = {0, 0, 1, 1}, l = {920, 923}, m = "onTap-uYHVD98", n = {"$this$onTap_u2duYHVD98", "autoSwitchToMinute", "$this$onTap_u2duYHVD98", "autoSwitchToMinute"}, s = {"L$0", "Z$0", "L$0", "Z$0"})
public final class TimePickerKt$onTap$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    public TimePickerKt$onTap$1(Continuation<? super TimePickerKt$onTap$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TimePickerKt.m1147onTapuYHVD98(null, 0.0f, 0.0f, 0.0f, false, 0L, null, this);
    }
}
