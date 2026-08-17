package androidx.compose.material3.carousel;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR+\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\bR\u0014\u0010\u001f\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\bR\u0014\u0010!\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\bR\u0014\u0010#\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0018¨\u0006%"}, d2 = {"Landroidx/compose/material3/carousel/CarouselItemDrawInfoImpl;", "Landroidx/compose/material3/carousel/CarouselItemDrawInfo;", "<init>", "()V", "<set-?>", "", "sizeState", "getSizeState", "()F", "setSizeState", "(F)V", "sizeState$delegate", "Landroidx/compose/runtime/MutableFloatState;", "minSizeState", "getMinSizeState", "setMinSizeState", "minSizeState$delegate", "maxSizeState", "getMaxSizeState", "setMaxSizeState", "maxSizeState$delegate", "Landroidx/compose/ui/geometry/Rect;", "maskRectState", "getMaskRectState", "()Landroidx/compose/ui/geometry/Rect;", "setMaskRectState", "(Landroidx/compose/ui/geometry/Rect;)V", "maskRectState$delegate", "Landroidx/compose/runtime/MutableState;", "size", "getSize", "minSize", "getMinSize", "maxSize", "getMaxSize", "maskRect", "getMaskRect", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CarouselItemDrawInfoImpl implements CarouselItemDrawInfo {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: sizeState$delegate, reason: from kotlin metadata */
    private final MutableFloatState sizeState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: minSizeState$delegate, reason: from kotlin metadata */
    private final MutableFloatState minSizeState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: maxSizeState$delegate, reason: from kotlin metadata */
    private final MutableFloatState maxSizeState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: maskRectState$delegate, reason: from kotlin metadata */
    private final MutableState maskRectState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Rect.INSTANCE.getZero(), null, 2, null);

    @Override // androidx.compose.material3.carousel.CarouselItemDrawInfo
    public Rect getMaskRect() {
        return getMaskRectState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Rect getMaskRectState() {
        return (Rect) this.maskRectState.getValue();
    }

    @Override // androidx.compose.material3.carousel.CarouselItemDrawInfo
    public float getMaxSize() {
        return getMaxSizeState();
    }

    public final float getMaxSizeState() {
        return this.maxSizeState.getFloatValue();
    }

    @Override // androidx.compose.material3.carousel.CarouselItemDrawInfo
    public float getMinSize() {
        return getMinSizeState();
    }

    public final float getMinSizeState() {
        return this.minSizeState.getFloatValue();
    }

    @Override // androidx.compose.material3.carousel.CarouselItemDrawInfo
    public float getSize() {
        return getSizeState();
    }

    public final float getSizeState() {
        return this.sizeState.getFloatValue();
    }

    public final void setMaskRectState(Rect rect) {
        this.maskRectState.setValue(rect);
    }

    public final void setMaxSizeState(float f) {
        this.maxSizeState.setFloatValue(f);
    }

    public final void setMinSizeState(float f) {
        this.minSizeState.setFloatValue(f);
    }

    public final void setSizeState(float f) {
        this.sizeState.setFloatValue(f);
    }
}
