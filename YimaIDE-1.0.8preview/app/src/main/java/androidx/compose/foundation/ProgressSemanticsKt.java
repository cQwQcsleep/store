package androidx.compose.foundation;

import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007¨\u0006\b"}, d2 = {"progressSemantics", "Landroidx/compose/ui/Modifier;", "value", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "steps", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ProgressSemanticsKt {
    public static Unit a(float f, ClosedFloatingPointRange closedFloatingPointRange, int i, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, new ProgressBarRangeInfo(((Number) RangesKt.coerceIn(Float.valueOf(f), closedFloatingPointRange)).floatValue(), closedFloatingPointRange, i));
        return Unit.INSTANCE;
    }

    public static Unit b(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setProgressBarRangeInfo(semanticsPropertyReceiver, ProgressBarRangeInfo.INSTANCE.getIndeterminate());
        return Unit.INSTANCE;
    }

    public static final Modifier progressSemantics(Modifier modifier, final float f, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int i) {
        return SemanticsModifierKt.semantics(modifier, true, new Function1() { // from class: teb
            public final Object invoke(Object obj) {
                return ProgressSemanticsKt.a(f, closedFloatingPointRange, i, (SemanticsPropertyReceiver) obj);
            }
        });
    }

    public static /* synthetic */ Modifier progressSemantics$default(Modifier modifier, float f, ClosedFloatingPointRange closedFloatingPointRange, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            closedFloatingPointRange = RangesKt.rangeTo(0.0f, 1.0f);
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return progressSemantics(modifier, f, closedFloatingPointRange, i);
    }

    public static final Modifier progressSemantics(Modifier modifier) {
        return SemanticsModifierKt.semantics(modifier, true, new Function1() { // from class: ueb
            public final Object invoke(Object obj) {
                return ProgressSemanticsKt.b((SemanticsPropertyReceiver) obj);
            }
        });
    }
}
