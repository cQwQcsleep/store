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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_controlCamera", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ControlCamera", "Landroidx/compose/material/icons/Icons$Sharp;", "getControlCamera", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ControlCameraKt {
    private static ImageVector _controlCamera;

    public static final ImageVector getControlCamera(Icons.Sharp sharp) {
        ImageVector imageVector = _controlCamera;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ControlCamera", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.54f, 8.46f);
        pathBuilder.lineTo(2.0f, 12.0f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.76f, -1.77f);
        pathBuilder.lineTo(5.54f, 12.0f);
        pathBuilder.lineToRelative(1.76f, -1.77f);
        pathBuilder.close();
        pathBuilder.moveTo(18.46f, 8.46f);
        pathBuilder.lineToRelative(-1.76f, 1.77f);
        pathBuilder.lineTo(18.46f, 12.0f);
        pathBuilder.lineToRelative(-1.76f, 1.77f);
        pathBuilder.lineToRelative(1.76f, 1.77f);
        pathBuilder.lineTo(22.0f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 18.46f);
        pathBuilder.lineToRelative(-1.77f, -1.76f);
        pathBuilder.lineToRelative(-1.77f, 1.76f);
        pathBuilder.lineTo(12.0f, 22.0f);
        pathBuilder.lineToRelative(3.54f, -3.54f);
        pathBuilder.lineToRelative(-1.77f, -1.76f);
        pathBuilder.close();
        pathBuilder.moveTo(8.46f, 5.54f);
        pathBuilder.lineToRelative(1.77f, 1.76f);
        pathBuilder.lineTo(12.0f, 5.54f);
        pathBuilder.lineToRelative(1.77f, 1.76f);
        pathBuilder.lineToRelative(1.77f, -1.76f);
        pathBuilder.lineTo(12.0f, 2.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 12.0f);
        pathBuilder2.moveToRelative(-3.0f, 0.0f);
        pathBuilder2.arcToRelative(3.0f, 3.0f, 0.0f, true, true, 6.0f, 0.0f);
        pathBuilder2.arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _controlCamera = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
