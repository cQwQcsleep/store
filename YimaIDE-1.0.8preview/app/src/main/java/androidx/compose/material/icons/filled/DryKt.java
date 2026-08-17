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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dry", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dry", "Landroidx/compose/material/icons/Icons$Filled;", "getDry", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DryKt {
    private static ImageVector _dry;

    public static final ImageVector getDry(Icons.Filled filled) {
        ImageVector imageVector = _dry;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Dry", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.65f, 4.86f);
        pathBuilder.lineToRelative(-0.07f, -0.07f);
        pathBuilder.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilder.lineTo(15.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(-1.89f);
        pathBuilder.lineToRelative(-0.06f, 0.43f);
        pathBuilder.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilder.lineToRelative(0.07f, 0.06f);
        pathBuilder.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilder.lineTo(14.98f, 9.0f);
        pathBuilder.horizontalLineToRelative(1.91f);
        pathBuilder.lineToRelative(0.06f, -0.43f);
        pathBuilder.curveTo(17.16f, 7.21f, 16.68f, 5.86f, 15.65f, 4.86f);
        pathBuilder.close();
        pathBuilder.moveTo(19.65f, 4.86f);
        pathBuilder.lineToRelative(-0.07f, -0.07f);
        pathBuilder.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilder.lineTo(19.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(-1.89f);
        pathBuilder.lineToRelative(-0.06f, 0.43f);
        pathBuilder.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilder.lineToRelative(0.07f, 0.06f);
        pathBuilder.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilder.lineTo(18.98f, 9.0f);
        pathBuilder.horizontalLineToRelative(1.91f);
        pathBuilder.lineToRelative(0.06f, -0.43f);
        pathBuilder.curveTo(21.16f, 7.21f, 20.68f, 5.86f, 19.65f, 4.86f);
        pathBuilder.close();
        pathBuilder.moveTo(9.12f, 5.0f);
        pathBuilder.lineToRelative(-7.18f, 6.79f);
        pathBuilder.curveTo(1.34f, 12.35f, 1.0f, 13.14f, 1.0f, 13.97f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilder.horizontalLineToRelative(6.25f);
        pathBuilder.horizontalLineTo(12.0f);
        pathBuilder.horizontalLineToRelative(5.75f);
        pathBuilder.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilder.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        pathBuilder.horizontalLineTo(12.0f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.horizontalLineToRelative(7.75f);
        pathBuilder.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilder.reflectiveCurveTo(20.44f, 17.0f, 19.75f, 17.0f);
        pathBuilder.horizontalLineTo(12.0f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.horizontalLineToRelative(8.75f);
        pathBuilder.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilder.reflectiveCurveToRelative(-0.56f, -1.25f, -1.25f, -1.25f);
        pathBuilder.horizontalLineTo(12.0f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.horizontalLineToRelative(6.75f);
        pathBuilder.curveToRelative(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilder.reflectiveCurveTo(19.44f, 10.0f, 18.75f, 10.0f);
        pathBuilder.horizontalLineTo(8.86f);
        pathBuilder.curveToRelative(0.64f, -1.11f, 1.48f, -2.58f, 1.49f, -2.61f);
        pathBuilder.curveToRelative(0.09f, -0.16f, 0.14f, -0.33f, 0.14f, -0.53f);
        pathBuilder.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilder.curveTo(10.22f, 6.12f, 9.12f, 5.0f, 9.12f, 5.0f);
        pathBuilder.lineTo(9.12f, 5.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _dry = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
