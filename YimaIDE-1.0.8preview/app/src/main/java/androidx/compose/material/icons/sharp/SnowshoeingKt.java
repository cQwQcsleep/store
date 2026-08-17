package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snowshoeing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snowshoeing", "Landroidx/compose/material/icons/Icons$Sharp;", "getSnowshoeing", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnowshoeingKt {
    private static ImageVector _snowshoeing;

    public static final ImageVector getSnowshoeing(Icons.Sharp sharp) {
        ImageVector imageVector = _snowshoeing;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Snowshoeing", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.5f, 3.5f);
        pathBuilder.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(12.5f, 4.6f, 12.5f, 3.5f);
        pathBuilder.close();
        pathBuilder.moveTo(6.32f, 19.03f);
        pathBuilder.lineToRelative(-1.14f, -1.47f);
        pathBuilder.lineTo(4.0f, 18.5f);
        pathBuilder.lineToRelative(2.38f, 3.04f);
        pathBuilder.curveToRelative(0.51f, 0.65f, 1.16f, 1.15f, 1.88f, 1.41f);
        pathBuilder.curveToRelative(0.28f, 0.1f, 0.53f, 0.04f, 0.72f, -0.11f);
        pathBuilder.curveToRelative(0.3f, -0.23f, 0.42f, -0.7f, 0.12f, -1.07f);
        pathBuilder.curveToRelative(-0.08f, -0.1f, -0.2f, -0.17f, -0.31f, -0.22f);
        pathBuilder.curveToRelative(-0.43f, -0.18f, -0.82f, -0.45f, -1.14f, -0.83f);
        pathBuilder.lineToRelative(-0.08f, -0.1f);
        pathBuilder.lineTo(11.0f, 18.2f);
        pathBuilder.lineToRelative(0.89f, -3.22f);
        pathBuilder.lineToRelative(2.11f, 2.0f);
        pathBuilder.verticalLineToRelative(4.52f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(23.0f);
        pathBuilder.horizontalLineToRelative(3.87f);
        pathBuilder.curveToRelative(0.82f, 0.0f, 1.61f, -0.21f, 2.26f, -0.61f);
        pathBuilder.curveToRelative(0.26f, -0.16f, 0.37f, -0.39f, 0.37f, -0.64f);
        pathBuilder.curveToRelative(0.0f, -0.38f, -0.3f, -0.75f, -0.77f, -0.75f);
        pathBuilder.curveToRelative(-0.13f, 0.0f, -0.26f, 0.04f, -0.37f, 0.1f);
        pathBuilder.curveToRelative(-0.4f, 0.23f, -0.87f, 0.37f, -1.36f, 0.4f);
        pathBuilder.lineToRelative(0.0f, -6.02f);
        pathBuilder.lineToRelative(-2.11f, -2.0f);
        pathBuilder.lineToRelative(0.6f, -3.0f);
        pathBuilder.curveTo(15.79f, 11.98f, 17.8f, 13.0f, 20.0f, 13.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.curveToRelative(-1.9f, 0.0f, -3.51f, -1.02f, -4.31f, -2.42f);
        pathBuilder.lineToRelative(-1.0f, -1.58f);
        pathBuilder.curveToRelative(-0.4f, -0.6f, -1.0f, -1.0f, -1.7f, -1.0f);
        pathBuilder.curveTo(12.24f, 6.0f, 11.58f, 6.34f, 7.0f, 8.28f);
        pathBuilder.verticalLineTo(13.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineTo(9.58f);
        pathBuilder.lineToRelative(1.79f, -0.7f);
        pathBuilder.lineTo(9.2f, 17.0f);
        pathBuilder.lineTo(6.32f, 19.03f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _snowshoeing = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
