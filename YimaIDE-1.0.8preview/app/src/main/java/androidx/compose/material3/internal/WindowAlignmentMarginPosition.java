package androidx.compose.material3.internal;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/compose/material3/internal/WindowAlignmentMarginPosition;", "", "<init>", "()V", "Horizontal", "Vertical", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAlignmentMarginPosition {
    public static final int $stable = 0;
    public static final WindowAlignmentMarginPosition INSTANCE = new WindowAlignmentMarginPosition();

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J/\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0012\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/internal/WindowAlignmentMarginPosition$Horizontal;", "Landroidx/compose/material3/internal/MenuPosition$Horizontal;", "alignment", "Landroidx/compose/ui/Alignment$Horizontal;", "margin", "", "<init>", "(Landroidx/compose/ui/Alignment$Horizontal;I)V", "position", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuWidth", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "position-95KtPRI", "(Landroidx/compose/ui/unit/IntRect;JILandroidx/compose/ui/unit/LayoutDirection;)I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* data */ class Horizontal implements MenuPosition.Horizontal {
        public static final int $stable = 0;
        private final Alignment.Horizontal alignment;
        private final int margin;

        public Horizontal(Alignment.Horizontal horizontal, int i) {
            this.alignment = horizontal;
            this.margin = i;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Alignment.Horizontal getAlignment() {
            return this.alignment;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getMargin() {
            return this.margin;
        }

        public static /* synthetic */ Horizontal copy$default(Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                horizontal2 = horizontal.alignment;
            }
            if ((i2 & 2) != 0) {
                i = horizontal.margin;
            }
            return horizontal.copy(horizontal2, i);
        }

        public final Horizontal copy(Alignment.Horizontal alignment, int margin) {
            return new Horizontal(alignment, margin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Horizontal)) {
                return false;
            }
            Horizontal horizontal = (Horizontal) other;
            return Intrinsics.areEqual(this.alignment, horizontal.alignment) && this.margin == horizontal.margin;
        }

        public int hashCode() {
            return (this.alignment.hashCode() * 31) + Integer.hashCode(this.margin);
        }

        @Override // androidx.compose.material3.internal.MenuPosition.Horizontal
        /* JADX INFO: renamed from: position-95KtPRI */
        public int mo1367position95KtPRI(IntRect anchorBounds, long windowSize, int menuWidth, LayoutDirection layoutDirection) {
            int i = (int) (windowSize >> 32);
            if (menuWidth >= i - (this.margin * 2)) {
                return Alignment.INSTANCE.getCenterHorizontally().align(menuWidth, i, layoutDirection);
            }
            int iAlign = this.alignment.align(menuWidth, i, layoutDirection);
            int i2 = this.margin;
            return RangesKt.coerceIn(iAlign, i2, (i - i2) - menuWidth);
        }

        public String toString() {
            return "Horizontal(alignment=" + this.alignment + ", margin=" + this.margin + ')';
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/material3/internal/WindowAlignmentMarginPosition$Vertical;", "Landroidx/compose/material3/internal/MenuPosition$Vertical;", "alignment", "Landroidx/compose/ui/Alignment$Vertical;", "margin", "", "<init>", "(Landroidx/compose/ui/Alignment$Vertical;I)V", "position", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuHeight", "position-JVtK1S4", "(Landroidx/compose/ui/unit/IntRect;JI)I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* data */ class Vertical implements MenuPosition.Vertical {
        public static final int $stable = 0;
        private final Alignment.Vertical alignment;
        private final int margin;

        public Vertical(Alignment.Vertical vertical, int i) {
            this.alignment = vertical;
            this.margin = i;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Alignment.Vertical getAlignment() {
            return this.alignment;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getMargin() {
            return this.margin;
        }

        public static /* synthetic */ Vertical copy$default(Vertical vertical, Alignment.Vertical vertical2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                vertical2 = vertical.alignment;
            }
            if ((i2 & 2) != 0) {
                i = vertical.margin;
            }
            return vertical.copy(vertical2, i);
        }

        public final Vertical copy(Alignment.Vertical alignment, int margin) {
            return new Vertical(alignment, margin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Vertical)) {
                return false;
            }
            Vertical vertical = (Vertical) other;
            return Intrinsics.areEqual(this.alignment, vertical.alignment) && this.margin == vertical.margin;
        }

        public int hashCode() {
            return (this.alignment.hashCode() * 31) + Integer.hashCode(this.margin);
        }

        @Override // androidx.compose.material3.internal.MenuPosition.Vertical
        /* JADX INFO: renamed from: position-JVtK1S4 */
        public int mo1368positionJVtK1S4(IntRect anchorBounds, long windowSize, int menuHeight) {
            int i = (int) (windowSize & 4294967295L);
            if (menuHeight >= i - (this.margin * 2)) {
                return Alignment.INSTANCE.getCenterVertically().align(menuHeight, i);
            }
            int iAlign = this.alignment.align(menuHeight, i);
            int i2 = this.margin;
            return RangesKt.coerceIn(iAlign, i2, (i - i2) - menuHeight);
        }

        public String toString() {
            return "Vertical(alignment=" + this.alignment + ", margin=" + this.margin + ')';
        }
    }

    private WindowAlignmentMarginPosition() {
    }
}
