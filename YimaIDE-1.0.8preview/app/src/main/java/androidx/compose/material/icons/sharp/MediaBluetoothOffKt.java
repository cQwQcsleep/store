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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mediaBluetoothOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MediaBluetoothOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getMediaBluetoothOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MediaBluetoothOffKt {
    private static ImageVector _mediaBluetoothOff;

    public static final ImageVector getMediaBluetoothOff(Icons.Sharp sharp) {
        ImageVector imageVector = _mediaBluetoothOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MediaBluetoothOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 6.17f);
        pathBuilder.verticalLineTo(3.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.verticalLineToRelative(1.17f);
        pathBuilder.lineTo(9.0f, 6.17f);
        pathBuilder.close();
        pathBuilder.moveTo(19.42f, 15.0f);
        pathBuilder.lineTo(22.0f, 17.57f);
        pathBuilder.lineToRelative(-0.8f, 0.8f);
        pathBuilder.lineToRelative(-6.78f, -6.78f);
        pathBuilder.lineToRelative(0.8f, -0.8f);
        pathBuilder.lineToRelative(2.75f, 2.75f);
        pathBuilder.verticalLineTo(9.0f);
        pathBuilder.horizontalLineToRelative(0.6f);
        pathBuilder.lineTo(22.0f, 12.43f);
        pathBuilder.lineTo(19.42f, 15.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.17f, 13.55f);
        pathBuilder.lineToRelative(1.13f, -1.13f);
        pathBuilder.lineToRelative(-1.13f, -1.13f);
        pathBuilder.verticalLineTo(13.55f);
        pathBuilder.close();
        pathBuilder.moveTo(17.21f, 17.21f);
        pathBuilder.lineToRelative(3.98f, 3.98f);
        pathBuilder.lineToRelative(-1.41f, 1.41f);
        pathBuilder.lineToRelative(-3.98f, -3.98f);
        pathBuilder.lineToRelative(-0.58f, 0.58f);
        pathBuilder.lineToRelative(-0.85f, -0.85f);
        pathBuilder.lineToRelative(0.58f, -0.58f);
        pathBuilder.lineTo(11.0f, 13.83f);
        pathBuilder.verticalLineTo(17.0f);
        pathBuilder.curveToRelative(0.0f, 2.21f, -1.78f, 4.0f, -3.99f, 4.0f);
        pathBuilder.reflectiveCurveTo(3.0f, 19.21f, 3.0f, 17.0f);
        pathBuilder.reflectiveCurveToRelative(1.79f, -4.0f, 4.01f, -4.0f);
        pathBuilder.curveToRelative(0.73f, 0.0f, 1.41f, 0.21f, 2.0f, 0.55f);
        pathBuilder.verticalLineToRelative(-1.72f);
        pathBuilder.lineTo(1.39f, 4.22f);
        pathBuilder.lineTo(2.8f, 2.81f);
        pathBuilder.lineToRelative(13.56f, 13.56f);
        pathBuilder.lineTo(17.21f, 17.21f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _mediaBluetoothOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
