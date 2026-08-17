package androidx.compose.material3.internal;

import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bÁ\u0002\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u0007J\u0010\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u0007¨\u0006\u0014"}, d2 = {"Landroidx/compose/material3/internal/MenuPosition;", "", "<init>", "()V", "startToAnchorStart", "Landroidx/compose/material3/internal/MenuPosition$Horizontal;", "offset", "", "endToAnchorEnd", "leftToWindowLeft", "margin", "rightToWindowRight", "topToAnchorBottom", "Landroidx/compose/material3/internal/MenuPosition$Vertical;", "bottomToAnchorTop", "centerToAnchorTop", "topToWindowTop", "bottomToWindowBottom", "Vertical", "Horizontal", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MenuPosition {
    public static final int $stable = 0;
    public static final MenuPosition INSTANCE = new MenuPosition();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bç\u0080\u0001\u0018\u00002\u00020\u0001J/\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/material3/internal/MenuPosition$Horizontal;", "", "position", "", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuWidth", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "position-95KtPRI", "(Landroidx/compose/ui/unit/IntRect;JILandroidx/compose/ui/unit/LayoutDirection;)I", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface Horizontal {
        /* JADX INFO: renamed from: position-95KtPRI */
        int mo1367position95KtPRI(IntRect anchorBounds, long windowSize, int menuWidth, LayoutDirection layoutDirection);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bç\u0080\u0001\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/material3/internal/MenuPosition$Vertical;", "", "position", "", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuHeight", "position-JVtK1S4", "(Landroidx/compose/ui/unit/IntRect;JI)I", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public interface Vertical {
        /* JADX INFO: renamed from: position-JVtK1S4 */
        int mo1368positionJVtK1S4(IntRect anchorBounds, long windowSize, int menuHeight);
    }

    private MenuPosition() {
    }

    public static /* synthetic */ Vertical bottomToAnchorTop$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.bottomToAnchorTop(i);
    }

    public static /* synthetic */ Vertical bottomToWindowBottom$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.bottomToWindowBottom(i);
    }

    public static /* synthetic */ Vertical centerToAnchorTop$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.centerToAnchorTop(i);
    }

    public static /* synthetic */ Horizontal endToAnchorEnd$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.endToAnchorEnd(i);
    }

    public static /* synthetic */ Horizontal leftToWindowLeft$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.leftToWindowLeft(i);
    }

    public static /* synthetic */ Horizontal rightToWindowRight$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.rightToWindowRight(i);
    }

    public static /* synthetic */ Horizontal startToAnchorStart$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.startToAnchorStart(i);
    }

    public static /* synthetic */ Vertical topToAnchorBottom$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.topToAnchorBottom(i);
    }

    public static /* synthetic */ Vertical topToWindowTop$default(MenuPosition menuPosition, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return menuPosition.topToWindowTop(i);
    }

    public final Vertical bottomToAnchorTop(int offset) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return new AnchorAlignmentOffsetPosition.Vertical(companion.getBottom(), companion.getTop(), offset);
    }

    public final Vertical bottomToWindowBottom(int margin) {
        return new WindowAlignmentMarginPosition.Vertical(Alignment.INSTANCE.getBottom(), margin);
    }

    public final Vertical centerToAnchorTop(int offset) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return new AnchorAlignmentOffsetPosition.Vertical(companion.getCenterVertically(), companion.getTop(), offset);
    }

    public final Horizontal endToAnchorEnd(int offset) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return new AnchorAlignmentOffsetPosition.Horizontal(companion.getEnd(), companion.getEnd(), offset);
    }

    public final Horizontal leftToWindowLeft(int margin) {
        return new WindowAlignmentMarginPosition.Horizontal(AbsoluteAlignment.INSTANCE.getLeft(), margin);
    }

    public final Horizontal rightToWindowRight(int margin) {
        return new WindowAlignmentMarginPosition.Horizontal(AbsoluteAlignment.INSTANCE.getRight(), margin);
    }

    public final Horizontal startToAnchorStart(int offset) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return new AnchorAlignmentOffsetPosition.Horizontal(companion.getStart(), companion.getStart(), offset);
    }

    public final Vertical topToAnchorBottom(int offset) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return new AnchorAlignmentOffsetPosition.Vertical(companion.getTop(), companion.getBottom(), offset);
    }

    public final Vertical topToWindowTop(int margin) {
        return new WindowAlignmentMarginPosition.Vertical(Alignment.INSTANCE.getTop(), margin);
    }
}
