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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_troubleshoot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Troubleshoot", "Landroidx/compose/material/icons/Icons$Filled;", "getTroubleshoot", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TroubleshootKt {
    private static ImageVector _troubleshoot;

    public static final ImageVector getTroubleshoot(Icons.Filled filled) {
        ImageVector imageVector = _troubleshoot;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Troubleshoot", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(22.0f, 20.59f);
        pathBuilder.lineToRelative(-4.69f, -4.69f);
        pathBuilder.curveTo(18.37f, 14.55f, 19.0f, 12.85f, 19.0f, 11.0f);
        pathBuilder.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilder.curveToRelative(-4.08f, 0.0f, -7.44f, 3.05f, -7.93f, 7.0f);
        pathBuilder.horizontalLineToRelative(2.02f);
        pathBuilder.curveTo(5.57f, 7.17f, 8.03f, 5.0f, 11.0f, 5.0f);
        pathBuilder.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilder.curveToRelative(-2.42f, 0.0f, -4.5f, -1.44f, -5.45f, -3.5f);
        pathBuilder.horizontalLineTo(3.4f);
        pathBuilder.curveTo(4.45f, 16.69f, 7.46f, 19.0f, 11.0f, 19.0f);
        pathBuilder.curveToRelative(1.85f, 0.0f, 3.55f, -0.63f, 4.9f, -1.69f);
        pathBuilder.lineTo(20.59f, 22.0f);
        pathBuilder.lineTo(22.0f, 20.59f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(8.43f, 9.69f);
        pathBuilder2.lineToRelative(1.22f, 5.31f);
        pathBuilder2.lineToRelative(1.64f, 0.0f);
        pathBuilder2.lineToRelative(1.26f, -3.78f);
        pathBuilder2.lineToRelative(0.95f, 2.28f);
        pathBuilder2.lineToRelative(2.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, -1.5f);
        pathBuilder2.lineToRelative(-1.0f, 0.0f);
        pathBuilder2.lineToRelative(-1.25f, -3.0f);
        pathBuilder2.lineToRelative(-1.54f, 0.0f);
        pathBuilder2.lineToRelative(-1.12f, 3.37f);
        pathBuilder2.lineToRelative(-1.24f, -5.37f);
        pathBuilder2.lineToRelative(-1.65f, 0.0f);
        pathBuilder2.lineToRelative(-1.25f, 4.0f);
        pathBuilder2.lineToRelative(-5.45f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, 1.5f);
        pathBuilder2.lineToRelative(6.55f, 0.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _troubleshoot = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
