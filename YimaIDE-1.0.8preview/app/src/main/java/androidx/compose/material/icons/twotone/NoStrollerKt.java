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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noStroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoStroller", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNoStroller", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoStrollerKt {
    private static ImageVector _noStroller;

    public static final ImageVector getNoStroller(Icons.TwoTone twoTone) {
        ImageVector imageVector = _noStroller;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NoStroller", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.1f, 5.27f);
        pathBuilder.curveTo(8.71f, 5.1f, 9.35f, 5.0f, 10.0f, 5.0f);
        pathBuilder.curveToRelative(0.29f, 0.0f, 0.58f, 0.02f, 0.86f, 0.05f);
        pathBuilder.lineTo(9.49f, 6.67f);
        pathBuilder.lineTo(8.1f, 5.27f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 12.17f);
        pathBuilder.verticalLineTo(8.66f);
        pathBuilder.lineToRelative(-1.61f, 1.89f);
        pathBuilder.lineTo(15.0f, 12.17f);
        pathBuilder.close();
        pathBuilder.moveTo(12.17f, 15.0f);
        pathBuilder.lineToRelative(-1.39f, -1.39f);
        pathBuilder.lineTo(9.6f, 15.0f);
        pathBuilder.horizontalLineTo(12.17f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(8.0f, 20.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder2.reflectiveCurveTo(8.0f, 18.9f, 8.0f, 20.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.0f, 8.66f);
        pathBuilder2.verticalLineToRelative(3.51f);
        pathBuilder2.lineToRelative(2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(-7.9f);
        pathBuilder2.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilder2.curveTo(19.42f, 5.0f, 20.0f, 5.66f, 20.0f, 6.48f);
        pathBuilder2.verticalLineTo(7.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineTo(6.48f);
        pathBuilder2.curveTo(22.0f, 4.56f, 20.52f, 3.0f, 18.65f, 3.0f);
        pathBuilder2.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilder2.lineToRelative(-3.5f, 4.11f);
        pathBuilder2.lineToRelative(1.42f, 1.42f);
        pathBuilder2.lineTo(15.0f, 8.66f);
        pathBuilder2.close();
        pathBuilder2.moveTo(19.78f, 22.61f);
        pathBuilder2.lineToRelative(-1.91f, -1.91f);
        pathBuilder2.curveTo(17.58f, 21.46f, 16.86f, 22.0f, 16.0f, 22.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder2.curveToRelative(0.0f, -0.86f, 0.54f, -1.58f, 1.3f, -1.87f);
        pathBuilder2.lineTo(14.17f, 17.0f);
        pathBuilder2.horizontalLineTo(7.43f);
        pathBuilder2.curveToRelative(-0.85f, 0.0f, -1.31f, -1.0f, -0.76f, -1.65f);
        pathBuilder2.lineToRelative(2.69f, -3.16f);
        pathBuilder2.lineTo(1.39f, 4.22f);
        pathBuilder2.lineToRelative(1.41f, -1.41f);
        pathBuilder2.lineToRelative(7.86f, 7.86f);
        pathBuilder2.lineToRelative(1.42f, 1.42f);
        pathBuilder2.lineToRelative(0.0f, 0.0f);
        pathBuilder2.lineToRelative(9.11f, 9.11f);
        pathBuilder2.lineTo(19.78f, 22.61f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.17f, 15.0f);
        pathBuilder2.lineToRelative(-1.39f, -1.39f);
        pathBuilder2.lineTo(9.6f, 15.0f);
        pathBuilder2.horizontalLineTo(12.17f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.0f, 5.0f);
        pathBuilder2.curveToRelative(0.29f, 0.0f, 0.58f, 0.02f, 0.86f, 0.05f);
        pathBuilder2.lineTo(9.49f, 6.67f);
        pathBuilder2.lineToRelative(1.42f, 1.42f);
        pathBuilder2.lineTo(14.3f, 4.1f);
        pathBuilder2.curveTo(13.03f, 3.4f, 11.56f, 3.0f, 10.0f, 3.0f);
        pathBuilder2.curveTo(8.77f, 3.0f, 7.6f, 3.25f, 6.53f, 3.7f);
        pathBuilder2.lineTo(8.1f, 5.27f);
        pathBuilder2.curveTo(8.71f, 5.1f, 9.35f, 5.0f, 10.0f, 5.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _noStroller = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
