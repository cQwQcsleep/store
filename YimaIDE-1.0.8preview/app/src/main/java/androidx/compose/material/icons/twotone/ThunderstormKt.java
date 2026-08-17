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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thunderstorm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Thunderstorm", "Landroidx/compose/material/icons/Icons$TwoTone;", "getThunderstorm", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThunderstormKt {
    private static ImageVector _thunderstorm;

    public static final ImageVector getThunderstorm(Icons.TwoTone twoTone) {
        ImageVector imageVector = _thunderstorm;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Thunderstorm", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.73f, 9.01f);
        pathBuilder.lineTo(16.2f, 8.87f);
        pathBuilder.lineToRelative(-0.25f, -1.52f);
        pathBuilder.curveTo(15.63f, 5.44f, 13.94f, 4.0f, 12.0f, 4.0f);
        pathBuilder.curveToRelative(-1.44f, 0.0f, -2.77f, 0.78f, -3.48f, 2.04f);
        pathBuilder.lineTo(8.03f, 6.91f);
        pathBuilder.lineTo(7.04f, 7.04f);
        pathBuilder.curveTo(5.31f, 7.27f, 4.0f, 8.76f, 4.0f, 10.5f);
        pathBuilder.curveTo(4.0f, 12.43f, 5.57f, 14.0f, 7.5f, 14.0f);
        pathBuilder.horizontalLineToRelative(10.0f);
        pathBuilder.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilder.curveTo(20.0f, 10.22f, 19.01f, 9.13f, 17.73f, 9.01f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(17.92f, 7.02f);
        pathBuilder2.curveTo(17.45f, 4.18f, 14.97f, 2.0f, 12.0f, 2.0f);
        pathBuilder2.curveTo(9.82f, 2.0f, 7.83f, 3.18f, 6.78f, 5.06f);
        pathBuilder2.curveTo(4.09f, 5.41f, 2.0f, 7.74f, 2.0f, 10.5f);
        pathBuilder2.curveTo(2.0f, 13.53f, 4.47f, 16.0f, 7.5f, 16.0f);
        pathBuilder2.horizontalLineToRelative(10.0f);
        pathBuilder2.curveToRelative(2.48f, 0.0f, 4.5f, -2.02f, 4.5f, -4.5f);
        pathBuilder2.curveTo(22.0f, 9.16f, 20.21f, 7.23f, 17.92f, 7.02f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.5f, 14.0f);
        pathBuilder2.horizontalLineToRelative(-10.0f);
        pathBuilder2.curveTo(5.57f, 14.0f, 4.0f, 12.43f, 4.0f, 10.5f);
        pathBuilder2.curveToRelative(0.0f, -1.74f, 1.31f, -3.23f, 3.04f, -3.46f);
        pathBuilder2.lineToRelative(0.99f, -0.13f);
        pathBuilder2.lineToRelative(0.49f, -0.87f);
        pathBuilder2.curveTo(9.23f, 4.78f, 10.56f, 4.0f, 12.0f, 4.0f);
        pathBuilder2.curveToRelative(1.94f, 0.0f, 3.63f, 1.44f, 3.95f, 3.35f);
        pathBuilder2.lineToRelative(0.25f, 1.52f);
        pathBuilder2.lineToRelative(1.54f, 0.14f);
        pathBuilder2.curveTo(19.01f, 9.13f, 20.0f, 10.22f, 20.0f, 11.5f);
        pathBuilder2.curveTo(20.0f, 12.88f, 18.88f, 14.0f, 17.5f, 14.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(14.8f, 17.0f);
        pathBuilder3.lineToRelative(-2.9f, 3.32f);
        pathBuilder3.lineToRelative(2.0f, 1.0f);
        pathBuilder3.lineToRelative(-2.35f, 2.68f);
        pathBuilder3.lineToRelative(2.65f, 0.0f);
        pathBuilder3.lineToRelative(2.9f, -3.32f);
        pathBuilder3.lineToRelative(-2.0f, -1.0f);
        pathBuilder3.lineToRelative(2.35f, -2.68f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(8.8f, 17.0f);
        pathBuilder4.lineToRelative(-2.9f, 3.32f);
        pathBuilder4.lineToRelative(2.0f, 1.0f);
        pathBuilder4.lineToRelative(-2.35f, 2.68f);
        pathBuilder4.lineToRelative(2.65f, 0.0f);
        pathBuilder4.lineToRelative(2.9f, -3.32f);
        pathBuilder4.lineToRelative(-2.0f, -1.0f);
        pathBuilder4.lineToRelative(2.35f, -2.68f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _thunderstorm = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
