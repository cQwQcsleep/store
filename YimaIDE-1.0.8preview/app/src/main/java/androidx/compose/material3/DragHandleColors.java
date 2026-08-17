package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\t¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/DragHandleColors;", "", "color", "Landroidx/compose/ui/graphics/Color;", "pressedColor", "draggedColor", "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getColor-0d7_KjU", "()J", "J", "getPressedColor-0d7_KjU", "getDraggedColor-0d7_KjU", "equals", "", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DragHandleColors {
    public static final int $stable = 0;
    private final long color;
    private final long draggedColor;
    private final long pressedColor;

    private DragHandleColors(long j, long j2, long j3) {
        this.color = j;
        this.pressedColor = j2;
        this.draggedColor = j3;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof DragHandleColors)) {
            return false;
        }
        DragHandleColors dragHandleColors = (DragHandleColors) other;
        return Color.m3135equalsimpl0(this.color, dragHandleColors.color) && Color.m3135equalsimpl0(this.pressedColor, dragHandleColors.pressedColor) && Color.m3135equalsimpl0(this.draggedColor, dragHandleColors.draggedColor);
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: getDraggedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDraggedColor() {
        return this.draggedColor;
    }

    /* JADX INFO: renamed from: getPressedColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getPressedColor() {
        return this.pressedColor;
    }

    public int hashCode() {
        return (((Color.m3141hashCodeimpl(this.color) * 31) + Color.m3141hashCodeimpl(this.pressedColor)) * 31) + Color.m3141hashCodeimpl(this.draggedColor);
    }

    public /* synthetic */ DragHandleColors(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }
}
