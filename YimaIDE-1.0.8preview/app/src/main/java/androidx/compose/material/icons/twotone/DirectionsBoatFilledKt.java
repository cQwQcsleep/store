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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBoatFilled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBoatFilled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDirectionsBoatFilled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBoatFilledKt {
    private static ImageVector _directionsBoatFilled;

    public static final ImageVector getDirectionsBoatFilled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _directionsBoatFilled;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DirectionsBoatFilled", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.77f, 12.66f);
        pathBuilder.lineToRelative(-1.12f, 3.97f);
        pathBuilder.curveToRelative(-0.78f, -0.43f, -1.07f, -0.86f, -2.65f, -2.67f);
        pathBuilder.curveTo(14.4f, 15.78f, 13.57f, 17.0f, 12.0f, 17.0f);
        pathBuilder.curveToRelative(-1.53f, 0.0f, -2.34f, -1.15f, -4.0f, -3.04f);
        pathBuilder.curveToRelative(-1.6f, 1.82f, -1.87f, 2.21f, -2.65f, 2.65f);
        pathBuilder.lineToRelative(-1.13f, -3.96f);
        pathBuilder.lineTo(12.0f, 10.11f);
        pathBuilder.lineTo(19.77f, 12.66f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.77f, 12.66f);
        pathBuilder2.lineToRelative(-1.12f, 3.97f);
        pathBuilder2.curveToRelative(-0.78f, -0.43f, -1.07f, -0.86f, -2.65f, -2.67f);
        pathBuilder2.curveTo(14.4f, 15.78f, 13.57f, 17.0f, 12.0f, 17.0f);
        pathBuilder2.curveToRelative(-1.53f, 0.0f, -2.34f, -1.15f, -4.0f, -3.04f);
        pathBuilder2.curveToRelative(-1.6f, 1.82f, -1.87f, 2.21f, -2.65f, 2.65f);
        pathBuilder2.lineToRelative(-1.13f, -3.96f);
        pathBuilder2.lineTo(12.0f, 10.11f);
        pathBuilder2.lineTo(19.77f, 12.66f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.0f, 1.0f);
        pathBuilder2.horizontalLineTo(9.0f);
        pathBuilder2.verticalLineToRelative(3.0f);
        pathBuilder2.horizontalLineTo(6.0f);
        pathBuilder2.curveTo(4.9f, 4.0f, 4.0f, 4.9f, 4.0f, 6.0f);
        pathBuilder2.verticalLineToRelative(4.62f);
        pathBuilder2.lineToRelative(-1.29f, 0.42f);
        pathBuilder2.curveToRelative(-0.63f, 0.19f, -0.81f, 0.84f, -0.66f, 1.28f);
        pathBuilder2.lineTo(3.95f, 19.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.curveToRelative(1.6f, 0.0f, 3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilder2.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilder2.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(0.05f);
        pathBuilder2.lineToRelative(1.91f, -6.68f);
        pathBuilder2.curveToRelative(0.11f, -0.37f, 0.04f, -1.06f, -0.66f, -1.28f);
        pathBuilder2.lineTo(20.0f, 10.62f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.horizontalLineToRelative(-3.0f);
        pathBuilder2.verticalLineTo(1.0f);
        pathBuilder2.lineTo(15.0f, 1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.0f, 9.97f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineToRelative(3.97f);
        pathBuilder2.lineTo(12.0f, 8.0f);
        pathBuilder2.lineTo(6.0f, 9.97f);
        pathBuilder2.lineTo(6.0f, 9.97f);
        pathBuilder2.close();
        pathBuilder2.moveTo(16.0f, 19.68f);
        pathBuilder2.curveToRelative(-1.22f, 0.85f, -2.61f, 1.28f, -4.0f, 1.28f);
        pathBuilder2.reflectiveCurveToRelative(-2.78f, -0.43f, -4.0f, -1.28f);
        pathBuilder2.curveTo(6.78f, 20.53f, 5.39f, 21.0f, 4.0f, 21.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.curveToRelative(1.38f, 0.0f, 2.74f, -0.35f, 4.0f, -0.99f);
        pathBuilder2.curveToRelative(1.26f, 0.64f, 2.63f, 0.97f, 4.0f, 0.97f);
        pathBuilder2.reflectiveCurveToRelative(2.74f, -0.32f, 4.0f, -0.97f);
        pathBuilder2.curveToRelative(1.26f, 0.65f, 2.62f, 0.99f, 4.0f, 0.99f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.horizontalLineToRelative(-2.0f);
        pathBuilder2.curveTo(18.61f, 21.0f, 17.22f, 20.53f, 16.0f, 19.68f);
        pathBuilder2.lineTo(16.0f, 19.68f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _directionsBoatFilled = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
