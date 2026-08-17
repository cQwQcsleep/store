package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pix", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pix", "Landroidx/compose/material/icons/Icons$Sharp;", "getPix", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PixKt {
    private static ImageVector _pix;

    public static final ImageVector getPix(Icons.Sharp sharp) {
        ImageVector imageVector = _pix;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Pix", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.45f, 16.52f);
        pathBuilder.lineToRelative(-3.01f, -3.01f);
        pathBuilder.curveToRelative(-0.11f, -0.11f, -0.24f, -0.13f, -0.31f, -0.13f);
        pathBuilder.reflectiveCurveToRelative(-0.2f, 0.02f, -0.31f, 0.13f);
        pathBuilder.lineTo(8.8f, 16.53f);
        pathBuilder.curveToRelative(-0.34f, 0.34f, -0.87f, 0.89f, -2.64f, 0.89f);
        pathBuilder.lineToRelative(3.71f, 3.7f);
        pathBuilder.curveToRelative(1.17f, 1.17f, 3.07f, 1.17f, 4.24f, 0.0f);
        pathBuilder.lineToRelative(3.72f, -3.71f);
        pathBuilder.curveTo(16.92f, 17.41f, 16.16f, 17.23f, 15.45f, 16.52f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(8.8f, 7.47f);
        pathBuilder2.lineToRelative(3.02f, 3.02f);
        pathBuilder2.curveToRelative(0.08f, 0.08f, 0.2f, 0.13f, 0.31f, 0.13f);
        pathBuilder2.reflectiveCurveToRelative(0.23f, -0.05f, 0.31f, -0.13f);
        pathBuilder2.lineToRelative(2.99f, -2.99f);
        pathBuilder2.curveToRelative(0.71f, -0.74f, 1.52f, -0.91f, 2.43f, -0.91f);
        pathBuilder2.lineToRelative(-3.72f, -3.71f);
        pathBuilder2.curveToRelative(-1.17f, -1.17f, -3.07f, -1.17f, -4.24f, 0.0f);
        pathBuilder2.lineToRelative(-3.71f, 3.7f);
        pathBuilder2.curveTo(7.95f, 6.58f, 8.49f, 7.16f, 8.8f, 7.47f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(21.11f, 9.85f);
        pathBuilder3.lineToRelative(-2.25f, -2.26f);
        pathBuilder3.horizontalLineTo(17.6f);
        pathBuilder3.curveToRelative(-0.54f, 0.0f, -1.08f, 0.22f, -1.45f, 0.61f);
        pathBuilder3.lineToRelative(-3.0f, 3.0f);
        pathBuilder3.curveToRelative(-0.28f, 0.28f, -0.65f, 0.42f, -1.02f, 0.42f);
        pathBuilder3.curveToRelative(-0.36f, 0.0f, -0.74f, -0.15f, -1.02f, -0.42f);
        pathBuilder3.lineTo(8.09f, 8.17f);
        pathBuilder3.curveToRelative(-0.38f, -0.38f, -0.9f, -0.6f, -1.45f, -0.6f);
        pathBuilder3.horizontalLineTo(5.17f);
        pathBuilder3.lineToRelative(-2.29f, 2.3f);
        pathBuilder3.curveToRelative(-1.17f, 1.17f, -1.17f, 3.07f, 0.0f, 4.24f);
        pathBuilder3.lineToRelative(2.29f, 2.3f);
        pathBuilder3.horizontalLineToRelative(1.48f);
        pathBuilder3.curveToRelative(0.54f, 0.0f, 1.06f, -0.22f, 1.45f, -0.6f);
        pathBuilder3.lineToRelative(3.02f, -3.02f);
        pathBuilder3.curveToRelative(0.28f, -0.28f, 0.65f, -0.42f, 1.02f, -0.42f);
        pathBuilder3.curveToRelative(0.37f, 0.0f, 0.74f, 0.14f, 1.02f, 0.42f);
        pathBuilder3.lineToRelative(3.01f, 3.01f);
        pathBuilder3.curveToRelative(0.38f, 0.38f, 0.9f, 0.6f, 1.45f, 0.6f);
        pathBuilder3.horizontalLineToRelative(1.26f);
        pathBuilder3.lineToRelative(2.25f, -2.26f);
        pathBuilder3.curveTo(22.3f, 12.96f, 22.3f, 11.04f, 21.11f, 9.85f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _pix = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
