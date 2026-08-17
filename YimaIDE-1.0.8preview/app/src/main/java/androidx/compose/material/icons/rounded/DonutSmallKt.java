package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutSmall", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutSmall", "Landroidx/compose/material/icons/Icons$Rounded;", "getDonutSmall", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutSmallKt {
    private static ImageVector _donutSmall;

    public static final ImageVector getDonutSmall(Icons.Rounded rounded) {
        ImageVector imageVector = _donutSmall;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DonutSmall", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.0f, 3.18f);
        pathBuilder.verticalLineToRelative(17.64f);
        pathBuilder.curveToRelative(0.0f, 0.64f, -0.59f, 1.12f, -1.21f, 0.98f);
        pathBuilder.curveTo(5.32f, 20.8f, 2.0f, 16.79f, 2.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(3.32f, -8.8f, 7.79f, -9.8f);
        pathBuilder.curveToRelative(0.62f, -0.14f, 1.21f, 0.34f, 1.21f, 0.98f);
        pathBuilder.close();
        pathBuilder.moveTo(13.03f, 3.18f);
        pathBuilder.verticalLineToRelative(6.81f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(6.79f);
        pathBuilder.curveToRelative(0.64f, 0.0f, 1.12f, -0.59f, 0.98f, -1.22f);
        pathBuilder.curveToRelative(-0.85f, -3.76f, -3.8f, -6.72f, -7.55f, -7.57f);
        pathBuilder.curveToRelative(-0.63f, -0.14f, -1.22f, 0.34f, -1.22f, 0.98f);
        pathBuilder.close();
        pathBuilder.moveTo(13.03f, 14.01f);
        pathBuilder.verticalLineToRelative(6.81f);
        pathBuilder.curveToRelative(0.0f, 0.64f, 0.59f, 1.12f, 1.22f, 0.98f);
        pathBuilder.curveToRelative(3.76f, -0.85f, 6.71f, -3.82f, 7.56f, -7.58f);
        pathBuilder.curveToRelative(0.14f, -0.62f, -0.35f, -1.22f, -0.98f, -1.22f);
        pathBuilder.horizontalLineToRelative(-6.79f);
        pathBuilder.curveToRelative(-0.56f, 0.01f, -1.01f, 0.46f, -1.01f, 1.01f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _donutSmall = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
