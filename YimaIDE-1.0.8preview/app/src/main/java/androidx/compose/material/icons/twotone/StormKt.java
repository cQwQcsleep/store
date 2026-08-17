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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_storm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Storm", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStorm", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StormKt {
    private static ImageVector _storm;

    public static final ImageVector getStorm(Icons.TwoTone twoTone) {
        ImageVector imageVector = _storm;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Storm", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 12.0f);
        pathBuilder.moveToRelative(-2.0f, 0.0f);
        pathBuilder.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilder.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(17.2f, 9.0f);
        pathBuilder2.curveTo(15.54f, 6.13f, 11.86f, 5.15f, 9.0f, 6.8f);
        pathBuilder2.curveToRelative(-2.67f, 1.54f, -3.7f, 4.84f, -2.5f, 7.6f);
        pathBuilder2.curveToRelative(0.09f, 0.2f, 0.19f, 0.4f, 0.3f, 0.6f);
        pathBuilder2.curveToRelative(1.66f, 2.87f, 5.33f, 3.85f, 8.2f, 2.2f);
        pathBuilder2.curveToRelative(2.67f, -1.54f, 3.7f, -4.84f, 2.5f, -7.6f);
        pathBuilder2.curveTo(17.41f, 9.4f, 17.31f, 9.2f, 17.2f, 9.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 16.0f);
        pathBuilder2.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilder2.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilder2.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilder2.reflectiveCurveTo(14.21f, 16.0f, 12.0f, 16.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(12.0f, 8.0f);
        pathBuilder3.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilder3.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilder3.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder3.reflectiveCurveTo(14.21f, 8.0f, 12.0f, 8.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(12.0f, 14.0f);
        pathBuilder3.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder3.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder3.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder3.reflectiveCurveTo(13.1f, 14.0f, 12.0f, 14.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(18.93f, 8.0f);
        pathBuilder4.curveTo(16.72f, 4.18f, 11.82f, 2.87f, 8.0f, 5.07f);
        pathBuilder4.curveToRelative(-1.41f, 0.82f, -2.48f, 2.0f, -3.16f, 3.37f);
        pathBuilder4.curveTo(4.71f, 6.24f, 5.06f, 4.04f, 5.86f, 2.0f);
        pathBuilder4.horizontalLineTo(3.74f);
        pathBuilder4.curveTo(2.2f, 6.49f, 2.52f, 11.58f, 5.07f, 16.0f);
        pathBuilder4.curveToRelative(1.1f, 1.91f, 2.88f, 3.19f, 4.86f, 3.72f);
        pathBuilder4.curveToRelative(1.98f, 0.53f, 4.16f, 0.31f, 6.07f, -0.79f);
        pathBuilder4.curveToRelative(1.41f, -0.82f, 2.48f, -2.0f, 3.16f, -3.37f);
        pathBuilder4.curveToRelative(0.13f, 2.2f, -0.21f, 4.4f, -1.01f, 6.44f);
        pathBuilder4.horizontalLineToRelative(2.11f);
        pathBuilder4.curveTo(21.79f, 17.51f, 21.48f, 12.42f, 18.93f, 8.0f);
        pathBuilder4.close();
        pathBuilder4.moveTo(15.0f, 17.2f);
        pathBuilder4.curveToRelative(-2.87f, 1.65f, -6.54f, 0.67f, -8.2f, -2.2f);
        pathBuilder4.curveToRelative(-0.11f, -0.2f, -0.21f, -0.4f, -0.3f, -0.6f);
        pathBuilder4.curveTo(5.3f, 11.64f, 6.33f, 8.34f, 9.0f, 6.8f);
        pathBuilder4.curveToRelative(2.86f, -1.65f, 6.54f, -0.67f, 8.2f, 2.2f);
        pathBuilder4.curveToRelative(0.11f, 0.2f, 0.21f, 0.4f, 0.3f, 0.6f);
        pathBuilder4.curveTo(18.7f, 12.36f, 17.67f, 15.66f, 15.0f, 17.2f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _storm = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
