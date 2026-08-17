package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forward5", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Forward5", "Landroidx/compose/material/icons/Icons$Filled;", "getForward5", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Forward5Kt {
    private static ImageVector _forward5;

    public static final ImageVector getForward5(Icons.Filled filled) {
        ImageVector imageVector = _forward5;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Forward5", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilder.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.lineToRelative(5.0f, -5.0f);
        pathBuilder.lineToRelative(-5.0f, -5.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilder.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilder.curveToRelative(4.42f, 0.0f, 8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilder.horizontalLineTo(18.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.03f, 15.38f);
        pathBuilder2.curveToRelative(-0.44f, 0.0f, -0.58f, -0.31f, -0.6f, -0.56f);
        pathBuilder2.horizontalLineToRelative(-0.84f);
        pathBuilder2.curveToRelative(0.03f, 0.85f, 0.79f, 1.25f, 1.44f, 1.25f);
        pathBuilder2.curveToRelative(0.93f, 0.0f, 1.44f, -0.63f, 1.44f, -1.43f);
        pathBuilder2.curveToRelative(0.0f, -1.33f, -0.97f, -1.44f, -1.3f, -1.44f);
        pathBuilder2.curveToRelative(-0.2f, 0.0f, -0.43f, 0.05f, -0.64f, 0.16f);
        pathBuilder2.lineToRelative(0.11f, -0.92f);
        pathBuilder2.horizontalLineToRelative(1.7f);
        pathBuilder2.verticalLineToRelative(-0.71f);
        pathBuilder2.horizontalLineToRelative(-2.39f);
        pathBuilder2.lineToRelative(-0.25f, 2.17f);
        pathBuilder2.lineToRelative(0.67f, 0.17f);
        pathBuilder2.curveToRelative(0.13f, -0.13f, 0.28f, -0.23f, 0.57f, -0.23f);
        pathBuilder2.curveToRelative(0.4f, 0.0f, 0.69f, 0.23f, 0.69f, 0.75f);
        pathBuilder2.curveTo(12.62f, 14.64f, 12.65f, 15.38f, 12.03f, 15.38f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _forward5 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
