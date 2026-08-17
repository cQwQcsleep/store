package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaWideAngle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaWideAngle", "Landroidx/compose/material/icons/Icons$Filled;", "getPanoramaWideAngle", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaWideAngleKt {
    private static ImageVector _panoramaWideAngle;

    public static final ImageVector getPanoramaWideAngle(Icons.Filled filled) {
        ImageVector imageVector = _panoramaWideAngle;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PanoramaWideAngle", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 6.0f);
        pathBuilder.curveToRelative(2.45f, 0.0f, 4.71f, 0.2f, 7.29f, 0.64f);
        pathBuilder.curveToRelative(0.47f, 1.78f, 0.71f, 3.58f, 0.71f, 5.36f);
        pathBuilder.curveToRelative(0.0f, 1.78f, -0.24f, 3.58f, -0.71f, 5.36f);
        pathBuilder.curveToRelative(-2.58f, 0.44f, -4.84f, 0.64f, -7.29f, 0.64f);
        pathBuilder.reflectiveCurveToRelative(-4.71f, -0.2f, -7.29f, -0.64f);
        pathBuilder.curveTo(4.24f, 15.58f, 4.0f, 13.78f, 4.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -1.78f, 0.24f, -3.58f, 0.71f, -5.36f);
        pathBuilder.curveTo(7.29f, 6.2f, 9.55f, 6.0f, 12.0f, 6.0f);
        pathBuilder.moveToRelative(0.0f, -2.0f);
        pathBuilder.curveToRelative(-2.73f, 0.0f, -5.22f, 0.24f, -7.95f, 0.72f);
        pathBuilder.lineToRelative(-0.93f, 0.16f);
        pathBuilder.lineToRelative(-0.25f, 0.9f);
        pathBuilder.curveTo(2.29f, 7.85f, 2.0f, 9.93f, 2.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.29f, 4.15f, 0.87f, 6.22f);
        pathBuilder.lineToRelative(0.25f, 0.89f);
        pathBuilder.lineToRelative(0.93f, 0.16f);
        pathBuilder.curveToRelative(2.73f, 0.49f, 5.22f, 0.73f, 7.95f, 0.73f);
        pathBuilder.reflectiveCurveToRelative(5.22f, -0.24f, 7.95f, -0.72f);
        pathBuilder.lineToRelative(0.93f, -0.16f);
        pathBuilder.lineToRelative(0.25f, -0.89f);
        pathBuilder.curveToRelative(0.58f, -2.08f, 0.87f, -4.16f, 0.87f, -6.23f);
        pathBuilder.reflectiveCurveToRelative(-0.29f, -4.15f, -0.87f, -6.22f);
        pathBuilder.lineToRelative(-0.25f, -0.89f);
        pathBuilder.lineToRelative(-0.93f, -0.16f);
        pathBuilder.curveTo(17.22f, 4.24f, 14.73f, 4.0f, 12.0f, 4.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _panoramaWideAngle = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
