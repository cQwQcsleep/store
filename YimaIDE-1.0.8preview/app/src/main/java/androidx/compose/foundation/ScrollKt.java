package androidx.compose.foundation;

import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a2\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a<\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a2\u0010\u000f\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a<\u0010\u000f\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001aJ\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¨\u0006\u0014"}, d2 = {"rememberScrollState", "Landroidx/compose/foundation/ScrollState;", "initial", "", "(ILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/ScrollState;", "verticalScroll", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "reverseScrolling", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "horizontalScroll", "scroll", "isScrollable", "isVertical", "useLocalOverscrollFactory", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ScrollKt {
    public static final Modifier horizontalScroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2) {
        return scroll$default(modifier, scrollState, z2, flingBehavior, z, false, true, null, 64, null);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            flingBehavior = null;
        }
        FlingBehavior flingBehavior2 = flingBehavior;
        if ((i & 16) != 0) {
            z2 = false;
        }
        return horizontalScroll(modifier, scrollState, overscrollEffect, z3, flingBehavior2, z2);
    }

    public static final ScrollState rememberScrollState(final int i, Composer composer, int i2, int i3) {
        boolean z = true;
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464256199, i2, -1, "androidx.compose.foundation.rememberScrollState (Scroll.kt:70)");
        }
        Object[] objArr = new Object[0];
        Saver<ScrollState, ?> saver = ScrollState.INSTANCE.getSaver();
        if ((((i2 & 14) ^ 6) <= 4 || !composer.changed(i)) && (i2 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: byc
                public final Object invoke() {
                    return ScrollKt.rememberScrollState$lambda$0$0(i);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ScrollState scrollState = (ScrollState) RememberSaveableKt.rememberSaveable(objArr, saver, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return scrollState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScrollState rememberScrollState$lambda$0$0(int i) {
        return new ScrollState(i);
    }

    private static final Modifier scroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, boolean z3, boolean z4, OverscrollEffect overscrollEffect) {
        Orientation orientation = z3 ? Orientation.Vertical : Orientation.Horizontal;
        return (z4 ? ScrollableAreaKt.scrollableArea$default(modifier, scrollState, orientation, z2, z, flingBehavior, scrollState.getInternalInteractionSource(), null, 64, null) : ScrollableAreaKt.scrollableArea$default(modifier, scrollState, orientation, overscrollEffect, z2, z, flingBehavior, scrollState.getInternalInteractionSource(), null, 128, null)).then(new ScrollingLayoutElement(scrollState, z, z3));
    }

    public static /* synthetic */ Modifier scroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, boolean z3, boolean z4, OverscrollEffect overscrollEffect, int i, Object obj) {
        return scroll(modifier, scrollState, z, flingBehavior, z2, z3, z4, (i & 64) != 0 ? null : overscrollEffect);
    }

    public static final Modifier verticalScroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2) {
        return scroll$default(modifier, scrollState, z2, flingBehavior, z, true, true, null, 64, null);
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            flingBehavior = null;
        }
        FlingBehavior flingBehavior2 = flingBehavior;
        if ((i & 16) != 0) {
            z2 = false;
        }
        return verticalScroll(modifier, scrollState, overscrollEffect, z3, flingBehavior2, z2);
    }

    public static final Modifier horizontalScroll(Modifier modifier, ScrollState scrollState, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, boolean z2) {
        return scroll(modifier, scrollState, z2, flingBehavior, z, false, false, overscrollEffect);
    }

    public static final Modifier verticalScroll(Modifier modifier, ScrollState scrollState, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, boolean z2) {
        return scroll(modifier, scrollState, z2, flingBehavior, z, true, false, overscrollEffect);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return horizontalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return verticalScroll(modifier, scrollState, z, flingBehavior, z2);
    }
}
