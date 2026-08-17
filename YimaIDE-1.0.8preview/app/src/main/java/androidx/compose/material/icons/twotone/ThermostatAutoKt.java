package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thermostatAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThermostatAuto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getThermostatAuto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThermostatAutoKt {
    private static ImageVector _thermostatAuto;

    public static final ImageVector getThermostatAuto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _thermostatAuto;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ThermostatAuto", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.8f, 13.6f);
        pathBuilder.lineTo(9.0f, 13.0f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.reflectiveCurveTo(7.0f, 5.45f, 7.0f, 6.0f);
        pathBuilder.verticalLineToRelative(7.0f);
        pathBuilder.lineToRelative(-0.8f, 0.6f);
        pathBuilder.curveTo(5.45f, 14.16f, 5.0f, 15.06f, 5.0f, 16.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.curveTo(11.0f, 15.06f, 10.55f, 14.17f, 9.8f, 13.6f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(11.0f, 12.0f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilder2.reflectiveCurveTo(5.0f, 4.34f, 5.0f, 6.0f);
        pathBuilder2.verticalLineToRelative(6.0f);
        pathBuilder2.curveToRelative(-1.21f, 0.91f, -2.0f, 2.37f, -2.0f, 4.0f);
        pathBuilder2.curveToRelative(0.0f, 1.12f, 0.38f, 2.14f, 1.0f, 2.97f);
        pathBuilder2.verticalLineTo(19.0f);
        pathBuilder2.horizontalLineToRelative(0.02f);
        pathBuilder2.curveToRelative(0.91f, 1.21f, 2.35f, 2.0f, 3.98f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(3.06f, -0.79f, 3.98f, -2.0f);
        pathBuilder2.horizontalLineTo(12.0f);
        pathBuilder2.verticalLineToRelative(-0.03f);
        pathBuilder2.curveToRelative(0.62f, -0.83f, 1.0f, -1.85f, 1.0f, -2.97f);
        pathBuilder2.curveTo(13.0f, 14.37f, 12.21f, 12.91f, 11.0f, 12.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(5.0f, 16.0f);
        pathBuilder2.curveToRelative(0.0f, -0.94f, 0.45f, -1.84f, 1.2f, -2.4f);
        pathBuilder2.lineTo(7.0f, 13.0f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilder2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder2.verticalLineToRelative(7.0f);
        pathBuilder2.lineToRelative(0.8f, 0.6f);
        pathBuilder2.curveToRelative(0.75f, 0.57f, 1.2f, 1.46f, 1.2f, 2.4f);
        pathBuilder2.horizontalLineTo(5.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.62f, 4.0f);
        pathBuilder2.horizontalLineToRelative(-1.61f);
        pathBuilder2.lineToRelative(-3.38f, 9.0f);
        pathBuilder2.horizontalLineToRelative(1.56f);
        pathBuilder2.lineTo(16.0f, 10.7f);
        pathBuilder2.horizontalLineToRelative(3.63f);
        pathBuilder2.lineToRelative(0.8f, 2.3f);
        pathBuilder2.horizontalLineTo(22.0f);
        pathBuilder2.lineTo(18.62f, 4.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(16.47f, 9.39f);
        pathBuilder2.lineToRelative(1.31f, -3.72f);
        pathBuilder2.horizontalLineToRelative(0.08f);
        pathBuilder2.lineToRelative(1.31f, 3.72f);
        pathBuilder2.horizontalLineTo(16.47f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _thermostatAuto = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
