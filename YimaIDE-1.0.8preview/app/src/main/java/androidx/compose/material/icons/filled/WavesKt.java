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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_waves", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Waves", "Landroidx/compose/material/icons/Icons$Filled;", "getWaves", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WavesKt {
    private static ImageVector _waves;

    public static final ImageVector getWaves(Icons.Filled filled) {
        ImageVector imageVector = _waves;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Waves", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.0f, 16.99f);
        pathBuilder.curveToRelative(-1.35f, 0.0f, -2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.33f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilder.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.33f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilder.verticalLineToRelative(1.95f);
        pathBuilder.curveToRelative(1.35f, 0.0f, 2.2f, -0.42f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.33f, 1.17f, -0.6f, 2.05f, -0.6f);
        pathBuilder.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilder.reflectiveCurveToRelative(2.2f, -0.42f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.33f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilder.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilder.verticalLineToRelative(-1.95f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilder.close();
        pathBuilder.moveTo(17.0f, 12.54f);
        pathBuilder.curveToRelative(-1.35f, 0.0f, -2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.32f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilder.reflectiveCurveToRelative(-2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.32f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilder.verticalLineToRelative(1.95f);
        pathBuilder.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.35f, 1.15f, -0.6f, 2.05f, -0.6f);
        pathBuilder.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilder.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.35f, 1.15f, -0.6f, 2.05f, -0.6f);
        pathBuilder.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilder.verticalLineToRelative(-1.95f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilder.close();
        pathBuilder.moveTo(19.95f, 4.46f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.58f, -0.8f, -2.95f, -0.8f);
        pathBuilder.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.32f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.37f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilder.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.33f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilder.verticalLineToRelative(1.93f);
        pathBuilder.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.33f, 1.17f, -0.6f, 2.05f, -0.6f);
        pathBuilder.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilder.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilder.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilder.lineTo(22.0f, 5.04f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.58f);
        pathBuilder.close();
        pathBuilder.moveTo(17.0f, 8.09f);
        pathBuilder.curveToRelative(-1.35f, 0.0f, -2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.35f, -1.15f, 0.6f, -2.05f, 0.6f);
        pathBuilder.reflectiveCurveToRelative(-1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilder.reflectiveCurveToRelative(-2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilder.curveToRelative(-0.65f, 0.35f, -1.15f, 0.6f, -2.05f, 0.6f);
        pathBuilder.verticalLineToRelative(1.95f);
        pathBuilder.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilder.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilder.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilder.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilder.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilder.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilder.lineTo(22.0f, 9.49f);
        pathBuilder.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilder.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _waves = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
