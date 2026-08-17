package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public /* synthetic */ class InteractiveComponentSizeKt$MinimumInteractiveTopAlignmentLine$1 extends FunctionReferenceImpl implements Function2<Integer, Integer, Integer> {
    public static final InteractiveComponentSizeKt$MinimumInteractiveTopAlignmentLine$1 INSTANCE = new InteractiveComponentSizeKt$MinimumInteractiveTopAlignmentLine$1();

    public InteractiveComponentSizeKt$MinimumInteractiveTopAlignmentLine$1() {
        super(2, MathKt.class, "min", "min(II)I", 1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
    }

    public final Integer invoke(int i, int i2) {
        return Integer.valueOf(Math.min(i, i2));
    }
}
