package androidx.compose.ui.input.pointer;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0002\u0010\u0006\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0003H\u0082\b¨\u0006\t"}, d2 = {"ProcessResult", "Landroidx/compose/ui/input/pointer/ProcessResult;", "dispatchedToAPointerInputModifier", "", "anyMovementConsumed", "anyChangeConsumed", "(ZZZ)I", "toInt", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PointerInputEventProcessorKt {
    public static final int ProcessResult(boolean z, boolean z2, boolean z3) {
        return ProcessResult.m4541constructorimpl((z ? 1 : 0) | ((z2 ? 1 : 0) << 1) | ((z3 ? 1 : 0) << 2));
    }

    private static final int toInt(boolean z) {
        return z ? 1 : 0;
    }
}
