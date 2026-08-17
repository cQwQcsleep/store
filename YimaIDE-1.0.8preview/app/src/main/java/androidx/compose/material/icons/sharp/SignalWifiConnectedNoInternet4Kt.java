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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signalWifiConnectedNoInternet4", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SignalWifiConnectedNoInternet4", "Landroidx/compose/material/icons/Icons$Sharp;", "getSignalWifiConnectedNoInternet4", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignalWifiConnectedNoInternet4Kt {
    private static ImageVector _signalWifiConnectedNoInternet4;

    public static final ImageVector getSignalWifiConnectedNoInternet4(Icons.Sharp sharp) {
        ImageVector imageVector = _signalWifiConnectedNoInternet4;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SignalWifiConnectedNoInternet4", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(24.0f, 8.98f);
        pathBuilder.curveTo(20.93f, 5.9f, 16.69f, 4.0f, 12.0f, 4.0f);
        pathBuilder.curveTo(7.31f, 4.0f, 3.07f, 5.9f, 0.0f, 8.98f);
        pathBuilder.lineTo(12.0f, 21.0f);
        pathBuilder.verticalLineToRelative(-9.0f);
        pathBuilder.horizontalLineToRelative(8.99f);
        pathBuilder.lineTo(24.0f, 8.98f);
        pathBuilder.close();
        pathBuilder.moveTo(19.59f, 14.0f);
        pathBuilder.lineToRelative(-2.09f, 2.09f);
        pathBuilder.lineToRelative(-0.3f, -0.3f);
        pathBuilder.lineTo(15.41f, 14.0f);
        pathBuilder.lineTo(14.0f, 15.41f);
        pathBuilder.lineToRelative(1.79f, 1.79f);
        pathBuilder.lineToRelative(0.3f, 0.3f);
        pathBuilder.lineTo(14.0f, 19.59f);
        pathBuilder.lineTo(15.41f, 21.0f);
        pathBuilder.lineToRelative(2.09f, -2.08f);
        pathBuilder.lineTo(19.59f, 21.0f);
        pathBuilder.lineTo(21.0f, 19.59f);
        pathBuilder.lineToRelative(-2.08f, -2.09f);
        pathBuilder.lineTo(21.0f, 15.41f);
        pathBuilder.lineTo(19.59f, 14.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _signalWifiConnectedNoInternet4 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
