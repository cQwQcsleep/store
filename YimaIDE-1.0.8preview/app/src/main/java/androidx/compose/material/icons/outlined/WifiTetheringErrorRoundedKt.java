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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiTetheringErrorRounded", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiTetheringErrorRounded", "Landroidx/compose/material/icons/Icons$Outlined;", "getWifiTetheringErrorRounded", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiTetheringErrorRoundedKt {
    private static ImageVector _wifiTetheringErrorRounded;

    public static final ImageVector getWifiTetheringErrorRounded(Icons.Outlined outlined) {
        ImageVector imageVector = _wifiTetheringErrorRounded;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.WifiTetheringErrorRounded", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 7.0f);
        pathBuilder.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilder.curveToRelative(0.0f, 1.66f, 0.68f, 3.15f, 1.76f, 4.24f);
        pathBuilder.lineToRelative(1.42f, -1.42f);
        pathBuilder.curveTo(8.45f, 15.1f, 8.0f, 14.11f, 8.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilder.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilder.curveToRelative(0.0f, 1.11f, -0.45f, 2.1f, -1.18f, 2.82f);
        pathBuilder.lineToRelative(1.42f, 1.42f);
        pathBuilder.curveTo(17.32f, 16.15f, 18.0f, 14.66f, 18.0f, 13.0f);
        pathBuilder.curveTo(18.0f, 9.69f, 15.31f, 7.0f, 12.0f, 7.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 3.0f);
        pathBuilder.curveTo(6.48f, 3.0f, 2.0f, 7.48f, 2.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, 2.76f, 1.12f, 5.26f, 2.93f, 7.07f);
        pathBuilder.lineToRelative(1.42f, -1.42f);
        pathBuilder.curveTo(4.9f, 17.21f, 4.0f, 15.21f, 4.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, -4.42f, 3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilder.curveToRelative(2.53f, 0.0f, 4.78f, 1.17f, 6.24f, 3.0f);
        pathBuilder.horizontalLineToRelative(2.42f);
        pathBuilder.curveTo(18.93f, 5.01f, 15.7f, 3.0f, 12.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 11.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.23f, 1.05f, 0.59f, 1.41f);
        pathBuilder.curveTo(10.95f, 14.77f, 11.45f, 15.0f, 12.0f, 15.0f);
        pathBuilder.reflectiveCurveToRelative(1.05f, -0.23f, 1.41f, -0.59f);
        pathBuilder.curveTo(13.77f, 14.05f, 14.0f, 13.55f, 14.0f, 13.0f);
        pathBuilder.curveTo(14.0f, 11.9f, 13.1f, 11.0f, 12.0f, 11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(20.0f, 10.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(6.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(20.0f, 18.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(18.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _wifiTetheringErrorRounded = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
