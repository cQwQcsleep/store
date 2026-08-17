package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lunchDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LunchDining", "Landroidx/compose/material/icons/Icons$Filled;", "getLunchDining", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LunchDiningKt {
    private static ImageVector _lunchDining;

    public static final ImageVector getLunchDining(Icons.Filled filled) {
        ImageVector imageVector = _lunchDining;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LunchDining", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        PathFillType.Companion companion = PathFillType.Companion;
        int i = companion.getEvenOdd-Rg-k1Os();
        Color.Companion companion2 = Color.Companion;
        SolidColor solidColor = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion3 = StrokeCap.Companion;
        int i2 = companion3.getButt-KaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.Companion;
        int i3 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(22.0f, 10.0f);
        pathBuilder.curveToRelative(0.32f, -3.28f, -4.28f, -6.0f, -9.99f, -6.0f);
        pathBuilder.curveTo(6.3f, 4.0f, 1.7f, 6.72f, 2.02f, 10.0f);
        pathBuilder.horizontalLineTo(22.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), i, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i2, i3, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i4 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion3.getButt-KaPHkGw();
        int i6 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(5.35f, 13.5f);
        pathBuilder2.curveToRelative(0.55f, 0.0f, 0.78f, 0.14f, 1.15f, 0.36f);
        pathBuilder2.curveToRelative(0.45f, 0.27f, 1.07f, 0.64f, 2.18f, 0.64f);
        pathBuilder2.reflectiveCurveToRelative(1.73f, -0.37f, 2.18f, -0.64f);
        pathBuilder2.curveToRelative(0.37f, -0.23f, 0.59f, -0.36f, 1.15f, -0.36f);
        pathBuilder2.curveToRelative(0.55f, 0.0f, 0.78f, 0.14f, 1.15f, 0.36f);
        pathBuilder2.curveToRelative(0.45f, 0.27f, 1.07f, 0.64f, 2.18f, 0.64f);
        pathBuilder2.curveToRelative(1.11f, 0.0f, 1.73f, -0.37f, 2.18f, -0.64f);
        pathBuilder2.curveToRelative(0.37f, -0.23f, 0.59f, -0.36f, 1.15f, -0.36f);
        pathBuilder2.curveToRelative(0.55f, 0.0f, 0.78f, 0.14f, 1.15f, 0.36f);
        pathBuilder2.curveToRelative(0.45f, 0.27f, 1.07f, 0.63f, 2.17f, 0.64f);
        pathBuilder2.verticalLineToRelative(-1.98f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, -0.79f, -0.16f, -1.16f, -0.38f);
        pathBuilder2.curveToRelative(-0.45f, -0.27f, -1.07f, -0.64f, -2.18f, -0.64f);
        pathBuilder2.curveToRelative(-1.11f, 0.0f, -1.73f, 0.37f, -2.18f, 0.64f);
        pathBuilder2.curveToRelative(-0.37f, 0.23f, -0.6f, 0.36f, -1.15f, 0.36f);
        pathBuilder2.reflectiveCurveToRelative(-0.78f, -0.14f, -1.15f, -0.36f);
        pathBuilder2.curveToRelative(-0.45f, -0.27f, -1.07f, -0.64f, -2.18f, -0.64f);
        pathBuilder2.reflectiveCurveToRelative(-1.73f, 0.37f, -2.18f, 0.64f);
        pathBuilder2.curveToRelative(-0.37f, 0.23f, -0.59f, 0.36f, -1.15f, 0.36f);
        pathBuilder2.curveToRelative(-0.55f, 0.0f, -0.78f, -0.14f, -1.15f, -0.36f);
        pathBuilder2.curveToRelative(-0.45f, -0.27f, -1.07f, -0.64f, -2.18f, -0.64f);
        pathBuilder2.curveToRelative(-1.11f, 0.0f, -1.73f, 0.37f, -2.18f, 0.64f);
        pathBuilder2.curveTo(2.78f, 12.37f, 2.56f, 12.5f, 2.0f, 12.5f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.curveToRelative(1.11f, 0.0f, 1.73f, -0.37f, 2.21f, -0.64f);
        pathBuilder2.curveTo(4.58f, 13.63f, 4.8f, 13.5f, 5.35f, 13.5f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i4, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i7 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor3 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i8 = companion3.getButt-KaPHkGw();
        int i9 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(2.0f, 16.0f);
        pathBuilder3.verticalLineToRelative(2.0f);
        pathBuilder3.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder3.horizontalLineToRelative(16.0f);
        pathBuilder3.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder3.verticalLineToRelative(-2.0f);
        pathBuilder3.horizontalLineTo(2.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), i7, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i8, i9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _lunchDining = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
