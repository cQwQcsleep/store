package androidx.compose.foundation.gestures;

import android.view.ViewConfiguration;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\b\u001a\u00020\t*\u00020\nH\u0001¢\u0006\u0002\b\u000bJ\u0011\u0010\f\u001a\u00020\t*\u00020\nH\u0001¢\u0006\u0002\b\rJ#\u0010\u000e\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/gestures/AndroidConfig;", "Landroidx/compose/foundation/gestures/ScrollConfig;", "viewConfiguration", "Landroid/view/ViewConfiguration;", "<init>", "(Landroid/view/ViewConfiguration;)V", "getViewConfiguration", "()Landroid/view/ViewConfiguration;", "getVerticalScrollFactor", "", "Landroidx/compose/ui/unit/Density;", "getVerticalScrollFactor$foundation", "getHorizontalScrollFactor", "getHorizontalScrollFactor$foundation", "calculateMouseWheelScroll", "Landroidx/compose/ui/geometry/Offset;", "event", "Landroidx/compose/ui/input/pointer/PointerEvent;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "calculateMouseWheelScroll-8xgXZGE", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/input/pointer/PointerEvent;J)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidConfig implements ScrollConfig {
    public static final int $stable = 8;
    private final ViewConfiguration viewConfiguration;

    public AndroidConfig(ViewConfiguration viewConfiguration) {
        this.viewConfiguration = viewConfiguration;
    }

    @Override // androidx.compose.foundation.gestures.ScrollConfig
    /* JADX INFO: renamed from: calculateMouseWheelScroll-8xgXZGE, reason: not valid java name */
    public long mo523calculateMouseWheelScroll8xgXZGE(Density density, PointerEvent pointerEvent, long j) {
        float f = -getVerticalScrollFactor$foundation(density);
        float f2 = -getHorizontalScrollFactor$foundation(density);
        List changes = pointerEvent.getChanges();
        Offset offset = Offset.box-impl(Offset.Companion.getZero-F1C5BW0());
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            offset = Offset.box-impl(Offset.plus-MK-Hz9U(offset.unbox-impl(), ((PointerInputChange) changes.get(i)).getScrollDelta-F1C5BW0()));
        }
        long j2 = offset.unbox-impl();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32)) * f2;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j2 & 4294967295L)) * f;
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
    }

    public final float getHorizontalScrollFactor$foundation(Density density) {
        return ViewConfigurationApi26Impl.INSTANCE.getHorizontalScrollFactor(this.viewConfiguration);
    }

    public final float getVerticalScrollFactor$foundation(Density density) {
        return ViewConfigurationApi26Impl.INSTANCE.getVerticalScrollFactor(this.viewConfiguration);
    }

    public final ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }
}
