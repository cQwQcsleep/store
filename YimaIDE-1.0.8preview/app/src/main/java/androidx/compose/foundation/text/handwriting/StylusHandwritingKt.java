package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.text.TextPointerIcon_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.node.DpTouchBoundsExpansion;
import androidx.compose.ui.node.TouchBoundsExpansionKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000\"\u0016\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\r\u001a\u00020\tX\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000b\"\u0014\u0010\u000f\u001a\u00020\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"stylusHandwriting", "Landroidx/compose/ui/Modifier;", "enabled", "", "showHoverIcon", "onHandwritingSlopExceeded", "Lkotlin/Function0;", "", "HandwritingBoundsVerticalOffset", "Landroidx/compose/ui/unit/Dp;", "getHandwritingBoundsVerticalOffset", "()F", "F", "HandwritingBoundsHorizontalOffset", "getHandwritingBoundsHorizontalOffset", "HandwritingBoundsExpansion", "Landroidx/compose/ui/node/DpTouchBoundsExpansion;", "getHandwritingBoundsExpansion", "()Landroidx/compose/ui/node/DpTouchBoundsExpansion;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class StylusHandwritingKt {
    private static final DpTouchBoundsExpansion HandwritingBoundsExpansion;
    private static final float HandwritingBoundsHorizontalOffset;
    private static final float HandwritingBoundsVerticalOffset;

    static {
        float f = Dp.constructor-impl(40.0f);
        HandwritingBoundsVerticalOffset = f;
        float f2 = Dp.constructor-impl(10.0f);
        HandwritingBoundsHorizontalOffset = f2;
        HandwritingBoundsExpansion = TouchBoundsExpansionKt.DpTouchBoundsExpansion-a9UjIt4(f2, f, f2, f);
    }

    public static final DpTouchBoundsExpansion getHandwritingBoundsExpansion() {
        return HandwritingBoundsExpansion;
    }

    public static final float getHandwritingBoundsHorizontalOffset() {
        return HandwritingBoundsHorizontalOffset;
    }

    public static final float getHandwritingBoundsVerticalOffset() {
        return HandwritingBoundsVerticalOffset;
    }

    public static final Modifier stylusHandwriting(Modifier modifier, boolean z, boolean z2, Function0<Unit> function0) {
        if (!z || !StylusHandwriting_androidKt.isStylusHandwritingSupported()) {
            return modifier;
        }
        if (z2) {
            modifier = PointerIconKt.stylusHoverIcon(modifier, TextPointerIcon_androidKt.getHandwritingPointerIcon(), false, HandwritingBoundsExpansion);
        }
        return modifier.then(new StylusHandwritingElement(function0));
    }
}
