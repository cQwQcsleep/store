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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stroller", "Landroidx/compose/material/icons/Icons$Outlined;", "getStroller", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrollerKt {
    private static ImageVector _stroller;

    public static final ImageVector getStroller(Icons.Outlined outlined) {
        ImageVector imageVector = _stroller;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Stroller", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.0f, 20.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(18.0f, 18.9f, 18.0f, 20.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 18.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(7.1f, 18.0f, 6.0f, 18.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 8.66f);
        pathBuilder.lineTo(9.6f, 15.0f);
        pathBuilder.horizontalLineTo(15.0f);
        pathBuilder.verticalLineTo(8.66f);
        pathBuilder.moveTo(18.65f, 3.0f);
        pathBuilder.curveTo(20.52f, 3.0f, 22.0f, 4.56f, 22.0f, 6.48f);
        pathBuilder.verticalLineTo(7.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(6.48f);
        pathBuilder.curveTo(20.0f, 5.66f, 19.42f, 5.0f, 18.65f, 5.0f);
        pathBuilder.curveTo(17.97f, 5.0f, 17.58f, 5.59f, 17.0f, 6.27f);
        pathBuilder.verticalLineTo(15.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.horizontalLineTo(7.43f);
        pathBuilder.curveToRelative(-0.85f, 0.0f, -1.31f, -1.0f, -0.76f, -1.65f);
        pathBuilder.lineToRelative(8.8f, -10.32f);
        pathBuilder.curveTo(16.11f, 4.27f, 16.99f, 3.0f, 18.65f, 3.0f);
        pathBuilder.lineTo(18.65f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(10.0f, 5.0f);
        pathBuilder.curveTo(9.35f, 5.0f, 8.71f, 5.09f, 8.09f, 5.27f);
        pathBuilder.lineToRelative(1.4f, 1.4f);
        pathBuilder.lineToRelative(1.37f, -1.61f);
        pathBuilder.curveTo(10.58f, 5.02f, 10.29f, 5.0f, 10.0f, 5.0f);
        pathBuilder.moveTo(10.0f, 3.0f);
        pathBuilder.curveToRelative(1.56f, 0.0f, 3.03f, 0.4f, 4.3f, 1.1f);
        pathBuilder.lineToRelative(-4.7f, 5.51f);
        pathBuilder.lineTo(4.72f, 4.72f);
        pathBuilder.curveTo(6.21f, 3.64f, 8.03f, 3.0f, 10.0f, 3.0f);
        pathBuilder.lineTo(10.0f, 3.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _stroller = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
