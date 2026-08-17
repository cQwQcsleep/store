package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0003\u001a\u00020\u00028WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/FloatState;", "Landroidx/compose/runtime/State;", "", "value", "getValue", "()Ljava/lang/Float;", "floatValue", "getFloatValue", "()F", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface FloatState extends State<Float> {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DefaultImpls {
        @Deprecated
        public static Float getValue(FloatState floatState) {
            return Float.valueOf(FloatState.access$getValue$jd(floatState));
        }
    }

    static /* synthetic */ float access$getValue$jd(FloatState floatState) {
        return super.getValue().floatValue();
    }

    float getFloatValue();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.runtime.State
    default Float getValue() {
        return Float.valueOf(getFloatValue());
    }
}
