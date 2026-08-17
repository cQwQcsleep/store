package androidx.compose.material.icons.outlined;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mapsUgc", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MapsUgc", "Landroidx/compose/material/icons/Icons$Outlined;", "getMapsUgc", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MapsUgcKt {
    private static ImageVector _mapsUgc;

    public static final ImageVector getMapsUgc(Icons.Outlined outlined) {
        ImageVector imageVector = _mapsUgc;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MapsUgc", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 4.0f);
        pathBuilder.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilder.curveToRelative(-1.18f, 0.0f, -2.34f, -0.26f, -3.43f, -0.78f);
        pathBuilder.curveToRelative(-0.27f, -0.13f, -0.56f, -0.19f, -0.86f, -0.19f);
        pathBuilder.curveToRelative(-0.19f, 0.0f, -0.38f, 0.03f, -0.56f, 0.08f);
        pathBuilder.lineToRelative(-3.2f, 0.94f);
        pathBuilder.lineToRelative(0.94f, -3.2f);
        pathBuilder.curveToRelative(0.14f, -0.47f, 0.1f, -0.98f, -0.11f, -1.42f);
        pathBuilder.curveTo(4.26f, 14.34f, 4.0f, 13.18f, 4.0f, 12.0f);
        pathBuilder.curveTo(4.0f, 7.59f, 7.59f, 4.0f, 12.0f, 4.0f);
        pathBuilder.moveTo(12.0f, 2.0f);
        pathBuilder.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 1.54f, 0.36f, 2.98f, 0.97f, 4.29f);
        pathBuilder.lineTo(1.0f, 23.0f);
        pathBuilder.lineToRelative(6.71f, -1.97f);
        pathBuilder.curveTo(9.02f, 21.64f, 10.46f, 22.0f, 12.0f, 22.0f);
        pathBuilder.curveToRelative(5.52f, 0.0f, 10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilder.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilder.lineTo(12.0f, 2.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i3 = PathFillType.Companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i4 = companion2.getButt-KaPHkGw();
        int i5 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(13.0f, 8.0f);
        pathBuilder2.lineToRelative(-2.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, 3.0f);
        pathBuilder2.lineToRelative(-3.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, 2.0f);
        pathBuilder2.lineToRelative(3.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, 3.0f);
        pathBuilder2.lineToRelative(2.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, -3.0f);
        pathBuilder2.lineToRelative(3.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, -2.0f);
        pathBuilder2.lineToRelative(-3.0f, 0.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i3, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i4, i5, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _mapsUgc = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
