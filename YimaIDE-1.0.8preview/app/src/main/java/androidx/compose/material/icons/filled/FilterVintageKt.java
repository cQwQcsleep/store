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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_filterVintage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FilterVintage", "Landroidx/compose/material/icons/Icons$Filled;", "getFilterVintage", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FilterVintageKt {
    private static ImageVector _filterVintage;

    public static final ImageVector getFilterVintage(Icons.Filled filled) {
        ImageVector imageVector = _filterVintage;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FilterVintage", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.7f, 12.4f);
        pathBuilder.curveToRelative(-0.28f, -0.16f, -0.57f, -0.29f, -0.86f, -0.4f);
        pathBuilder.curveToRelative(0.29f, -0.11f, 0.58f, -0.24f, 0.86f, -0.4f);
        pathBuilder.curveToRelative(1.92f, -1.11f, 2.99f, -3.12f, 3.0f, -5.19f);
        pathBuilder.curveToRelative(-1.79f, -1.03f, -4.07f, -1.11f, -6.0f, 0.0f);
        pathBuilder.curveToRelative(-0.28f, 0.16f, -0.54f, 0.35f, -0.78f, 0.54f);
        pathBuilder.curveToRelative(0.05f, -0.31f, 0.08f, -0.63f, 0.08f, -0.95f);
        pathBuilder.curveToRelative(0.0f, -2.22f, -1.21f, -4.15f, -3.0f, -5.19f);
        pathBuilder.curveTo(10.21f, 1.85f, 9.0f, 3.78f, 9.0f, 6.0f);
        pathBuilder.curveToRelative(0.0f, 0.32f, 0.03f, 0.64f, 0.08f, 0.95f);
        pathBuilder.curveToRelative(-0.24f, -0.2f, -0.5f, -0.39f, -0.78f, -0.55f);
        pathBuilder.curveToRelative(-1.92f, -1.11f, -4.2f, -1.03f, -6.0f, 0.0f);
        pathBuilder.curveToRelative(0.0f, 2.07f, 1.07f, 4.08f, 3.0f, 5.19f);
        pathBuilder.curveToRelative(0.28f, 0.16f, 0.57f, 0.29f, 0.86f, 0.4f);
        pathBuilder.curveToRelative(-0.29f, 0.11f, -0.58f, 0.24f, -0.86f, 0.4f);
        pathBuilder.curveToRelative(-1.92f, 1.11f, -2.99f, 3.12f, -3.0f, 5.19f);
        pathBuilder.curveToRelative(1.79f, 1.03f, 4.07f, 1.11f, 6.0f, 0.0f);
        pathBuilder.curveToRelative(0.28f, -0.16f, 0.54f, -0.35f, 0.78f, -0.54f);
        pathBuilder.curveToRelative(-0.05f, 0.32f, -0.08f, 0.64f, -0.08f, 0.96f);
        pathBuilder.curveToRelative(0.0f, 2.22f, 1.21f, 4.15f, 3.0f, 5.19f);
        pathBuilder.curveToRelative(1.79f, -1.04f, 3.0f, -2.97f, 3.0f, -5.19f);
        pathBuilder.curveToRelative(0.0f, -0.32f, -0.03f, -0.64f, -0.08f, -0.95f);
        pathBuilder.curveToRelative(0.24f, 0.2f, 0.5f, 0.38f, 0.78f, 0.54f);
        pathBuilder.curveToRelative(1.92f, 1.11f, 4.2f, 1.03f, 6.0f, 0.0f);
        pathBuilder.curveToRelative(-0.01f, -2.07f, -1.08f, -4.08f, -3.0f, -5.19f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 16.0f);
        pathBuilder.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilder.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilder.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilder.reflectiveCurveToRelative(-1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _filterVintage = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
