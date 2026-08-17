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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stadium", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stadium", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStadium", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StadiumKt {
    private static ImageVector _stadium;

    public static final ImageVector getStadium(Icons.TwoTone twoTone) {
        ImageVector imageVector = _stadium;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Stadium", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.0f, 10.04f);
        pathBuilder.curveTo(6.38f, 10.53f, 8.77f, 11.0f, 12.0f, 11.0f);
        pathBuilder.reflectiveCurveToRelative(5.62f, -0.47f, 7.0f, -0.96f);
        pathBuilder.curveTo(19.0f, 9.86f, 16.22f, 9.0f, 12.0f, 9.0f);
        pathBuilder.reflectiveCurveTo(5.0f, 9.86f, 5.0f, 10.04f);
        pathBuilder.close();
        pathBuilder.moveTo(20.0f, 11.8f);
        pathBuilder.curveToRelative(-1.82f, 0.73f, -4.73f, 1.2f, -8.0f, 1.2f);
        pathBuilder.reflectiveCurveToRelative(-6.18f, -0.47f, -8.0f, -1.2f);
        pathBuilder.verticalLineToRelative(6.78f);
        pathBuilder.curveToRelative(0.61f, 0.41f, 2.36f, 1.01f, 5.0f, 1.28f);
        pathBuilder.verticalLineTo(16.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.verticalLineToRelative(3.86f);
        pathBuilder.curveToRelative(2.64f, -0.27f, 4.39f, -0.87f, 5.0f, -1.28f);
        pathBuilder.verticalLineTo(11.8f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(7.0f, 5.0f);
        pathBuilder2.lineTo(3.0f, 7.0f);
        pathBuilder2.verticalLineTo(3.0f);
        pathBuilder2.lineTo(7.0f, 5.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 3.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.lineToRelative(4.0f, -2.0f);
        pathBuilder2.lineTo(18.0f, 3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(11.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.lineToRelative(4.0f, -2.0f);
        pathBuilder2.lineTo(11.0f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.0f, 18.0f);
        pathBuilder2.horizontalLineToRelative(-2.0f);
        pathBuilder2.lineToRelative(0.0f, 4.0f);
        pathBuilder2.curveToRelative(-5.05f, -0.15f, -9.0f, -1.44f, -9.0f, -3.0f);
        pathBuilder2.verticalLineToRelative(-9.0f);
        pathBuilder2.curveToRelative(0.0f, -1.66f, 4.48f, -3.0f, 10.0f, -3.0f);
        pathBuilder2.reflectiveCurveToRelative(10.0f, 1.34f, 10.0f, 3.0f);
        pathBuilder2.verticalLineToRelative(9.0f);
        pathBuilder2.curveToRelative(0.0f, 1.56f, -3.95f, 2.85f, -9.0f, 3.0f);
        pathBuilder2.lineTo(13.0f, 18.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(5.0f, 10.04f);
        pathBuilder2.curveTo(6.38f, 10.53f, 8.77f, 11.0f, 12.0f, 11.0f);
        pathBuilder2.reflectiveCurveToRelative(5.62f, -0.47f, 7.0f, -0.96f);
        pathBuilder2.curveTo(19.0f, 9.86f, 16.22f, 9.0f, 12.0f, 9.0f);
        pathBuilder2.reflectiveCurveTo(5.0f, 9.86f, 5.0f, 10.04f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 11.8f);
        pathBuilder2.curveToRelative(-1.82f, 0.73f, -4.73f, 1.2f, -8.0f, 1.2f);
        pathBuilder2.reflectiveCurveToRelative(-6.18f, -0.47f, -8.0f, -1.2f);
        pathBuilder2.verticalLineToRelative(6.78f);
        pathBuilder2.curveToRelative(0.61f, 0.41f, 2.36f, 1.01f, 5.0f, 1.28f);
        pathBuilder2.verticalLineTo(16.0f);
        pathBuilder2.horizontalLineToRelative(6.0f);
        pathBuilder2.verticalLineToRelative(3.86f);
        pathBuilder2.curveToRelative(2.64f, -0.27f, 4.39f, -0.87f, 5.0f, -1.28f);
        pathBuilder2.verticalLineTo(11.8f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _stadium = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
