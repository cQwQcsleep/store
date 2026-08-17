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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatReclineExtra", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatReclineExtra", "Landroidx/compose/material/icons/Icons$Filled;", "getAirlineSeatReclineExtra", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatReclineExtraKt {
    private static ImageVector _airlineSeatReclineExtra;

    public static final ImageVector getAirlineSeatReclineExtra(Icons.Filled filled) {
        ImageVector imageVector = _airlineSeatReclineExtra;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AirlineSeatReclineExtra", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.35f, 5.64f);
        pathBuilder.curveToRelative(-0.9f, -0.64f, -1.12f, -1.88f, -0.49f, -2.79f);
        pathBuilder.curveToRelative(0.63f, -0.9f, 1.88f, -1.12f, 2.79f, -0.49f);
        pathBuilder.curveToRelative(0.9f, 0.64f, 1.12f, 1.88f, 0.49f, 2.79f);
        pathBuilder.curveToRelative(-0.64f, 0.9f, -1.88f, 1.12f, -2.79f, 0.49f);
        pathBuilder.close();
        pathBuilder.moveTo(16.0f, 19.0f);
        pathBuilder.lineTo(8.93f, 19.0f);
        pathBuilder.curveToRelative(-1.48f, 0.0f, -2.74f, -1.08f, -2.96f, -2.54f);
        pathBuilder.lineTo(4.0f, 7.0f);
        pathBuilder.lineTo(2.0f, 7.0f);
        pathBuilder.lineToRelative(1.99f, 9.76f);
        pathBuilder.curveTo(4.37f, 19.2f, 6.47f, 21.0f, 8.94f, 21.0f);
        pathBuilder.lineTo(16.0f, 21.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.23f, 15.0f);
        pathBuilder.horizontalLineToRelative(-4.88f);
        pathBuilder.lineToRelative(-1.03f, -4.1f);
        pathBuilder.curveToRelative(1.58f, 0.89f, 3.28f, 1.54f, 5.15f, 1.22f);
        pathBuilder.lineTo(15.47f, 9.99f);
        pathBuilder.curveToRelative(-1.63f, 0.31f, -3.44f, -0.27f, -4.69f, -1.25f);
        pathBuilder.lineTo(9.14f, 7.47f);
        pathBuilder.curveToRelative(-0.23f, -0.18f, -0.49f, -0.3f, -0.76f, -0.38f);
        pathBuilder.curveToRelative(-0.32f, -0.09f, -0.66f, -0.12f, -0.99f, -0.06f);
        pathBuilder.horizontalLineToRelative(-0.02f);
        pathBuilder.curveToRelative(-1.23f, 0.22f, -2.05f, 1.39f, -1.84f, 2.61f);
        pathBuilder.lineToRelative(1.35f, 5.92f);
        pathBuilder.curveTo(7.16f, 16.98f, 8.39f, 18.0f, 9.83f, 18.0f);
        pathBuilder.horizontalLineToRelative(6.85f);
        pathBuilder.lineToRelative(3.82f, 3.0f);
        pathBuilder.lineToRelative(1.5f, -1.5f);
        pathBuilder.lineToRelative(-5.77f, -4.5f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _airlineSeatReclineExtra = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
