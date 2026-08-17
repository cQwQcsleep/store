package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/TooltipCaretShape;", "Landroidx/compose/ui/graphics/Shape;", "transformationMatrix", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/Matrix;", "tooltipShape", "caretShape", "<init>", "(Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;)V", "tooltipPath", "Landroidx/compose/ui/graphics/Path;", "getTooltipPath", "()Landroidx/compose/ui/graphics/Path;", "combinedPath", "getCombinedPath", "caretPath", "getCaretPath", "createOutline", "Landroidx/compose/ui/graphics/Outline;", "size", "Landroidx/compose/ui/geometry/Size;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "createOutline-Pq9zytI", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Landroidx/compose/ui/graphics/Outline;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class TooltipCaretShape implements Shape {
    private final Shape caretShape;
    private final Shape tooltipShape;
    private final MutableState<Matrix> transformationMatrix;
    private final Path tooltipPath = AndroidPath_androidKt.Path();
    private final Path combinedPath = AndroidPath_androidKt.Path();
    private final Path caretPath = AndroidPath_androidKt.Path();

    public TooltipCaretShape(MutableState<Matrix> mutableState, Shape shape, Shape shape2) {
        this.transformationMatrix = mutableState;
        this.tooltipShape = shape;
        this.caretShape = shape2;
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* JADX INFO: renamed from: createOutline-Pq9zytI */
    public Outline mo44createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
        this.tooltipPath.reset();
        this.combinedPath.reset();
        this.caretPath.reset();
        Outline outlineMo44createOutlinePq9zytI = this.tooltipShape.mo44createOutlinePq9zytI(size, layoutDirection, density);
        Outline outlineMo44createOutlinePq9zytI2 = this.caretShape.mo44createOutlinePq9zytI(size, layoutDirection, density);
        if (outlineMo44createOutlinePq9zytI instanceof Outline.Generic) {
            Path.m3418addPathUv8p0NA$default(this.tooltipPath, ((Outline.Generic) outlineMo44createOutlinePq9zytI).getPath(), 0L, 2, null);
        } else if (outlineMo44createOutlinePq9zytI instanceof Outline.Rounded) {
            Path.addRoundRect$default(this.tooltipPath, ((Outline.Rounded) outlineMo44createOutlinePq9zytI).getRoundRect(), null, 2, null);
        } else {
            if (!(outlineMo44createOutlinePq9zytI instanceof Outline.Rectangle)) {
                bu8.a();
                return null;
            }
            Path.addRect$default(this.tooltipPath, ((Outline.Rectangle) outlineMo44createOutlinePq9zytI).getRect(), null, 2, null);
        }
        if (outlineMo44createOutlinePq9zytI2 instanceof Outline.Generic) {
            Path.m3418addPathUv8p0NA$default(this.caretPath, ((Outline.Generic) outlineMo44createOutlinePq9zytI2).getPath(), 0L, 2, null);
        } else if (outlineMo44createOutlinePq9zytI2 instanceof Outline.Rounded) {
            Path.addRoundRect$default(this.caretPath, ((Outline.Rounded) outlineMo44createOutlinePq9zytI2).getRoundRect(), null, 2, null);
        } else {
            if (!(outlineMo44createOutlinePq9zytI2 instanceof Outline.Rectangle)) {
                bu8.a();
                return null;
            }
            Path.addRect$default(this.caretPath, ((Outline.Rectangle) outlineMo44createOutlinePq9zytI2).getRect(), null, 2, null);
        }
        this.caretPath.mo3025transform58bKbWc(this.transformationMatrix.getValue().m3401unboximpl());
        this.combinedPath.mo3023opN5in7k0(this.tooltipPath, this.caretPath, PathOperation.INSTANCE.m3443getUnionb3I0S0c());
        return new Outline.Generic(this.combinedPath);
    }

    public final Path getCaretPath() {
        return this.caretPath;
    }

    public final Path getCombinedPath() {
        return this.combinedPath;
    }

    public final Path getTooltipPath() {
        return this.tooltipPath;
    }
}
