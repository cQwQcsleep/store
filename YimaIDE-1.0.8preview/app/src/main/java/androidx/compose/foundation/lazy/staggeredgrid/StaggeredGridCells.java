package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001:\u0003\b\t\nJ\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "", "calculateCrossAxisCellSizes", "", "Landroidx/compose/ui/unit/Density;", "availableSize", "", "spacing", "Fixed", "Adaptive", "FixedSize", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface StaggeredGridCells {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells$Fixed;", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "count", "", "<init>", "(I)V", "calculateCrossAxisCellSizes", "", "Landroidx/compose/ui/unit/Density;", "availableSize", "spacing", "hashCode", "equals", "", "other", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Fixed implements StaggeredGridCells {
        public static final int $stable = 0;
        private final int count;

        public Fixed(int i) {
            this.count = i;
            if (i > 0) {
                return;
            }
            InlineClassHelperKt.throwIllegalArgumentException("grid with no rows/columns");
        }

        @Override // androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
        public int[] calculateCrossAxisCellSizes(Density density, int i, int i2) {
            return LazyStaggeredGridCellsKt.calculateCellsCrossAxisSizeImpl(i, this.count, i2);
        }

        public boolean equals(Object other) {
            return (other instanceof Fixed) && this.count == ((Fixed) other).count;
        }

        public int hashCode() {
            return -this.count;
        }
    }

    int[] calculateCrossAxisCellSizes(Density density, int i, int i2);

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells$FixedSize;", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "size", "Landroidx/compose/ui/unit/Dp;", "<init>", "(FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "calculateCrossAxisCellSizes", "", "Landroidx/compose/ui/unit/Density;", "availableSize", "", "spacing", "hashCode", "equals", "", "other", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class FixedSize implements StaggeredGridCells {
        public static final int $stable = 0;
        private final float size;

        private FixedSize(float f) {
            this.size = f;
        }

        @Override // androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
        public int[] calculateCrossAxisCellSizes(Density density, int i, int i2) {
            int i3 = density.roundToPx-0680j_4(this.size);
            int i4 = i3 + i2;
            int i5 = i2 + i;
            if (i4 >= i5) {
                return new int[]{i};
            }
            int i6 = i5 / i4;
            int[] iArr = new int[i6];
            for (int i7 = 0; i7 < i6; i7++) {
                iArr[i7] = i3;
            }
            return iArr;
        }

        public boolean equals(Object other) {
            return (other instanceof FixedSize) && Dp.equals-impl0(this.size, ((FixedSize) other).size);
        }

        public int hashCode() {
            return Dp.hashCode-impl(this.size);
        }

        public /* synthetic */ FixedSize(float f, DefaultConstructorMarker defaultConstructorMarker) {
            this(f);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells$Adaptive;", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "minSize", "Landroidx/compose/ui/unit/Dp;", "<init>", "(FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "calculateCrossAxisCellSizes", "", "Landroidx/compose/ui/unit/Density;", "availableSize", "", "spacing", "hashCode", "equals", "", "other", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Adaptive implements StaggeredGridCells {
        public static final int $stable = 0;
        private final float minSize;

        private Adaptive(float f) {
            this.minSize = f;
            if (Dp.compareTo-0680j_4(f, Dp.constructor-impl(0.0f)) > 0) {
                return;
            }
            InlineClassHelperKt.throwIllegalArgumentException("invalid minSize");
        }

        @Override // androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
        public int[] calculateCrossAxisCellSizes(Density density, int i, int i2) {
            return LazyStaggeredGridCellsKt.calculateCellsCrossAxisSizeImpl(i, Math.max((i + i2) / (density.roundToPx-0680j_4(this.minSize) + i2), 1), i2);
        }

        public boolean equals(Object other) {
            return (other instanceof Adaptive) && Dp.equals-impl0(this.minSize, ((Adaptive) other).minSize);
        }

        public int hashCode() {
            return Dp.hashCode-impl(this.minSize);
        }

        public /* synthetic */ Adaptive(float f, DefaultConstructorMarker defaultConstructorMarker) {
            this(f);
        }
    }
}
