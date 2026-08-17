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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rocket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rocket", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRocket", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RocketKt {
    private static ImageVector _rocket;

    public static final ImageVector getRocket(Icons.TwoTone twoTone) {
        ImageVector imageVector = _rocket;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Rocket", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.98f, 18.25f);
        pathBuilder.curveToRelative(-0.29f, -0.9f, -0.57f, -1.94f, -0.76f, -3.0f);
        pathBuilder.lineTo(6.0f, 16.07f);
        pathBuilder.verticalLineToRelative(2.98f);
        pathBuilder.lineTo(7.98f, 18.25f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 4.36f);
        pathBuilder.curveToRelative(0.0f, 0.0f, -3.0f, 2.02f, -3.0f, 8.64f);
        pathBuilder.curveToRelative(0.0f, 2.25f, 1.0f, 5.0f, 1.0f, 5.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 1.0f, -2.75f, 1.0f, -5.0f);
        pathBuilder.curveTo(15.0f, 6.38f, 12.0f, 4.36f, 12.0f, 4.36f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 13.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder.curveTo(14.0f, 12.1f, 13.1f, 13.0f, 12.0f, 13.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 19.05f);
        pathBuilder.verticalLineToRelative(-2.98f);
        pathBuilder.lineToRelative(-1.22f, -0.81f);
        pathBuilder.curveToRelative(-0.19f, 1.05f, -0.47f, 2.1f, -0.76f, 3.0f);
        pathBuilder.lineTo(18.0f, 19.05f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.0f, 11.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.reflectiveCurveTo(14.0f, 12.1f, 14.0f, 11.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(7.98f, 18.25f);
        pathBuilder2.curveToRelative(-0.29f, -0.9f, -0.57f, -1.94f, -0.76f, -3.0f);
        pathBuilder2.lineTo(6.0f, 16.07f);
        pathBuilder2.verticalLineToRelative(2.98f);
        pathBuilder2.lineTo(7.98f, 18.25f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 2.0f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 5.0f, 2.0f, 5.0f, 11.0f);
        pathBuilder2.lineToRelative(2.11f, 1.41f);
        pathBuilder2.curveToRelative(0.56f, 0.37f, 0.89f, 1.0f, 0.89f, 1.66f);
        pathBuilder2.verticalLineTo(22.0f);
        pathBuilder2.lineToRelative(-5.0f, -2.0f);
        pathBuilder2.horizontalLineTo(9.0f);
        pathBuilder2.lineToRelative(-5.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(-5.93f);
        pathBuilder2.curveToRelative(0.0f, -0.67f, 0.33f, -1.29f, 0.89f, -1.66f);
        pathBuilder2.lineTo(7.0f, 13.0f);
        pathBuilder2.curveTo(7.0f, 4.0f, 12.0f, 2.0f, 12.0f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 4.36f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, -3.0f, 2.02f, -3.0f, 8.64f);
        pathBuilder2.curveToRelative(0.0f, 2.25f, 1.0f, 5.0f, 1.0f, 5.0f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 1.0f, -2.75f, 1.0f, -5.0f);
        pathBuilder2.curveTo(15.0f, 6.38f, 12.0f, 4.36f, 12.0f, 4.36f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 19.05f);
        pathBuilder2.verticalLineToRelative(-2.98f);
        pathBuilder2.lineToRelative(-1.22f, -0.81f);
        pathBuilder2.curveToRelative(-0.19f, 1.05f, -0.47f, 2.1f, -0.76f, 3.0f);
        pathBuilder2.lineTo(18.0f, 19.05f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _rocket = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
