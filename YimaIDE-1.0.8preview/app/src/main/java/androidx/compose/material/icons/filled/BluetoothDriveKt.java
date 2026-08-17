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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bluetoothDrive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BluetoothDrive", "Landroidx/compose/material/icons/Icons$Filled;", "getBluetoothDrive", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BluetoothDriveKt {
    private static ImageVector _bluetoothDrive;

    public static final ImageVector getBluetoothDrive(Icons.Filled filled) {
        ImageVector imageVector = _bluetoothDrive;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BluetoothDrive", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.0f, 10.0f);
        pathBuilder.horizontalLineTo(4.81f);
        pathBuilder.lineToRelative(1.04f, -3.0f);
        pathBuilder.horizontalLineTo(15.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.horizontalLineTo(5.5f);
        pathBuilder.curveTo(4.84f, 5.0f, 4.29f, 5.42f, 4.08f, 6.01f);
        pathBuilder.lineTo(2.0f, 12.0f);
        pathBuilder.verticalLineToRelative(8.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.horizontalLineToRelative(12.0f);
        pathBuilder.verticalLineToRelative(1.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(-8.0f);
        pathBuilder.horizontalLineToRelative(-3.0f);
        pathBuilder.curveTo(15.9f, 12.0f, 15.0f, 11.1f, 15.0f, 10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.5f, 16.0f);
        pathBuilder.curveTo(5.67f, 16.0f, 5.0f, 15.33f, 5.0f, 14.5f);
        pathBuilder.reflectiveCurveTo(5.67f, 13.0f, 6.5f, 13.0f);
        pathBuilder.reflectiveCurveTo(8.0f, 13.67f, 8.0f, 14.5f);
        pathBuilder.reflectiveCurveTo(7.33f, 16.0f, 6.5f, 16.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.5f, 16.0f);
        pathBuilder.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilder.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder.reflectiveCurveTo(16.33f, 16.0f, 15.5f, 16.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(22.0f, 3.85f);
        pathBuilder2.lineTo(19.15f, 1.0f);
        pathBuilder2.horizontalLineToRelative(-0.5f);
        pathBuilder2.verticalLineToRelative(3.79f);
        pathBuilder2.lineToRelative(-2.3f, -2.29f);
        pathBuilder2.lineToRelative(-0.7f, 0.7f);
        pathBuilder2.lineTo(18.44f, 6.0f);
        pathBuilder2.lineToRelative(-2.79f, 2.79f);
        pathBuilder2.lineToRelative(0.7f, 0.71f);
        pathBuilder2.lineToRelative(2.3f, -2.3f);
        pathBuilder2.verticalLineTo(11.0f);
        pathBuilder2.horizontalLineToRelative(0.5f);
        pathBuilder2.lineTo(22.0f, 8.14f);
        pathBuilder2.lineTo(19.85f, 6.0f);
        pathBuilder2.lineTo(22.0f, 3.85f);
        pathBuilder2.close();
        pathBuilder2.moveTo(19.65f, 2.91f);
        pathBuilder2.lineToRelative(0.94f, 0.94f);
        pathBuilder2.lineToRelative(-0.94f, 0.94f);
        pathBuilder2.verticalLineTo(2.91f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.59f, 8.14f);
        pathBuilder2.lineToRelative(-0.94f, 0.94f);
        pathBuilder2.verticalLineTo(7.2f);
        pathBuilder2.lineTo(20.59f, 8.14f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _bluetoothDrive = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
