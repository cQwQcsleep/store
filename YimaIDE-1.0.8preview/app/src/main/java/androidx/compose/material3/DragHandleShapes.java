package androidx.compose.material3;

import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/DragHandleShapes;", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "pressedShape", "draggedShape", "<init>", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;)V", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "getPressedShape", "getDraggedShape", "equals", "", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DragHandleShapes {
    public static final int $stable = 0;
    private final Shape draggedShape;
    private final Shape pressedShape;
    private final Shape shape;

    public DragHandleShapes(Shape shape, Shape shape2, Shape shape3) {
        this.shape = shape;
        this.pressedShape = shape2;
        this.draggedShape = shape3;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof DragHandleShapes)) {
            return false;
        }
        DragHandleShapes dragHandleShapes = (DragHandleShapes) other;
        return Intrinsics.areEqual(this.shape, dragHandleShapes.shape) && Intrinsics.areEqual(this.pressedShape, dragHandleShapes.pressedShape) && Intrinsics.areEqual(this.draggedShape, dragHandleShapes.draggedShape);
    }

    public final Shape getDraggedShape() {
        return this.draggedShape;
    }

    public final Shape getPressedShape() {
        return this.pressedShape;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public int hashCode() {
        return (((this.shape.hashCode() * 31) + this.pressedShape.hashCode()) * 31) + this.draggedShape.hashCode();
    }
}
