package androidx.compose.material3;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R+\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR+\u0010\u0014\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u0014\u0010\u0018\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000bR\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/BottomAppBarStateImpl;", "Landroidx/compose/material3/BottomAppBarState;", "initialHeightOffsetLimit", "", "initialHeightOffset", "initialContentOffset", "<init>", "(FFF)V", "<set-?>", "heightOffsetLimit", "getHeightOffsetLimit", "()F", "setHeightOffsetLimit", "(F)V", "heightOffsetLimit$delegate", "Landroidx/compose/runtime/MutableFloatState;", "newOffset", "heightOffset", "getHeightOffset", "setHeightOffset", "contentOffset", "getContentOffset", "setContentOffset", "contentOffset$delegate", "collapsedFraction", "getCollapsedFraction", "_heightOffset", "Landroidx/compose/runtime/MutableFloatState;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class BottomAppBarStateImpl implements BottomAppBarState {
    private MutableFloatState _heightOffset;

    /* JADX INFO: renamed from: contentOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState contentOffset;

    /* JADX INFO: renamed from: heightOffsetLimit$delegate, reason: from kotlin metadata */
    private final MutableFloatState heightOffsetLimit;

    public BottomAppBarStateImpl(float f, float f2, float f3) {
        this.heightOffsetLimit = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.contentOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f3);
        this._heightOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f2);
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public float getCollapsedFraction() {
        if (getHeightOffsetLimit() == 0.0f) {
            return 0.0f;
        }
        return getHeightOffset() / getHeightOffsetLimit();
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public float getContentOffset() {
        return this.contentOffset.getFloatValue();
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public float getHeightOffset() {
        return this._heightOffset.getFloatValue();
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public float getHeightOffsetLimit() {
        return this.heightOffsetLimit.getFloatValue();
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public void setContentOffset(float f) {
        this.contentOffset.setFloatValue(f);
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public void setHeightOffset(float f) {
        this._heightOffset.setFloatValue(RangesKt.coerceIn(f, getHeightOffsetLimit(), 0.0f));
    }

    @Override // androidx.compose.material3.BottomAppBarState
    public void setHeightOffsetLimit(float f) {
        this.heightOffsetLimit.setFloatValue(f);
    }
}
