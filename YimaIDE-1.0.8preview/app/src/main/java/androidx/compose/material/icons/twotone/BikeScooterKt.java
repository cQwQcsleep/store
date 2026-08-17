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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bikeScooter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BikeScooter", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBikeScooter", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BikeScooterKt {
    private static ImageVector _bikeScooter;

    public static final ImageVector getBikeScooter(Icons.TwoTone twoTone) {
        ImageVector imageVector = _bikeScooter;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.BikeScooter", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(10.0f, 14.0f);
        pathBuilder.horizontalLineToRelative(0.74f);
        pathBuilder.lineTo(8.82f, 5.56f);
        pathBuilder.curveTo(8.61f, 4.65f, 7.8f, 4.0f, 6.87f, 4.0f);
        pathBuilder.horizontalLineTo(3.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(3.87f);
        pathBuilder.lineToRelative(1.42f, 6.25f);
        pathBuilder.curveToRelative(0.0f, 0.0f, -0.01f, 0.0f, -0.01f, 0.0f);
        pathBuilder.curveTo(6.12f, 12.9f, 4.47f, 14.73f, 4.09f, 17.0f);
        pathBuilder.horizontalLineTo(0.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.curveTo(6.0f, 15.79f, 7.79f, 14.0f, 10.0f, 14.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.0f, 8.0f);
        pathBuilder2.horizontalLineToRelative(-0.82f);
        pathBuilder2.lineToRelative(-1.35f, -3.69f);
        pathBuilder2.curveTo(16.55f, 3.52f, 15.8f, 3.0f, 14.96f, 3.0f);
        pathBuilder2.horizontalLineTo(11.0f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.horizontalLineToRelative(3.96f);
        pathBuilder2.lineToRelative(1.1f, 3.0f);
        pathBuilder2.horizontalLineTo(10.4f);
        pathBuilder2.lineToRelative(0.46f, 2.0f);
        pathBuilder2.horizontalLineTo(15.0f);
        pathBuilder2.curveToRelative(-0.43f, 0.58f, -0.75f, 1.25f, -0.9f, 2.0f);
        pathBuilder2.horizontalLineToRelative(-2.79f);
        pathBuilder2.lineToRelative(0.46f, 2.0f);
        pathBuilder2.horizontalLineToRelative(2.33f);
        pathBuilder2.curveToRelative(0.44f, 2.23f, 2.31f, 3.88f, 4.65f, 3.99f);
        pathBuilder2.curveToRelative(2.8f, 0.13f, 5.25f, -2.19f, 5.25f, -5.0f);
        pathBuilder2.curveTo(24.0f, 10.2f, 21.8f, 8.0f, 19.0f, 8.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(19.0f, 16.0f);
        pathBuilder2.curveToRelative(-1.68f, 0.0f, -3.0f, -1.32f, -3.0f, -3.0f);
        pathBuilder2.curveToRelative(0.0f, -0.93f, 0.41f, -1.73f, 1.05f, -2.28f);
        pathBuilder2.lineToRelative(0.96f, 2.64f);
        pathBuilder2.lineToRelative(1.88f, -0.68f);
        pathBuilder2.lineToRelative(-0.97f, -2.67f);
        pathBuilder2.curveToRelative(0.03f, 0.0f, 0.06f, -0.01f, 0.09f, -0.01f);
        pathBuilder2.curveToRelative(1.68f, 0.0f, 3.0f, 1.32f, 3.0f, 3.0f);
        pathBuilder2.reflectiveCurveTo(20.68f, 16.0f, 19.0f, 16.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(10.0f, 15.0f);
        pathBuilder3.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilder3.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilder3.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilder3.reflectiveCurveTo(11.66f, 15.0f, 10.0f, 15.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(10.0f, 19.0f);
        pathBuilder3.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilder3.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilder3.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder3.reflectiveCurveTo(10.55f, 19.0f, 10.0f, 19.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _bikeScooter = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
