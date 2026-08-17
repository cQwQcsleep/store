package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_energySavingsLeaf", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EnergySavingsLeaf", "Landroidx/compose/material/icons/Icons$Outlined;", "getEnergySavingsLeaf", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnergySavingsLeafKt {
    private static ImageVector _energySavingsLeaf;

    public static final ImageVector getEnergySavingsLeaf(Icons.Outlined outlined) {
        ImageVector imageVector = _energySavingsLeaf;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.EnergySavingsLeaf", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 3.0f);
        pathBuilder.curveTo(12.0f, 3.0f, 12.0f, 3.0f, 12.0f, 3.0f);
        pathBuilder.curveToRelative(-4.8f, 0.0f, -9.0f, 3.86f, -9.0f, 9.0f);
        pathBuilder.curveToRelative(0.0f, 2.12f, 0.74f, 4.07f, 1.97f, 5.61f);
        pathBuilder.lineTo(3.0f, 19.59f);
        pathBuilder.lineTo(4.41f, 21.0f);
        pathBuilder.lineToRelative(1.97f, -1.97f);
        pathBuilder.curveTo(7.93f, 20.26f, 9.88f, 21.0f, 12.0f, 21.0f);
        pathBuilder.curveToRelative(2.3f, 0.0f, 4.61f, -0.88f, 6.36f, -2.64f);
        pathBuilder.curveTo(20.12f, 16.61f, 21.0f, 14.3f, 21.0f, 12.0f);
        pathBuilder.lineToRelative(0.0f, -9.0f);
        pathBuilder.lineTo(12.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 1.87f, -0.73f, 3.63f, -2.05f, 4.95f);
        pathBuilder.curveTo(15.63f, 18.27f, 13.87f, 19.0f, 12.0f, 19.0f);
        pathBuilder.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilder.curveToRelative(0.0f, -1.9f, 0.74f, -3.68f, 2.1f, -4.99f);
        pathBuilder.curveTo(8.42f, 5.71f, 10.16f, 5.0f, 12.0f, 5.0f);
        pathBuilder.lineToRelative(7.0f, 0.0f);
        pathBuilder.lineTo(19.0f, 12.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(8.46f, 12.63f);
        pathBuilder2.lineToRelative(4.05f, 0.4f);
        pathBuilder2.lineToRelative(-2.44f, 3.33f);
        pathBuilder2.curveToRelative(-0.11f, 0.16f, -0.1f, 0.38f, 0.04f, 0.52f);
        pathBuilder2.curveToRelative(0.15f, 0.15f, 0.4f, 0.16f, 0.56f, 0.01f);
        pathBuilder2.lineToRelative(5.16f, -4.63f);
        pathBuilder2.curveToRelative(0.33f, -0.3f, 0.15f, -0.85f, -0.3f, -0.89f);
        pathBuilder2.lineToRelative(-4.05f, -0.4f);
        pathBuilder2.lineToRelative(2.44f, -3.33f);
        pathBuilder2.curveToRelative(0.11f, -0.16f, 0.1f, -0.38f, -0.04f, -0.52f);
        pathBuilder2.curveToRelative(-0.15f, -0.15f, -0.4f, -0.16f, -0.56f, -0.01f);
        pathBuilder2.lineToRelative(-5.16f, 4.63f);
        pathBuilder2.curveTo(7.84f, 12.04f, 8.02f, 12.59f, 8.46f, 12.63f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _energySavingsLeaf = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
