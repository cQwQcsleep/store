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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutSmall", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutSmall", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDonutSmall", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutSmallKt {
    private static ImageVector _donutSmall;

    public static final ImageVector getDonutSmall(Icons.TwoTone twoTone) {
        ImageVector imageVector = _donutSmall;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DonutSmall", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.99f, 9.0f);
        pathBuilder.horizontalLineToRelative(3.43f);
        pathBuilder.curveTo(18.6f, 7.0f, 17.0f, 5.4f, 15.0f, 4.58f);
        pathBuilder.verticalLineToRelative(3.43f);
        pathBuilder.curveToRelative(0.37f, 0.28f, 0.71f, 0.62f, 0.99f, 0.99f);
        pathBuilder.close();
        pathBuilder.moveTo(4.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 3.35f, 2.04f, 6.24f, 5.0f, 7.42f);
        pathBuilder.verticalLineToRelative(-3.44f);
        pathBuilder.curveToRelative(-1.23f, -0.93f, -2.0f, -2.4f, -2.0f, -3.99f);
        pathBuilder.curveTo(7.0f, 10.4f, 7.77f, 8.93f, 9.0f, 8.0f);
        pathBuilder.lineTo(9.0f, 4.58f);
        pathBuilder.curveTo(6.04f, 5.76f, 4.0f, 8.65f, 4.0f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 15.99f);
        pathBuilder.verticalLineToRelative(3.43f);
        pathBuilder.curveToRelative(2.0f, -0.82f, 3.6f, -2.42f, 4.42f, -4.42f);
        pathBuilder.horizontalLineToRelative(-3.43f);
        pathBuilder.curveToRelative(-0.28f, 0.37f, -0.62f, 0.71f, -0.99f, 0.99f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.82f, 11.0f);
        pathBuilder2.horizontalLineToRelative(7.13f);
        pathBuilder2.curveToRelative(-0.47f, -4.72f, -4.23f, -8.48f, -8.95f, -8.95f);
        pathBuilder2.verticalLineToRelative(7.13f);
        pathBuilder2.curveToRelative(0.85f, 0.31f, 1.51f, 0.97f, 1.82f, 1.82f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.0f, 4.58f);
        pathBuilder2.curveTo(17.0f, 5.4f, 18.6f, 7.0f, 19.42f, 9.0f);
        pathBuilder2.horizontalLineToRelative(-3.43f);
        pathBuilder2.curveToRelative(-0.28f, -0.37f, -0.62f, -0.71f, -0.99f, -0.99f);
        pathBuilder2.lineTo(15.0f, 4.58f);
        pathBuilder2.close();
        pathBuilder2.moveTo(2.0f, 12.0f);
        pathBuilder2.curveToRelative(0.0f, 5.19f, 3.95f, 9.45f, 9.0f, 9.95f);
        pathBuilder2.verticalLineToRelative(-7.13f);
        pathBuilder2.curveTo(9.84f, 14.4f, 9.0f, 13.3f, 9.0f, 12.0f);
        pathBuilder2.curveToRelative(0.0f, -1.3f, 0.84f, -2.4f, 2.0f, -2.82f);
        pathBuilder2.lineTo(11.0f, 2.05f);
        pathBuilder2.curveToRelative(-5.05f, 0.5f, -9.0f, 4.76f, -9.0f, 9.95f);
        pathBuilder2.close();
        pathBuilder2.moveTo(9.0f, 4.58f);
        pathBuilder2.verticalLineToRelative(3.44f);
        pathBuilder2.curveToRelative(-1.23f, 0.92f, -2.0f, 2.39f, -2.0f, 3.98f);
        pathBuilder2.curveToRelative(0.0f, 1.59f, 0.77f, 3.06f, 2.0f, 3.99f);
        pathBuilder2.verticalLineToRelative(3.44f);
        pathBuilder2.curveTo(6.04f, 18.24f, 4.0f, 15.35f, 4.0f, 12.0f);
        pathBuilder2.curveToRelative(0.0f, -3.35f, 2.04f, -6.24f, 5.0f, -7.42f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.0f, 14.82f);
        pathBuilder2.verticalLineToRelative(7.13f);
        pathBuilder2.curveToRelative(4.72f, -0.47f, 8.48f, -4.23f, 8.95f, -8.95f);
        pathBuilder2.horizontalLineToRelative(-7.13f);
        pathBuilder2.curveToRelative(-0.31f, 0.85f, -0.97f, 1.51f, -1.82f, 1.82f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.0f, 15.99f);
        pathBuilder2.curveToRelative(0.37f, -0.28f, 0.71f, -0.61f, 0.99f, -0.99f);
        pathBuilder2.horizontalLineToRelative(3.43f);
        pathBuilder2.curveTo(18.6f, 17.0f, 17.0f, 18.6f, 15.0f, 19.42f);
        pathBuilder2.verticalLineToRelative(-3.43f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _donutSmall = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
