package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_appsOutage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AppsOutage", "Landroidx/compose/material/icons/Icons$Rounded;", "getAppsOutage", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppsOutageKt {
    private static ImageVector _appsOutage;

    public static final ImageVector getAppsOutage(Icons.Rounded rounded) {
        ImageVector imageVector = _appsOutage;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AppsOutage", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.0f, 8.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(7.1f, 4.0f, 6.0f, 4.0f);
        pathBuilder.reflectiveCurveTo(4.0f, 4.9f, 4.0f, 6.0f);
        pathBuilder.reflectiveCurveTo(4.9f, 8.0f, 6.0f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 20.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(10.9f, 20.0f, 12.0f, 20.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 20.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(4.9f, 20.0f, 6.0f, 20.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 14.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(4.9f, 14.0f, 6.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 14.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(10.9f, 14.0f, 12.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.07f, 4.0f);
        pathBuilder.curveTo(12.05f, 4.0f, 12.02f, 4.0f, 12.0f, 4.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.curveToRelative(0.22f, 0.0f, 0.43f, -0.04f, 0.63f, -0.1f);
        pathBuilder.curveTo(12.22f, 7.01f, 12.0f, 6.03f, 12.0f, 5.0f);
        pathBuilder.curveTo(12.0f, 4.66f, 12.02f, 4.33f, 12.07f, 4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 12.0f);
        pathBuilder.curveToRelative(-1.03f, 0.0f, -2.01f, -0.22f, -2.9f, -0.63f);
        pathBuilder.curveTo(16.04f, 11.57f, 16.0f, 11.78f, 16.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.curveToRelative(0.0f, -0.02f, 0.0f, -0.05f, 0.0f, -0.07f);
        pathBuilder.curveTo(19.67f, 11.98f, 19.34f, 12.0f, 19.0f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 20.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveTo(16.9f, 20.0f, 18.0f, 20.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 0.0f);
        pathBuilder.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilder.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilder.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilder.reflectiveCurveTo(21.76f, 0.0f, 19.0f, 0.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.5f, 7.5f);
        pathBuilder.curveTo(19.5f, 7.78f, 19.28f, 8.0f, 19.0f, 8.0f);
        pathBuilder.curveToRelative(-0.27f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.reflectiveCurveTo(18.72f, 7.0f, 19.0f, 7.0f);
        pathBuilder.reflectiveCurveTo(19.5f, 7.22f, 19.5f, 7.5f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 6.0f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.verticalLineToRelative(-3.0f);
        pathBuilder.curveTo(18.5f, 2.22f, 18.72f, 2.0f, 19.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.curveTo(19.5f, 5.78f, 19.28f, 6.0f, 19.0f, 6.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _appsOutage = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
